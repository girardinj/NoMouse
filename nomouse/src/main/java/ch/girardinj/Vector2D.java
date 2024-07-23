package ch.girardinj;

public class Vector2D {
    private double x;
    private double y;

    Vector2D (double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector2D normalize() {
        if (this.x == 0 && this.y == 0)
            return this;
        
        double factor = getLength();
        
        double x = this.x / factor;
        double y = this.y / factor;

        return new Vector2D(x, y);
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public double getLength() {
        return Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2));
    }

    public Vector2D add (double x, double y) {
        this.x += x;
        this.y += y;
        return this;
    }

    public Vector2D add (Vector2D vector) {
        this.x += vector.getX();
        this.y += vector.getY();
        return this;
    }

    public Vector2D mult(double value) {
        this.x *= value;
        this.y *= value;
        return this;
    }


    @Override
    public String toString() {
        return "x: " + this.x + " y: " + this.y;
    }
}
