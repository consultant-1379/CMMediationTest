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

import com.ericsson.cifwk.taf.data.Host;
import com.ericsson.cifwk.taf.handlers.netsim.implementation.NetsimNE;
import com.ericsson.nms.cmmediation.CMMediationTest.integrationtest.data.NetSimHandlerDataProvider;
import com.ericsson.nms.cmmediation.CMMediationTest.integrationtest.operators.api.NetsimHandlerApiOperator;
/**
*
*	Operator for executing Test Cases for NetsimHandler
*/
public class NetsimHandlerOperator {
	NetsimHandlerApiOperator netsimHandlerApiOperator = new NetsimHandlerApiOperator();
	NetSimHandlerDataProvider netsimHandlerDataProvider = new NetSimHandlerDataProvider();
	Host currentHost;
	String currentSimulation;
	NetsimNE currentNode;
	String currentCommand;

	/**
	 * Return netsim status
	 * @param host
	 * @return boolean
	 */
	public Boolean isNetsimRunning(Host host){
		currentHost = host;
		return netsimHandlerApiOperator.isNetsimRunning(currentHost);
	}
	/**
	 * Starts the NE
	 * @param host
	 * @param simulation
	 * @param node
	 * @return boolean
	 */
	public boolean startNE(Host host, String simulation, NetsimNE node){
		currentHost = host;
		currentSimulation = simulation;
		currentNode = node;
		return netsimHandlerApiOperator.startNE(currentHost, currentSimulation, currentNode);
	}
	/**
	 * Checks if Ne is started
	 * @param host
	 * @param node
	 * @return boolean
	 */
	public boolean isNEStarted(Host host, NetsimNE node){
		return netsimHandlerApiOperator.isNEStarted(currentHost, currentNode);
	}
	/**
	 * Expected NE start value
	 * @return boolean
	 */
	public boolean expectedStartNE(){
		return netsimHandlerDataProvider.expectedStartNE(currentHost, currentNode);
	}

	/**
	 * Stops the NE
	 * @param host
	 * @param simulation
	 * @param node
	 * @return boolean
	 */
	public boolean stopNE(Host host, String simulation, NetsimNE node){
		currentHost = host;
		currentSimulation = simulation;
		currentNode = node;
		return netsimHandlerApiOperator.stopNE(currentHost, currentSimulation, currentNode);
	}
	/**
	 * Expected NE stop value
	 * @return boolean
	 */
	public boolean expectedStopNE(){
		return netsimHandlerDataProvider.expectedStopNE(currentHost, currentNode);
	}
}
