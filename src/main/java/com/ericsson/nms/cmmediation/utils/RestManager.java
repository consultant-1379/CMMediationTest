package com.ericsson.nms.cmmediation.utils;

import java.util.*;

import com.ericsson.cifwk.taf.RestOperator;
import com.ericsson.cifwk.taf.data.Host;
import com.ericsson.cifwk.taf.tools.RestTool;
import com.ericsson.nms.cmmediation.CMMediationTest.integrationtest.operators.rest.RestCallFailedException;

/**
 * 
 * REST Context Operator for executing Test Cases for ModelledEventBus
 */
public class RestManager {

	private Host host;

	/**
	 * Utility class that allows calls to be made to a single host using the TAF
	 * utilities. A response processor can be provided to handle generic
	 * response handling. The default response processor is a whitespace /
	 * newline / tab remover.
	 * 
	 * @param host
	 * @param processor
	 * 
	 */
	public RestManager(Host host) {
		this.host = host;
	}

	public List<String> get(String url) {
		List<String> responses = get(url, null);
		return responses;
	}

	public List<String> get(String url, Map params) {
		RestTool restTool = new RestTool(host);

		List<String> responses = null;
		if (params != null) {
			responses = restTool.get(url, params);
		} else {
			responses = restTool.get(url);
		}
		if (!restTool.wasSuccesfull()) {
			throw new RestCallFailedException("Rest Call failed");
		}

		return responses;

	}
}
