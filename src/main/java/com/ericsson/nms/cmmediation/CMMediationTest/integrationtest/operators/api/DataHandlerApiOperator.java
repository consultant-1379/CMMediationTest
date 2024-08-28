/*------------------------------------------------------------------------------
 *******************************************************************************
 * COPYRIGHT Ericsson 2012
 *
 * The copyright to the computer program(s) herein is the property of
 * Ericsson Inc. The programs may be used and/or copied only with written
 * permission from Ericsson Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 *******************************************************************************
 *----------------------------------------------------------------------------*/
package com.ericsson.nms.cmmediation.CMMediationTest.integrationtest.operators.api;

import java.util.List;

import com.ericsson.cifwk.taf.data.*;
/**
 * API Context Operator for executing Test Cases for DataHandler
 */
public class DataHandlerApiOperator {
	/**
	 * Return the hostType based on the hostName
	 * @param hostName
	 * @return hostType
	 */
	public HostType getHostType(String hostName) {
		HostType type = null;
		List<Host> hosts = DataHandler.getHosts();
		for (Host h : hosts) {
			if (h.getType().equals(HostType.JBOSS)
					&& h.getHostname().equals(hostName)) {
				type = h.getType();
				break;
			}
			
		}
		return type;
	}
}
