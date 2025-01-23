package com.dama.gui._configurations.dependencies;

public enum Duration {
    BULLET {
        @Override
        public int getTime() {
            return 5 * 60 * 1000;
        }
    },
    BLITZ {
        @Override
        public int getTime() {
            return 10 * 60 * 1000;
        }
    },
    RAPID {
        @Override
        public int getTime() {
            return 15 * 60 * 1000;
        }
    },
    INFINITE {
        @Override
        public int getTime() {
            return 0;
        }
    };

    public boolean isInfinite() {
        return this == INFINITE;
    }
    
    public abstract int getTime();
}
