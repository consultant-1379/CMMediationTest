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

public class Row {

	HashMap<String, Object> data;

	public Row(List<String> rowDataString, List<String> headers,
			Map<String, CsvDataType> types) {
		this.data = new HashMap<>();
		if (headers == null) {
			headers = new ArrayList<>();
			for (int i = 0; i < rowDataString.size(); i++) {
				headers.add(Integer.toString(i));
			}
		}
		if (rowDataString.size() != headers.size()) {
			throw new IllegalArgumentException(
					"Columns and headers are not 1:1");
		}
		for (int i = 0; i < rowDataString.size(); i++) {
			String header = headers.get(i);
			String item = rowDataString.get(i);
			Object itemCast = null;
			CsvDataType type = null;
			if (types.get("all") != null) {
				type = types.get("all");
			}
			if (types.get(header) != null) {
				type = types.get(header);
			}
			itemCast = cast(type, item);
			this.data.put(header, itemCast);
		}
	}

	/**
	 * @param type
	 * @param item
	 * @return
	 */
	private Object cast(CsvDataType type, String item) {
		Object itemCast = item;
		try {
			if (type == CsvDataType.INTEGER) {
				itemCast = Integer.parseInt(item);
			} else if (type == CsvDataType.DECIMAL) {
				itemCast = Double.parseDouble(item);
			} else if (type == CsvDataType.BOOLEAN) {
				itemCast = Boolean.parseBoolean(item);
			}
		} catch (NumberFormatException nfe) {
			System.out.println("Number Format Exception !!!");
			return itemCast;
		}
		return itemCast;
	}

	public String get(String header) {
		return (String) data.get(header);
	}

	public String get(int columnNumber) {
		return (String) data.get(Integer.toString(columnNumber));
	}

	public Object[] getArray(String[] keys) {
		Object[] answer = new Object[keys.length];
		for (int i = 0; i < keys.length; i++) {
			answer[i] = data.get(keys[i]);
		}
		return answer;
	}
}
