package com.dama.engine.dependencies;
import java.util.Objects;

public class Position {
    
    // Define Variables
    private int x;
    private int y;
    
    /**
     * Constructor: Create to handle coordinates or positioning
     * @param x Integers
     * @param y Integers
     */
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    /**
     * Change into new position x, y
     * @param x Integers
     * @param y Integers
     */
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    /**
     * Get the x position
     * @return Integers
     */
    public int x() {
        return this.x;
    }
    
    /**
     * Get the y position
     * @return Integers
     */
    public int y() {
        return this.y;
    }
    
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Position)) return false;
        
        Position other = (Position) obj;
        return this.x == other.x && this.y == other.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.x, this.y);
    }

    
    @Override
    public String toString() {
        return "Position: [x=" + x + "], [y=" + y +"]";
    }
}
