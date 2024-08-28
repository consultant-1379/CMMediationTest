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
package com.ericsson.nms.cmmediation.CMMediationTest.integrationtest.test.cases;

import java.util.List;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import com.ericsson.cifwk.taf.TorTestCaseHelper;
import com.ericsson.cifwk.taf.annotations.Context;
import com.ericsson.cifwk.taf.annotations.VUsers;
import com.ericsson.cifwk.taf.data.Host;
import com.ericsson.cifwk.taf.handlers.netsim.implementation.NetsimNE;
import com.ericsson.nms.cmmediation.CMMediationTest.integrationtest.data.NetSimHandlerDataProvider;
import com.ericsson.nms.cmmediation.CMMediationTest.integrationtest.operators.NetsimHandlerOperator;
import com.ericsson.nms.cmmediation.CMMediationTest.integrationtest.test.data.NetsimHandlerTestData;
/**
 * TC Class to show how to use TAF NetsimHandler
 */
public class NetsimHandlerTest extends TorTestCaseHelper {
	Logger logger = Logger.getLogger(NetsimHandlerTest.class);
	NetsimHandlerOperator netsimOperator = new NetsimHandlerOperator();

	NetSimHandlerDataProvider netsimHandlerdataProvider = new NetSimHandlerDataProvider();

	/**
	 * Check Netsim is running before executing any testcase
	 */
	// @BeforeClass
	public void checkIsNetsimRunning() {
		List<Host> netsimHostList = netsimHandlerdataProvider
				.getAllNetsimHosts();
		for (Host host : netsimHostList) {
			assert netsimOperator.isNetsimRunning(host) == true;
		}
	}
	/**
	 * Check if a network element is started
	 * @DESCRIPTION Get list of Network elements, choose one at random, start it and then check if it is started
	 * @PRE Verify connection to host
	 * @PRIORITY HIGH
	 */
	@VUsers(vusers = { 1 })
	@Context(context = { Context.API })
	@Test(groups = { "Acceptance" }, dataProvider = "startStopNEs", dataProviderClass = NetsimHandlerTestData.class)
	public void checkNetworkElementIsStarted(Host host, String simulation, NetsimNE node){
		saveAssertEquals("TC failed as Netsim is not running", netsimOperator.isNetsimRunning(host).booleanValue(), true);
		
		setTestCase("CIP-868_Func_3","Start a network element $node on $host with simulation $simulation and check it is started. Stop a network element and check it is stopped");
		
		logger.info("Start a network element"+ node+" on "+host+" with simulation"+ simulation+" and check it is started. Stop a network element and check it is stopped");
		
		setTestStep("Execute Start selected network element and Verify the selected network elements was started");
		setTestInfo("Running test with host: $host, simulation: $simulation, node: $node");
		
		logger.info("Running start test with host: "+host+", simulation: "+simulation +" , node: "+node);
		boolean actual = netsimOperator.startNE(host, simulation, node);
		boolean expected = netsimOperator.expectedStartNE();
		assert actual == expected;

		setTestStep("Execute Stop selected network element and Verify the selected network elements was stopped");
		setTestInfo("Running test with host: $host, simulation: $simulation, node: $node");
		logger.info("Running stop test with host: "+host+", simulation: "+simulation +" , node: "+node);
		actual = netsimOperator.stopNE(host, simulation, node);
		expected = netsimOperator.expectedStopNE();
		assert actual = expected;
	}
}
