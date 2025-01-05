package component.Panel.subPanel;

import utilities.FontManager;
import utilities.FontManager.*;
import utilities.GameHistoryData;
import utilities.GameHistoryManager;
import java.awt.Dimension;
import java.util.List;

public class History extends javax.swing.JPanel {

    private static GameHistoryManager gameHistoryManager = new GameHistoryManager();

    public History() {
        initComponents();

        titleLabel.setFont(FontManager.getFont(FontName.POPPINS_BOLD, FontType.POPPINS, titleLabel.getFont().getSize()));
        logoColumn.setFont(FontManager.getFont(FontName.POPPINS_MEDIUM, FontType.POPPINS, logoColumn.getFont().getSize()));
        PlayerColumn.setFont(FontManager.getFont(FontName.POPPINS_MEDIUM, FontType.POPPINS, PlayerColumn.getFont().getSize()));
        resultColumn.setFont(FontManager.getFont(FontName.POPPINS_MEDIUM, FontType.POPPINS, resultColumn.getFont().getSize()));
        dateColumn.setFont(FontManager.getFont(FontName.POPPINS_MEDIUM, FontType.POPPINS, dateColumn.getFont().getSize()));

        GameHistoryData gameData = new GameHistoryData(
                "Blitz",
                new String[]{"neko", "coby"},
                new int[]{1, 1}, 1, "2024 - 07 - 19"
        );

        // Examples
        gameHistoryManager.addGameResult("Blitz",
                new String[]{"MID1", "Flaming hot cheetos"},
                new int[]{0, 1}, 0, "2024 - 04 - 18");

        gameHistoryManager.addGameResult("Blitz",
                new String[]{"MID1", "Flaming hot cheetos"},
                new int[]{0, 2}, 0, "2024 - 04 - 18");

        gameHistoryManager.addGameResult(gameData);

        refreshContent();

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
        logoColumn1 = new javax.swing.JLabel();
        content = new javax.swing.JPanel();

        setBackground(new java.awt.Color(29, 28, 26));
        setForeground(new java.awt.Color(255, 255, 255));
        setFocusable(false);
        setMinimumSize(new java.awt.Dimension(400, 130));
        setName("historyPanel"); // NOI18N
        setPreferredSize(new java.awt.Dimension(400, 130));
        setLayout(new java.awt.BorderLayout());

        title.setBackground(new java.awt.Color(38, 37, 34));
        title.setForeground(new java.awt.Color(255, 255, 255));
        title.setMinimumSize(new java.awt.Dimension(400, 90));
        title.setOpaque(false);
        title.setPreferredSize(new java.awt.Dimension(400, 90));
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
        gridBagConstraints.gridwidth = 5;
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

        logoColumn1.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        logoColumn1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        logoColumn1.setToolTipText(null);
        logoColumn1.setFocusable(false);
        logoColumn1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        logoColumn1.setMaximumSize(new java.awt.Dimension(10, 50));
        logoColumn1.setMinimumSize(new java.awt.Dimension(10, 50));
        logoColumn1.setName(""); // NOI18N
        logoColumn1.setPreferredSize(new java.awt.Dimension(10, 50));
        logoColumn1.setRequestFocusEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        title.add(logoColumn1, gridBagConstraints);

        add(title, java.awt.BorderLayout.NORTH);

        content.setBackground(new java.awt.Color(38, 37, 34));
        content.setForeground(new java.awt.Color(255, 255, 255));
        content.setMinimumSize(getPreferredSize());
        content.setName(""); // NOI18N
        content.setOpaque(false);
        content.setPreferredSize(new java.awt.Dimension(300, 60));
        content.setLayout(new java.awt.GridBagLayout());
        add(content, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel PlayerColumn;
    private javax.swing.JPanel content;
    private javax.swing.JLabel dateColumn;
    private javax.swing.JLabel logoColumn;
    private javax.swing.JLabel logoColumn1;
    private javax.swing.JLabel resultColumn;
    private javax.swing.JPanel title;
    private javax.swing.JLabel titleLabel;
    // End of variables declaration//GEN-END:variables

    public void refreshContent() {
        content.removeAll();

        if (gameHistoryManager == null) {
            return;
        }

        // Get the game history data
        List<GameHistoryData> gameData = gameHistoryManager.getGameResults();
        gameData = gameData.subList(0, Math.min(gameData.size(), 9));

        // Set the size of content depending on the numbers of label
        int labelHeight = 85;
        int contentHeight = labelHeight * (gameData.isEmpty() ? 1 : gameData.size());
        contentHeight = Math.max(contentHeight, labelHeight);

        int mainHeight = title.getSize().height + contentHeight;

        // Set the new dimension of the content
        Dimension content_size = new Dimension((int) content.getPreferredSize().getWidth(), contentHeight);
        Dimension main_size = new Dimension((int) this.getPreferredSize().getWidth(), mainHeight);

        // Set the new preffered Size of the contnet
        content.setPreferredSize(content_size);
        this.setPreferredSize(main_size);

        int iterator = 0;

        // Iterate over all the data
        for (int x = (gameData.size() - 1); x >= 0; x--) {

            GameHistoryData data = gameData.get(x);

            HistoryData dataPanel = new HistoryData(
                    data.type,
                    data.playersName,
                    data.playersScore,
                    data.result,
                    data.date
            );

            java.awt.GridBagConstraints gridBagConstraints;

            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = iterator;
            gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.weighty = 1.0;
            gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
            content.add(dataPanel, gridBagConstraints);

            iterator++;
        }

        content.revalidate();
        content.repaint();
    }

    public static GameHistoryManager getGameHistoryManager() {
        return gameHistoryManager;
    }
}
