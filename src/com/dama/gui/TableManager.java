package com.dama.gui;

import com.dama.engine.board.Board;
import com.dama.gui.game_panel.Table;
import com.dama.gui.game_panel.Table.Status;
import com.dama.sound.SoundManager;

public final class TableManager {
    
    /**
     * Reverse the player display from top to bottom, bottom to top
     * USED: for the system(TilePanel) only -> same package only
     * @param table     Table
     */
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
    
    public static void setWinner(final Table table, final boolean opponent) {
        if (!table.gameEnded()) {
            if (opponent) {
                if (table.getGameBoard().getCurrentPlayer().getOpponent().getAlliance().isWhite()) {
                    table.setStatus(Status.WHITE_PLAYER_WIN);
                } else {
                    table.setStatus(Status.BLACK_PLAYER_WIN);
                }
            } else {
                if (table.getGameBoard().getCurrentPlayer().getOpponent().getAlliance().isWhite()) {
                    table.setStatus(Status.WHITE_PLAYER_WIN);
                } else {
                    table.setStatus(Status.BLACK_PLAYER_WIN);
                }
            }

            stopPlayerTimer(table);
            table.getBoardPanel().disableBoard();
            table.getBoardPanel().getDragGlassPane().showGameEnd(table.getStatus(), table);
            SoundManager.Sounds.GAME_END_SOUND.play();
        }
    }
    
    public static void setStalemate(final Table table) {
        if (!table.gameEnded()) {
            table.setStatus(Status.STALEMATE);
            stopPlayerTimer(table);
            table.getBoardPanel().disableBoard();
            table.getBoardPanel().getDragGlassPane().showGameEnd(table.getStatus(), table);
        }
    }
    
    public static void undoMove(final Table table) {
        int index = table.getCurrentGameStateIndex();
        if (index > 0 && !table.gameEnded() &&
            table.getCurrentPlayerPanel().getAvailableBackMove() > 0) {
            index--;
            table.setCurrentGameStateIndex(index);
            table.getCurrentPlayerPanel().subsAvailableBackMove();
            setNewBoard(table, table.getGamePlay().get(table.getCurrentGameStateIndex()));
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
    }
}
