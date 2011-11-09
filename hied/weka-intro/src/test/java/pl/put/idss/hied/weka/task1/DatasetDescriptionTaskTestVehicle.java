package pl.put.idss.hied.weka.task1;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import pl.put.idss.hied.weka.Utils;
import weka.core.Instances;

public class DatasetDescriptionTaskTestVehicle {

	private DatasetDescriptionTask task;

	@Before
	public void setUp() throws IOException {
		Instances instances = Utils.loadInstances("vehicle.arff");
		task = new DatasetDescriptionTask(instances);
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_getCounters_null() {
		task.getNoExamples(null);
	}

	@Test
	public void test_getCounters_opel() {
		Assert.assertEquals(212, task.getNoExamples("opel"));
	}

	@Test
	public void test_getCounters_saab() {
		Assert.assertEquals(217, task.getNoExamples("saab"));
	}

	@Test
	public void test_getCounters_bus() {
		Assert.assertEquals(218, task.getNoExamples("bus"));
	}

	@Test
	public void test_getCounters_van() {
		Assert.assertEquals(199, task.getNoExamples("van"));
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_getCounters_fake() {
		task.getNoExamples("fake");
	}

}
