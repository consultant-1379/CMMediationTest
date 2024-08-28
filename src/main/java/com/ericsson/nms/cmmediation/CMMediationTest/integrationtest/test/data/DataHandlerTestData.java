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
package com.ericsson.nms.cmmediation.CMMediationTest.integrationtest.test.data;

import org.testng.annotations.DataProvider;

import com.ericsson.cifwk.taf.TestData;
import com.ericsson.nms.cmmediation.CMMediationTest.integrationtest.data.DataHandlerDataProvider;

/**
 * Test DataProvider for executing Test Cases for DataHandlerTest
 */

public class DataHandlerTestData implements TestData {
	static DataHandlerDataProvider dataProvider = new DataHandlerDataProvider();

	/**
	 * Method to retrieve the host data
	 * @return host info
	 */
	@DataProvider(name = "hostTestData")
	public static Object[][] provideHostData() {
		return new Object[][] { { "eap" } };
	}
}
