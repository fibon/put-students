package pl.put.idss.hied.weka.task2;

import java.util.List;

import org.apache.commons.math.stat.descriptive.moment.Mean;

import pl.put.idss.hied.weka.Utils;
import weka.core.Attribute;
import weka.core.Instance;
import weka.core.Instances;
import weka.filters.SimpleBatchFilter;

public class MeanFilter extends SimpleBatchFilter {

	@Override
	protected Instances determineOutputFormat(Instances inputFormat) throws Exception {
		return new Instances(inputFormat);
	}

	@Override
	@SuppressWarnings("unchecked")
	protected Instances process(Instances instances) throws Exception {
		Instances result = getOutputFormat();
		for (Instance instance : instances) {
			result.add((Instance) instance.copy());
		}
		List<Attribute> attributes = Utils.enumerationToList(instances.enumerateAttributes());
		for (Attribute attribute : attributes) {
			if (attribute.isNumeric()) {
				double mean = gatherMean(attribute, result);
				setAttribute(attribute, result, mean);
			}
		}
		return result;
	}

	private double gatherMean(Attribute attribute, Instances instances) {
		Mean mean = new Mean();
		for (Instance instance : instances) {
			if (!instance.isMissing(attribute)) {
				mean.increment(instance.value(attribute));
			}
		}
		return mean.getResult();
	}

	private void setAttribute(Attribute attribute, Instances instances, double mean) {
		for (Instance instance : instances) {
			if (!instance.isMissing(attribute)) {
				instance.setValue(attribute, mean);
			}
		}
	}

	@Override
	public String globalInfo() {
		return getClass().getSimpleName();
	}

}
