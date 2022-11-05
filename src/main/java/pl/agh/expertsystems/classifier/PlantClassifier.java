package pl.agh.expertsystems.classifier;

import pl.agh.expertsystems.classifier.model.Fact;
import pl.agh.expertsystems.classifier.model.Plant;

import java.util.Arrays;

public class PlantClassifier extends AnyClassifier<Plant> {

    @Override
    public Plant classify(Fact... facts) {
        Plant resultPlant = Plant.empty();
        kieSession.setGlobal("resultPlant", resultPlant);
        Arrays.stream(facts).forEach(kieSession::insert);
        System.out.println(kieSession.getFactCount());
        kieSession.fireAllRules();
        System.out.println(kieSession.getFactCount());
        System.out.println(resultPlant.getPlantName());

        return resultPlant;
    }
}
