package pl.agh.expertsystems.classifier;

import org.junit.Before;
import org.junit.Test;
import pl.agh.expertsystems.classifier.model.*;

import static junit.framework.TestCase.assertEquals;

public class PlantClassifierTest {

    private PlantClassifier plantClassifier;

    @Before
    public void setup() {
        plantClassifier = new PlantClassifier();
    }

    @Test
    public void whenCriteriaMatching_ThenSuggestFIR() {
        Plant result = plantClassifier.classify(
                new Cone(Cone.Orientation.UPWARDS, Cone.DecayPlace.TREE, Cone.Shape.ELONGATED),
                new Temp(1),
                new Temp(5)
                //new Needle("SOFT")
        );
        assertEquals(PlantName.FIR, result.getPlantName());
    }

    @Test
    public void whenCriteriaMatching_ThenSuggestYEW() {
        Plant result = plantClassifier.classify(
                Fruit.create(Fruit.Type.BERRY),
                new Berry(Berry.Color.RED)
        );
        assertEquals(PlantName.YEW, result.getPlantName());
    }
}
