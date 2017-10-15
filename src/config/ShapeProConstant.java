package config;

import shape.ShapeColor;

import java.awt.*;

public interface ShapeProConstant {
    public static final int PROGRAM_WIDTH = 800;
    public static final int PROGRAM_HEIGHT = 600;
    public static final int DEFAULT_SHAPE_SIZE = 50;

    public static final int BUTTON_CERNTRAL_X = 50;
    public static final int BUTTON_CERNTRAL_y = 30;

    public static final String[] BUTTON_KEY_LIST = { "Rect", "Triangle", "Circle",
                                                        "Reset", "Save", "Open" };

    public static final String PATH = "./src/file/shapes.json";

    public static final ShapeColor RECT_COLOR = new ShapeColor(244, 67, 54);
    public static final ShapeColor TRIANGLE_COLOR = new ShapeColor(126, 87, 194);
    public static final ShapeColor CIRCLE_COLOR = new ShapeColor(0, 121, 107);

    public static final ShapeColor RECT_WEAK_COLOR = new ShapeColor(239, 154, 154);
    public static final ShapeColor TRIANGLE_WEAK_COLOR = new ShapeColor(179, 157, 219);
    public static final ShapeColor CIRCLE_WEAK_COLOR = new ShapeColor(128, 203, 196);

    public static final ShapeColor BUTTON_COLOR = new ShapeColor(189, 189, 189);
    public static final ShapeColor BUTTON_TEXT_COLOR = new ShapeColor(33, 33, 33);

    public static final ShapeColor BACKGROUND_COLOR = new ShapeColor(240, 240, 240);
}
