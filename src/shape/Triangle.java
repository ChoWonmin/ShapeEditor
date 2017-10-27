package shape;

import config.ShapeProConstant;
import processing.core.PApplet;

public class Triangle extends Shape {

    public Triangle() {
        color = ShapeProConstant.TRIANGLE_COLOR;
    }

    @Override
    public void draw(PApplet pApplet) {
        int[] posX = new int[3]; int[] posY = new int[3];

        posX[0] = centralPoint.getFloorX(size);
        posY[0] = centralPoint.getFloorY(size);
        posX[1] = centralPoint.getCentralX();
        posY[1] = centralPoint.getCeilY(size);
        posX[2] = centralPoint.getCeilX(size);
        posY[2] = centralPoint.getFloorY(size);

        ShapeColor fillColor = isCollected(pApplet.mouseX, pApplet.mouseY) ? ShapeProConstant.TRIANGLE_WEAK_COLOR : color;
        pApplet.fill(fillColor.getRGB());

        pApplet.triangle( posX[0], posY[0], posX[1],  posY[1], posX[2], posY[2]);
    }

    @Override
    public boolean isCollected (int mouseX, int mouseY) {
        boolean collected = false;

        boolean[] check = new boolean[3];

        int[] tempX = new int[2]; int[] tempY = new int[2];

        tempX[0] = centralPoint.getFloorX(size);
        tempY[0] = centralPoint.getFloorY(size);
        tempX[1] = centralPoint.getCeilX(size);
        tempY[1] = centralPoint.getCeilY(size);

        check[0] = (mouseX - centralPoint.getCentralX()) * (-2*size) - (-size) * (mouseY - tempY[1]) < 0.0;
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