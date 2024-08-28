package com.ericsson.nms.cmmediation.CMMediationTest.integrationtest.data;

import java.util.*;

import com.ericsson.cifwk.taf.DataProvider;
import com.ericsson.cifwk.taf.data.DataHandler;
import com.ericsson.cifwk.taf.data.Host;
import com.ericsson.cifwk.taf.utils.csv.CsvReader;

/**
 * 
 * DataProvider for executing Test Cases for ModelledEventBus
 */
public class ModelledEventBusDataProvider implements DataProvider {

	public static final String DATA_FILE = "messagedata.csv";
	public static final int COUNTS_COLUMN = 0;
	public static final int X_COLUMN = 1;
	public static final int Y_COLUMN = 2;
	public static final int ROUNDS_COLUMN = 3;
	public static final int TIME_COLUMN = 4;
	public static final int RECEIVED_COLUMN = 5;
	public static final int RECEIVED2_COLUMN = 6;

	/*
	 * Test Data is fetched from a file that is an output of test analysis, but
	 * it may be a combination of test analysis data and dynamic reconciliation
	 * of data
	 */

	public static String getCountsSend() {
		String counts = null;
		String x;
		String y;
		String rounds = null;
		String time;
		String received;
		String received2;

		List<List<String>> result = new ArrayList<List<String>>();
		/*
		 * DataHandler should be the main entity accessed from Data Provider
		 */
		CsvReader testItems = DataHandler.readCsv(DATA_FILE);
		int rows = testItems.getRowCount();
		if (rows == 2) {
			counts = testItems.getCell(COUNTS_COLUMN, 1);
			rounds = testItems.getCell(ROUNDS_COLUMN, 1);
		} else {
			for (int i = 1; i < testItems.getRowCount(); i++) {
				counts = testItems.getCell(COUNTS_COLUMN, i);
				rounds = testItems.getCell(ROUNDS_COLUMN, i);
				result.add(Arrays.asList(counts, rounds));
				System.out.println("In Dataproiver results " + result);
			}
		}
		return counts;
	}
}
