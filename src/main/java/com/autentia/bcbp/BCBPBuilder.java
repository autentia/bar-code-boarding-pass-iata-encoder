/**
 * Bar Code Boarding Pass Encoder by Autentia Real Bussiness Solution S.L.
 * Copyright (C) 2014 Autentia Real Bussiness Solution S.L.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.autentia.bcbp;

import java.util.*;

import com.autentia.bcbp.elements.*;
import com.autentia.bcbp.segments.*;

public class BCBPBuilder {

	private static final int maxNumberOfSecondarySegmentsLegs = 3;

	private PrimarySegment primarySegment;

	private ArrayList<SecondarySegment> secondarySegments;

	private SecurityItems securityItems;

	public BCBPBuilder(PrimarySegment primarySegment) {
		this.primarySegment = primarySegment;
		secondarySegments = new ArrayList<SecondarySegment>();
		this.securityItems = new SecurityItems("", "");
	}

	public void addSecondarySegment(SecondarySegment secondarySegment) {
		if (secondarySegments.size() < maxNumberOfSecondarySegmentsLegs) this.secondarySegments.add(secondarySegment);
	}

	public void addSecurityItems(SecurityItems securityItems) {
		this.securityItems = securityItems;
	}

	public String toEncoded() {
		final StringBuffer code = new StringBuffer();

		int numSecondarySegments = secondarySegments.size();
		primarySegment.setNumberOfSegments(1 + numSecondarySegments);
		code.append(primarySegment.toString());

		for (int i = 0; i < numSecondarySegments; i++) {
			code.append(secondarySegments.get(i).toString());
		}

		code.append(securityItems.toString());

		return code.toString().toUpperCase();
	}
}
