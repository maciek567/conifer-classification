package pl.agh.expertsystems.classifier.model;

public interface Fact {

    static Fact fromString(String string) {
        switch (string) {
            case "fruit.type.berry":
                return Fruit.create(Fruit.Type.BERRY);
            case "fruit.type.cone":
                return Fruit.create(Fruit.Type.CONE);
            case "berry.color.red":
                return new Berry(Berry.Color.RED);
            case "berry.color.blue":
                return new Berry(Berry.Color.BLUE);
            case "cone.shape.elongated":
                return Cone.builder().shape(Cone.Shape.ELONGATED).build();
            case "cone.shape.oval":
                return Cone.builder().shape(Cone.Shape.OVAL).build();
            case "cone.orientation.downwards":
                return Cone.builder().orientation(Cone.Orientation.DOWNWARDS).build();
            case "cone.orientation.upwards":
                return Cone.builder().orientation(Cone.Orientation.UPWARDS).build();
            case "cone.decayplace.tree":
                return Cone.builder().decayPlace(Cone.DecayPlace.TREE).build();
            case "cone.decayplace.ground":
                return Cone.builder().decayPlace(Cone.DecayPlace.GROUND).build();
            case "cone.seed.nut_shaped":
                return Cone.builder().seed(Cone.Seed.NUT_SHAPED).build();
            case "cone.seed.wing_shaped":
                return Cone.builder().seed(Cone.Seed.WING_SHAPED).build();
            case "conesize.small":
                return new ConeSize(ConeSize.Size.SMALL);
            case "conesize.big":
                return new ConeSize(ConeSize.Size.BIG);
            case "needle.scent.lemon":
                return new Needle(null, Needle.Scent.LEMON);
            case "needle.countinonebase.always_two":
                return new Needle(Needle.CountInOneBase.ALWAYS_TWO, null);
            case "needle.countinonebase.always_five":
                return new Needle(Needle.CountInOneBase.ALWAYS_FIVE, null);
            case "needle.countinonebase.more_than_twenty":
                return new Needle(Needle.CountInOneBase.MORE_THAN_TWENTY, null);
            case "plantshape.shape.bush":
                return new PlantShape(PlantShape.Shape.BUSH);

            default:
                return null;
        }
    }
}
