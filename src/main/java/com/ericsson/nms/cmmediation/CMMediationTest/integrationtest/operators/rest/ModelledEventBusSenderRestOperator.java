package com.ericsson.nms.cmmediation.CMMediationTest.integrationtest.operators.rest;

import java.util.*;

import com.ericsson.cifwk.taf.RestOperator;
import com.ericsson.cifwk.taf.data.*;

import static com.ericsson.nms.cmmediation.CMMediationTest.integrationtest.getters.rest.ModelledEventBusRestGetter.*;

import com.ericsson.nms.cmmediation.CMMediationTest.integrationtest.getters.rest.EventBusMeasurement;
import com.ericsson.nms.cmmediation.utils.RestManager;
import com.ericsson.nms.cmmediation.utils.Url;

/**
 * 
 * REST Context Operator for executing Test Cases for ModelledEventBus
 */
public class ModelledEventBusSenderRestOperator implements RestOperator {

	private RestManager restManager;

	public ModelledEventBusSenderRestOperator(Host host) {
		this.restManager = new RestManager(host);

	}

	public EventBusMeasurement send(long rounds, long quantity, long delay) {
		String url = new Url(
				"JmsPerformanceSender/rest/modeledChannelSender/sendBatchQueueMessages")
				.and("rounds", Long.toString(rounds))
				.and("quantity", Long.toString(quantity))
				.and("delay", Long.toString(delay)).toString();

		List<String> responses = restManager.get(url);
		List<EventBusMeasurement> measurements = readSenderResponse(responses);
		return sum(measurements);

	}

}
