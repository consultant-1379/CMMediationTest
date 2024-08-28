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

import com.ericsson.cifwk.taf.data.Host;
import com.ericsson.cifwk.taf.handlers.netsim.implementation.NetsimNE;
import com.ericsson.nms.cmmediation.CMMediationTest.integrationtest.data.NetSimHandlerDataProvider;
import com.ericsson.nms.cmmediation.CMMediationTest.integrationtest.data.RestToolExampleDataProvider;
/**
 * Test DataProvider for executing Test Cases for DataHandlerTest
 */
public class NetsimHandlerTestData {
	private static NetSimHandlerDataProvider netsimHandlerdataProvider = new NetSimHandlerDataProvider();
	
	/**
	 * Check netsim hosts 
	 * @DESCRIPTION This test reads in host data from file and returns the list of netsim hosts
	 * @PRE None
	 * @PRIORITY HIGH
	 */
	@DataProvider(name = "netsimHosts")
	public static Object[][] provideNetsimHosts() {
		List netsimHostList = netsimHandlerdataProvider.getAllNetsimHosts();
		return new Object[][] { netsimHostList.toArray() };
	}

	private static Map provideNetsimHostAndSimulation() {
		Map<String, Host> netsimHostandSimMap = new HashMap<String, Host>();
		List<String> simList = new ArrayList<String>();
		List<Host> netsimHostList = netsimHandlerdataProvider
				.getAllNetsimHosts();
		for (Host host : netsimHostList) {
			simList = netsimHandlerdataProvider.getListOfSimulations(host);
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
			node = netsimHandlerdataProvider.getListOfNEs(thisHost, sim);
			testMethodArguments[0] = thisHost;
			testMethodArguments[1] = sim;
			testMethodArguments[2] = node;
			result[idx] = testMethodArguments;
			idx++;
		}

		return result;

	}
}
