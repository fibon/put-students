package pl.put.idss.hied.weka;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.commons.math.stat.descriptive.StorelessUnivariateStatistic;
import org.apache.commons.math.stat.descriptive.UnivariateStatistic;

import weka.core.Attribute;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.AbstractFileLoader;
import weka.core.converters.ConverterUtils;

public final class Utils {

	private Utils() {
	}

	public static Instances loadInstances(String filename) throws IOException {
		AbstractFileLoader loader = ConverterUtils.getLoaderForFile(filename);
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream stream = null;
		try {
			stream = classLoader.getResourceAsStream(filename);
			loader.setSource(stream);
			return loadData(loader);
		} finally {
			IOUtils.closeQuietly(stream);
		}
	}

	public static Instances loadInstances(File file) throws IOException {
		AbstractFileLoader loader = ConverterUtils.getLoaderForFile(file);
		loader.setSource(file);
		return loadData(loader);
	}

	private static Instances loadData(AbstractFileLoader loader) throws IOException {
		Instances data = loader.getDataSet();
		if (data.classIndex() == -1) {
			data.setClassIndex(data.numAttributes() - 1);
		}
		return data;
	}

	public static <T> List<T> enumerationToList(Enumeration<T> enumeration) {
		List<T> list = new ArrayList<T>();
		while (enumeration.hasMoreElements()) {
			list.add(enumeration.nextElement());
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public static double[] getAttributeStats(Instances instances, StorelessUnivariateStatistic stat) {
		List<Attribute> attributes = Utils.enumerationToList(instances.enumerateAttributes());
		double[] stats = new double[attributes.size()];
		for (int i = 0; i < attributes.size(); i++) {
			Attribute a = attributes.get(i);
			stat.clear();
			double d = 0;
			for (Instance instance : instances) {
				if (!instance.isMissing(a)) {
					stat.increment(instance.value(a));
					d += instance.value(a);
				}
			}
			stats[i] = stat.getResult();
		}
		return stats;
	}

	public static double getAttributeStats(Instances instances, StorelessUnivariateStatistic stat,
			UnivariateStatistic agg) {
		double[] stats = getAttributeStats(instances, stat);
		return agg.evaluate(stats);
	}

}
