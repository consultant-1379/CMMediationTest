package com.ericsson.nms.cmmediation.CMMediationTest.integrationtest.test.data;

import java.util.*;

import com.ericsson.cifwk.taf.TestData;
import com.ericsson.cifwk.taf.data.DataHandler;
import com.ericsson.nms.cmmediation.utils.CsvDataType;
import com.ericsson.nms.cmmediation.utils.CsvFile;

import org.testng.annotations.DataProvider;

/**
 * 
 * Test DataProvider for executing Test Cases for ModelledEventBus
 */
public class ModelledEventBusTestDataProvider implements TestData {

	@DataProvider(name = "ModelledEventBusTestData")
	public static Object[][] ModelledEventBusTestData() {
		Map<String, CsvDataType> types = new HashMap<>();
		types.put("all", CsvDataType.INTEGER);

		Object[][] result = new CsvFile(
				DataHandler.readCsv("performancedata.csv"), types).getColumns(
				"quantity", "batches", "delay", "expectedTime", "sleep");
		return result;
	}
}