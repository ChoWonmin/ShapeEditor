package shape;

import config.ShapeProConstant;
import processing.core.PApplet;

import java.awt.Color;
import java.io.Serializable;

public abstract class Shape implements Cloneable, Serializable{

    protected CentralPoint centralPoint;
    protected int size;
    protected ShapeColor color;

    public Shape() {
        centralPoint = new CentralPoint();
        size = ShapeProConstant.DEFAULT_SHAPE_SIZE;
    }
    public abstract void draw(PApplet pApplet);
    public abstract boolean isCollected(int mouseX, int mouseY);

    @Override
    public Shape clone(){
        try {
            return (Shape)super.clone();
        }catch (CloneNotSupportedException e){
            e.printStackTrace();
            return null;
        }
    }

    public void changeColor(ShapeColor color) {
        this.color = color;
    }

    public int getCentralX() {
        return centralPoint.getCentralX();
    }
    public int getCentralY() {
        return centralPoint.getCentralY();
    }

    public CentralPoint getCentralPoint() {
        return centralPoint;
    }

    public void setCentralPoint(int centralX, int centralY){
        centralPoint.setCentralX(centralX);
        centralPoint.setCentralY(centralY);
    }
    public void setCentralPoint(CentralPoint centralPoint) {
        this.centralPoint = centralPoint;
    }

}