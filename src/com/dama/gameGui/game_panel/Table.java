package com.dama.gameGui.game_panel;

import com.dama.engine.board.Board;
import com.dama.engine.board.Tile;
import com.dama.engine.dependencies.Alliance;
import com.dama.engine.pieces.Piece;
import com.dama.gameGui.GameInfo;
import com.dama.engine.sounds.SoundManager;
import com.dama.gameGui.TableManager;

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
    private final BoardPanel boardPanel;
    private final PlayerPanel bottomPlayerPanel;
    private final PlayerPanel topPlayerPanel;
    
    // Board, Board List, Board Setting
    private final List<Board> gamePlay;
    private int currentGameStateIndex;    
    private Board gameBoard;
    private boolean reversed;

    // Players Selection -> Tiles, Piece  -> same package only
    private Tile sourceTile;
    private Tile destinationTile;
    private Piece selectedPiece;
    private Status status;

    // Constructor: Define a system of graphical game interface
    public Table() {
        super(new GridBagLayout());
        this.gamePlay = new ArrayList<>();
        this.currentGameStateIndex = 0;
        this.gameBoard = Board.createStandardBoard();
        this.gamePlay.add(gameBoard);
        this.boardPanel = new BoardPanel(this);
        
        if (!GameInfo.isGameStart()) boardPanel.disableBoard();
        else SoundManager.Sounds.GAME_START_SOUND.play();
            
        if (GameInfo.getDirection() == GameInfo.GameSwitch.NORMAL) {
            this.boardPanel.setDirection(BoardPanel.Direction.NORMAL);
            this.bottomPlayerPanel = new PlayerPanel(this, gameBoard.getWhitePlayer(), GameInfo.getBotttomPlayerName());
            this.topPlayerPanel = new PlayerPanel(this, gameBoard.getBlackPlayer(), GameInfo.getTopPlayerName());
        } else { 
            if (GameInfo.canChangeTurn())
                this.boardPanel.setDirection(BoardPanel.Direction.NORMAL);
            else
                this.boardPanel.setDirection(BoardPanel.Direction.FLIPPED);
            this.bottomPlayerPanel = new PlayerPanel(this, gameBoard.getBlackPlayer(), GameInfo.getBotttomPlayerName());
            this.topPlayerPanel = new PlayerPanel(this, gameBoard.getWhitePlayer(), GameInfo.getTopPlayerName());
        }
        
        this.bottomPlayerPanel.startTimer(GameInfo.getGameDuration());
        this.topPlayerPanel.startTimer(GameInfo.getGameDuration());
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
        if (GameInfo.canChangeTurn() && GameInfo.getDirection() == GameInfo.GameSwitch.FLIPPED) {
            this.reversed = true;
            addComponents(topPlayerPanel, bottomPlayerPanel);
        } else {
            this.reversed = false;
            addComponents(bottomPlayerPanel, topPlayerPanel);
        }
    }
    
    // Configuration: Display the components of the Table
    public void addComponents(final PlayerPanel bottomPlayer, final PlayerPanel topPlayer) {
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
    
    //-------------Helper_Methods---------------//
    
    // Table Current Player Selection:
    public Tile getSourceTile() {
        return this.sourceTile;
    }
    
    public void setSourceTile(final Tile tile) {
        this.sourceTile = tile;
    }
    
    public Tile getDestinationTile() {
        return this.destinationTile;
    }
    
    public void setDestinationTile(final Tile tile) {
        this.destinationTile = tile;
    }
    
    public Piece getSelectedPiece() {
        return this.selectedPiece;
    }
    
    public void setSelectedPiece(final Piece piece) {
        this.selectedPiece = piece;
    }
    
    // Table Current Status:
    public Status getStatus() {
        return this.status;
    }
    
    public void setStatus(final Status status) {
        this.status = status;
    } 
    
    public boolean isReversed() {
        return this.reversed;
    }
    
    public void reverse(boolean bool) {
        this.reversed = bool;
    }
    
    public int getCurrentGameStateIndex() {
        return this.currentGameStateIndex;
    }
    
    public void setCurrentGameStateIndex(int index) {
        this.currentGameStateIndex = index;
    }
    
    public boolean isOnGame() {
        return this.currentGameStateIndex == gamePlay.size() - 1;
    }
    
    public boolean gameEnded() {
        return this.status != null;
    }
    
    // Table Variables Getters:
    public List<Board> getGamePlay() {
        return Collections.unmodifiableList(this.gamePlay);
    }
    
    public void addBoardToGamePlay(final Board board) {
        this.gamePlay.add(board);
    }
    
    public void removeBoardToGamePlay(final int index) {
        this.gamePlay.remove(index);
    }
    
    public Board getGameBoard() {
        return this.gameBoard;
    }
    
    public void setGameBoard(final Board board) {
        this.gameBoard = board;
    }

    public BoardPanel getBoardPanel() {
        return this.boardPanel;
    }
    
    public PlayerPanel getTopPlayerPanel() {
        return this.topPlayerPanel;
    }
    
    public PlayerPanel getBottomPlayerPanel() {
        return this.bottomPlayerPanel;
    }
    
    public PlayerPanel getCurrentPlayerPanel() {
        Alliance alliance = gameBoard.getCurrentPlayer().getAlliance();
        if (topPlayerPanel.getPlayer().getAlliance() == alliance) {
            return topPlayerPanel;
        }
        return bottomPlayerPanel;
    }
    
    public PlayerPanel getOpponentPlayerPanel() {
        Alliance alliance = gameBoard.getCurrentPlayer().getAlliance();
        if (topPlayerPanel.getPlayer().getAlliance() != alliance) {
            return topPlayerPanel;
        }
        return bottomPlayerPanel;
    }
    
    // Enum State: The Status of the game
    public static enum Status {
        STALEMATE {
            @Override
            public PlayerPanel getWinner(final PlayerPanel topPlayerPanel, final PlayerPanel bottomPlayerPanel) {
                return null;
            }
        },
        BLACK_PLAYER_WIN {
            @Override
            public PlayerPanel getWinner(final PlayerPanel topPlayerPanel, final PlayerPanel bottomPlayerPanel) {
                return topPlayerPanel.getPlayer().getAlliance().isBlack() ?
                        topPlayerPanel : bottomPlayerPanel;
            }
        },
        WHITE_PLAYER_WIN {
            @Override
            public PlayerPanel getWinner(final PlayerPanel topPlayerPanel, final PlayerPanel bottomPlayerPanel) {
                return topPlayerPanel.getPlayer().getAlliance().isWhite() ?
                        topPlayerPanel : bottomPlayerPanel;
            }
        };
        
        public abstract PlayerPanel getWinner(final PlayerPanel topPlayerPanel, final PlayerPanel bottomPlayerPanel);
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
