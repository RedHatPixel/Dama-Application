package com.dama.gui._configurations.dependencies;

public enum Orientation {
    RANDOM {
        @Override
        public Orientation getOpposite() {
            return null;
        }
    },
    FLIPPED {
        @Override
        public Orientation getOpposite() {
            return NORMAL;
        }
    },
    NORMAL {
        @Override
        public Orientation getOpposite() {
            return FLIPPED;
        }
    };
    
    public boolean isNormal() {
        return this == NORMAL;
    }
    
    public boolean isRandom() {
        return this == RANDOM;
    }
    
    public abstract Orientation getOpposite();
}
