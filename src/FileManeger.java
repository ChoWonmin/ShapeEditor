import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import config.ShapeProConstant;
import shape.Shape;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class FileManeger {
    private Gson gson;
    private Type shapesType;

    public FileManeger(List<Shape> shapes) {
        Type shapesType = new TypeToken<List<Shape>>(){}.getType();
        gson = new GsonBuilder()
                        .registerTypeHierarchyAdapter(Shape.class,new ShapesTypeAdapter()).create();
    }

    public void save(List<Shape> shapes){
        try(Writer writer = new OutputStreamWriter(new FileOutputStream(ShapeProConstant.PATH))){
            String json = gson.toJson(shapes);
            System.out.println(json);
            writer.write(json);
            writer.close();
        } catch (IOException e){
            e.printStackTrace();
        }

    }

    public ArrayList<Shape> open(){
        ArrayList<Shape> shapes = new ArrayList<Shape>();
        try(Reader reader = new InputStreamReader(new FileInputStream(ShapeProConstant.PATH))){

            Type type = new TypeToken<ArrayList<Shape>>(){}.getType();
            shapes = gson.fromJson(reader, type);
            System.out.println(shapes);
        }catch (Exception e) {
            e.printStackTrace();
        }

        return shapes;
    }




}