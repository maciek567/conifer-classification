package pl.agh.expertsystems.classifier.model;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class ConeSize implements Fact {
    public enum Size {
        SMALL,
        BIG
    }

    Size size;
}
