package com.ericsson.nms.cmmediation.CMMediationTest.integrationtest.operators.rest;

import java.util.List;

import com.ericsson.cifwk.taf.RestOperator;
import com.ericsson.cifwk.taf.data.*;
import com.ericsson.nms.cmmediation.CMMediationTest.integrationtest.getters.rest.EventBusMeasurement;
import com.ericsson.nms.cmmediation.utils.RestManager;
import static com.ericsson.nms.cmmediation.CMMediationTest.integrationtest.getters.rest.ModelledEventBusRestGetter.*;

/**
 * 
 * REST Context Operator for executing Test Cases for ModelledEventBus
 */
public class ModelledEventBusReceiverRestOperator implements RestOperator {

	private RestManager restManager;

	public ModelledEventBusReceiverRestOperator(Host host) {
		this.restManager = new RestManager(host);
	}

	public EventBusMeasurement getStats() {
		String url = "JmsPerformanceReceiver/rest/channelReceiver/getStats";

		List<String> responses = restManager.get(url);
		List<EventBusMeasurement> measurements = readSenderResponse(responses);
		return sum(measurements);
	}

	public void clear() {
		restManager
				.get("JmsPerformanceReceiver/rest/channelReceiver/clearStats");
	}
}
