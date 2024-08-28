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

import com.ericsson.cifwk.taf.TestCase;
import com.ericsson.cifwk.taf.TorTestCaseHelper;
import com.ericsson.cifwk.taf.annotations.Context;
import com.ericsson.cifwk.taf.annotations.VUsers;
import com.ericsson.cifwk.taf.data.Host;
import com.ericsson.cifwk.taf.handlers.netsim.implementation.NetsimNE;
import com.ericsson.nms.cmmediation.CMMediationTest.integrationtest.operators.SshRemoteCommandExecutorOperator;
import com.ericsson.nms.cmmediation.CMMediationTest.integrationtest.test.data.NetsimHandlerTestData;
import com.ericsson.nms.cmmediation.CMMediationTest.integrationtest.test.data.SshRemoteCommandExecutorTestData;
/**
 * TC Class to show how to use TAF SshRemoteCommandExecutor
 */
public class SshRemoteCommandExecutorTest extends TorTestCaseHelper implements
		TestCase {
	SshRemoteCommandExecutorOperator remoteExecutor = new SshRemoteCommandExecutorOperator();

	/**
	 * Verify command execution
	 * 
	 * @DESCRIPTION Goal of the test case is to verify execution of command
	 *              provides expected result
	 * @PRE
	 * @PRIORITY HIGH
	 */
	@VUsers(vusers = { 1 })
	@Context(context = { Context.API })
	@Test(groups = { "Acceptance" }, dataProvider = "startStopNEs", dataProviderClass = NetsimHandlerTestData.class)
	public void verifyCommandExecution(Host host, String simulation,
			NetsimNE node) {
		setTestCase(
				"TC_SshRemoteCommandExecutorTest_1",
				"Verify stop node command execution on host: $host, simulation: $simulation, node: $node");

		saveAssertTrue("Nodes cant be stoped",
				remoteExecutor.executeStopNE(host, simulation, node));

		setTestCase(
				"TC_SshRemoteCommandExecutorTest_1",
				"Verify start node command execution on host: $host, simulation: $simulation, node: $node");
		saveAssertTrue("Nodes cant be started",
				remoteExecutor.executeStartNE(host, simulation, node));

	}

}
