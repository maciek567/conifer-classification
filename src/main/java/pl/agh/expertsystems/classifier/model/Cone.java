package pl.agh.expertsystems.classifier.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@AllArgsConstructor
@Builder
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
        OVAL
    }

    public enum Seed {
        WING_SHAPED,
        NUT_SHAPED
    }

    Orientation orientation;

    DecayPlace decayPlace;

    Shape shape;

    Seed seed;
}
