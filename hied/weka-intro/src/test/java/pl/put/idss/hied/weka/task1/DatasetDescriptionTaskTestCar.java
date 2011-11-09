package pl.put.idss.hied.weka.task1;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import pl.put.idss.hied.weka.Utils;
import weka.core.Instances;

public class DatasetDescriptionTaskTestCar {

	private DatasetDescriptionTask task;

	@Before
	public void setUp() throws IOException {
		Instances instances = Utils.loadInstances("car.arff");
		task = new DatasetDescriptionTask(instances);
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_getCounters_null() {
		task.getNoExamples(null);
	}

	@Test
	public void test_getCounters_unacc() {
		Assert.assertEquals(1210, task.getNoExamples("unacc"));
	}

	@Test
	public void test_getCounters_acc() {
		Assert.assertEquals(384, task.getNoExamples("acc"));
	}

	@Test
	public void test_getCounters_good() {
		Assert.assertEquals(69, task.getNoExamples("good"));
	}

	@Test
	public void test_getCounters_vgood() {
		Assert.assertEquals(65, task.getNoExamples("vgood"));
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_getCounters_fake() {
		task.getNoExamples("fake");
	}

}
