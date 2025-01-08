package com.dama.gui;

import com.dama.engine.board.Board;
import com.dama.engine.board.Tile;
import com.dama.engine.pieces.Piece;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.BorderFactory;

import javax.swing.JPanel;

public final class Table extends JPanel {

    // Static Variables
    private static final Dimension PREFERRED_SIZE = new Dimension(500, 600);
    private static final Color BACKGROUND_COLOR = new Color(48, 46, 43);

    // Define Variables
    private final List<Board> gamePlay;
    private final BoardPanel boardPanel;
    private final PlayerPanel bottomPlayerPanel;
    private final PlayerPanel topPlayerPanel;
    private boolean reversed;
    private Board gameBoard;

    // Players Selection -> Tiles, Piece  -> same package only
    Tile sourceTile;
    Tile destinationTile;
    Piece selectedPiece;
    Status status;

    // Constructor: Define a system of graphical game interface
    public Table() {
        super(new GridBagLayout());
        this.gamePlay = new ArrayList<>();
        this.gameBoard = Board.createStandardBoard();
        this.boardPanel = new BoardPanel(this);
        this.gamePlay.add(gameBoard);
        this.reversed = false;
        
        if (GameInfo.GAME_DURATION == GameInfo.GameDuration.NULL)
            boardPanel.disableBoard();
        
        if (GameInfo.BOARD_DIRECTION == GameInfo.GameSwitch.NORMAL) {
            this.boardPanel.setDirection(BoardPanel.Direction.NORMAL);
            this.bottomPlayerPanel = new PlayerPanel(this, gameBoard.getWhitePlayer(), GameInfo.BOTTOM_PLAYER_NAME);
            this.topPlayerPanel = new PlayerPanel(this, gameBoard.getBlackPlayer(), GameInfo.TOP_PLAYER_NAME);
        } else {
            this.boardPanel.setDirection(BoardPanel.Direction.FLIPPED);
            this.bottomPlayerPanel = new PlayerPanel(this, gameBoard.getBlackPlayer(), GameInfo.BOTTOM_PLAYER_NAME);
            this.topPlayerPanel = new PlayerPanel(this, gameBoard.getWhitePlayer(), GameInfo.TOP_PLAYER_NAME);
        }

        this.bottomPlayerPanel.startTimer(GameInfo.GAME_DURATION);
        this.topPlayerPanel.startTimer(GameInfo.GAME_DURATION);
        initComponents();
    }

    // Configuration: Set the TablePanel GUI Display
    private void initComponents() {
        setBackground(BACKGROUND_COLOR);
        setForeground(Color.WHITE);
        setOpaque(true);
        setFocusable(false);
        setVisible(true);
        setPreferredSize(PREFERRED_SIZE);
        setMinimumSize(getPreferredSize());
        setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));
        setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        setName("Table");
        setRequestFocusEnabled(false);
        addComponents(bottomPlayerPanel, topPlayerPanel);
    }
    
    // Configuration: Display the components of the Table
    private void addComponents(final PlayerPanel bottomPlayer, final PlayerPanel topPlayer) {
        removeAll();
        GridBagConstraints gridBagConstraints;
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1;
        gridBagConstraints.weighty = 0.8;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        add(boardPanel, gridBagConstraints);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.anchor = GridBagConstraints.EAST;
        add(topPlayer, gridBagConstraints);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weightx = 1;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.anchor = GridBagConstraints.NORTH;
        add(bottomPlayer, gridBagConstraints);
    }
    
    /**
     * Get the game engine
     * USED: to retrieve this to the control system [BoardPanel]  -> same package only
     * @return Board
     */
    Board getGameBoard() {
        return this.gameBoard;
    }
    
    /**
     * Get the game history
     * USED: to retrieve this to the control system [BoardPanel]  -> same package only
     * @return List of Board
     */
    List<Board> getGamePlay() {
        return Collections.unmodifiableList(this.gamePlay);
    }
    
    /**
     * Get the game GUI
     * USED: to retrieve this to the control system [BoardPanel]  -> same package only
     * @return Board
     */
    BoardPanel getBoardPanel() {
        return this.boardPanel;
    }
    
    /**
     * Get the game GUI top player
     * USED: for the system(TilePanel) only -> same package only
     * @return PlayerPanel
     */
    PlayerPanel getTopPlayerPanel() {
        return this.topPlayerPanel;
    }
    
    /**
     * Get the game GUI bottom player
     * USED: for the system(TilePanel) only -> same package only
     * @return PlayerPanel
     */
    PlayerPanel getBottomPlayerPanel() {
        return this.bottomPlayerPanel;
    }
    
    /**
     * Set the game engine of the Game
     * USED: to set a new line of boards by Board.builder()  -> same package only
     * @param board Board
     */
    void setGameBoard(final Board board) {
        this.gamePlay.add(board);
        this.gameBoard = board;
    }
    
    /**
     * Reverse the player display from top to bottom, bottom to top
     * USED: for the system(TilePanel) only -> same package only
     */
    void reversePlayer() {
        reversed = !reversed;
        if (reversed)
            addComponents(this.topPlayerPanel, this.bottomPlayerPanel);
        else
            addComponents(this.bottomPlayerPanel, this.topPlayerPanel);
        revalidate();
        repaint();
    }
    
    /**
     * Stop Timer function when game ends
     * USED: for the system(TilePanel) only -> same package only
     */
    void stopPlayerTimer() {
        this.topPlayerPanel.stopTimer();
        this.bottomPlayerPanel.stopTimer();
    }
    
    // Enum State: The Status of the game
    public static enum Status {
        STALEMATE {
            @Override
            PlayerPanel getWinner(final PlayerPanel topPlayerPanel, final PlayerPanel bottomPlayerPanel) {
                return null;
            }
        },
        BLACK_PLAYER_WIN {
            @Override
            PlayerPanel getWinner(final PlayerPanel topPlayerPanel, final PlayerPanel bottomPlayerPanel) {
                return topPlayerPanel.getPlayer().getAlliance().isBlack() ?
                        topPlayerPanel : bottomPlayerPanel;
            }
        },
        WHITE_PLAYER_WIN {
            @Override
            PlayerPanel getWinner(final PlayerPanel topPlayerPanel, final PlayerPanel bottomPlayerPanel) {
                return topPlayerPanel.getPlayer().getAlliance().isWhite() ?
                        topPlayerPanel : bottomPlayerPanel;
            }
        };
        
        abstract PlayerPanel getWinner(final PlayerPanel topPlayerPanel, final PlayerPanel bottomPlayerPanel);
    }
    
    // Set the Proper Size for the Table Panel
    @Override
    public void setBounds(int x, int y, int width, int height) {
        int size = Math.min(width, height);
        int totalWidth = (int) (size * 0.8);
        int offSetX = x + ((width + ((int) (size * 0.2))) - size) / 2;
        int offSetY = y + (height - size) / 2;
        super.setBounds(offSetX, offSetY, totalWidth, size);
    }
}
