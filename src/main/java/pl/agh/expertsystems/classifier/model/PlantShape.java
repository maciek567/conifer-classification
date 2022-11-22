package pl.agh.expertsystems.classifier.model;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class PlantShape implements Fact {

    public enum Shape {
        TREE,
        BUSH
    }

    Shape shape;
}
