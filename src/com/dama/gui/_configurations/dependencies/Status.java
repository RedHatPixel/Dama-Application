package com.dama.gui._configurations.dependencies;
import com.dama.gui.gamePanel.PlayerPanel;

public enum Status {
    STALEMATE {
        @Override
        public PlayerPanel getWinner(final PlayerPanel topPlayerPanel, final PlayerPanel bottomPlayerPanel) {
            return null;
        }
    },
    BLACK_PLAYER_WIN {
        @Override
        public PlayerPanel getWinner(final PlayerPanel topPlayerPanel, final PlayerPanel bottomPlayerPanel) {
            return topPlayerPanel.getPlayerInfo().getPlayer().getAlliance().isBlack() ?
                    topPlayerPanel : bottomPlayerPanel;
        }
    },
    WHITE_PLAYER_WIN {
        @Override
        public PlayerPanel getWinner(final PlayerPanel topPlayerPanel, final PlayerPanel bottomPlayerPanel) {
            return topPlayerPanel.getPlayerInfo().getPlayer().getAlliance().isWhite() ?
                    topPlayerPanel : bottomPlayerPanel;
        }
    };
    
    public boolean isStalemate() {
        return this == STALEMATE;
    }

    public abstract PlayerPanel getWinner(final PlayerPanel topPlayerPanel, final PlayerPanel bottomPlayerPanel);
}
