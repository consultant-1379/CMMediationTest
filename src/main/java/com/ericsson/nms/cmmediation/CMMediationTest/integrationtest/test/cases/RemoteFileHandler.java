package com.ericsson.nms.cmmediation.CMMediationTest.integrationtest.test.cases;

import org.testng.annotations.Test;

import com.ericsson.cifwk.taf.TestCase;
import com.ericsson.cifwk.taf.TorTestCaseHelper;
import com.ericsson.cifwk.taf.annotations.Context;
import com.ericsson.cifwk.taf.annotations.VUsers;
import com.ericsson.cifwk.taf.data.Host;
import com.ericsson.nms.cmmediation.CMMediationTest.integrationtest.operators.RemoteFileHandlerOperator;
import com.ericsson.nms.cmmediation.CMMediationTest.integrationtest.test.data.RemoteFileHandlerTestDataProvider;

/**
 * This class tests an API TAF provides which allows you to compare 2 remote files and copy a remote file to the local machine.
 *
 * @author ethomev
 *
 */
public class RemoteFileHandler extends TorTestCaseHelper implements TestCase {
	RemoteFileHandlerOperator remoteFileHandlerOperator = new RemoteFileHandlerOperator();

	/**
	 * /* compareRemoteFiles /* @DESCRIPTION Verify that 2 files which are on
	 * seperate hosts can successfully be compared. Their md5 checksums and
	 * filesizes are compared. /* NOTE: This is the only test for this component
	 * as all the other methods just created an instance of the
	 * SshRemoteCommandExceutor and executed a command, /* returning true or
	 * false. All those commands have been included as commands in the
	 * SshRemoteCommandExecutor performance test. /* @PRE Two hosts with the
	 * same file on each host. /* @PRIORITY HIGH
	 */
	@Context(context = { Context.API })
	@VUsers(vusers = { 1 })
	@Test(groups = { "Acceptance Test" }, dataProvider = "remoteHostData", dataProviderClass = RemoteFileHandlerTestDataProvider.class)
	public void compareRemoteFiles(Host host1, Host host2, String filepath1,
			String filepath2) {
		setTestCase("CIP-962_Func_2", "compareRemoteFiles");

		setTestStep("Compare Two Remote Files and verify Correct Result is returned");
		assert remoteFileHandlerOperator.compareRemoteFiles(host1, host2,
				filepath1, filepath2) == remoteFileHandlerOperator
				.expectedCompareRemoteFilesResult(host1, host2, filepath1,
						filepath2);
	}

	/**
	 * /* Copy Remote File to Local /* @DESCRIPTION Verify that a file can be
	 * copied from a remote machine to the local machine. The File is copied to
	 * the home directory of the local machine /* @PRE A remote host with a file
	 * to copy on it /* @PRIORITY HIGH
	 */
	@VUsers(vusers = { 1 })
	@Context(context = { Context.API })
	@Test(groups = { "Acceptance Test" }, dataProvider = "remoteFileData", dataProviderClass = RemoteFileHandlerTestDataProvider.class)
	public void copyRemoteFileToLocal(Host host, String file) {
		setTestcase("CIP-1665_Func_1", "Copy Remote File to Local");

		setTestStep("Copy the file to the local machine and verify operation is successful");
		assert remoteFileHandlerOperator.copyRemoteFile(host, file) == remoteFileHandlerOperator.expectedCopyRemoteFileResult(file);

	}
}