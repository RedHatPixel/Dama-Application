package com.dama.gui._controls;

import app.panels.CardHandlers.CardPanelRegistry;
import com.dama.gui.controlPanel.PlayerSetting;
import com.dama.engine.board.Board;
import com.dama.engine.sounds.SoundManager;
import com.dama.engine.records.GameData;
import com.dama.engine.records.GameDataManager;
import com.dama.engine.records.GamePlay;
import com.dama.gui.gamePanel.TablePanel;
import com.dama.gui._configurations.dependencies.Status;
import com.dama.gui.gamePanel.PlayerPanel;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

public final class TableManager {
    
    // Constructor: Prevent Instantiation
    private TableManager() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated.");
    }
   
    public static void reversePlayerPanel(final TablePanel table) {
        table.setOrientation(table.getOrientation().getOpposite());
        if (table.getOrientation().isNormal())
            table.addComponents(table.getOpponentPanel(), table.getPlayerPanel());
        else
            table.addComponents(table.getPlayerPanel(), table.getOpponentPanel());
        table.revalidate();
        table.repaint();
    }
    
    public static void stopPlayerTimer(final TablePanel table) {
        table.getPlayerPanel().getPlayerTimer().stopTimer();
        table.getOpponentPanel().getPlayerTimer().stopTimer();
    }
    
    public static void setPlayersTimerMoveInterval(final TablePanel table) {
        table.getPlayerPanel().getPlayerTimer().setNewTimeInterval();
        table.getOpponentPanel().getPlayerTimer().setNewTimeInterval();
    }
    
    public static void setPlayersScore(final TablePanel table, final Status status) {
        final PlayerPanel winner = status.getWinner(
                table.getOpponentPanel(), table.getPlayerPanel());
        
        if (winner != null) {
            if (winner.getPlayerInfo().getPlayer().getAlliance().equals(
                table.getPlayerPanel().getPlayerInfo().getPlayer().getAlliance()
            )) {
                table.getPlayerPanel().getPlayerInfo().addScore();
            } else {
                table.getOpponentPanel().getPlayerInfo().addScore();
            }
        }
    } 
    
    public static void setWinner(final TablePanel table, final boolean opponent) {
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

            setPlayersScore(table, table.getStatus());
            stopPlayerTimer(table);
            table.getBoardPanel().disableBoard();
            table.getBoardPanel().getDragGlassPane().showGameEnd(table.getStatus(), table);
            SoundManager.Sounds.GAME_END_SOUND.play();
            if (CardPanelRegistry.isInstanced(PlayerSetting.class)) {
                CardPanelRegistry.getInstance(PlayerSetting.class).setScoreInformation();
            }
            
            final GameData data = new GameData(
                    table.getGameInfo().getDuration(),
                    new PlayerPanel[] {
                        table.getOpponentPanel(), table.getPlayerPanel()
                    },
                    table.getStatus(),
                    LocalDate.now().format(DateTimeFormatter.ofPattern("MM / dd / yyyy"))
            );
            GameDataManager.addGameResult(data);
        }
    }
    
    public static void setStalemate(final TablePanel table) {
        if (!table.gameEnded()) {
            table.setStatus(Status.STALEMATE);
            stopPlayerTimer(table);
            table.getBoardPanel().disableBoard();
            table.getBoardPanel().getDragGlassPane().showGameEnd(table.getStatus(), table);
            SoundManager.Sounds.GAME_END_SOUND.play();
            
            final GameData data = new GameData(
                table.getGameInfo().getDuration(),
                new PlayerPanel[] {
                    table.getOpponentPanel(), table.getPlayerPanel()
                },
                table.getStatus(),
                LocalDate.now().format(DateTimeFormatter.ofPattern("MM / dd / yyyy"))
            );
            GameDataManager.addGameResult(data);
        }
    }
    
    public static void undoMove(final TablePanel table) {
        final GamePlay gamePlay = table.getGamePlay();
        if ((!table.gameEnded() && table.isOnGame()) &&
            table.getOpponentPlayerPanel().getPlayerInfo().getAvailableUndoMove() > 0) {
            
            if (gamePlay.goBackward()) {
                table.getCurrentPlayerPanel().getPlayerTimer().undoTimer();
                table.getOpponentPlayerPanel().getPlayerTimer().undoTimer();
                table.getOpponentPlayerPanel().getPlayerInfo().subUndoMove();
                updateNewBoard(table, gamePlay, gamePlay.getCurrentBoard());
                table.getBoardPanel().drawBoard(table.getGameBoard());
                table.getBoardPanel().highlightLatestMove(table.getGameBoard());
            }
        }
    }
    
    public static void navigateBack(final TablePanel table) {
        final GamePlay gamePlay = table.getGamePlay();
        if (gamePlay.goBackward()) {
            table.getBoardPanel().disableBoard();
            table.setGameBoard(gamePlay.getCurrentBoard());
            table.getBoardPanel().drawBoard(table.getGameBoard());
            table.getBoardPanel().highlightLatestMove(table.getGameBoard());
            table.getBoardPanel().getDragGlassPane().removeAnimation();
            if (table.gameEnded()) {
                table.getPlayerPanel().getPlayerTimer().moveTimeBackward();
                table.getOpponentPanel().getPlayerTimer().moveTimeBackward();
            }
        }
    }

    public static void navigateForward(final TablePanel table) {
        final GamePlay gamePlay = table.getGamePlay();
        if (gamePlay.goForward()) {
            table.getBoardPanel().disableBoard();
            table.setGameBoard(gamePlay.getCurrentBoard());
            table.getBoardPanel().drawBoard(table.getGameBoard());
            table.getBoardPanel().highlightLatestMove(table.getGameBoard());
            table.getBoardPanel().getDragGlassPane().removeAnimation();
            if (table.isOnGame() && !table.gameEnded()) {
                table.getBoardPanel().openBoard();
                table.getBoardPanel().drawMovable(table.getGameBoard());
            }
            else if (table.gameEnded()) {
                table.getPlayerPanel().moveTimerForward();
                table.getOpponentPanel().moveTimerForward();
            }
        }
    }
    
    public static void updateNewBoard(final TablePanel table, final GamePlay gamePlay, final Board board) {
        gamePlay.updateBoard();
        table.setGameBoard(board);
        
        if (CardPanelRegistry.isInstanced(PlayerSetting.class)) {
            CardPanelRegistry.getInstance(PlayerSetting.class).updateGameRecords();
        }
    }

    public static void setNewBoard(final TablePanel table, final GamePlay gamePlay, final Board board) {
        gamePlay.addBoard(board);
        table.setGameBoard(board);
        
        if (CardPanelRegistry.isInstanced(PlayerSetting.class)) {
            CardPanelRegistry.getInstance(PlayerSetting.class).updateGameRecords();
        }
    }
}
