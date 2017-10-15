package shape;

import config.ShapeProConstant;
import processing.core.PApplet;
import processing.event.MouseEvent;

import java.awt.Color;

public class Triangle extends Shape {

    public Triangle() {
        color = ShapeProConstant.TRIANGLE_COLOR;
    }

    @Override
    public void draw(PApplet pApplet) {
        int centralX = centralPoint.getCentralX();
        int centralY = centralPoint.getCentralY();
        int[] posX = new int[3]; int[] posY = new int[3];

        posX[0] = centralX-size; posY[0] = centralY-size;
        posX[1] = centralX;       posY[1] = centralY+size;
        posX[2] = centralX+size; posY[2] = centralY-size;

        ShapeColor fillColor = isCollected(pApplet.mouseX, pApplet.mouseY) ? ShapeProConstant.TRIANGLE_WEAK_COLOR : color;
        pApplet.fill(fillColor.getRGB());

        pApplet.triangle( posX[0], posY[0], posX[1],  posY[1], posX[2], posY[2]);
    }

    @Override
    public boolean isCollected (int mouseX, int mouseY) {
        boolean collected = false;
        int centralX = centralPoint.getCentralX();
        int centralY = centralPoint.getCentralY();

        boolean[] check = new boolean[3];

        int[] tempX = new int[2]; int[] tempY = new int[2];

        tempX[0] = centralX-size; tempY[0] = centralY-size;
        tempX[1] = centralX+size; tempY[1] = centralY+size;

        check[0] = (mouseX - centralX) * (-2*size) - (-size) * (mouseY - tempY[1]) < 0.0;
        check[1] = (mouseX - tempX[1]) * (2*size) - (-size) * (mouseY - tempY[0]) < 0.0;
        check[2] = (mouseX - tempX[0]) * (0) - (2*size) * (mouseY - tempY[0]) < 0.0;

        if((check[0] == check[1]) && (check[1] == check[2]))
            collected = true;

        return collected;
    }

    @Override
    public Triangle clone() {
        return (Triangle) super.clone();
    }
}