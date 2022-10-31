package pl.agh.expertsystems.classifier;

import org.junit.Before;
import org.junit.Test;
import pl.agh.expertsystems.classifier.model.Cone;
import pl.agh.expertsystems.classifier.model.Plant;
import pl.agh.expertsystems.classifier.model.Temp;

import static junit.framework.TestCase.assertEquals;

public class PlantClassifierTest {

    private PlantClassifier plantClassifier;

    @Before
    public void setup() {
        plantClassifier = new PlantClassifier();
    }

    @Test
    public void whenCriteriaMatching_ThenSuggestJODLA() {
        Plant result = plantClassifier.classify(
                Cone.UP(),
                new Temp(1),
                new Temp(5)
        );
        assertEquals("JODLA", result.getName());
    }
}
