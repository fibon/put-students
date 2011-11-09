package pl.put.idss.hied.weka.task3;

import java.util.Random;

import weka.classifiers.AbstractClassifier;
import weka.core.Attribute;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.Utils;

public class RandomClassifier extends AbstractClassifier {

	private Random random = new Random();

	@Override
	public double classifyInstance(Instance instance) throws Exception {
		Attribute attribute = instance.classAttribute();
		if (attribute.isNumeric()) {
			double lower = attribute.getLowerNumericBound();
			double upper = attribute.getUpperNumericBound();
			return lower + (upper - lower) * random.nextDouble();
		} else if (attribute.isNominal()) {
			return random.nextInt(attribute.numValues());
		} else {
			return Utils.missingValue();
		}
	}

	@Override
	public void buildClassifier(Instances data) throws Exception {
	}

}
