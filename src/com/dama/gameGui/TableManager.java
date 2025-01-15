package com.dama.gameGui;

import com.dama.engine.board.Board;
import com.dama.gameGui.game_panel.Table;
import com.dama.gameGui.game_panel.Table.Status;
import com.dama.engine.sounds.SoundManager;
import appGui.panels.CardHandlers.CardPanelRegistry;
import appGui.panels.controlPanel.PlayerSetting;
import com.dama.engine.history.GameData;
import com.dama.engine.history.GameDataManager;
import com.dama.gameGui.game_panel.PlayerPanel;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public final class TableManager {
    
    // Constructor: Prevent Instantiation
    private TableManager() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated.");
    }
   
    public static void reversePlayerPanel(final Table table) {
        table.reverse(!table.isReversed());
        if (table.isReversed()) {
            table.addComponents(table.getTopPlayerPanel(), table.getBottomPlayerPanel());
        }
        else {
            table.addComponents(table.getBottomPlayerPanel(), table.getTopPlayerPanel());
        }
        table.revalidate();
        table.repaint();
    }
    
    public static void stopPlayerTimer(final Table table) {
        table.getTopPlayerPanel().stopTimer();
        table.getBottomPlayerPanel().stopTimer();
    }
    
    public static void setPlayersTimerMoveInterval(final Table table) {
        table.getOpponentPlayerPanel().setNewTimeInterval();
        table.getCurrentPlayerPanel().setNewTimeInterval();
    }
    
    public static void setWinner(final Table table, final boolean opponent) {
        if (!table.gameEnded()) {
            if (opponent) {
                if (table.getGameBoard().getCurrentPlayer().getOpponent().getAlliance().isWhite()) {
                    table.setStatus(Status.WHITE_PLAYER_WIN);
                } else {
                    table.setStatus(Status.BLACK_PLAYER_WIN);
                }
            } else {
                if (table.getGameBoard().getCurrentPlayer().getAlliance().isWhite()) {
                    table.setStatus(Status.WHITE_PLAYER_WIN);
                } else {
                    table.setStatus(Status.BLACK_PLAYER_WIN);
                }
            }

            stopPlayerTimer(table);
            table.getBoardPanel().disableBoard();
            table.getBoardPanel().getDragGlassPane().showGameEnd(table.getStatus(), table);
            SoundManager.Sounds.GAME_END_SOUND.play();
            if (CardPanelRegistry.isInstanced(PlayerSetting.class)) {
                CardPanelRegistry.getInstance(PlayerSetting.class).getGameInformation();
            }
            final GameData data = new GameData(
                    table,
                    GameInfo.getGameDuration(),
                    new PlayerPanel[] {
                        table.getTopPlayerPanel(), table.getBottomPlayerPanel()
                    },
                    new int[] {
                        GameInfo.getTopPlayerScore(), GameInfo.getBottomPlayerScore()
                    },
                    table.getStatus(),
                    LocalDate.now().format(DateTimeFormatter.ofPattern("MM/dd/yyyy"))
            );
            GameDataManager.addGameResult(data);
        }
    }
    
    public static void setStalemate(final Table table) {
        if (!table.gameEnded()) {
            table.setStatus(Status.STALEMATE);
            stopPlayerTimer(table);
            table.getBoardPanel().disableBoard();
            table.getBoardPanel().getDragGlassPane().showGameEnd(table.getStatus(), table);
            SoundManager.Sounds.GAME_END_SOUND.play();
            if (CardPanelRegistry.isInstanced(PlayerSetting.class)) {
                CardPanelRegistry.getInstance(PlayerSetting.class).getGameInformation();
            }
            final GameData data = new GameData(
                    table,
                    GameInfo.getGameDuration(),
                    new PlayerPanel[] {
                        table.getTopPlayerPanel(), table.getBottomPlayerPanel()
                    },
                    new int[] {
                        GameInfo.getTopPlayerScore(), GameInfo.getBottomPlayerScore()
                    },
                    table.getStatus(),
                    LocalDate.now().format(DateTimeFormatter.ofPattern("MM/dd/yyyy"))
            );
            GameDataManager.addGameResult(data);
        }
    }
    
    public static void undoMove(final Table table) {
        int index = Board.getPlayerLatestMove(table.getGamePlay());
        if (index > 0 && !table.gameEnded() && table.isOnGame() &&
            table.getOpponentPlayerPanel().getAvailableBackMove() > 0) {
            index--;
            table.setCurrentGameStateIndex(index);
            table.getCurrentPlayerPanel().setLatestTime();
            table.getOpponentPlayerPanel().setLatestTime();
            table.getOpponentPlayerPanel().subsAvailableBackMove();
            updateNewBoard(table, table.getGamePlay().get(index));
            table.getBoardPanel().drawBoard(table.getGameBoard());
            table.getBoardPanel().highlightLatestMove(table.getGameBoard());
        }
    }
    
    public static void navigateBack(final Table table) {
        int index = table.getCurrentGameStateIndex();
        if (index > 0) {
            index--;
            table.setCurrentGameStateIndex(index);
            table.getBoardPanel().disableBoard();
            table.setGameBoard(table.getGamePlay().get(index));
            table.getBoardPanel().drawBoard(table.getGameBoard());
            table.getBoardPanel().highlightLatestMove(table.getGameBoard());
            table.getBoardPanel().getDragGlassPane().removeAnimation();
            if (table.gameEnded()) {
                table.getCurrentPlayerPanel().moveBackTime();
                table.getOpponentPlayerPanel().moveBackTime();
            }
        }
    }

    public static void navigateForward(final Table table) {
        int index = table.getCurrentGameStateIndex();
        if (index < table.getGamePlay().size() - 1) {
            index++; 
            table.setCurrentGameStateIndex(index);
            table.setGameBoard(table.getGamePlay().get(index));
            table.getBoardPanel().drawBoard(table.getGameBoard());
            table.getBoardPanel().highlightLatestMove(table.getGameBoard());
            table.getBoardPanel().getDragGlassPane().removeAnimation();
            table.getBoardPanel().disableBoard();
            if (table.isOnGame() && !table.gameEnded()) {
                table.getBoardPanel().openBoard();
                table.getBoardPanel().drawMovable(table.getGameBoard());
            }
            else if (table.gameEnded()) {
                table.getOpponentPlayerPanel().moveForwardTime();
                table.getCurrentPlayerPanel().moveForwardTime();
            }
        }
    }
    
    public static void updateNewBoard(final Table table, final Board board) {
        // Remove any future states if a new move is made
        while (table.getGamePlay().size() > table.getCurrentGameStateIndex() + 1) {
            table.removeBoardToGamePlay(table.getGamePlay().size() - 1);
        }
        
        table.setCurrentGameStateIndex(table.getGamePlay().size() - 1);
        table.setGameBoard(board);
        
        if (CardPanelRegistry.isInstanced(PlayerSetting.class)) {
            CardPanelRegistry.getInstance(PlayerSetting.class).updateGameRecords();
        }
    }

    public static void setNewBoard(final Table table, final Board board) {
        // Remove any future states if a new move is made
        while (table.getGamePlay().size() > table.getCurrentGameStateIndex() + 1) {
            table.removeBoardToGamePlay(table.getGamePlay().size() - 1);
        }
        
        table.addBoardToGamePlay(board);
        table.setCurrentGameStateIndex(table.getGamePlay().size() - 1);
        table.setGameBoard(board);
        
        if (CardPanelRegistry.isInstanced(PlayerSetting.class)) {
            CardPanelRegistry.getInstance(PlayerSetting.class).updateGameRecords();
        }
    }
}
