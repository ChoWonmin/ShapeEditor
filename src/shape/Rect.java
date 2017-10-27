package shape;

import config.ShapeProConstant;
import processing.core.PApplet;

public class Rect extends Shape{

    public Rect() {
        super();
        color = ShapeProConstant.RECT_COLOR;
    }

    @Override
    public void draw(PApplet pApplet) {
        ShapeColor fillColor = isCollected(pApplet.mouseX, pApplet.mouseY) ? ShapeProConstant.RECT_WEAK_COLOR : color;
        pApplet.fill(fillColor.getRGB());

        pApplet.rect(centralPoint.getCentralX()-size, centralPoint.getCentralY()-size, 2*size, 2*size);
    }

    @Override
    public boolean isCollected(int mouseX, int mouseY) {
        boolean collected = false;
        int centralX = centralPoint.getCentralX();
        int centralY = centralPoint.getCentralY();

        if(centralPoint.getFloorX(size)<=mouseX && mouseX<=centralPoint.getCeilX(size)
                && centralPoint.getFloorY(size)<=mouseY && mouseY<=centralPoint.getCeilY(size))
            collected = true;

        return collected;
    }

    @Override
    public Rect clone() {
        return (Rect) super.clone();
    }
}