package pl.agh.expertsystems.classifier.model;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor(staticName = "create")
public class Fruit implements Fact {

    public enum Type {
        BERRY,
        CONE
    }

    Type type;
}
