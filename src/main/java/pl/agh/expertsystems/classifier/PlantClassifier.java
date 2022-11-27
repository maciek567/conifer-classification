package pl.agh.expertsystems.classifier;

import pl.agh.expertsystems.classifier.model.*;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static pl.agh.expertsystems.classifier.model.Cone.Shape;
import static pl.agh.expertsystems.classifier.model.Needle.CountInOneBase;
import static pl.agh.expertsystems.classifier.model.Needle.Scent;

public class PlantClassifier extends AnyClassifier<Plant> {

    private final Plant resultPlant;
    private final Map<String, String> hints;

    public PlantClassifier() {
        resultPlant = Plant.empty();
        kieSession.setGlobal("resultPlant", resultPlant);

        hints = initializeHints();
    }

    @Override
    public Plant classify(Fact... facts) {
        Arrays.stream(facts).forEach(kieSession::insert);
        kieSession.fireAllRules();

        return resultPlant;
    }

    public ClassificationResult insertNew(Fact fact) {
        kieSession.insert(fact);

        Set<?> factObjectsBefore = new HashSet<>(kieSession.getObjects());

        kieSession.fireAllRules();

        if (Objects.nonNull(resultPlant.getPlantName())) {
            return new ClassificationResult(Optional.of(resultPlant), Collections.emptyList());
        }

        Set<?> difference = new HashSet<>(kieSession.getObjects());
        difference.removeAll(factObjectsBefore);

        return new ClassificationResult(
                Optional.empty(),
                difference.stream()
                        //.peek(System.out::print)
                        .map(d -> hints.get(d.toString()))
                        .filter(Objects::nonNull)
                        .collect(Collectors.toList())
        );
    }

    public String getStartHint() {
        return "Hint: - fruit.type.berry or fruit.type.cone";
    }

    private static Map<String, String> initializeHints() {
        Map<String, String> hints = new HashMap<>();
        hints.put("BerryPlant()", hint(Collections.singletonMap(Berry.Color.class, Berry.Color.values())));
        hints.put("ConePlant()", hint(Stream.of(new Object[][]{
                {Cone.Shape.class, Shape.values()}, {Scent.class, new Needle.Scent[]{Scent.LEMON}},
                {Needle.CountInOneBase.class, new CountInOneBase[]{CountInOneBase.ALWAYS_FIVE}}
        }).collect(Collectors.toMap(data -> (Class) data[0], data -> (Object[]) data[1]))));
        hints.put("FirHint()", hint(Collections.singletonMap(Cone.Orientation.class, Cone.Orientation.values())));
        hints.put("SpruceHint()", hint(Collections.singletonMap(Cone.DecayPlace.class, Cone.DecayPlace.values())));
        hints.put("LarchHint()", hint(Collections.singletonMap(Needle.CountInOneBase.class,
                new CountInOneBase[]{CountInOneBase.ALWAYS_TWO, CountInOneBase.MORE_THAN_TWENTY})));
        hints.put("TwoNeedlePine()", hint(Stream.of(new Object[][]{
                {ConeSize.class, ConeSize.Size.values()}, {PlantShape.Shape.class, new PlantShape.Shape[]{PlantShape.Shape.BUSH}}
        }).collect(Collectors.toMap(data -> (Class) data[0], data -> (Object[]) data[1]))));
        hints.put("FiveNeedlePine()", hint(Collections.singletonMap(Cone.Seed.class, Cone.Seed.values())));
        return hints;
    }

    private static String hint(Map<Class, Object[]> hints) {
        return hints.entrySet().stream()
                .map(entry -> {
                    String type = entry.getKey().getCanonicalName().replaceFirst("pl.agh.expertsystems.classifier.model.", "");
                    return Arrays.stream(entry.getValue()).map(v -> type + "." + v).map(String::toLowerCase).collect(Collectors.joining(" or "));
                }).reduce("Hint: ", (hint1, hint2) -> hint1 + " - " + hint2);
    }
}
