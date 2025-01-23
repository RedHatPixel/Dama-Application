package com.dama.engine.records;

import com.dama.engine.board.Board;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GamePlay {

    private final List<Board> boardHistory;
    private int currentIndex;

    // Constructor: Initializes the board history and sets the current index.
    public GamePlay(final Board initialBoard) {
        this.boardHistory = new ArrayList<>();
        this.boardHistory.add(initialBoard);
        this.currentIndex = 0;
    }

    // Add new board to the list and possibly remove out of bounds latest boards
    public void addBoard(final Board newBoard) {
        while (boardHistory.size() > currentIndex + 1) {
            boardHistory.remove(boardHistory.size() - 1);
        }
        // Add the new board to the history
        boardHistory.add(newBoard);
        currentIndex = boardHistory.size() - 1;
    }
    
    // Update the board to its sublist of the current index
    public void updateBoard() {
        while (boardHistory.size() > currentIndex + 1) {
            boardHistory.remove(boardHistory.size() - 1);
        }
        currentIndex = boardHistory.size() - 1;
    }

    // Navigates backward in the board history.
    public boolean goBackward() {
        if (currentIndex > 0) {
            currentIndex--;
            return true;
        }
        return false;
    }

    // Navigates forward in the board history.
    public boolean goForward() {
        if (currentIndex < boardHistory.size() - 1) {
            currentIndex++;
            return true;
        }
        return false;
    }
    
    // Check if the index is on the latest board
    public boolean isOnLatestIndex() {
        return currentIndex == boardHistory.size() - 1;
    }

    // Gets the current board.
    public Board getCurrentBoard() {
        return boardHistory.get(currentIndex);
    }

    // Gets the index of the current board.
    public int getCurrentIndex() {
        return currentIndex;
    }

    // Gets the total number of boards in the history.
    public int getBoardHistorySize() {
        return boardHistory.size();
    }
    
    public List<Board> getBoardHistory() {
        return Collections.unmodifiableList(boardHistory);
    }

    // Resets the board history to a single initial board.
    public void reset(final Board initialBoard) {
        boardHistory.clear();
        boardHistory.add(initialBoard);
        currentIndex = 0;
    }
        
    /**
     * Check if there is a capture move within 50 moves
     * @param gamePlay List of Board
     * @return List of Board
     */
    public static List<Board> calculate50LatestMove(final List<Board> gamePlay) {
        final List<Board> boards = new ArrayList<>();
        
        if (gamePlay.size() >= 50) {
            final int startAt = gamePlay.size() - 1;
            final int endsAt = gamePlay.size() - 50;
            for (int i = startAt; i >= endsAt; i--) {
                if (gamePlay.get(i).getLatestMove().getType().isNaN())
                    continue;
                if (gamePlay.get(i).getLatestMove().getType().canAttack())
                    boards.add(gamePlay.get(i));
            }
        }
        else boards.addAll(gamePlay);
        return Collections.unmodifiableList(boards);
    }
    
    /**
     * Check the player latest move among the captures
     * @param gamePlay  List of Board
     * @return Board
     */
    public static int getPlayerLatestMove(final List<Board> gamePlay) {
        int index = Math.max(0, gamePlay.size() - 1);
        for (int i = gamePlay.size() -1; i >= 0; i--) {
            if (gamePlay.get(i).getLatestMove().getType().canAttack()) {
                index = i;
                continue;
            }
            return index;
        }
        return index;
    }
}

