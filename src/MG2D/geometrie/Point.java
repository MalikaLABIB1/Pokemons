package MG2D.geometrie;

public class Point {
    private int x, y;
    
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    // Getters
    public int getX() { return x; }
    public int getY() { return y; }
    
    // Setters
    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }

    // Properly implemented translation method
    public void translater(int dx, int dy) {
        this.x += dx;
        this.y += dy;
    }
    
    // Optional: Add toString() for debugging
    @Override
    public String toString() {
        return "Point(" + x + ", " + y + ")";
    }
}