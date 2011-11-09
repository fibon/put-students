package pl.put.idss.hied.weka.task2;

import java.io.IOException;

import org.apache.commons.math.stat.descriptive.summary.Sum;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import pl.put.idss.hied.weka.Utils;
import weka.core.Instances;
import weka.filters.Filter;

public class PlusOneFilterTestCar {

	private static final int ORIGINAL_SUM = 12960;

	private PlusOneFilter filter = new PlusOneFilter();

	private Instances instances;

	@Before
	public void setUp() throws IOException {
		instances = Utils.loadInstances("car.arff");
	}

	@Test
	public void test_original() {
		double sum = Utils.getAttributeStats(instances, new Sum(), new Sum());
		Assert.assertEquals(ORIGINAL_SUM, sum, 0);
	}

	@Test
	public void test_filter() throws Exception {
		filter.setInputFormat(instances);
		Instances filtered = Filter.useFilter(instances, filter);

		double sum = Utils.getAttributeStats(filtered, new Sum(), new Sum());
		Assert.assertEquals(ORIGINAL_SUM, sum, 0);
	}

}
