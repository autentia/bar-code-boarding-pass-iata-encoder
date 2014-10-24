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
package com.autentia.bcbp.elements;

import com.autentia.bcbp.items.*;
import com.autentia.bcbp.items.Item.PaddingType;

public class MandatoryItemsUnique {

	private static int maxNumberOfLegs = 4;

	private static int formatCodeLength = 1;

	private static int numberOfLegsLength = 1;

	private static int passengerNameLength = 20;

	private static int electronicTicketIndicatorLength = 1;

	private static String standardFormatCode = "M";

	private static String initialNumberOfLegs = "1";

	private Item formatCode;

	private Item numberOfLegs;

	private Item passengerName;

	private Item electronicTicketIndicator;

	public MandatoryItemsUnique(String passengerName, String electronicTicketIndicator) {

		this.formatCode = new Item(standardFormatCode, formatCodeLength, 1, PaddingType.String);
		this.numberOfLegs = new Item(initialNumberOfLegs, numberOfLegsLength, 5, PaddingType.Number);
		this.passengerName = new Item(passengerName, passengerNameLength, 11, PaddingType.String);
		this.electronicTicketIndicator = new Item(electronicTicketIndicator, electronicTicketIndicatorLength, 253,
				PaddingType.String);
	}

	public MandatoryItemsUnique(String passengerName, String passengerSurname, String electronicTicketIndicator) {
		super();

		this.formatCode = new Item(standardFormatCode, formatCodeLength, 1, PaddingType.String);
		this.numberOfLegs = new Item(initialNumberOfLegs, numberOfLegsLength, 5, PaddingType.Number);
		this.passengerName = new Item(obtainCorrectName(passengerName, passengerSurname), passengerNameLength, 11,
				PaddingType.String);
		this.electronicTicketIndicator = new Item(electronicTicketIndicator, electronicTicketIndicatorLength, 253,
				PaddingType.String);
	}

	@Override
	public String toString() {
		final StringBuffer encoder = new StringBuffer();

		encoder.append(formatCode.getEncoded()).append(numberOfLegs.getEncoded()).append(passengerName.getEncoded())
				.append(electronicTicketIndicator.getEncoded());

		return encoder.toString();
	}

	public void setNumberOfSegments(int nLegs) {
		if (nLegs > maxNumberOfLegs) return;
		numberOfLegs.setValue(nLegs + "");
	}

	private String obtainCorrectName(String passengerName, String passengerSurname) {
		int maximumSurnameLength = 18;
		if (passengerSurname.length() > maximumSurnameLength) {
			String surname = passengerSurname.substring(0, maximumSurnameLength);
			String initial = passengerName.substring(0, 1);

			return surname + "/" + initial;
		}

		return passengerSurname + "/" + passengerName;
	}
}
