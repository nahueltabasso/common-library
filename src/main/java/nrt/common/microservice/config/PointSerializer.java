package nrt.common.microservice.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.locationtech.jts.geom.Point;

import java.io.IOException;

/**
 * Custon Serializer for Geo Coordinates (GPS Points)
 *
 * @author nahueltabasso 
 */
public class PointSerializer extends JsonSerializer<Point> {


    @Override
    public void serialize(Point point, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeFieldName("latitude");
        jsonGenerator.writeNumber(point.getX());
        jsonGenerator.writeFieldName("longitude");
        jsonGenerator.writeNumber(point.getY());
    }
}
