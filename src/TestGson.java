import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
class Point{
    int x;
    int y;

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
abstract class Shape {
    Point point;

    // public Shape(int x, int y) {
// this.x = x;
// this.y = y;
// }

    public Shape() {
        this.point = new Point();
    }

    public abstract void draw();

    @Override
    public String toString() {
        return point.toString();
    }
}

class Rect extends Shape {

    @Override
    public void draw() {
        System.out.println("Rect draw");
    }
}

class Circle extends Shape {

    @Override
    public void draw() {
        System.out.println("Circle draw");
    }
}

class ShapeTypeAdapter1 extends TypeAdapter<Shape> {

    @Override
    public void write(JsonWriter writer, Shape shape) throws IOException {
        System.out.println("aaa");

        writer.beginObject();
        writer.name("type").value(shape.getClass().getName());
        writer.name("x").value(shape.point.x);
        writer.name("y").value(shape.point.y);
        writer.endObject();
    }

    @Override
    public Shape read(JsonReader reader) throws IOException {
        reader.beginObject();

        Shape s = null;
        while (reader.hasNext()) {
            switch (reader.nextName()) {
                case "type":
                    String type = reader.nextString();
                    if (type.equals("Rect"))
                        s = new Rect();
                    else if (type.equals("Circle"))
                        s = new Circle();

                    break;
                case "x":
                    if (s != null) {
                        s.point.x = reader.nextInt();
                    }
                    break;
                case "y":
                    if (s != null) {
                        s.point.y = reader.nextInt();
                    }
                    break;
            }
        }

        reader.endObject();
        return s;
    }
}
public class TestGson {
    public static void main123(String[] args) {
// Rect rect = new Rect(10, 42);

        List<Shape> shapes = new ArrayList<>();
        shapes.add(new Rect());
        shapes.add(new Circle());
        shapes.add(new Circle());
        shapes.add(new Rect());

// Gson gson = new Gson();

        Gson gson = new GsonBuilder()
                .registerTypeHierarchyAdapter(Shape.class, new ShapeTypeAdapter1())
                .create();

        String json = gson.toJson(shapes);
        System.out.println(json);

// 1. Generic(C++ Template) => Type Token(gson)
// 2.
// Generic (c++ template :: 코드생성)
// java (코드 생성하지는 않고 compile에서 확인
        Type type = new TypeToken<List<Shape>>(){}.getType();
        shapes = gson.fromJson(json, type);
        for (Shape e : shapes)
            System.out.println(e.toString());

    }
}