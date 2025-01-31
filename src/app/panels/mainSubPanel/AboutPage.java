package app.panels.mainSubPanel;

import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import utilities.FontManager;

public class AboutPage extends javax.swing.JPanel {

    public AboutPage() {
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

        setMinimumSize(new java.awt.Dimension(600, 600));
        setName(""); // NOI18N
        setPreferredSize(new java.awt.Dimension(600, 600));
        setLayout(new java.awt.BorderLayout());

        content.setBackground(new java.awt.Color(48, 46, 43));
        content.setMinimumSize(new java.awt.Dimension(600, 500));
        content.setPreferredSize(new java.awt.Dimension(600, 500));
        content.setLayout(new java.awt.GridBagLayout());

        titleOne.setFont(new java.awt.Font("Segoe UI Black", 1, 24)); // NOI18N
        titleOne.setForeground(new java.awt.Color(255, 255, 255));
        titleOne.setText("ABOUT DAMA");
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
        introduction.setText("\tWelcome to Dama, a modern twist on the traditional Filipino board game! Dama is a strategy game played on an 8x8 board, where players aim to outsmart their opponent by capturing all their pieces or blocking their moves.\n\n\tOur digital version of Dama brings the fun and challenge of the classic game to your fingertips, featuring:\n\nTwo Game Modes: \n\t> Play regular Dama, combining analytical thinking and strategy.\n\nInteractive Gameplay: \n\t> Intuitive controls and dynamic visuals for a seamless experience.\n\nTraditional Rules: \n\t> Stay true to the game with familiar rules and mechanics.\n\nWhether you're a seasoned player or new to the game, Dama is a great way to sharpen your mind and enjoy hours of fun. Dive in and let the battle of wits begin!"); // NOI18N
        introduction.setWrapStyleWord(true);
        introduction.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 25, 10, 10));
        introduction.setFocusable(false);
        introduction.setMinimumSize(new java.awt.Dimension(300, 500));
        introduction.setName(""); // NOI18N
        introduction.setOpaque(false);
        introduction.setPreferredSize(new java.awt.Dimension(300, 500));
        introduction.setRequestFocusEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        content.add(introduction, gridBagConstraints);

        add(content, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel content;
    // End of variables declaration//GEN-END:variables
}
