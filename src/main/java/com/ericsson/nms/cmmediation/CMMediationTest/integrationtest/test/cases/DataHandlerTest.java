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

import org.testng.annotations.Test;

import se.ericsson.jcat.fw.annotations.Setup;

import com.ericsson.cifwk.taf.TestCase;
import com.ericsson.cifwk.taf.TorTestCaseHelper;
import com.ericsson.cifwk.taf.annotations.Context;
import com.ericsson.cifwk.taf.annotations.VUsers;
import com.ericsson.nms.cmmediation.CMMediationTest.integrationtest.operators.DataHandlerOperator;
import com.ericsson.nms.cmmediation.CMMediationTest.integrationtest.test.data.DataHandlerTestData;
/**
 * TC Class to show how to use TAF DataHandler
 */
public class DataHandlerTest extends TorTestCaseHelper implements TestCase{
	DataHandlerOperator dataHandlerOperator = new DataHandlerOperator();
	
	
	/**
	 * Check host type read from file is in Data Handler
	 * @DESCRIPTION This test reads in host data from file and checks it exists in Data Handler
	 * @PRE None
	 * @PRIORITY HIGH
	 */
	@VUsers(vusers = { 1 })
	@Context(context = { Context.API })
	@Test(groups = { "Acceptance" }, dataProvider = "hostTestData", dataProviderClass = DataHandlerTestData.class)
	public void testDataHAndler(String hostName) {
		setTestCase("CIP-856_Func_5","Check host data for $hostname and type $hostType read from file exists in Data Handler");

		setTestStep("Execute Get Host from properties file equals Host from Data Handler");
		assert dataHandlerOperator.getHostType(hostName) == dataHandlerOperator.expectedHostTypeResult();
	}
}
