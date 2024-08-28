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
package com.ericsson.nms.cmmediation.CMMediationTest.integrationtest.getters.rest;

public class EventBusMeasurement {

	private long count;

	private double time;
	private double rate;

	public EventBusMeasurement(String count, String time) {
		this(Long.parseLong(count), Double.parseDouble(time));
	}

	public EventBusMeasurement(long count, double time) {
		this.count = count;
		this.time = time;
		this.rate = ((double) this.count) / this.time;
	}

	public long getCount() {
		return count;
	}

	public double getTime() {
		return time;
	}

	public double getRate() {
		return rate;
	}

}
