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

import java.util.List;

import com.ericsson.cifwk.taf.GenericOperator;
import com.ericsson.cifwk.taf.data.Host;
import com.ericsson.cifwk.taf.handlers.netsim.implementation.NetsimNE;
import com.ericsson.nms.cmmediation.CMMediationTest.integrationtest.data.SshRemoteCommandExecutorDataProvider;
import com.ericsson.nms.cmmediation.CMMediationTest.integrationtest.operators.api.RemoteCommandExecutorApiOperator;
/**
*
*	Operator for executing Test Cases for SshRemoteCommandExecutor
*/
public class SshRemoteCommandExecutorOperator implements GenericOperator {
	public static final String CMD_START = "echo -e '";
	public static final String CMD_SIM = ".open ";
	public static final String CMD_MID = "\\n";
	public static final String CMD_END = "' | /netsim/inst/netsim_pipe";
	private static final String CMD_IS_STARTED = "echo -e '.show started"
			+ CMD_END;
	public static final String SIM_PATH = "/netsim/netsim_dbdir/simdir/netsim/netsimdir";
	public static final String SIM_START_IDENTIFIER = "total";
	
	public static final String NE_CMD_START = "\\n selectnocallback ";
	public static final String NE_START_CMD_END = " \\n.start -parallel";
	public static final String NE_STOP_CMD_END = " \\n.stop -parallel";

	SshRemoteCommandExecutorDataProvider dataProvider = new SshRemoteCommandExecutorDataProvider();
	RemoteCommandExecutorApiOperator apiOperator = new RemoteCommandExecutorApiOperator();
	Host currentHost;
	Host host;

	/**
	 * @param hosts
	 *   the host to set
	 */
	public void setHost(Host host) {
		this.host = host;
	}

	/**
	 * Execute command to start the NE
	 * @param host
	 * @param simulation
	 * @param node
	 * @return boolean
	 */
	public boolean  executeStopNE(Host host, String simulation, NetsimNE node) {
		apiOperator.setHost(host);
		String fullCommandString = CMD_START + CMD_SIM +simulation + CMD_MID + NE_STOP_CMD_END + CMD_END;
		return apiOperator.execute(fullCommandString);
	}
	/**
	 * Execute command to stop the NE
	 * @param host
	 * @param simulation
	 * @param node
	 * @return boolean
	 */
	public boolean executeStartNE(Host host, String simulation, NetsimNE node) {
		apiOperator.setHost(host);
		String fullCommandString = CMD_START + CMD_SIM +simulation + CMD_MID + NE_START_CMD_END + CMD_END;
		return apiOperator.execute(fullCommandString);
	}
}
