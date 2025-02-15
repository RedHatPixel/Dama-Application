package com.dama.gui.gamePanel;

import com.dama.engine.dependencies.Position;
import com.dama.engine.board.Board;
import com.dama.engine.board.BoardUtils;
import com.dama.engine.pieces.Piece;
import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.SwingUtilities;

public final class BoardPanel extends JPanel {
    
    // Static Variables
    private static final Dimension PREFERRED_SIZE = new Dimension(500, 500);
    private static final Color BACKGROUND_COLOR = new Color(160, 81, 45);
    private static final Color BORDER_COLOR = new Color(160, 81, 45);
    
    // Define Variables
    private final TablePanel table;
    private final DragGlassPane dragGlassPane;
    private final JPanel tilesContainer;
    private final Map<Position, TilePanel> boardTiles;
    private Direction direction;
    private Dimension lastSize;
    private int thickness;
    
    // Constructor: Define a system of graphical board interface
    BoardPanel(final TablePanel table) {
        super(new BorderLayout());
        this.table = table;
        this.dragGlassPane = new DragGlassPane(this);
        this.tilesContainer = new JPanel(new GridLayout(8, 8));
        this.boardTiles = new LinkedHashMap<>();
        this.thickness = calculateThickness();
        this.lastSize = getSize();
        this.direction = Direction.NORMAL;
        initComponents();
        displayTilePanels();
    }
    
    /**
     * Constructor: Define a non-system of graphical board interface
     * WARNING: This is only used to Display GUI -> No Functionality
     */
    public BoardPanel() {
        this(null);
    }
    
