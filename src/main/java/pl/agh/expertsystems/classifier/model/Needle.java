package pl.agh.expertsystems.classifier.model;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class Needle implements Fact {

    public enum CountInOneBase {
        ALWAYS_TWO,
        MORE_THAN_TWENTY
    }

    CountInOneBase countInOneBase;
}
