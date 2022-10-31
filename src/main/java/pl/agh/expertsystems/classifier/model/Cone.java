package pl.agh.expertsystems.classifier.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Cone implements Fact {
    String orientation;

    public static Cone up() {
        return new Cone("up");
    }
}
