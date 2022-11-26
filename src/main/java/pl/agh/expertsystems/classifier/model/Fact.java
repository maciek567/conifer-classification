package pl.agh.expertsystems.classifier.model;

public interface Fact {

    static Fact fromString(String string)
    {
        switch (string) {
            case "fruit.type.berry": return Fruit.create(Fruit.Type.BERRY);
            case "berry.color.red": return new Berry(Berry.Color.RED);
            case "berry.color.blue": return new Berry(Berry.Color.BLUE);

            default:
                return null;
        }
    }
}
