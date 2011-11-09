package pl.put.idss.hied.weka.task2;

import java.util.Enumeration;
import java.util.Vector;

import org.apache.commons.lang3.StringUtils;

import weka.core.Option;
import weka.core.OptionHandler;
import weka.core.Utils;
import weka.filters.Filter;

public class DiscretizingFilterTask extends Filter implements OptionHandler {

	private int noRanges;

	public DiscretizingFilterTask() {
		this(1);
	}

	public DiscretizingFilterTask(int noRanges) {
		this.noRanges = noRanges;
	}

	// TODO implement logic

	public Enumeration<Option> listOptions() {
		Vector<Option> options = new Vector<Option>();
		options.add(new Option("number of ranges", "N", 1, "-N <num>"));
		return options.elements();
	}

	public void setOptions(String[] options) throws Exception {
		String string = Utils.getOption('N', options);
		noRanges = StringUtils.isNotBlank(string) ? Integer.valueOf(string) : 1;
	}

	public String[] getOptions() {
		return new String[] { "-N", String.valueOf(noRanges) };
	}

}
