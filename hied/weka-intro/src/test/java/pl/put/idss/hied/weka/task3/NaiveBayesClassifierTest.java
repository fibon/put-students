package pl.put.idss.hied.weka.task3;

import java.io.File;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import pl.put.idss.hied.weka.Utils;
import weka.core.Instances;
import weka.experiment.CSVResultListener;
import weka.experiment.ClassifierSplitEvaluator;
import weka.experiment.CrossValidationResultProducer;
import weka.experiment.ExplicitTestsetResultProducer;
import weka.experiment.RandomSplitResultProducer;
import weka.experiment.ResultProducer;

public class NaiveBayesClassifierTest {

	private static final int TEST_RUNS = 3;

	private Instances instances;

	private NaiveBayes classifier = new NaiveBayes();

	private CSVResultListener resultListener = new CSVResultListener();

	@Before
	public void setUp() throws IOException {
		instances = Utils.loadInstances("car.arff");
	}

	@Test
	public void test_random() throws Exception {
		RandomSplitResultProducer producer = new RandomSplitResultProducer();
		ClassifierSplitEvaluator evaluator = new ClassifierSplitEvaluator();
		evaluator.setClassifier(classifier);
		producer.setInstances(instances);
		producer.setSplitEvaluator(evaluator);
		producer.setResultListener(resultListener);
		resultListener.setOutputFile(new File("target/naive_bayes_test_random.txt"));

		producer.setTrainPercent(0.8);
		run(producer);
	}

	@Test
	public void test_crossvalidation() throws Exception {
		CrossValidationResultProducer producer = new CrossValidationResultProducer();
		ClassifierSplitEvaluator evaluator = new ClassifierSplitEvaluator();
		evaluator.setClassifier(classifier);
		producer.setInstances(instances);
		producer.setSplitEvaluator(evaluator);
		producer.setResultListener(resultListener);
		resultListener.setOutputFile(new File("target/naive_bayes_test_crossvalidation.txt"));

		producer.setNumFolds(10);
		run(producer);
	}

	@Test
	public void test_training_test() throws Exception {
		ExplicitTestsetResultProducer producer = new ExplicitTestsetResultProducer();
		ClassifierSplitEvaluator evaluator = new ClassifierSplitEvaluator();
		evaluator.setClassifier(classifier);
		producer.setInstances(instances);
		producer.setSplitEvaluator(evaluator);
		producer.setResultListener(resultListener);
		resultListener.setOutputFile(new File("target/naive_bayes_test_training_test.txt"));

		producer.setTestsetDir(new File("src/main/resources"));
		producer.setTestsetSuffix(".arff");
		run(producer);
	}

	private void run(ResultProducer producer) throws Exception {
		producer.preProcess();
		for (int i = 0; i < TEST_RUNS; i++) {
			producer.doRun(i);
		}
		producer.postProcess();
	}

}
