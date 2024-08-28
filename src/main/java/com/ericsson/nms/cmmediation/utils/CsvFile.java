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
package com.ericsson.nms.cmmediation.utils;

import java.util.*;

import com.ericsson.cifwk.taf.utils.csv.CsvReader;

public class CsvFile {

	private List<Row> rows;
	private CsvReader reader;

	/**
	 * Create a csv file from a provided csv reader. A CSV object contains rows
	 * which can be accessed based on the header strings in the underlying file. <br>
	 * This constructor allows for reading headerless CSV files. This is not
	 * recommended for code readability purposes.
	 * 
	 * @param readCsv
	 *            The TAF csvReader to use.
	 * @param types
	 *            A map that provides type information for the columns. The
	 *            column name is the key, and the type uses the enum
	 *            CsvDataType. The <b>"all"</b> key can be used to type all
	 *            columns.
	 * @param hasHeaders
	 *            When false disables the use of headers when reading the CSV.
	 */
	public CsvFile(CsvReader reader, Map<String, CsvDataType> types,
			boolean hasHeaders) {
		this.reader = reader;
		rows = new ArrayList<>();
		if (hasHeaders) {
			for (int i = 1; i < reader.getRowCount(); i++) {

				rows.add(new Row(reader.getRow(i), reader.getHeaders(), types));
			}
		} else {
			for (int i = 0; i < reader.getRowCount(); i++) {
				rows.add(new Row(reader.getRow(i), null, types));
			}
		}
	}

	/**
	 * Create a csv file from a provided csv reader. A CSV object contains rows
	 * which can be accessed based on the header strings in the underlying file. <br>
	 * <br>
	 * This constructor assumes the first line of the underlying CSV file
	 * contains headers.
	 * 
	 * @param readCsv
	 *            The TAF csvReader to use.
	 * @param types
	 *            A map that provides type information for the columns. The
	 *            column name is the key, and the type uses the enum
	 *            CsvDataType. The <b>"all"</b> key can be used to type all
	 *            columns.
	 */
	public CsvFile(CsvReader readCsv, Map<String, CsvDataType> types) {
		this(readCsv, types, true);
	}

	/**
	 * @return All rows from this CSV File
	 */
	public List<Row> getRows() {
		return this.rows;
	}

	/**
	 * By providing the names of columns an array of rows can be returned in 2D
	 * array format. This is suited to the return type of TestNG DataProvider
	 * methods.
	 * 
	 * @param columns
	 *            A variable length argument containing the ordered list of
	 *            columns to be returned
	 * @return An array of rows from the csv file corresponding to the provided
	 *         headers
	 */
	public Object[][] getColumns(String... columns) {
		Object[][] answer = new Object[this.rows.size()][columns.length];
		for (int i = 0; i < this.rows.size(); i++) {
			answer[i] = this.rows.get(i).getArray(columns);
		}
		return answer;
	}
}
