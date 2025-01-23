package app.panels.mainSubPanel;

import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import utilities.FontManager;

public class TutorialPage extends javax.swing.JPanel {

    public TutorialPage() {
        initComponents();
        
        for (Component comp : content.getComponents()) {
            if (comp instanceof JLabel) {
                JLabel label = (JLabel) comp;
                label.setFont(FontManager.getFont(
                        FontManager.FontName.POPPINS_BLACK, 
                        FontManager.FontType.POPPINS, 
                        comp.getFont().getSize()));
            } else if (comp instanceof JTextArea) {
                JTextArea textArea = (JTextArea) comp;
                textArea.setFont(FontManager.getFont(
                        FontManager.FontName.POPPINS_REGULAR, 
                        FontManager.FontType.POPPINS, 
                        comp.getFont().getSize()));
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        content = new javax.swing.JPanel();
        javax.swing.JLabel titleOne = new javax.swing.JLabel();
        javax.swing.JTextArea introduction = new javax.swing.JTextArea();
        javax.swing.JLabel subtitleOne = new javax.swing.JLabel();
        javax.swing.JTextArea settingBody = new javax.swing.JTextArea();
        javax.swing.JLabel subTitleTwo = new javax.swing.JLabel();
        javax.swing.JTextArea rulesBody = new javax.swing.JTextArea();
        javax.swing.JLabel subTitleThree = new javax.swing.JLabel();
        javax.swing.JTextArea promotingBody = new javax.swing.JTextArea();
        javax.swing.JLabel subTitleFour = new javax.swing.JLabel();
        javax.swing.JTextArea winningBody = new javax.swing.JTextArea();
        javax.swing.JLabel subTitleFive = new javax.swing.JLabel();
        javax.swing.JTextArea strategiesBody = new javax.swing.JTextArea();
        javax.swing.JLabel subTitleSix = new javax.swing.JLabel();
        javax.swing.JTextArea tryBody = new javax.swing.JTextArea();

        setMinimumSize(new java.awt.Dimension(600, 1400));
        setPreferredSize(new java.awt.Dimension(600, 1400));
        setLayout(new java.awt.BorderLayout());

        content.setBackground(new java.awt.Color(48, 46, 43));
        content.setMinimumSize(new java.awt.Dimension(390, 550));
        content.setLayout(new java.awt.GridBagLayout());

        titleOne.setFont(new java.awt.Font("Segoe UI Black", 1, 24)); // NOI18N
        titleOne.setForeground(new java.awt.Color(255, 255, 255));
        titleOne.setText("HOW TO PLAY");
        titleOne.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        content.add(titleOne, gridBagConstraints);

        introduction.setEditable(false);
        introduction.setColumns(20);
        introduction.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        introduction.setForeground(new java.awt.Color(255, 255, 255));
        introduction.setLineWrap(true);
        introduction.setRows(5);
        introduction.setTabSize(5);
        introduction.setText("\tWelcome to our guide on playing Dama, the classic and strategic board game! Whether you're a beginner or just brushing up on the rules, this tutorial will walk you through everything you need to know."); // NOI18N
        introduction.setWrapStyleWord(true);
        introduction.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 25, 10, 10));
        introduction.setFocusable(false);
        introduction.setMinimumSize(new java.awt.Dimension(300, 80));
        introduction.setOpaque(false);
        introduction.setPreferredSize(new java.awt.Dimension(300, 80));
        introduction.setRequestFocusEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        content.add(introduction, gridBagConstraints);

        subtitleOne.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        subtitleOne.setForeground(new java.awt.Color(255, 255, 255));
        subtitleOne.setText("Setting Up the Game");
        subtitleOne.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        content.add(subtitleOne, gridBagConstraints);

        settingBody.setEditable(false);
        settingBody.setColumns(20);
        settingBody.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        settingBody.setForeground(new java.awt.Color(255, 255, 255));
        settingBody.setLineWrap(true);
        settingBody.setRows(5);
        settingBody.setTabSize(5);
        settingBody.setText("\tThe Dama board has 64 squares, alternating between light and dark. Only the dark squares are used during the game. Each player starts with 12 pieces and Player 1 uses light pieces then Player 2 uses dark pieces. Players place their pieces on the dark squares of the first three rows on their respective sides."); // NOI18N
        settingBody.setWrapStyleWord(true);
        settingBody.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 25, 10, 10));
        settingBody.setFocusable(false);
        settingBody.setOpaque(false);
        settingBody.setPreferredSize(new java.awt.Dimension(300, 100));
        settingBody.setRequestFocusEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        content.add(settingBody, gridBagConstraints);

        subTitleTwo.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        subTitleTwo.setForeground(new java.awt.Color(255, 255, 255));
        subTitleTwo.setText("Rules of Movement");
        subTitleTwo.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        subTitleTwo.setName(""); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        content.add(subTitleTwo, gridBagConstraints);

        rulesBody.setEditable(false);
        rulesBody.setColumns(20);
        rulesBody.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        rulesBody.setForeground(new java.awt.Color(255, 255, 255));
        rulesBody.setLineWrap(true);
        rulesBody.setRows(5);
        rulesBody.setTabSize(5);
        rulesBody.setText("There are three types of movement in dama;\n1. Basic Movement: \n\t> Pieces move diagonally forward, one square or multiple tiles at a time.\n2. Capture Movement:\n\t> A piece can jump over an adjacent opponent’s piece to an empty square immediately on the other side. Rules dictate that capturing is mandatory.\n3. Multiple Captures:\n\t> A single piece can make multiple jumps in one turn if there are valid moves.\n\t> When your piece is a king (dama) then you have the authorities to not capture the another available captures if a basic move is available within the diagonal line."); // NOI18N
        rulesBody.setWrapStyleWord(true);
        rulesBody.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 25, 10, 10));
        rulesBody.setFocusable(false);
        rulesBody.setMinimumSize(new java.awt.Dimension(300, 230));
        rulesBody.setOpaque(false);
        rulesBody.setPreferredSize(new java.awt.Dimension(300, 230));
        rulesBody.setRequestFocusEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        content.add(rulesBody, gridBagConstraints);

        subTitleThree.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        subTitleThree.setForeground(new java.awt.Color(255, 255, 255));
        subTitleThree.setText(" Promoting a Piece (Becoming \"Dama\")");
        subTitleThree.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        subTitleThree.setName(""); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        content.add(subTitleThree, gridBagConstraints);

        promotingBody.setEditable(false);
        promotingBody.setColumns(20);
        promotingBody.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        promotingBody.setForeground(new java.awt.Color(255, 255, 255));
        promotingBody.setLineWrap(true);
        promotingBody.setRows(5);
        promotingBody.setTabSize(5);
        promotingBody.setText("\tWhen a piece reaches the opponent's back row, it becomes a \"Dama\" (king).\n\nAbilities of a Dama:\n\t> A Dama can move and capture diagonally forward and backward.\n\t> A Dama has unlimited range, meaning it can move any number of squares in a diagonal line, provided no pieces block its path."); // NOI18N
        promotingBody.setWrapStyleWord(true);
        promotingBody.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 25, 10, 10));
        promotingBody.setFocusable(false);
        promotingBody.setMinimumSize(new java.awt.Dimension(300, 150));
        promotingBody.setOpaque(false);
        promotingBody.setPreferredSize(new java.awt.Dimension(300, 150));
        promotingBody.setRequestFocusEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        content.add(promotingBody, gridBagConstraints);

        subTitleFour.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        subTitleFour.setForeground(new java.awt.Color(255, 255, 255));
        subTitleFour.setText("Winning the Game");
        subTitleFour.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        subTitleFour.setName(""); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        content.add(subTitleFour, gridBagConstraints);

        winningBody.setEditable(false);
        winningBody.setColumns(20);
        winningBody.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        winningBody.setForeground(new java.awt.Color(255, 255, 255));
        winningBody.setLineWrap(true);
        winningBody.setRows(5);
        winningBody.setTabSize(5);
        winningBody.setText("The game ends when one player:\n\t> Captures all of their opponent’s pieces.\n\t> Blocks their opponent from making any legal moves.\n\t> Both are not capturing anything after 50 moves (25 move each player)\n"); // NOI18N
        winningBody.setWrapStyleWord(true);
        winningBody.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 25, 10, 10));
        winningBody.setFocusable(false);
        winningBody.setMinimumSize(new java.awt.Dimension(300, 100));
        winningBody.setOpaque(false);
        winningBody.setPreferredSize(new java.awt.Dimension(300, 100));
        winningBody.setRequestFocusEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        content.add(winningBody, gridBagConstraints);

        subTitleFive.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        subTitleFive.setForeground(new java.awt.Color(255, 255, 255));
        subTitleFive.setText("Strategies to Win");
        subTitleFive.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        subTitleFive.setName(""); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        content.add(subTitleFive, gridBagConstraints);

        strategiesBody.setEditable(false);
        strategiesBody.setColumns(20);
        strategiesBody.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        strategiesBody.setForeground(new java.awt.Color(255, 255, 255));
        strategiesBody.setLineWrap(true);
        strategiesBody.setRows(5);
        strategiesBody.setTabSize(5);
        strategiesBody.setText("Control the Center:\n\t> Occupying the center of the board gives you more mobility and strategic options.\nPlan Ahead:\n\t> Think a few moves ahead to predict your opponent's strategy.\nUse Sacrifices Wisely:\n\t> Sometimes, sacrificing a piece can open up opportunities for multiple captures.\nProtect Your Pieces:\n\t> Keep your pieces in groups to reduce the risk of being captured."); // NOI18N
        strategiesBody.setWrapStyleWord(true);
        strategiesBody.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 25, 10, 10));
        strategiesBody.setFocusable(false);
        strategiesBody.setMinimumSize(new java.awt.Dimension(300, 200));
        strategiesBody.setOpaque(false);
        strategiesBody.setPreferredSize(new java.awt.Dimension(300, 200));
        strategiesBody.setRequestFocusEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        content.add(strategiesBody, gridBagConstraints);

        subTitleSix.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        subTitleSix.setForeground(new java.awt.Color(255, 255, 255));
        subTitleSix.setText("Try It in Our Game!");
        subTitleSix.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        subTitleSix.setName(""); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        content.add(subTitleSix, gridBagConstraints);

        tryBody.setEditable(false);
        tryBody.setColumns(20);
        tryBody.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tryBody.setForeground(new java.awt.Color(255, 255, 255));
        tryBody.setLineWrap(true);
        tryBody.setRows(5);
        tryBody.setTabSize(5);
        tryBody.setText("Now that you know the basics, dive into our Dama game to put your skills to the test! Select from different game modes and challenge your friends."); // NOI18N
        tryBody.setWrapStyleWord(true);
        tryBody.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 25, 10, 10));
        tryBody.setFocusable(false);
        tryBody.setMinimumSize(new java.awt.Dimension(300, 50));
        tryBody.setOpaque(false);
        tryBody.setPreferredSize(new java.awt.Dimension(300, 50));
        tryBody.setRequestFocusEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 13;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        content.add(tryBody, gridBagConstraints);

        add(content, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel content;
    // End of variables declaration//GEN-END:variables
}
