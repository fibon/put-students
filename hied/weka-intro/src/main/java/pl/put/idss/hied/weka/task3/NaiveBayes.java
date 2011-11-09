package pl.put.idss.hied.weka.task3;

import weka.classifiers.AbstractClassifier;
import weka.core.Attribute;
import weka.core.Instance;
import weka.core.Instances;

public class NaiveBayes extends AbstractClassifier {

	@Override
	public double[] distributionForInstance(Instance instance) throws Exception {
		Attribute attribute = instance.classAttribute();
		if (attribute.isNominal()) {
			// TODO implement logic
			return new double[instance.classAttribute().numValues()];
		} else {
			return new double[0];
		}
	}

	@Override
	public void buildClassifier(Instances data) throws Exception {
		// TODO implement logic
	}

}
