package pl.agh.expertsystems.classifier;

import junit.framework.AssertionFailedError;
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
    public void whenCriteriaMatching_ThenSuggestPine() {
        Plant result = plantClassifier.classify(
                ConePlant.create(),
                new Cone(Cone.Orientation.UPWARDS, Cone.DecayPlace.GROUND, Cone.Shape.ROUND),
                new Needle(Needle.CountInOneBase.ALWAYS_TWO)
        );
        assertEquals(PlantName.PINE, result.getPlantName());
    }

    @Test
    public void whenCriteriaMatching_ThenSuggestFir() {
        Plant result = plantClassifier.classify(
                ConePlant.create(),
                new Cone(Cone.Orientation.UPWARDS, Cone.DecayPlace.TREE, Cone.Shape.ELONGATED)
        );
        assertEquals(PlantName.FIR, result.getPlantName());
    }

    @Test
    public void whenCriteriaMatching_ThenSuggestSpruce() {
        Plant result = plantClassifier.classify(
                ConePlant.create(),
                new Cone(Cone.Orientation.DOWNWARDS, Cone.DecayPlace.GROUND, Cone.Shape.ELONGATED)
        );
        assertEquals(PlantName.SPRUCE, result.getPlantName());
    }

    @Test(expected = AssertionFailedError.class)
    public void whenCriteriaMatchPine_ThenNotSuggestSpruce() {
        Plant result = plantClassifier.classify(
                ConePlant.create(),
                new Cone(Cone.Orientation.UPWARDS, Cone.DecayPlace.GROUND, Cone.Shape.ROUND),
                new Needle(Needle.CountInOneBase.ALWAYS_TWO)
        );
        assertEquals(PlantName.SPRUCE, result.getPlantName());
    }

    @Test(expected = AssertionFailedError.class)
    public void whenCriteriaMatchYew_ThenNotSuggestFir() {
        Plant result = plantClassifier.classify(
                BerryPlant.create(),
                new Berry(Berry.Color.RED)
        );
        assertEquals(PlantName.FIR, result.getPlantName());
    }

    @Test(expected = AssertionFailedError.class)
    public void whenCriteriaMatchFir_ThenNotSuggestPine() {
        Plant result = plantClassifier.classify(
                ConePlant.create(),
                new Cone(Cone.Orientation.UPWARDS, Cone.DecayPlace.TREE, Cone.Shape.ELONGATED)
        );
        assertEquals(PlantName.PINE, result.getPlantName());
    }

    @Test
    public void whenCriteriaMatching_ThenSuggestLarch() {
        Plant result = plantClassifier.classify(
                new Needle(Needle.CountInOneBase.MORE_THAN_TWENTY),
                Cone.havingDecayPlace(Cone.DecayPlace.GROUND).withShape(Cone.Shape.ROUND),
                Fruit.create(Fruit.Type.CONE)
        );
        assertEquals(PlantName.LARCH, result.getPlantName());
    }

    @Test
    public void whenCriteriaMatching_ThenSuggestYew() {
        Plant result = plantClassifier.classify(
                new Needle(Needle.CountInOneBase.MORE_THAN_TWENTY),
                Fruit.create(Fruit.Type.BERRY),
                new Berry(Berry.Color.RED)
        );
        assertEquals(PlantName.YEW, result.getPlantName());
    }

    @Test
    public void whenCriteriaMatching_ThenSuggestJuniper() {
        Plant result = plantClassifier.classify(
                new Needle(Needle.CountInOneBase.MORE_THAN_TWENTY),
                Fruit.create(Fruit.Type.BERRY),
                new Berry(Berry.Color.BLUE)
        );
        assertEquals(PlantName.JUNIPER, result.getPlantName());
    }
}
