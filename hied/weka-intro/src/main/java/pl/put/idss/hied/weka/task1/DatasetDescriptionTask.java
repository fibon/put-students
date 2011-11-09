package pl.put.idss.hied.weka.task1;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.mutable.MutableInt;

import weka.core.Instances;

public class DatasetDescriptionTask {

	private final Instances instances;

	private final Map<String, MutableInt> classes = new HashMap<String, MutableInt>();

	public DatasetDescriptionTask(Instances instances) {
		Validate.notNull(instances);
		this.instances = instances;
		gatherClassAssignmentData();
	}

	private void gatherClassAssignmentData() {
		// TODO implement logic
	}

	public int getNoExamples(String classLabel) {
		MutableInt counter = classes.get(classLabel);
		Validate.isTrue(counter != null, "Unknown class: {}", classLabel);
		return counter.intValue();
	}

}
