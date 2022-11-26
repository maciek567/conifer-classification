package pl.agh.expertsystems.classifier;

import lombok.AllArgsConstructor;
import lombok.Value;
import pl.agh.expertsystems.classifier.model.Plant;


import java.util.List;
import java.util.Optional;

@Value
@AllArgsConstructor
public class ClassificationResult {
    Optional<Plant> plant;
    List<String> hints;


}
