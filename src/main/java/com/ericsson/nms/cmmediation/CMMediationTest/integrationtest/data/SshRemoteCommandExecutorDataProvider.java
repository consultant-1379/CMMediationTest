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
package com.ericsson.nms.cmmediation.CMMediationTest.integrationtest.data;

import java.util.*;

import com.ericsson.cifwk.taf.DataProvider;
import com.ericsson.cifwk.taf.data.*;
import com.ericsson.cifwk.taf.handlers.netsim.NetsimHandler;
import com.ericsson.cifwk.taf.handlers.netsim.implementation.NetsimNE;
import com.ericsson.cifwk.taf.utils.csv.CsvReader;
/**
*
*	DataProvider for executing Test Cases for NEtsimHandler
*/
public class SshRemoteCommandExecutorDataProvider implements DataProvider {
public final static  String HOST_NAME = "CI-Netsim";
	
	NetsimHandler netsimHandler;
	
	/**
	 * Method to get the Netsim  
	 * @return list of netsim hosts
	 */
	public List<Host> getAllNetsimHosts(){
		List<Host> hosts = DataHandler.getHosts();
		List<Host> netSimHosts = new ArrayList<Host>();
		for (Host host : hosts) {
			if(host.getType().equals(HostType.NETSIM) && host.getHostname().equals(HOST_NAME))
				netSimHosts.add(host);
		}
		return netSimHosts;
	}
	
	/**
	 * Method to get the list of all simulations for  hosts 
	 * @return list of  simulations
	 */
	public List<String> getListOfSimulations(Host host){
		netsimHandler = new NetsimHandler(host);
		return netsimHandler.getListOfSimulations();
	}
	
	/**
	 * Method to get the list of expected started NEs
	 * @return list of  started NEs
	 */
	public List<NetsimNE> getExpectedListOfStartedNEs(Host host, String simulation){
		netsimHandler = new NetsimHandler(host);
		return netsimHandler.getAllStartedNEs(simulation);
	}
	public NetsimNE getListOfNEs(Host host, String sim){
		NetsimNE selectedNode = null;
		netsimHandler = new NetsimHandler(host);
		List<NetsimNE> listOfNes = netsimHandler.getAllNEs(sim);
		Collections.shuffle(listOfNes);
		//Need to get a node from the list that has been set up properly i.e. has a valid IP address
		for (NetsimNE netsimNE : listOfNes) {
			if(netsimNE.getIp() != "????")
				selectedNode = netsimNE;
			break;
		}
		return selectedNode;
	}
	
}
