package shape;

import config.ShapeProConstant;
import processing.core.PApplet;

public class Circle extends Shape {

    public Circle() {
        super();
        color = ShapeProConstant.CIRCLE_COLOR;
    }

    @Override
    public void draw(PApplet pApplet) {
        ShapeColor fillColor = isCollected(pApplet.mouseX, pApplet.mouseY) ? ShapeProConstant.CIRCLE_WEAK_COLOR : color;
        pApplet.fill(fillColor.getRGB());

        pApplet.ellipse(centralPoint.getCentralX(), centralPoint.getCentralY(), 2*size, 2*size);
    }

    @Override
    public boolean isCollected(int mouseX, int mouseY) {
        boolean collected = false;
        int centralX = centralPoint.getCentralX();
        int centralY = centralPoint.getCentralY();

        double distance;
        distance = Math.sqrt((double)((centralX-mouseX)*(centralX-mouseX) +(centralY-mouseY)*(centralY-mouseY)));

        if(distance < size)
            collected = true;

        return collected;
    }

    @Override
    public Circle clone() {
        return (Circle) super.clone();
    }

}