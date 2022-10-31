package pl.agh.expertsystems.classifier;

import config.DroolsBeanFactory;
import org.kie.api.runtime.KieSession;
import pl.agh.expertsystems.classifier.model.Fact;

public abstract class AnyClassifier<T> {

    protected final KieSession kieSession;

    public AnyClassifier() {
        kieSession = new DroolsBeanFactory().getKieSession();
    }

    public abstract T classify(Fact... facts);
}
