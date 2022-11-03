package pl.agh.expertsystems.classifier.model;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class Berry implements Fact {

    public enum Color {
        BLUE,
        RED
    }

    Color color;
}
