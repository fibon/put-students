package pl.put.idss.hied.weka.task1;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pl.put.idss.hied.weka.Utils;
import weka.core.Attribute;
import weka.core.Instances;

public class DatasetDescriptionExample {

	private static final Logger LOG = LoggerFactory.getLogger(DatasetDescriptionExample.class);

	private final Instances instances;

	public DatasetDescriptionExample(Instances instances) {
		this.instances = instances;
	}

	public void describeDataset(Logger log) {
		log.info("Number of instances: {}", instances.size());
		log.info("Number of attributes: {}", instances.numAttributes());
		log.info("Decision class: {}", instances.classAttribute().name());
		log.info("Number of classes: {}", instances.numClasses());
		log.info("");
	}

	@SuppressWarnings("unchecked")
	public void describeAttributes(Logger log) {
		Enumeration<Attribute> attrs = instances.enumerateAttributes();
		while (attrs.hasMoreElements()) {
			Attribute a = attrs.nextElement();
			log.info("Attribute - name: {}", a.name());
			log.info("Attribute - type: {}", Attribute.typeToString(a));
			if (a.isNumeric()) {
				log.info("Attribute - lower bound: ", a.getLowerNumericBound());
				log.info("Attribute - upper bound: ", a.getUpperNumericBound());
			} else if (a.isNominal()) {
				log.info("Attribute - number of values: ", a.numValues());
				List<?> values = Utils.enumerationToList(a.enumerateValues());
				log.info("Attribute - values: ", ArrayUtils.toString(values.toArray()));
			}
		}
		log.info("");
	}

	public static void main(String[] args) {
		File file = new File(args[0]);
		try {
			Instances instances = Utils.loadInstances(file);
			DatasetDescriptionExample datasets = new DatasetDescriptionExample(instances);
			datasets.describeDataset(LOG);
			datasets.describeAttributes(LOG);
		} catch (IOException e) {
			LOG.error(e.getMessage(), e);
		}
	}

}
