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
package com.ericsson.nms.cmmediation.CMMediationTest.integrationtest.getters.api;

import com.ericsson.cifwk.taf.data.Host;
import com.ericsson.cifwk.taf.handlers.netsim.NetsimHandler;

public class NetsimHandlerApiGetter {
	private static NetsimHandler netsimHandler;

	public NetsimHandler getService(Host host){
		if (netsimHandler == null){
			netsimHandler = new NetsimHandler(host);
		}
		return netsimHandler;
	}
	
	public NetsimHandler getNewService(Host host){
		return new NetsimHandler(host);
	}
}
