package Domain.Collision;

import Domain.Statistics.GameConfiguration;
import Domain.Utils.Position;

import java.util.ArrayList;

public class Collider {
    private int locationX;
    private int locationY;
    private double length;
    private double width;
    private int angle;
    private double midPointX;
    private double midPointY;
    private ArrayList<Position> cornerList;
    boolean isBlocker;
    static int L=GameConfiguration.getInstance().getData().getL();
    //This class is created for simulating the window of GameObjects.
    //will be updated, some problems occurs because of the double-int casting
    public Collider(Position p, double width, double height, int angle, boolean isBlocker){
        this.locationX = (int) p.getX();
        this.locationY = (int) p.getY();
        this.length = height;
        this.width = width;
        this.angle = angle;
        midPointX= locationX + width/2;
        midPointY = locationY + height/2;
        this.isBlocker = isBlocker;
        setCorners(p);

    }
    private void setCorners(Position p){
            if (isBlocker){ ;
                if(!isGround()){
                    cornerList = new ArrayList<Position>();
                    cornerList.add(new Position(midPointX-0.5*L, midPointY-0.5*L));
                    cornerList.add(new Position(midPointX+0.5*L, midPointY-0.5*L));
                    cornerList.add(new Position(midPointX-0.5*L, midPointY+0.5*L));
                    cornerList.add(new Position(midPointX+0.5*L, midPointY+0.5*L));
                }else {
                    cornerList = new ArrayList<Position>();
                    cornerList.add(new Position(midPointX - 2*L, midPointY - 2 * L));
                    cornerList.add(new Position(midPointX + 2*L, midPointY - 2*L));
                    cornerList.add(new Position(midPointX - 2*L, midPointY + 2*L));
                    cornerList.add(new Position(midPointX + 2*L, midPointY +2 *L));
                }
            }else {
                cornerList = new ArrayList<Position>();
                cornerList.add(p);
                cornerList.add(new Position(locationX + width, locationY));
                cornerList.add(new Position(locationX, locationY + length));
                cornerList.add(new Position(locationX + width, locationY + length));
            }

    }

    public ArrayList<Position> getCornerList() {
        return cornerList;
    }

    public boolean intersects(Collider second){

      for (Position p: cornerList){
          if(second.isIn(p)) return true;
        }

        for (Position p: second.getCornerList()){
            if(this.isIn(p)) return true;
        }

        return false;
    }

    public int getLocationX() {
        return locationX;
    }

    public void setLocationX(int locationX) {
        this.locationX = locationX;
    }

    public int getLocationY() {
        return locationY;
    }

    public void setLocationY(int locationY) {
        this.locationY = locationY;
    }

    public double getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getAngle() {
        return angle;
    }

    public void setAngle(int angle) {
        this.angle = angle;
    }
    public boolean isGround(){
        return length+locationY>GameConfiguration.getInstance().getData().getGameScreenHeight()-150;
    }

    public Position getPosition(){
        return new Position(getLocationX(), getLocationY());
    }

    public boolean isIn(Position p){
        if (isBlocker){
                if (!isGround()){
                   return p.getX() >= midPointX-0.5*L && p.getX() <= midPointX+0.5*L &&
                            p.getY() >= midPointY-0.5*L && p.getY() <= midPointY + 0.5*L;
                }
                return p.getX() >= midPointX-2*L && p.getX() <= midPointX+2*L &&
                        p.getY() >= midPointY-2*L && p.getY() <= midPointY + 2*L;

        }
        return p.getX() >= this.locationX && p.getX() <= this.locationX + width &&
                p.getY() >= this.locationY && p.getY() <= this.locationY + length;
    }
}