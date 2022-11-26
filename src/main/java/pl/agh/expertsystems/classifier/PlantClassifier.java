package pl.agh.expertsystems.classifier;

import lombok.extern.slf4j.Slf4j;
import pl.agh.expertsystems.classifier.model.Berry;
import pl.agh.expertsystems.classifier.model.Fact;
import pl.agh.expertsystems.classifier.model.Plant;
import sun.rmi.runtime.Log;

import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

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

    private static Map<String, String> initializeHints() {
        Map<String, String> hints = new HashMap<>();
        hints.put("BerryPlant()", hint(Berry.Color.class, Berry.Color.values()));
        return hints;
    }

    private static String hint(Class c, Object[] values) {
        String type = c.getCanonicalName().replaceFirst("pl.agh.expertsystems.classifier.model.", "");
        return "Hint: " + Arrays.stream(values).map(v -> type + "." + v).map(String::toLowerCase).collect(Collectors.joining(" or "));
    }
}
