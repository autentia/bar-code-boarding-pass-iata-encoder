/**
 * Bar Code Boarding Pass Encoder by Autentia Real Bussiness Solution S.L. Copyright (C) 2014 Autentia Real Bussiness
 * Solution S.L. This program is free software: you can redistribute it and/or modify it under the terms of the GNU General
 * Public License as published by the Free Software Foundation, either version 3 of the License. This program is distributed
 * in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details. You should have received a copy of
 * the GNU General Public License along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package com.autentia.bcbp.elements;

import java.util.*;

import org.apache.commons.lang3.*;

import com.autentia.bcbp.items.*;
import com.autentia.bcbp.items.Item.PaddingType;

public class MandatoryItemsRepeated {

	private static int maxVariableFieldSize = 255;

	private static int operatingCarrierPNRCodeLength = 7;

	private static int fromCityAirportCodeLength = 3;

	private static int toCityAirportCodeLength = 3;

	private static int operatingCarrierDesignatorLength = 3;

	private static int flightNumberLength = 5;

	private static int dateOfFlightLength = 3;

	private static int compartmentCodeLength = 1;

	private static int seatNumberLength = 4;

	private static int chekinSequenceNumberLength = 5;

	private static int passengerStatusLength = 1;

	private static int variableFieldSizeLength = 2;

	private Item operatingCarrierPNRCode;

	private Item fromCityAirportCode;

	private Item toCityAirportCode;

	private Item operatingCarrierDesignator;

	private Item flightNumber;

	private Item dateOfFlight;

	private Item compartmentCode;

	private Item seatNumber;

	private Item chekinSequenceNumber;

	private Item passengerStatus;

	private Item variableFieldSize;

	public MandatoryItemsRepeated(String operatingCarrierPNRCode, String fromCityAirportCode, String toCityAirportCode,
			String operatingCarrierDesignator, String flightNumber, Date dateOfFlight, String compartmentCode,
			String seatNumber, String checkinSequenceNumber, String passengerStatus) {

		final Calendar calendar = Calendar.getInstance();

		calendar.setTime(dateOfFlight);

		this.operatingCarrierPNRCode = new Item(operatingCarrierPNRCode, operatingCarrierPNRCodeLength, 7,
				PaddingType.String);

		this.fromCityAirportCode = new Item(fromCityAirportCode, fromCityAirportCodeLength, 26, PaddingType.String);

		this.toCityAirportCode = new Item(toCityAirportCode, toCityAirportCodeLength, 38, PaddingType.String);

		this.operatingCarrierDesignator = new Item(operatingCarrierDesignator, operatingCarrierDesignatorLength, 42,
				PaddingType.String);

		this.flightNumber = new Item(flightNumber, flightNumberLength, 43, PaddingType.NumberFollowedByAlpha);

		this.dateOfFlight = new Item(String.valueOf(calendar.get(Calendar.DAY_OF_YEAR)), dateOfFlightLength, 46,
				PaddingType.Number);

		this.compartmentCode = new Item(compartmentCode, compartmentCodeLength, 71, PaddingType.String);

		if (StringUtils.isBlank(seatNumber)) {
			this.seatNumber = new Item(checkinSequenceNumber, seatNumberLength, 104, PaddingType.Number);
		} else {
			this.seatNumber = new Item(seatNumber, seatNumberLength, 104, PaddingType.Number);
		}

		this.chekinSequenceNumber = new Item(checkinSequenceNumber, chekinSequenceNumberLength, 107,
				PaddingType.NumberFollowedByAlpha);

		this.passengerStatus = new Item(passengerStatus, passengerStatusLength, 113, PaddingType.String);

		this.variableFieldSize = new Item("00", variableFieldSizeLength, 6, PaddingType.Number);
	}

	@Override
	public String toString() {
		final StringBuffer encoder = new StringBuffer();

		encoder.append(operatingCarrierPNRCode.getEncoded()).append(fromCityAirportCode.getEncoded())
				.append(toCityAirportCode.getEncoded()).append(operatingCarrierDesignator.getEncoded())
				.append(flightNumber.getEncoded()).append(dateOfFlight.getEncoded())
				.append(compartmentCode.getEncoded()).append(seatNumber.getEncoded())
				.append(chekinSequenceNumber.getEncoded()).append(passengerStatus.getEncoded())
				.append(variableFieldSize.getEncoded());

		return encoder.toString();
	}

	public void setVariableFieldSize(int variableFieldSize) {
		if (variableFieldSize > maxVariableFieldSize) return;

		String hexadecimalValue = Integer.toHexString(variableFieldSize);

		this.variableFieldSize.setValue(hexadecimalValue);
	}

	public void setHexadecimalVariableFieldSize(String variableFieldSize) {
		this.variableFieldSize.setValue(variableFieldSize);
	}
}
