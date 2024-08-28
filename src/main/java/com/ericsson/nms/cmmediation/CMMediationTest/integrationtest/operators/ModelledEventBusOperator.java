package com.ericsson.nms.cmmediation.CMMediationTest.integrationtest.operators;

import com.ericsson.cifwk.taf.GenericOperator;
import com.ericsson.cifwk.taf.annotations.Context;
import com.ericsson.nms.cmmediation.CMMediationTest.integrationtest.data.ModelledEventBusDataProvider;
import com.ericsson.nms.cmmediation.CMMediationTest.integrationtest.getters.rest.ModelledEventBusRestGetter;
import com.ericsson.nms.cmmediation.CMMediationTest.integrationtest.operators.rest.RestToolExampleRestOperator;

/**
 * 
 * Operator for executing Test Cases for ModelledEventBus
 */
public class ModelledEventBusOperator implements GenericOperator {

	// Dummy Operator Code generated to show the interworkings of the TAF
	// Classes
	String stringToOperate;

	public String operate(String name) {
		String text = "x";
		// TODO: here, i will send this message through
		// ModelledEventBusSenderRestOperator

		return text;
	}

	public String expected() {
		String text = "x";
		return text;
		// new String("name");//
		// ModelledEventBusDataProvider().getPrintedStrings().contains(stringToOperate);
		// use dataprovider to read csv file
	}
}