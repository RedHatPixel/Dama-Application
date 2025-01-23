package com.dama.gui.gamePanel;

import com.dama.engine.records.GamePlay;
import com.dama.engine.sounds.SoundManager;
import com.dama.engine.board.Board;
import com.dama.engine.board.Tile;
import com.dama.engine.dependencies.Alliance;
import com.dama.engine.pieces.Piece;
import com.dama.gui._configurations.game.GameBuilder;
import com.dama.gui._configurations.game.GameInfo;
import com.dama.gui._configurations.game.GameSetting;
import com.dama.gui._configurations.dependencies.Orientation;
import com.dama.gui._configurations.dependencies.Status;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

public final class TablePanel extends JPanel {

    // Static Variables
    private static final Dimension PREFERRED_SIZE = new Dimension(500, 600);
    private static final Color BACKGROUND_COLOR = new Color(48, 46, 43);

    // Define Variables
    private final BoardPanel boardPanel;
    private final PlayerPanel playerPanel;
    private final PlayerPanel opponentPanel;
    
    // Board, Board List, Board Setting
    private final GameInfo gameInfo;
    private final GameSetting gameSetting;
    private Orientation orientation;
    private GamePlay gamePlay;
    private Board gameBoard;
    private boolean isPlaying;

    // Players Selection -> Tiles, Piece  -> same package only
    private Tile sourceTile;
    private Tile destinationTile;
    private Piece selectedPiece;
    private Status status;

    // Constructor: Define a system of graphical game interface
    public TablePanel(final GameBuilder builder) {
        super(new GridBagLayout());
        this.gameBoard = Board.createStandardBoard();
        this.gamePlay = new GamePlay(gameBoard);
        this.gameInfo = builder.getGameInfo();
        this.gameSetting = builder.getGameSetting();
        this.isPlaying = builder.isPlayable();
        this.boardPanel = new BoardPanel(this);
        
        if (gameInfo.getOrientation().isNormal()) {
            this.boardPanel.setDirection(BoardPanel.Direction.NORMAL);
            this.playerPanel = new PlayerPanel(
                this, gameBoard.getWhitePlayer(), 
                gameInfo.getPlayerName(), gameInfo.getPlayerScore());
            this.opponentPanel = new PlayerPanel(
                this, gameBoard.getBlackPlayer(), 
                gameInfo.getOpponentName(), gameInfo.getOpponentScore());
        } else { 
            if (gameSetting.canChangeTurn()) this.boardPanel.setDirection(BoardPanel.Direction.NORMAL);
            else this.boardPanel.setDirection(BoardPanel.Direction.FLIPPED);
            this.playerPanel = new PlayerPanel(
                this, gameBoard.getBlackPlayer(), 
                gameInfo.getPlayerName(), gameInfo.getPlayerScore());
            this.opponentPanel = new PlayerPanel(
                this, gameBoard.getWhitePlayer(), 
                gameInfo.getOpponentName(), gameInfo.getOpponentScore());
        }
        
        this.playerPanel.getPlayerTimer().startTimer(gameInfo.getDuration(), 2000);
        this.opponentPanel.getPlayerTimer().startTimer(gameInfo.getDuration(), 2000);
        initComponents();
        
        if (gameSetting.canChangeTurn() && !gameInfo.getOrientation().isNormal()) {
            orientation = Orientation.NORMAL;
            addComponents(opponentPanel, playerPanel);
        }
        else {
            orientation = Orientation.FLIPPED;
            addComponents(playerPanel, opponentPanel);
        }
        
        if (!isPlaying) boardPanel.disableBoard();
        else {
            SoundManager.Sounds.GAME_START_SOUND.play();
            boardPanel.getDragGlassPane().showGameStart((TablePanel) this);
        }
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
    }
    
    // Configuration: Display the components of the TablePanel
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
    
    // TablePanel Current Player Selection:
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
    
    // TablePanel Current Status:
    public Status getStatus() {
        return this.status;
    }
    
    public void setStatus(final Status status) {
        this.status = status;
    } 
    
    public boolean isOnGame() {
        return this.gamePlay.isOnLatestIndex();
    }
    
    public boolean gameEnded() {
        return this.status != null;
    }
    
    public Orientation getOrientation() {
        return this.orientation;
    }
    
    public void setOrientation(final Orientation orientation) {
        this.orientation = orientation;
    }
    
    public boolean isPlaying() {
        return this.isPlaying;
    }
    // TablePanel Variables Getters:
    public GameInfo getGameInfo() {
        return this.gameInfo;
    }
    
    public GameSetting getGameSetting() {
        return this.gameSetting;
    }
    
    public GamePlay getGamePlay() {
        return this.gamePlay;
    }
    
    public void setGamePlay(final GamePlay gamePlay) {
        this.gamePlay = gamePlay;
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
    
    public PlayerPanel getOpponentPanel() {
        return this.opponentPanel;
    }
    
    public PlayerPanel getPlayerPanel() {
        return this.playerPanel;
    }
    
    public PlayerPanel getCurrentPlayerPanel() {
        Alliance alliance = gameBoard.getCurrentPlayer().getAlliance();
        if (opponentPanel.getPlayerInfo().getPlayer().getAlliance() == alliance) {
            return opponentPanel;
        }
        return playerPanel;
    }
    
    public PlayerPanel getOpponentPlayerPanel() {
        Alliance alliance = gameBoard.getCurrentPlayer().getAlliance();
        if (opponentPanel.getPlayerInfo().getPlayer().getOpponent().getAlliance() == alliance) {
            return opponentPanel;
        }
        return playerPanel;
    }
    
    // Set the Proper Size for the TablePanel Panel
    @Override
    public void setBounds(int x, int y, int width, int height) {
        int size = Math.min(width, height);
        int totalWidth = (int) (size * 0.8);
        int offSetX = x + ((width + ((int) (size * 0.2))) - size) / 2;
        int offSetY = y + (height - size) / 2;
        super.setBounds(offSetX, offSetY, totalWidth, size);
    }
}
