package com.dama.gui._configurations.dependencies;

public enum Duration {
    BULLET {
        @Override
        public int getTime() {
            return 5 * 60 * 1000;
        }

        @Override
        public String toString() {
            return "Bullet";
        }
    },
    BLITZ {
        @Override
        public int getTime() {
            return 10 * 60 * 1000;
        }

        @Override
        public String toString() {
            return "Blitz";
        }
    },
    RAPID {
        @Override
        public int getTime() {
            return 15 * 60 * 1000;
        }

        @Override
        public String toString() {
            return "Rapid";
        }
    },
    INFINITE {
        @Override
        public int getTime() {
            return 0;
        }

        @Override
        public String toString() {
            return "Infinite";
        }
    };
    
    public boolean isInfinite() {
        return this == INFINITE;
    }
    
    public String getFormattedTime() {
        int totalSeconds = getTime() / 1000;
        int minutes = totalSeconds / 60;
        int seconds = totalSeconds % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }
    
    @Override
    public abstract String toString();
    
    public abstract int getTime();
}
