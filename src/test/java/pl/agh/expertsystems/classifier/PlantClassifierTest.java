package pl.agh.expertsystems.classifier;

import junit.framework.AssertionFailedError;
import org.junit.Before;
import org.junit.Test;
import pl.agh.expertsystems.classifier.model.*;

import static junit.framework.TestCase.assertEquals;
import static pl.agh.expertsystems.classifier.model.Cone.*;
import static pl.agh.expertsystems.classifier.model.Needle.CountInOneBase;

public class PlantClassifierTest {

    private PlantClassifier plantClassifier;

    @Before
    public void setup() {
        plantClassifier = new PlantClassifier();
    }

    @Test
    public void whenCriteriaMatching_ThenSuggestScotsPine() {
        Plant result = plantClassifier.classify(
                ConePlant.create(),
                new Cone(Orientation.UPWARDS, DecayPlace.GROUND, Shape.OVAL, Seed.WING_SHAPED, Size.SMALL),
                new Needle(CountInOneBase.ALWAYS_TWO, false)
        );
        assertEquals(PlantName.SCOTS_PINE, result.getPlantName());
    }

    @Test
    public void whenCriteriaMatching_ThenSuggestFir() {
        Plant result = plantClassifier.classify(
                ConePlant.create(),
                new Cone(Orientation.UPWARDS, DecayPlace.TREE, Shape.ELONGATED, null, Size.BIG)
        );
        assertEquals(PlantName.FIR, result.getPlantName());
    }

    @Test
    public void whenCriteriaMatching_ThenSuggestSpruce() {
        Plant result = plantClassifier.classify(
                ConePlant.create(),
                new Cone(Orientation.DOWNWARDS, DecayPlace.GROUND, Shape.ELONGATED, null, Size.BIG)
        );
        assertEquals(PlantName.SPRUCE, result.getPlantName());
    }

    @Test(expected = AssertionFailedError.class)
    public void whenCriteriaMatchScotsPine_ThenNotSuggestSpruce() {
        Plant result = plantClassifier.classify(
                ConePlant.create(),
                new Cone(Orientation.UPWARDS, DecayPlace.GROUND, Shape.OVAL, null, Size.SMALL),
                new Needle(CountInOneBase.ALWAYS_TWO, false)
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
    public void whenCriteriaMatchFir_ThenNotSuggestScotsPine() {
        Plant result = plantClassifier.classify(
                ConePlant.create(),
                new Cone(Orientation.UPWARDS, DecayPlace.TREE, Shape.ELONGATED, null, Size.SMALL)
        );
        assertEquals(PlantName.SCOTS_PINE, result.getPlantName());
    }

    @Test
    public void whenCriteriaMatching_ThenSuggestLarch() {
        Plant result = plantClassifier.classify(
                Fruit.create(Fruit.Type.CONE),
                Needle.withCountInOneBase(CountInOneBase.MORE_THAN_TWENTY),
                Cone.builder()
                        .decayPlace(DecayPlace.GROUND)
                        .shape(Shape.OVAL)
                        .size(Size.SMALL)
                        .build());
        assertEquals(PlantName.LARCH, result.getPlantName());
    }

    @Test
    public void whenCriteriaMatching_ThenSuggestYew() {
        Plant result = plantClassifier.classify(
                new Needle(CountInOneBase.MORE_THAN_TWENTY, false),
                Fruit.create(Fruit.Type.BERRY),
                new Berry(Berry.Color.RED)
        );
        assertEquals(PlantName.YEW, result.getPlantName());
    }

    @Test
    public void whenCriteriaMatching_ThenSuggestJuniper() {
        Plant result = plantClassifier.classify(
                Fruit.create(Fruit.Type.BERRY),
                new Berry(Berry.Color.BLUE)
        );
        assertEquals(PlantName.JUNIPER, result.getPlantName());
    }

    @Test
    public void whenCriteriaMatchingPseudotsuga_ThenSuggestPseudotsuga() {
        Plant result = plantClassifier.classify(
                ConePlant.create(),
                Needle.withScent(true)
        );
        assertEquals(PlantName.PSEUDOTSUGA, result.getPlantName());
    }

    @Test
    public void whenCriteriaMatchingBlackPine_ThenSuggestBlackPine() {
        Plant result = plantClassifier.classify(
                ConePlant.create(),
                Cone.builder()
                        .shape(Shape.OVAL)
                        .size(Size.BIG)
                        .build(),
                Needle.withCountInOneBase(CountInOneBase.ALWAYS_TWO)
        );
        assertEquals(PlantName.BLACK_PINE, result.getPlantName());
    }

    @Test
    public void whenCriteriaMatchingPinusMugo_ThenSuggestPinusMugo() {
        Plant result = plantClassifier.classify(
                ConePlant.create(),
                Cone.builder()
                        .shape(Shape.OVAL)
                        .size(Size.SMALL)
                        .build(),
                Needle.withCountInOneBase(CountInOneBase.ALWAYS_TWO),
                new PlantShape(PlantShape.Shape.BUSH)
        );
        assertEquals(PlantName.PINUS_MUGO, result.getPlantName());
    }

    @Test
    public void whenCriteriaMatchingPinusCembra_ThenSuggestPinusCembra() {
        Plant result = plantClassifier.classify(
                ConePlant.create(),
                Needle.withCountInOneBase(CountInOneBase.ALWAYS_FIVE),
                Cone.builder()
                        .seed(Seed.NUT_SHAPED)
                        .size(Size.SMALL)
                        .build()
        );
        assertEquals(PlantName.PINUS_CEMBRA, result.getPlantName());
    }

    @Test
    public void whenCriteriaMatchingPinusStrobus_ThenSuggestPinusStrobus() {
        Plant result = plantClassifier.classify(
                ConePlant.create(),
                Needle.withCountInOneBase(CountInOneBase.ALWAYS_FIVE),
                Cone.builder()
                        .seed(Seed.WING_SHAPED)
                        .size(Size.BIG)
                        .build()
        );
        assertEquals(PlantName.PINUS_STROBUS, result.getPlantName());
    }

}
