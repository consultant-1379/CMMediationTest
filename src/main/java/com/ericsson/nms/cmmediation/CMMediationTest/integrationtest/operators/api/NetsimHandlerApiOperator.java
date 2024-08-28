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

import com.ericsson.cifwk.taf.ApiOperator;
import com.ericsson.cifwk.taf.data.Host;
import com.ericsson.cifwk.taf.handlers.netsim.implementation.NetsimNE;
import com.ericsson.nms.cmmediation.CMMediationTest.integrationtest.getters.api.NetsimHandlerApiGetter;
/**
 * API Context Operator for executing Test Cases for DataHandler
 */
public class NetsimHandlerApiOperator implements ApiOperator {
	NetsimHandlerApiGetter netsimHandlerApiGetter = new NetsimHandlerApiGetter();
	
	/**
	 * Return the netsim status, whether its running or not
	 * @param hostName
	 * @return boolean 
	 */
	public boolean isNetsimRunning(Host host) {
		return netsimHandlerApiGetter.getService(host).isNetsimRunning();
	}
	
	/**
	 * Return the list of simulations
	 * @param host
	 * @return list
	 */
	public List<String> getListOfSimulations(Host host) {
		return netsimHandlerApiGetter.getService(host).getListOfSimulations();
	}

	/**
	 * Return the list of NEs
	 * @param host
	 *  @param simulation
	 * @return list
	 */
	public List<NetsimNE> getListOfNEsFromSimulation(Host host,
			String simulation) {
		return netsimHandlerApiGetter.getService(host).getAllNEs(simulation);
	}
	
	/**
	 * Return the list of started NEs
	 * @param host
	 *  @param simulation
	 * @return list
	 */
	public List<NetsimNE> getListOfStartedNEs(Host host, String simulation) {
		return netsimHandlerApiGetter.getService(host).getAllStartedNEs(
				simulation);
	}

	/**
	 * Starts the NE and return status
	 * @param host
	 * @return simulation
	 * @return boolean
	 */
	public boolean startNE(Host host, String simulation, NetsimNE node) {
		return netsimHandlerApiGetter.getService(host)
				.startNE(simulation, node);
	}

	/**
	 * Check if NE is started and return the status
	 * @param host
	 *  @param node
	 * @return boolean
	 */
	public boolean isNEStarted(Host host, NetsimNE node) {
		return netsimHandlerApiGetter.getService(host).isNeStarted(
				node.getName());
	}

	/**
	 * Stops the NE and return status
	 * @param host
	 * @param simulation
	 * @param node
	 * @return boolean
	 */
	public boolean stopNE(Host host, String simulation, NetsimNE node) {
		return netsimHandlerApiGetter.getService(host).stopNE(simulation, node);
	}

}
