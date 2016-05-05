package neural;

import org.neuroph.core.data.DataSet;
import org.neuroph.core.learning.LearningRule;

public class EmptyLearningRule extends LearningRule {
    private static final long serialVersionUID = -5032322619342099534L;

    @Override
    public void learn(DataSet trainingSet) {
        System.out.println("Empty learning rule");
    }

}
