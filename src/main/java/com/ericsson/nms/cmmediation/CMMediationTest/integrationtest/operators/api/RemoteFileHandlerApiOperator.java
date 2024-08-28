package com.ericsson.nms.cmmediation.CMMediationTest.integrationtest.operators.api;

import org.apache.log4j.Logger;

import com.ericsson.cifwk.taf.ApiOperator;
import com.ericsson.cifwk.taf.data.Host;
import com.ericsson.cifwk.taf.handlers.RemoteFileHandler;
import com.ericsson.nms.cmmediation.CMMediationTest.integrationtest.getters.api.RemoteFileHandlerApiGetter;

public class RemoteFileHandlerApiOperator implements ApiOperator{

    private final Logger logger = Logger.getLogger(RemoteFileHandlerApiOperator.class);
    RemoteFileHandlerApiGetter rfhServiceGetter = new RemoteFileHandlerApiGetter();


	public boolean compareRemoteFiles(Host host1, Host host2, String filepath1, String filepath2){
		return rfhServiceGetter.getTestedService().compareRemoteFiles(host1, filepath1, host2, filepath2);
	}

	public boolean copyRemoteFile(Host host, String file){
		RemoteFileHandler handler = rfhServiceGetter.getTestedService(host);

		String newFile = file.split("/")[1];
		return handler.copyRemoteFileToLocal(file, newFile);
	}
}
