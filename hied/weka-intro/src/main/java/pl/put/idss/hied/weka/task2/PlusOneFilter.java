package pl.put.idss.hied.weka.task2;

import java.util.Enumeration;

import pl.put.idss.hied.weka.Utils;
import weka.core.Attribute;
import weka.core.Instance;
import weka.core.Instances;
import weka.filters.Filter;
import weka.filters.SimpleStreamFilter;

public class PlusOneFilter extends SimpleStreamFilter {

	@Override
	protected Instances determineOutputFormat(Instances inputFormat) throws Exception {
		return new Instances(inputFormat);
	}

	@Override
	@SuppressWarnings("unchecked")
	protected Instance process(Instance instance) throws Exception {
		Instance result = (Instance) instance.copy();
		Enumeration<Attribute> attrs = result.enumerateAttributes();
		while (attrs.hasMoreElements()) {
			Attribute a = attrs.nextElement();
			if (a.isNumeric() && !result.isMissing(a)) {
				double value = result.value(a);
				result.setValue(a, value + 1);
			}
		}
		return result;
	}

	@Override
	public String globalInfo() {
		return getClass().getSimpleName();
	}

	public static void main(String[] args) {
		Instances instances;
		try {
			instances = Utils.loadInstances("vehicle.arff");
			Filter.useFilter(instances, new PlusOneFilter());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
