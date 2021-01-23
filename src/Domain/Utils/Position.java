package Domain.Utils;

public class Position {
    private double x;
    private double y;
    private int rotation=0;

    public Position(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Position(){}

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getRotation() {
        return rotation;
    }

    public void setRotation(int rotation) {
        this.rotation = rotation;
    }
}
