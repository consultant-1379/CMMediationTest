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
package com.ericsson.nms.cmmediation.CMMediationTest.integrationtest.operators;

import com.ericsson.cifwk.taf.data.HostType;
import com.ericsson.nms.cmmediation.CMMediationTest.integrationtest.data.DataHandlerDataProvider;
import com.ericsson.nms.cmmediation.CMMediationTest.integrationtest.operators.api.DataHandlerApiOperator;

/**
 * 
 * Operator for executing Test Cases for DataHandler
 */
public class DataHandlerOperator {
	DataHandlerDataProvider dataProvider = new DataHandlerDataProvider();
	DataHandlerApiOperator apiOperator = new DataHandlerApiOperator();

	/**
	 * Return the hostType based on the hostName
	 * @param hostName
	 * @return hostType
	 */
	public HostType getHostType(String hostName) {
		return apiOperator.getHostType(hostName);
	}

	/**
	 * Return the expected hostType
	 * 
	 * @return hostType
	 */
	public HostType expectedHostTypeResult() {
		return HostType.JBOSS;
	}
}
