package com.dama.gui.controlPanel;

import com.dama.gui.gamePanel.TablePanel;
import com.dama.engine.board.Board;
import com.dama.engine.dependencies.Move;
import com.dama.engine.dependencies.Position;
import com.dama.engine.pieces.Piece;
import app.panels.GamePlay;
import app.panels.CardHandlers.CardPanelRegistry;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Component;
import javax.swing.*;
import utilities.FontManager;
import utilities.FontManager.*;
import utilities.ImageFiles;

public class MoveRecords extends JPanel {

    public MoveRecords() {
        initComponents();
    }
    
    public void updateGameRecord() {
        recordContent.removeAll();
        if (CardPanelRegistry.isInstanced(GamePlay.class)) {
            final TablePanel table = CardPanelRegistry.getInstance(GamePlay.class).getTable();
            final com.dama.engine.records.GamePlay gamePlay = table.getGamePlay();
            for (final Board board : gamePlay.getBoardHistory()) {
                if (board.getLatestMove().getType().isNaN()) continue;
                addMoveRecords(board);
            }
        }
        updateRecordContentHeight();
    }
    
    private void addMoveRecords(final Board board) {
        final Move move = board.getLatestMove();
        final Piece piece = move.getMovedPiece();
        
        final JLabel moveRecord = new JLabel();
        moveRecord.setFont(FontManager.getFont(
                FontName.POPPINS_REGULAR, FontType.POPPINS, 12));
        moveRecord.setForeground(Color.WHITE);
        moveRecord.setHorizontalAlignment(SwingConstants.LEFT);
        moveRecord.setHorizontalTextPosition(SwingConstants.RIGHT);
        moveRecord.setVerticalAlignment(SwingConstants.CENTER);
        moveRecord.setVerticalTextPosition(SwingConstants.CENTER);
        moveRecord.setIconTextGap(8);
        
        if (piece.getAlliance().isWhite()) moveRecord.setIcon(ImageFiles.WHITE_SELECTION);
        else    moveRecord.setIcon(ImageFiles.BLACK_SELECTION);
        
        final Position currPosition = move.getCurrentCoordinate();
        final Position landPosition = move.getLandingCoordinate();
        final String output = piece.getType() +
                            ":   [" + (currPosition.x() + 1) + ", " + (currPosition.y() + 1) + "]" +
                                    (move.getType().canAttack() ? "  x  " : "  >  ") +
                            "[" + (landPosition.x() + 1) + ", " + (landPosition.y() + 1) + "]";
               
        moveRecord.setText(output);
        moveRecord.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createEmptyBorder(0, 14, 0, 14), 
                        BorderFactory.createEmptyBorder(0, 14, 0, 14))
                );
        moveRecord.setPreferredSize(new Dimension(300, 30));
        moveRecord.setMaximumSize(getPreferredSize());
        moveRecord.setMinimumSize(getPreferredSize());
        recordContent.add(moveRecord);
        recordContent.revalidate();
        recordContent.repaint();
    }
    
    private void updateRecordContentHeight() {
        int totalHeight = 0;
        for (Component comp : recordContent.getComponents()) {
            totalHeight += comp.getPreferredSize().height;
        }
        totalHeight += (recordContent.getComponentCount() - 1) * 5;
        final Dimension newSize = new Dimension(recordContent.getPreferredSize().width, totalHeight);
        recordContent.setPreferredSize(newSize);
        recordContent.revalidate();
        recordContent.repaint();
        
        SwingUtilities.invokeLater(() -> {
            final JScrollBar verticalScrollBar = mainScrollPane.getVerticalScrollBar();
            verticalScrollBar.setValue(verticalScrollBar.getMaximum());
        });
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainScrollPane = new app.scrollPanes.MainScrollPane();
        recordContent = new javax.swing.JPanel();

        setBackground(new java.awt.Color(38, 37, 34));
        setForeground(new java.awt.Color(255, 255, 255));
        setFocusable(false);
        setMaximumSize(new java.awt.Dimension(325, 700));
        setMinimumSize(new java.awt.Dimension(325, 400));
        setName("Move Records"); // NOI18N
        setLayout(new java.awt.BorderLayout());

        mainScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        mainScrollPane.setOpaque(false);

        recordContent.setBackground(new java.awt.Color(38, 37, 34));
        recordContent.setForeground(new java.awt.Color(255, 255, 255));
        recordContent.setFocusable(false);
        recordContent.setMaximumSize(new java.awt.Dimension(300, 32767));
        recordContent.setMinimumSize(new java.awt.Dimension(300, 300));
        recordContent.setName("content"); // NOI18N
        recordContent.setPreferredSize(new java.awt.Dimension(300, 300));
        java.awt.FlowLayout flowLayout1 = new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 0, 5);
        flowLayout1.setAlignOnBaseline(true);
        recordContent.setLayout(flowLayout1);
        mainScrollPane.setViewportView(recordContent);

        add(mainScrollPane, java.awt.BorderLayout.CENTER);

        getAccessibleContext().setAccessibleName("Move Records");
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private app.scrollPanes.MainScrollPane mainScrollPane;
    private javax.swing.JPanel recordContent;
    // End of variables declaration//GEN-END:variables
}
