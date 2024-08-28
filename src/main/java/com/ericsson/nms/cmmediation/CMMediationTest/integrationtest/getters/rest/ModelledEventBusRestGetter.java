package com.ericsson.nms.cmmediation.CMMediationTest.integrationtest.getters.rest;

import com.ericsson.cifwk.taf.RestGetter;
import com.ericsson.cifwk.taf.data.Host;
import com.ericsson.nms.cmmediation.utils.*;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * 
 * REST Context Getter for executing Test Cases for ModelledEventBus
 */
public class ModelledEventBusRestGetter implements RestGetter {

	public static List<EventBusMeasurement> readReceiverResponse(
			List<String> responses) {
		ArrayList<EventBusMeasurement> cleaned = new ArrayList<>();
		for (String s : responses) {

			String[] i = s.replace("\n", "").split(Pattern.quote("|"));
			if (i.length < 2) {
				throw new IllegalArgumentException("Invalid rest response: "
						+ s);
			}
			cleaned.add(new EventBusMeasurement(i[0], i[1]));
		}
		return cleaned;
	}

	public static List<EventBusMeasurement> readSenderResponse(
			List<String> responses) {
		ArrayList<EventBusMeasurement> cleaned = new ArrayList<>();
		for (String s : responses) {

			String[] i = s.replace("\n", "").split(Pattern.quote("|"));
			if (i.length < 2) {
				throw new IllegalArgumentException("Invalid rest response: "
						+ s);
			}
			cleaned.add(new EventBusMeasurement(i[0], i[1]));
		}
		return cleaned;
	}

	public static EventBusMeasurement sum(List<EventBusMeasurement> input) {
		long totalCount = 0;
		double totalTime = 0;
		for (EventBusMeasurement data : input) {
			totalCount += data.getCount();
			totalTime += data.getTime();
		}
		return new EventBusMeasurement(totalCount, totalTime);
	}

}
