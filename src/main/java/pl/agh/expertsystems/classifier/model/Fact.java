package pl.agh.expertsystems.classifier.model;

public interface Fact {

    static Fact fromString(String string)
    {
        switch (string) {
            case "fruit.type.berry": return Fruit.create(Fruit.Type.BERRY);
            case "fruit.type.cone": return Fruit.create(Fruit.Type.CONE);
            case "berry.color.red": return new Berry(Berry.Color.RED);
            case "berry.color.blue": return new Berry(Berry.Color.BLUE);
            case "cone.shape.elongated": return new Cone(null, null, Cone.Shape.ELONGATED, null, null);
            case "cone.shape.oval": return new Cone(null, null, Cone.Shape.OVAL, null, null);
            case "cone.orientation.downwards": return new Cone(Cone.Orientation.DOWNWARDS, null, null, null, null);
            case "cone.orientation.upwards": return new Cone(Cone.Orientation.UPWARDS, null, null, null, null);
            case "cone.decayplace.tree": return new Cone(null, Cone.DecayPlace.TREE, null, null, null);
            case "cone.decayplace.ground": return new Cone(null, Cone.DecayPlace.GROUND, null, null, null);
            case "needle.countinonebase.more_than_twenty": return new Needle(Needle.CountInOneBase.MORE_THAN_TWENTY, null);

            default:
                return null;
        }
    }
}
