package com.ericsson.nms.cmmediation.CMMediationTest.integrationtest.test.cases;

import java.util.List;

import org.testng.annotations.Test;
import com.ericsson.cifwk.taf.TorTestCaseHelper;
import com.ericsson.cifwk.taf.TestCase;
import se.ericsson.jcat.fw.annotations.Setup;
import com.ericsson.cifwk.taf.annotations.Context;
import com.ericsson.cifwk.taf.annotations.VUsers;
import com.ericsson.cifwk.taf.data.*;
import com.ericsson.cifwk.taf.exceptions.TestCaseNotImplementedException;

import com.ericsson.nms.cmmediation.CMMediationTest.integrationtest.getters.rest.EventBusMeasurement;
import com.ericsson.nms.cmmediation.CMMediationTest.integrationtest.operators.ModelledEventBusOperator;
import com.ericsson.nms.cmmediation.CMMediationTest.integrationtest.operators.rest.ModelledEventBusReceiverRestOperator;
import com.ericsson.nms.cmmediation.CMMediationTest.integrationtest.operators.rest.ModelledEventBusSenderRestOperator;
import com.ericsson.nms.cmmediation.CMMediationTest.integrationtest.test.data.ModelledEventBusTestDataProvider;

/**
 * 
 * Class to execute tests against ModelledEventBus a change to commit - ignore
 **/

public class ModelledEventBus extends TorTestCaseHelper implements TestCase {

	// ModelledEventBusOperator modelledEventBusOperator = new
	// ModelledEventBusOperator();

	ModelledEventBusOperator dummyOperator = new ModelledEventBusOperator();
	ModelledEventBusSenderRestOperator sender;
	ModelledEventBusReceiverRestOperator receiver;

	@Setup
	public void prepareTestCaseForTORSF477_Perf_3() {
		Host interest = null;
		List<Host> hosts = DataHandler.getHosts();

		for (Host h : hosts) {
			if (h.getType().equals(HostType.JBOSS)
					&& h.getHostname().equals("eap")) {
				interest = h;

			}
		}

		this.sender = new ModelledEventBusSenderRestOperator(interest);
		this.receiver = new ModelledEventBusReceiverRestOperator(interest);
	}

	/**
	 * /* As a user of Service Framework I want performance test of event BUS
	 * and of Modeled Event Bus module /* @DESCRIPTION It shall take no longer
	 * than 30 seconds (0.5 minute) to send 7.5 Million messages /* @PRE 2 JBoss
	 * instances available with jmsPerformanceSender deployed on JBoss1 &
	 * jmsPerformanceReceiver deployed on JBoss2 /* @PRIORITY HIGH
	 */
	@VUsers(vusers = { 1 })
	@Context(context = { Context.REST })
	@Test(groups = { "Acceptance" }, dataProvider = "ModelledEventBusTestData", dataProviderClass = ModelledEventBusTestDataProvider.class, enabled = true)
	public void asAUserOfServiceFrameworkIWantPerformanceTestOfEventBUSAndOfModeledEventBusModule(
			long quantity, long batches, long delay, int expectedTime,
			int sleepTime) {
		setTestcase(
				"TORSF-477_Perf_3",
				"As a user of Service Framework I want performance test of event BUS and of Modeled Event Bus module");

		setTestStep("Send set quantity of messages from Senders");
		receiver.clear();
		EventBusMeasurement sentInfo = sender.send(quantity, batches, delay);
		long messagesSent = sentInfo.getCount();
		double sendingTime = sentInfo.getTime();
		setTestInfo("--> Sent " + messagesSent);
		sleep(sleepTime);

		setTestStep("Set quantity of messages were sent");
		assertEquals(messagesSent, quantity * batches);

		setTestStep("Check time taken to send set quantity of messages");
		setTestInfo("--> msgs sent in " + sendingTime + " seconds.");

		setTestStep("Verify that all sent messages were received");
		EventBusMeasurement receiverInfo = receiver.getStats();
		long messagesReceived = receiverInfo.getCount();
		double receivingTime = sentInfo.getTime();
		setTestInfo("--> Received " + messagesReceived + " messages");

		setTestStep("Number of sent messages = Number of received messages");
		assertTrue(messagesReceived == messagesSent);

		setTestStep("Check time taken to receive all messages");
		setTestInfo("--> Messages received in " + receivingTime + " seconds");

		setTestStep("Time taken to receive all messages was below target time");
		setTestInfo("--> Receiving time: " + receivingTime
				+ " is <= Expected time: " + expectedTime);
		assertTrue(receivingTime <= expectedTime);

	}
}