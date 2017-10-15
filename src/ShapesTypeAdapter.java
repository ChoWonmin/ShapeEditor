import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import shape.*;
import shape.Circle;
import shape.Rect;
import shape.Shape;

import java.io.IOException;

public class ShapesTypeAdapter extends TypeAdapter<Shape>{
    @Override
    public void write(JsonWriter jsonWriter, Shape shape) throws IOException {
        System.out.println("write");
        if (shape == null) {
            jsonWriter.nullValue();
            return;
        }

        jsonWriter.beginObject();
        jsonWriter.name("shapeType").value(shape.getClass().getName());
        jsonWriter.name("centralPoint");
        writePoint(jsonWriter, shape.getCentralPoint());
        jsonWriter.endObject();
    }

    private void writePoint(JsonWriter jsonWriter, CentralPoint point) throws IOException {
        jsonWriter.beginObject();
        jsonWriter.name("centralX").value(point.getCentralX());
        jsonWriter.name("centralY").value(point.getCentralY());
        jsonWriter.endObject();
    }

    @Override
    public Shape read(JsonReader jsonReader) throws IOException {
        jsonReader.beginObject();
        Shape shape = null;

        while (jsonReader.hasNext()) {
            switch (jsonReader.nextName()) {
                case "shapeType":
                    String type = jsonReader.nextString();
                    if (type.equals("shape.Rect"))
                        shape = new Rect();
                    else if (type.equals("shape.Circle"))
                        shape = new Circle();
                    else if (type.equals("shape.Triangle"))
                        shape = new Triangle();
                    break;
                case "centralPoint":
                    if (shape != null)
                        shape.setCentralPoint(readCentralPoint(jsonReader));
                    break;
            }
        }
        jsonReader.endObject();
        return shape;
    }
    private CentralPoint readCentralPoint(JsonReader reader) throws IOException {
        CentralPoint point = new CentralPoint();

        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();

            if (name.equals("centralX"))
                point.setCentralX(reader.nextInt());
            else if (name.equals("centralY"))
                point.setCentralY(reader.nextInt());
            else
                reader.skipValue();
        }
        reader.endObject();
        return point;
    }
}
