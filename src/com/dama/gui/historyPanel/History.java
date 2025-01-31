package com.dama.gui.historyPanel;

import app.frames.MainFrame;
import com.db.connection.DatabaseConnection;
import com.db.model.ModelHistory;
import com.db.model.ModelProfile;
import com.db.service.ServiceHistory;
import utilities.FontManager;
import utilities.FontManager.*;
import java.awt.Dimension;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class History extends JPanel {

    
    public History() {
        initComponents();
        
        titleLabel.setFont(FontManager.getFont(FontName.POPPINS_BOLD, FontType.POPPINS, titleLabel.getFont().getSize()));
        logoColumn.setFont(FontManager.getFont(FontName.POPPINS_MEDIUM, FontType.POPPINS, logoColumn.getFont().getSize()));
        PlayerColumn.setFont(FontManager.getFont(FontName.POPPINS_MEDIUM, FontType.POPPINS, PlayerColumn.getFont().getSize()));
        resultColumn.setFont(FontManager.getFont(FontName.POPPINS_MEDIUM, FontType.POPPINS, resultColumn.getFont().getSize()));
        dateColumn.setFont(FontManager.getFont(FontName.POPPINS_MEDIUM, FontType.POPPINS, dateColumn.getFont().getSize()));
        
        try {
            refreshContent();
        } catch (SQLException e) {
            System.err.println("Something went wrong while loading all history -> History line 27");
            System.out.println("Error: " + e.getMessage());
        }
        this.revalidate();
        this.repaint();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        title = new javax.swing.JPanel();
        titleLabel = new javax.swing.JLabel();
        logoColumn = new javax.swing.JLabel();
        PlayerColumn = new javax.swing.JLabel();
        dateColumn = new javax.swing.JLabel();
        resultColumn = new javax.swing.JLabel();
        iconColumn = new javax.swing.JLabel();
        editColumn = new javax.swing.JLabel();
        content = new javax.swing.JPanel();

        setBackground(new java.awt.Color(29, 28, 26));
        setForeground(new java.awt.Color(255, 255, 255));
        setFocusable(false);
        setMinimumSize(new java.awt.Dimension(600, 150));
        setName("historyPanel"); // NOI18N
        setPreferredSize(new java.awt.Dimension(600, 150));
        setLayout(new java.awt.BorderLayout());

        title.setBackground(new java.awt.Color(38, 37, 34));
        title.setForeground(new java.awt.Color(255, 255, 255));
        title.setMinimumSize(title.getPreferredSize());
        title.setOpaque(false);
        title.setPreferredSize(new java.awt.Dimension(600, 90));
        title.setLayout(new java.awt.GridBagLayout());

        titleLabel.setBackground(new java.awt.Color(38, 37, 34));
        titleLabel.setFont(new java.awt.Font("Segoe UI Black", 1, 16)); // NOI18N
        titleLabel.setForeground(new java.awt.Color(255, 255, 255));
        titleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleLabel.setText("History");
        titleLabel.setFocusable(false);
        titleLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        titleLabel.setMaximumSize(new java.awt.Dimension(400, 45));
        titleLabel.setMinimumSize(new java.awt.Dimension(400, 45));
        titleLabel.setOpaque(true);
        titleLabel.setPreferredSize(new java.awt.Dimension(400, 45));
        titleLabel.setRequestFocusEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        title.add(titleLabel, gridBagConstraints);

        logoColumn.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        logoColumn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        logoColumn.setToolTipText(null);
        logoColumn.setFocusable(false);
        logoColumn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        logoColumn.setMaximumSize(new java.awt.Dimension(50, 50));
        logoColumn.setMinimumSize(new java.awt.Dimension(50, 50));
        logoColumn.setPreferredSize(new java.awt.Dimension(50, 50));
        logoColumn.setRequestFocusEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        title.add(logoColumn, gridBagConstraints);

        PlayerColumn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        PlayerColumn.setForeground(new java.awt.Color(255, 255, 255));
        PlayerColumn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        PlayerColumn.setText("Player");
        PlayerColumn.setToolTipText(null);
        PlayerColumn.setFocusable(false);
        PlayerColumn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        PlayerColumn.setMaximumSize(new java.awt.Dimension(150, 50));
        PlayerColumn.setMinimumSize(new java.awt.Dimension(150, 50));
        PlayerColumn.setPreferredSize(new java.awt.Dimension(150, 50));
        PlayerColumn.setRequestFocusEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        title.add(PlayerColumn, gridBagConstraints);

        dateColumn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        dateColumn.setForeground(new java.awt.Color(255, 255, 255));
        dateColumn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        dateColumn.setText("Date");
        dateColumn.setToolTipText(null);
        dateColumn.setFocusable(false);
        dateColumn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        dateColumn.setMaximumSize(new java.awt.Dimension(120, 50));
        dateColumn.setMinimumSize(new java.awt.Dimension(120, 50));
        dateColumn.setPreferredSize(new java.awt.Dimension(120, 50));
        dateColumn.setRequestFocusEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);
        title.add(dateColumn, gridBagConstraints);

        resultColumn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        resultColumn.setForeground(new java.awt.Color(255, 255, 255));
        resultColumn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        resultColumn.setText("Result");
        resultColumn.setToolTipText(null);
        resultColumn.setFocusable(false);
        resultColumn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        resultColumn.setMaximumSize(new java.awt.Dimension(40, 50));
        resultColumn.setMinimumSize(new java.awt.Dimension(40, 50));
        resultColumn.setPreferredSize(new java.awt.Dimension(40, 50));
        resultColumn.setRequestFocusEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        title.add(resultColumn, gridBagConstraints);

        iconColumn.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        iconColumn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        iconColumn.setToolTipText(null);
        iconColumn.setFocusable(false);
        iconColumn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        iconColumn.setMaximumSize(new java.awt.Dimension(10, 50));
        iconColumn.setMinimumSize(new java.awt.Dimension(10, 50));
        iconColumn.setName(""); // NOI18N
        iconColumn.setPreferredSize(new java.awt.Dimension(10, 50));
        iconColumn.setRequestFocusEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        title.add(iconColumn, gridBagConstraints);

        editColumn.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        editColumn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        editColumn.setToolTipText(null);
        editColumn.setFocusable(false);
        editColumn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        editColumn.setMaximumSize(new java.awt.Dimension(10, 50));
        editColumn.setMinimumSize(new java.awt.Dimension(10, 50));
        editColumn.setName(""); // NOI18N
        editColumn.setPreferredSize(new java.awt.Dimension(10, 50));
        editColumn.setRequestFocusEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        title.add(editColumn, gridBagConstraints);

        add(title, java.awt.BorderLayout.NORTH);

        content.setBackground(new java.awt.Color(38, 37, 34));
        content.setForeground(new java.awt.Color(255, 255, 255));
        content.setMinimumSize(getPreferredSize());
        content.setName(""); // NOI18N
        content.setOpaque(false);
        content.setPreferredSize(new java.awt.Dimension(600, 70));
        content.setLayout(new java.awt.GridBagLayout());
        add(content, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel PlayerColumn;
    private javax.swing.JPanel content;
    private javax.swing.JLabel dateColumn;
    private javax.swing.JLabel editColumn;
    private javax.swing.JLabel iconColumn;
    private javax.swing.JLabel logoColumn;
    private javax.swing.JLabel resultColumn;
    private javax.swing.JPanel title;
    private javax.swing.JLabel titleLabel;
    // End of variables declaration//GEN-END:variables

    public void refreshContent() throws SQLException {
        content.removeAll();
        if (!MainFrame.isInstance()) return;
        final MainFrame mainManager = MainFrame.getInstance();
        final ModelProfile user = mainManager.getUserProfile();
        List<ModelHistory> gameHistory;
        
        final ServiceHistory history = mainManager.getServiceHistory();
        final boolean online = history.isConnectionValid();
        if (mainManager.isLogin() && online) {
            gameHistory = history.getLatestHistory(user.getUserID(), 20);
        } else {
            if (mainManager.isLogin() && !online) {
                DatabaseConnection.getInstance().reconnect(history);
            }
            final List<ModelHistory> localHistory = mainManager.getLocalHistory();
            final int size = localHistory.size();
            final List<ModelHistory> historySubList = new ArrayList<>(
                    mainManager.getLocalHistory().subList(Math.max(size - 20, 0), size));
            Collections.reverse(historySubList);
            gameHistory = historySubList;
        }
        
        SwingUtilities.invokeLater(() -> {
            final int labelHeight = 60;
            final int titleHeight = (int) title.getPreferredSize().getHeight();

            final int contentHeight = gameHistory.isEmpty() ? 80 : labelHeight * gameHistory.size();
            final int mainHeight = titleHeight + contentHeight;
            
            final Dimension contentSize = new Dimension((int) content.getPreferredSize().getWidth(), contentHeight);
            final Dimension mainSize = new Dimension((int) this.getPreferredSize().getWidth(), mainHeight);

            content.setSize(contentSize);
            content.setPreferredSize(contentSize);
            this.setPreferredSize(mainSize);
            this.setSize(mainSize);
        });
        
        for (int x = (gameHistory.size() - 1); x >= 0; x--) {
            final ModelHistory data = gameHistory.get(x);
            final HistoryData dataPanel = new HistoryData(data);
            
            java.awt.GridBagConstraints gridBagConstraints;
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = x;
            gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.weighty = 1.0;
            gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
            content.add(dataPanel, gridBagConstraints);
        }

        content.revalidate();
        content.repaint();
    }
}
