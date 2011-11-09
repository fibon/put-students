package pl.put.idss.hied.weka.task2;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import pl.put.idss.hied.weka.Utils;
import weka.core.Attribute;
import weka.core.Instance;
import weka.core.Instances;
import weka.filters.Filter;

public class DiscretizingFilterTaskTestVehicle {

	private Instances instances;

	@Before
	public void setUp() throws IOException {
		instances = Utils.loadInstances("vehicle.arff");
	}

	@Test
	public void test_2_ranges() throws Exception {
		test_discretization(2);
	}

	@Test
	public void test_3_ranges() throws Exception {
		test_discretization(3);
	}

	@Test
	public void test_5_ranges() throws Exception {
		test_discretization(5);
	}

	@Test
	public void test_10_ranges() throws Exception {
		test_discretization(10);
	}

	@SuppressWarnings("unchecked")
	public void test_discretization(int noRanges) throws Exception {
		DiscretizingFilterTask filter = new DiscretizingFilterTask(noRanges);
		filter.setInputFormat(instances);
		Instances filtered = Filter.useFilter(instances, filter);

		List<Attribute> attributes = Utils.enumerationToList(instances.enumerateAttributes());
		int[] expected = prepareExpectedUniques(attributes, noRanges);
		int[] actual = countUniques(attributes, filtered);
		Assert.assertArrayEquals(expected, actual);
	}

	private int[] prepareExpectedUniques(List<Attribute> attributes, int noRanges) {
		int[] uniques = new int[attributes.size()];
		for (int i = 0; i < attributes.size(); i++) {
			uniques[i] = attributes.get(i).isNumeric() ? noRanges : 0;
		}
		return uniques;
	}

	private int[] countUniques(List<Attribute> attributes, Instances instances) {
		int[] actual = new int[attributes.size()];
		for (int i = 0; i < attributes.size(); i++) {
			Set<Double> values = new HashSet<Double>();
			Attribute a = attributes.get(i);
			if (a.isNumeric()) {
				for (Instance instance : instances) {
					if (!instance.isMissing(a)) {
						values.add(instance.value(a));
					}
				}
			}
			actual[i] = values.size();
		}
		return actual;
	}

}
