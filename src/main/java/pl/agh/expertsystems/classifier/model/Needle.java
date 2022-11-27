package pl.agh.expertsystems.classifier.model;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class Needle implements Fact {

    public enum CountInOneBase {
        ALWAYS_TWO,
        ALWAYS_FIVE,
        MORE_THAN_TWENTY
    }

    public enum Scent {
        LEMON,
        OTHER
    }

    CountInOneBase countInOneBase;
    Scent scent;

    public static Needle withCountInOneBase(CountInOneBase countInOneBase) {
        return new Needle(countInOneBase, null);
    }

    public static Needle withScent(Scent scent) {
        return new Needle(null, scent);
    }
}
