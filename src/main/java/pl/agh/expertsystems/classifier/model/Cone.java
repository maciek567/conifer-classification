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

    public static Cone oriented(Orientation orientation) { return new Cone(orientation, null, null); }

    public static Cone havingDecayPlace(DecayPlace decayPlace) { return new Cone(null, decayPlace, null); }

    public static Cone havingShape(Shape shape) { return new Cone(null, null, shape); }

    public Cone withShape(Shape shape) { return new Cone(getOrientation(), getDecayPlace(), shape); }
}
