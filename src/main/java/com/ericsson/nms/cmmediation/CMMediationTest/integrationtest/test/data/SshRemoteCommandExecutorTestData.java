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
package com.ericsson.nms.cmmediation.CMMediationTest.integrationtest.test.data;

import java.util.*;

import org.testng.annotations.DataProvider;

import com.ericsson.cifwk.taf.TestData;
import com.ericsson.cifwk.taf.data.Host;
import com.ericsson.cifwk.taf.handlers.netsim.implementation.NetsimNE;
import com.ericsson.nms.cmmediation.CMMediationTest.integrationtest.data.SshRemoteCommandExecutorDataProvider;
/**
 * Test DataProvider for executing Test Cases for SshRemoteCommandExecutor
 */
public class SshRemoteCommandExecutorTestData implements TestData{
static SshRemoteCommandExecutorDataProvider dataProvider = new SshRemoteCommandExecutorDataProvider();
private static SshRemoteCommandExecutorDataProvider sshRemoteCommandExecutorDataProvider = new SshRemoteCommandExecutorDataProvider(); 

    private static Map provideNetsimHostAndSimulation() {
		Map<String, Host> netsimHostandSimMap = new HashMap<String, Host>();
		List<String> simList = new ArrayList<String>();
		List<Host> netsimHostList = sshRemoteCommandExecutorDataProvider
				.getAllNetsimHosts();
		for (Host host : netsimHostList) {
			simList = sshRemoteCommandExecutorDataProvider.getListOfSimulations(host);
			for (String sim : simList) {
				netsimHostandSimMap.put(sim, host);
			}
		}
		return netsimHostandSimMap;
	}
    /**
	 * Get list of nodes for all simulations
	 * @DESCRIPTION This test provides the list of nodes for all the simulations
	 * @PRE None
	 * @PRIORITY HIGH
	 */
	@DataProvider(name = "startStopNEs")
	public static Object[][] provideStartStopNEs() {
		List startStopNEData = new ArrayList();
		int idx = 0;
		Host thisHost = null;
		String sim = null;
		NetsimNE node = null;
		Map<String, Host> netsimHostandSimMap = provideNetsimHostAndSimulation();
		Object[][] result = new Object[netsimHostandSimMap.size()][];
		for (Map.Entry<String, Host> entry : netsimHostandSimMap.entrySet()) {
			Object[] testMethodArguments = new Object[3];
			sim = entry.getKey();
			thisHost = entry.getValue();
			node = sshRemoteCommandExecutorDataProvider.getListOfNEs(thisHost, sim);
			testMethodArguments[0] = thisHost;
			testMethodArguments[1] = sim;
			testMethodArguments[2] = node;
			result[idx] = testMethodArguments;
			idx++;
		}

		return result;

	}
    
   
}
