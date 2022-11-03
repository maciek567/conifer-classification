package pl.agh.expertsystems.classifier.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class Cone implements Fact {
    public enum Orientation {
        UPWARDS,
        DOWNWARDS
    }

    public enum DecayPlace {
        TREE,
        GROUND
    }

    public enum Shape {
        ELONGATED,
        ROUND
    }

    Orientation orientation;

    DecayPlace decayPlace;

    Shape shape;
}
