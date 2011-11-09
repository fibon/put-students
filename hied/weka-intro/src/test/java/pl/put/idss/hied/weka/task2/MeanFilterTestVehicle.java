package pl.put.idss.hied.weka.task2;

import java.io.IOException;

import org.apache.commons.math.stat.descriptive.moment.StandardDeviation;
import org.apache.commons.math.stat.descriptive.summary.Sum;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import pl.put.idss.hied.weka.Utils;
import weka.core.Instances;
import weka.filters.Filter;

public class MeanFilterTestVehicle {

	private MeanFilter filter = new MeanFilter();

	private Instances instances;

	@Before
	public void setUp() throws IOException {
		instances = Utils.loadInstances("vehicle.arff");
	}

	@Test
	public void test_filter() throws Exception {
		double[] originalSums = Utils.getAttributeStats(instances, new Sum());

		filter.setInputFormat(instances);
		Instances filtered = Filter.useFilter(instances, filter);

		double[] filteredSums = Utils.getAttributeStats(filtered, new Sum());
		double[] filteredStdDevs = Utils.getAttributeStats(filtered, new StandardDeviation());

		Assert.assertArrayEquals(originalSums, filteredSums, 0.00000001);
		Assert.assertArrayEquals(filteredStdDevs, new double[instances.numAttributes() - 1], 0);
	}

}