    // Configuration: Display the BoardPanel GUI design
    private void initComponents() {
        setBackground(BACKGROUND_COLOR);
        setForeground(Color.WHITE);
        setFocusable(false);
        setVisible(true);
        setPreferredSize(PREFERRED_SIZE);
        setMinimumSize(getPreferredSize());
        setName("Board");
        setRequestFocusEnabled(false);
        setDoubleBuffered(true);
        tilesContainer.setBackground(BORDER_COLOR);
        add(tilesContainer, BorderLayout.CENTER);
        SwingUtilities.invokeLater(() -> {
            JRootPane rootPane = SwingUtilities.getRootPane(this);
            if (rootPane != null) {
                rootPane.setGlassPane(dragGlassPane);
                dragGlassPane.setVisible(true);
            }
        });
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(final ComponentEvent e) {        
                if (!getSize().equals(lastSize)) {
                    updateBorder();
                    dragGlassPane.setBoardLocation();
                    lastSize = getSize();
                }
            }
        });
        
        validate();
        repaint();
    }
    
    //-------------TilePanel_Helper_Methods----------------//
    
    /**
     * Get the parent table of the BoardPanel
     * USED: for the system(TilePanel) only -> same package only
     * @return TablePanel
     */
    public TablePanel getTable() {
        return this.table;
    }
    
    /**
     * Get the visual presentation of moving icon
     * USED: for the system(TilePanel) only -> same package only
     * @return DragGlassPane
     */
    public DragGlassPane getDragGlassPane() {
        return this.dragGlassPane;
    }
    
    /**
     * Get the current direction of the board
     * USED: for the system(TilePanel) only -> same package only
     * @return Direction
     */
    public Direction getCurrentDirection() {
        return this.direction;
    }
    
    /**
     * Get the tiles within the boardPanel according to the point location
     * USED: for the system(TilePanel) only -> same package only
     * @param dropPoint Point
     * @return TilePanel
     */
    public TilePanel getTilePanelAt(final Point dropPoint) {
        final Component component = tilesContainer.getComponentAt(dropPoint);
        if (component instanceof TilePanel)
            return (TilePanel) component;
        return null;
    }
    
    //-------------Table_Helper_Methods----------------//
    
    /**
     * Set the new direction of the board
 USED: for the setting(TablePanel) only -> same package only
     * @param direction Direction
     */
    public void setDirection(final Direction direction) {
        this.direction = direction;
        drawBoard(table.getGameBoard());
    }
    
    /**
     * Disable the board system
 USED: for the parent(TablePanel) only -> same package only
     */
    public void disableBoard() {
        for (final TilePanel tilePanel : boardTiles.values()) {
            tilePanel.disableTile();
        }
        validate();
        repaint();
    }
    
    /**
     * Open the board system
 USED: for the parent(TablePanel) only -> same package only
     */
    public void openBoard() {
        for (final TilePanel tilePanel : boardTiles.values()) {
            tilePanel.openTile();
        }
        validate();
        repaint();
    }
    
    /**
     * Display the new transition of board
     * USED: for the system(TilePanel) only -> same package only
     * @param board     Board
     */
    public void drawBoard(final Board board) {
        tilesContainer.removeAll();
        for (final TilePanel tilePanel : direction.traverse(boardTiles).values()) {
            tilePanel.drawTile(board);
            tilesContainer.add(tilePanel);
        }
        validate();
        repaint();
    }
    
    /**
     * Display the possible movable pieces on the board
     * USED: for the system(TilePanel) only -> same package only
     * @param board Board
     */
    public void drawMovable(final Board board) {
        for (final TilePanel tilePanel : boardTiles.values()) {
            tilePanel.drawMovable(board);
        }
        validate();
        repaint();
    }
    
    /**
     * Display the possible movement of the piece
     * USED: for the system(TilePanel) only -> same package only
     * @param piece Piece
     * @param board Board
     */
    public void drawGuidance(final Piece piece, final Board board) {
        for (final TilePanel tilePanel : boardTiles.values()) {
            tilePanel.drawGuidance(piece, board);
        }
        validate();
        repaint();
    }
    
    //-------------BoardPanel_System----------------//
    
    /**
     * Display the latest moves of the piece
     * USED: for the system(TilePanel) only -> same package only
     * @param board Board
     */
    public void highlightLatestMove(final Board board) {
        for (final TilePanel tilePanel : boardTiles.values()) {
            tilePanel.drawHighlight(board);
        }
        validate();
        repaint();
    }
    
    // Configuration: Change the border thickness
    private void updateBorder() {
        thickness = calculateThickness();
        setBorder(BorderFactory.createMatteBorder(
                thickness, thickness, thickness, thickness, BORDER_COLOR));
    }
    
    // Configuration: Calculate the given sise of the border
    private int calculateThickness() {
        final double smallerDimension = Math.min(getSize().getHeight(), getSize().getWidth());
        return (int) (smallerDimension * 0.025); // 2.5% of the smaller dimension
    }
    
    // Configuration: Display each TilePanel GUI design
    private void displayTilePanels() {
        // Loop through rows of the board
        for (int row = 0; row < BoardUtils.NUM_TILES[0]; row++) {
            for (int col = 0; col < BoardUtils.NUM_TILES[1]; col++) {
                final Position position = new Position(row, col); // Create a new Position
                final TilePanel tilePanel = new TilePanel(this, position); // Create a new TilePanel
                // Insert the tilePanel into the boardTiles (List<TilePanel)
                boardTiles.put(position, tilePanel);
                tilesContainer.add(tilePanel); // Display the tilePanel
            }
        }
        validate();
        repaint();
    }

    // Enum State: The Direction of the board
    public static enum Direction {
        NORMAL {
            @Override
            Map<Position, TilePanel> traverse(final Map<Position, TilePanel> boardTiles) {
                return boardTiles;
            }
            
            @Override
            public Direction opposite() {
                return FLIPPED;
            }
        },
        FLIPPED {
            @Override
            Map<Position, TilePanel> traverse(final Map<Position, TilePanel> boardTiles) {
                final Map<Position, TilePanel> reversedMap = new LinkedHashMap<>();
                final List<Map.Entry<Position, TilePanel>> entries = new ArrayList<>(boardTiles.entrySet());
                Collections.reverse(entries);
                for (final Map.Entry<Position, TilePanel> entry : entries) {
                    reversedMap.put(entry.getKey(), entry.getValue());
                }
                return reversedMap;
            }
            
            @Override
            public Direction opposite() {
                return NORMAL;
            }
        };
        
        abstract Map<Position, TilePanel> traverse(final Map<Position, TilePanel> boardTiles);
        public abstract Direction opposite();
    }
    
    // Override setBounds to enforce square size
    @Override
    public void setBounds(int x, int y, int width, int height) {
        int size = Math.min(width, height);
        int offsetX = x + (width - size) / 2;
        int offsetY = y + (height - size) / 2;
        super.setBounds(offsetX, offsetY, size, size);
    }
}
