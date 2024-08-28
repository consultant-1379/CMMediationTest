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

public class Url {

	private Queue<Pair<String, String>> parameters;
	private String base;

	public Url(String base) {
		this.base = base;
		this.parameters = new LinkedList<>();
	}

	public Url and(String parameter, String value) {
		this.parameters.add(new Pair<String, String>(parameter, value));
		return this;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.base);
		if (parameters.isEmpty()) {
			return base;
		}
		sb.append("?");
		while (!parameters.isEmpty()) {
			Pair<String, String> pair = parameters.poll();
			sb.append(pair.getLeft()).append("=").append(pair.getRight());
			if (!parameters.isEmpty()) {
				sb.append("&");
			}
		}
		return sb.toString();
	}

}
