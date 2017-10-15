import config.ShapeProConstant;
import processing.core.PApplet;
import shape.Shape;

import java.awt.*;
import java.io.Serializable;

public class Button implements Serializable {

    private Shape shape;
    private String name;

    public Button(Shape shape, String name) {
        this.shape = shape;
        this.name = name;
    }

    public void draw(PApplet pApplet){
        shape.changeColor(ShapeProConstant.BUTTON_COLOR);
        shape.draw(pApplet);
        pApplet.fill(ShapeProConstant.BUTTON_TEXT_COLOR.getRGB());
        pApplet.textSize(15);
        pApplet.text(name, shape.getCentralX()- ShapeProConstant.DEFAULT_SHAPE_SIZE/2, shape.getCentralY());
    }

    public boolean isClicked(int mouseX, int mouseY){
        boolean clicked = false;

        if(shape.isCollected(mouseX, mouseY))
            clicked = true;
        return clicked;
    }

}
