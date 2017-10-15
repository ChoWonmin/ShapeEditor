import config.ShapeProConstant;
import processing.core.PApplet;
import processing.event.MouseEvent;
import shape.Circle;
import shape.Rect;
import shape.Shape;
import shape.Triangle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Program extends PApplet implements Serializable{

    private List<Shape> shapes;
    private HashMap<String,Button> buttons;
    private String[] buttonKeyList;

    public void settings(){
        shapes = new ArrayList<Shape>();
        buttons = new HashMap<String,Button>();
        buttonKeyList = ShapeProConstant.BUTTON_KEY_LIST;
        this.size(ShapeProConstant.PROGRAM_WIDTH, ShapeProConstant.PROGRAM_HEIGHT);
    }

    public void setup(){
        for(int i=0;i<buttonKeyList.length;i++) {
            Shape buttonShape = new Rect();
            buttonShape.setCentralPoint(ShapeProConstant.BUTTON_CERNTRAL_X * (2 * i + 1),ShapeProConstant.BUTTON_CERNTRAL_y);
            buttons.put(buttonKeyList[i], new Button(buttonShape,buttonKeyList[i]));
        }
    }

    @Override
    public void mouseDragged(MouseEvent event) {
        Shape dragShape = null;
        for(int i=shapes.size()-1; i>=0; i--) {
            if (shapes.get(i).isCollected(event.getX(), event.getY())) {
                dragShape = shapes.get(i);
                break;
            }
        }

        if(dragShape!=null)
            dragShape.setCentralPoint(event.getX(), event.getY());

    }

    @Override
    public void mouseClicked(MouseEvent event) {
        int programCentralX = ShapeProConstant.PROGRAM_WIDTH/2;
        int programCentralY = ShapeProConstant.PROGRAM_HEIGHT/2;
        FileManeger fileManeger = new FileManeger(shapes);

        Shape newShape = null;

        if(buttons.get(buttonKeyList[0]).isClicked(event.getX(), event.getY()))
            newShape = new Rect();
        else if(buttons.get(buttonKeyList[1]).isClicked(event.getX(), event.getY()))
            newShape = new Triangle();
        else if(buttons.get(buttonKeyList[2]).isClicked(event.getX(), event.getY()))
            newShape = new Circle();
        else if(buttons.get(buttonKeyList[3]).isClicked(event.getX(), event.getY()))
            shapes = new ArrayList<Shape>();
        else if(buttons.get(buttonKeyList[4]).isClicked(event.getX(), event.getY())) {
            try{
                fileManeger.save(shapes);
            }catch (Exception e){
                e.printStackTrace();
            }
        }else if(buttons.get(buttonKeyList[5]).isClicked(event.getX(), event.getY())) {
            try{
                shapes = fileManeger.open();
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        if(newShape!=null){
            newShape.setCentralPoint(programCentralX, programCentralY);
            shapes.add(newShape);
        }
    }

    public void draw(){
        this.background(ShapeProConstant.BACKGROUND_COLOR.getRGB());

        for (int i = 0; i < buttonKeyList.length; i++)
            buttons.get(buttonKeyList[i]).draw(this);

        for(Shape shape:shapes)
            if(shape!=null)
                shape.draw(this);
    }

    public static void main(String[] args) {
        Program.main("Program");
    }

}