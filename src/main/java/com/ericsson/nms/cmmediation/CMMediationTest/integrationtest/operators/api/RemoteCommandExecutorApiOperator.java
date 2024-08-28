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

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.ericsson.cifwk.taf.ApiOperator;
import com.ericsson.cifwk.taf.data.Host;
import com.ericsson.cifwk.taf.handlers.RemoteCommandExecutor;
import com.ericsson.nms.cmmediation.CMMediationTest.integrationtest.getters.api.RemoteCommandExecutorApiGetter;

public class RemoteCommandExecutorApiOperator implements ApiOperator {
	Logger logger = Logger.getLogger(RemoteCommandExecutorApiOperator.class);
	RemoteCommandExecutorApiGetter rceServiceGetter = new RemoteCommandExecutorApiGetter();
	List<Host> hosts = new ArrayList<Host>();
	Host host;

	/**
	 * @param host
	 *            the host to set
	 */
	public void setHost(Host host) {
		this.host = host;
	}


	public static final String CONNECTION_OK = RemoteCommandExecutorApiGetter.CONNECTED;
	public static final String AUTHENTICATION_FAILURE = RemoteCommandExecutorApiGetter.AUTHENTICATION_FAILURE;
	public static final String CONNECTION_FAILURE = RemoteCommandExecutorApiGetter.CONNECTION_FAILURE;

	public static final String RESULT = "RESULT";
	public static final String STD_OUT = "STDOUT";
	public static final String ERR_OUT = "ERROUT";
	public static final String EXIT_CODE = "EXITCODE";

	public boolean execute(String command) {
		boolean returnValue = false;

		RemoteCommandExecutor service = rceServiceGetter.getService(host);
		// returnValue
		// =service.execute("echo -e '.show started ' | /netsim/inst/netsim_pipe");
		returnValue = service.execute(command);
		String commandOutput = service.getStdOut();
		logger.debug("commandOutput" + commandOutput);
		int commandExitCode = service.getExitCode();
		logger.debug("commandExitCode" + commandExitCode);
		String errorOutput = service.getErrOut();
		logger.debug("errorOutput" + errorOutput);
		return returnValue;
	}

	/*
	 * public boolean invokeExecute(RemoteCommandExecutor service,String[]
	 * commandWithArguments){ boolean returnValue = false; //if
	 * (commandWithArguments.length == 1) returnValue
	 * =service.execute("echo -e '.show started ' | /netsim/inst/netsim_pipe");
	 * service.getStdOut(); service.getExitCode(); service.getErrOut(); else
	 * return
	 * service.execute(commandWithArguments[0],commandWithArguments[1..-1] as
	 * String[]); return returnValue; }
	 * 
	 * public String doConnect(Host host, RemoteCommandExecutor service) {
	 * String returnValue = null; setHostParameter(service, host); try {
	 * service.simplExec(RemoteCommandExecutorApiGetter.CONECTION_TEST_COMMAND);
	 * returnValue = CONNECTION_OK; } catch (Throwable e) { if
	 * (e.getMessage().contains(
	 * RemoteCommandExecutorApiGetter.CONNECTION_FAILURE)) returnValue =
	 * CONNECTION_FAILURE; if (e.getMessage().contains(
	 * RemoteCommandExecutorApiGetter.AUTHENTICATION_FAILURE)) returnValue =
	 * AUTHENTICATION_FAILURE; } return returnValue; }
	 */

	public void setHostParameter(RemoteCommandExecutor service, Host host) {
		service.setHost(host);
	}
	/*
	 * public void connect(Host host){ def service =
	 * rceServiceGetter.getNewService(host); doConnect(host, service); }
	 */
}