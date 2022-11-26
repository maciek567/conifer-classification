package pl.agh.expertsystems.classifier;

import pl.agh.expertsystems.classifier.model.Fact;
import pl.agh.expertsystems.classifier.model.Plant;

import java.util.Optional;
import java.util.Scanner;

public class UI {

    private boolean running;

    private final PlantClassifier classifier;

    private UI() {
        classifier = new PlantClassifier();
        running = true;
        startConsole();
    }

    private void startConsole() {
        Scanner scanner = new Scanner(System.in);

        while (running) {
            System.out.println("Enter a fact...");
            String input = scanner.nextLine();

            Fact fact = Fact.fromString(input.trim().toLowerCase());

            String output = Optional.ofNullable(fact).map(classifier::insertNew).map(result ->

                    result.getPlant().map(Plant::getPlantName).map(plantName -> {
                        running = false;
                        return "Result is: " + plantName;
                    }).orElseGet(() -> String.join("\n", result.getHints()))

            ).orElse("invalid input");

            System.out.println(output);
        }
    }

    public static void main(String[] args) { UI.run(args); }

    private static void run(String[] args) {
        new UI();
    }
}
