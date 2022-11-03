package pl.agh.expertsystems.classifier.model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Temp implements Fact { // Temp is just some intermediate fact
    private int t;
}
