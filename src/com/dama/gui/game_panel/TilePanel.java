 package com.dama.gui.game_panel;

import com.dama.engine.dependencies.Position;
import com.dama.engine.dependencies.Move;
import com.dama.engine.dependencies.Move.AttackMove;
import com.dama.engine.dependencies.Move.MultipleAttackMove;
import com.dama.engine.board.Board;
import com.dama.engine.board.BoardUtils;
import com.dama.engine.pieces.Piece;
 
import com.dama.gui.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public final class TilePanel extends JPanel {
    
    // Static Variables
    private static final Dimension PREFERRED_SIZE = new Dimension(62, 62);
    private static final Color TILE_THREATEN = new Color(212, 58, 47);
    private static final Color TILE_MOVED = new Color(252, 250, 88);
    private static final Color TILE_CAN_MOVE = new Color(114, 238, 114);
    private static final Color TILE_WHITE = new Color(210, 180, 140);
    private static final Color TILE_BLACK = new Color(160, 81, 45);
    private static final String DOT_IMAGE = "dot";
    
    // Define Variables
    private final TilePanelSystem tilePanelSystem;
    private final BoardPanel boardPanel;
    private final Position coordinate;
    private final Table table;
    private final JLabel content;
    private Dimension lastSize;
    private String image;
    
    // Tile Conditions
    private boolean threaten;
    private boolean selected;
    private boolean assigned;
    private boolean approval;
    
    // Constructor: Define each tile panel to display in board
    TilePanel(final BoardPanel boardPanel, final Position coordinate) {
        super(new BorderLayout());
        this.tilePanelSystem = new TilePanelSystem(this);
        this.boardPanel = boardPanel;
        this.coordinate = coordinate;
        this.table = boardPanel.getTable();
        this.content = new JLabel();
        this.image = new String();
        this.lastSize = getSize();
        
        initComponent();
    }
    
    // Configuration: Display the TilePanel GUI design   
    private void initComponent() {
        setBackgroundRespectiveColor();
        setBorder(BorderFactory.createEmptyBorder());
        setForeground(Color.WHITE);
        setPreferredSize(PREFERRED_SIZE);
        setMinimumSize(getPreferredSize());
        setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));
        setFocusable(false);
        setVisible(true);
        setName("Tile");
        setVisible(true);
        setOpaque(true);
        setDoubleBuffered(true);
        setRequestFocusEnabled(false);
        // WARNING: Remove Button Functionality -> Table == null
        if (table != null || GameInfo.getGameDuration() == GameInfo.GameDuration.NULL)  {
            assignTilePieceIcon(boardPanel.getTable().getGameBoard());
            addMouseListener(tilePanelSystem);
            addComponentListener(new ComponentAdapter() {
                @Override
                public void componentResized(final ComponentEvent e) {
                    if (!getSize().equals(lastSize)) {
                        initializeAndResizeTileIcon();
                        lastSize = getSize();
                    }
                }
            });
            addMouseMotionListener(new MouseAdapter() {
                @Override
                public void mouseDragged(final MouseEvent e) {
                    boardPanel.getDragGlassPane().setPointLocation(
                    SwingUtilities.convertPoint(
                            TilePanel.this, e.getPoint(), boardPanel.getDragGlassPane()));
                }
            });
        }
        content.setHorizontalAlignment(JLabel.CENTER);
        content.setVerticalAlignment(JLabel.CENTER);
        add(content, BorderLayout.CENTER);
        validate();
        repaint();
    }
    
    /**
     * Reset the tile display configuration
     * USED: for the manager(BoardPanel) only -> same package only
     * @param board Board
     */
    void drawTile(final Board board) {
        selected = false;
        assigned = false;
        threaten = false;
        approval = false;
        highlightMovablePiece(board);
        assignTilePieceIcon(board);
        setBackgroundRespectiveColor();
        initializeAndResizeTileIcon();
    }
    
    //-------------BoardPanel_Help_Methods---------------//
    
    /**
     * Show the after movement
     * USED: for the manager(BoardPanel) only -> same package only
     * @param board Board
     */
    void drawHighlight(final Board board) {
        assigned = false;
        highlightTileFinalMove(board);
        setBackgroundRespectiveColor();
        initializeAndResizeTileIcon();
    }
    
    /**
     * USED: for the manager(BoardPanel) only -> same package only
     * @param piece Piece
     * @param board Board
     */
    void drawGuidance(final Piece piece, final Board board) {
        selected = false;
        threaten = false;
        approval = false;
        highlightTilePiece(piece, board);
        highlightTileBackgroundCaptures(piece, board);
        assignTilePieceIcon(board);
        assignTileSelectionIcon(piece, board);
        setBackgroundRespectiveColor();
        initializeAndResizeTileIcon();
    }
    
    /**
     * Disable the tile functionality
     * USED: for the manager(BoardPanel) only -> same package only
     */
    void disableTile() {
        removeMouseListener(tilePanelSystem);
        for (final MouseMotionListener comp : this.getMouseMotionListeners()) {
            removeMouseMotionListener(comp);
        }
    }
    
    /**
     * Open the tile functionality
     * USED: for the manager(BoardPanel) only -> same package only
     */
    void openTile() {
        addMouseListener(tilePanelSystem);
        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(final MouseEvent e) {
                boardPanel.getDragGlassPane().setPointLocation(
                SwingUtilities.convertPoint(
                        TilePanel.this, e.getPoint(), boardPanel.getDragGlassPane()));
            }
        });
    }
    
    //-------------TilePanelSystem_Help_Methods---------------//
    
    /**
     * Set the proper background color of a tile
     * USED: for the TilePanelSystem only -> same package only
     */
    public void setBackgroundRespectiveColor() {
        final Color background = 
                BoardUtils.TILES_PATTERN[this.coordinate.x()][this.coordinate.y()] ? TILE_BLACK : TILE_WHITE;
        setBackground(
                threaten ? TILE_THREATEN :
                (approval) ? TILE_CAN_MOVE :
                (assigned && !image.equals(DOT_IMAGE)) ? TILE_MOVED :
                (selected || tilePanelSystem.pressed()) ? background.darker() :
                tilePanelSystem.hover() ? background.brighter() : background);
    }
    
    /**
     * Remove the tile piece icon
     * USED: for the TilePanelSystem only -> same package only
     */
    public void removeTilePieceIcon() {
        image = "";
        initializeAndResizeTileIcon();
    }
    
    /**
     * Get the coordinate of the tilePanel within the boardPanel
     * USED: for the TilePanelSystem only -> same package only
     * @return Position
     */
    public Position getCoordinate() {
        return this.coordinate;
    }
    
    /**
     * Get the boardPanel of the tilePanel
     * USED: for the TilePanelSystem only -> same package only
     * @return BoardPanel
     */
    public BoardPanel getBoardPanel() {
        return this.boardPanel;
    }
    
    /**
     * Get the image of the tilePanel content
     * USED: for the TilePanelSystem only -> same package only
     * @return ImageIcon
     */
    public ImageIcon getPieceIcon() {
        return (ImageIcon) this.content.getIcon();
    }
    
    //-------------TilePanel_Conditional_Methods---------------//
    
    // Configuration: Highlight the tiles background if the piece was selected
    private void highlightTilePiece(final Piece piece, final Board board) {
        if (piece == null) return;
        if (piece.getPosition().equals(coordinate) && 
            piece.getAlliance().equals(board.getCurrentPlayer().getAlliance())) {
            selected = true;
        }
    }
    
    // Configuration: Highlight the tiles background if the piece can be move
    private void highlightMovablePiece(final Board board) {
        if (!GameInfo.showMovablePiece()) return;
        for (final Move move : board.getCurrentPlayer().getLegalMoves()) {
            if (move.getMovedPiece().getPosition().equals(coordinate) &&
                board.getTile(coordinate).isOccupied()) {
                if (board.getCurrentPlayer().isForceCapture() && !move.getType().canAttack()) break;
                approval = true;
            }
        }
    }
    
    // Configuration: Highlight the tiles background if the piece was the latest move
    private void highlightTileFinalMove(final Board board) {
        if (board.getLatestMove() == null || !GameInfo.showLatestMove()) return;
        if (board.getLatestMove().getCurrentCoordinate().equals(coordinate) ||
            board.getLatestMove().getLandingCoordinate().equals(coordinate))
            assigned = true;
    }
    
    // Configuration: Highlight the tiles background if the piece in it can be capture
    private void highlightTileBackgroundCaptures(final Piece piece, final Board board) {
        if (piece == null || !GameInfo.showCaptures()) return;
        else if (piece.getAlliance() != board.getCurrentPlayer().getAlliance()) return;
        for (final Move move : piece.calculateLegalMoves(board)) {
            if (move.getType().canAttack()) {
                if (board.getLatestMove().getType().canAttackAgain() &&
                    !board.getLatestMovedPiece().equals(move.getMovedPiece()))
                    break;
                
                if (move instanceof AttackMove) {
                    AttackMove attackMove = (AttackMove) move;
                    if (attackMove.getAtackedPiece().getPosition().equals(coordinate)) {
                        threaten = true;
                        break;
                    }
                }
                
                if (move instanceof MultipleAttackMove) {
                    MultipleAttackMove multiAttackMove = (MultipleAttackMove) move;
                    for (final Piece pieces : multiAttackMove.getAttackedPieces()) {
                        if (pieces.getPosition().equals(coordinate)) {
                            threaten = true;
                            break;
                        }
                    }
                }
            }
        }
    }
    
    // Configuration: Highlight the tiles if it can be move
    private void assignTileSelectionIcon(final Piece piece, final Board board) {
        if (piece == null || !GameInfo.showValidMoves()) return;
        else if (board.getTile(coordinate).isOccupied()) return;
        else if (piece.getAlliance() != board.getCurrentPlayer().getAlliance()) return;
        image = "";
        for (final Move move : piece.calculateLegalMoves(board)) {
            if (move.getLandingCoordinate().equals(coordinate)) {
                if (board.getCurrentPlayer().isForceCapture() && !move.getType().canAttack()) return;
                if (board.getLatestMove().getType().canAttackAgain() &&
                    !board.getLatestMovedPiece().equals(move.getMovedPiece())) return;
                image = DOT_IMAGE;
            }
        }
    }
    
    // Configuration: Assign the Tiles Piece Icon relatively GUI design [isOccupied() method]
    private void assignTilePieceIcon(final Board board) {
        image = "";
        if (board.getTile(coordinate).isOccupied()) {
            image = board.getTile(coordinate).getPiece().getAlliance().toString() +
                    board.getTile(coordinate).getPiece().getType().toString();
        }
    }
    
    // Configuration: Calculate and Convert the image size relative to the tile height
    private void initializeAndResizeTileIcon() {
        if (!image.isBlank()) {
            int height = getHeight();
            if (image.equals(DOT_IMAGE))
                height *= 0.5;
            
            final ImageIcon imageIcon = GuiUtils.getResizedImage(image, height);
            content.setIcon(imageIcon);
            return;
        }
        
        content.setIcon(null);
    }
}
