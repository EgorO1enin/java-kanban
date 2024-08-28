package adapters;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.time.Duration;

public class DurationTestAdapter extends TypeAdapter<Duration> {
    @Override
    public void write(JsonWriter out, Duration duration) throws IOException {
        if (duration != null) {
            out.value(duration.toString()); // Сериализация в строку
        } else {
            out.nullValue();
        }
    }

    @Override
    public Duration read(JsonReader in) throws IOException {
        String durationString = in.nextString();
        return Duration.parse(durationString); // Десериализация из строки
    }
}