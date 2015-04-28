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

import static org.junit.Assert.*;

import java.util.*;

import org.junit.*;

public class MandatoryItemsRepeatedTest {

	private Date date;

	@Before
	public void setUpBeforeEachTest() {
		final Calendar calendar = Calendar.getInstance();
		calendar.set(2014, 7, 14);

		date = calendar.getTime();
	}

	@Test
	public void ifInitilizationIsCorrectShouldGenerateRightCodeTest() {
		String operatingCarrierPNRCode = "ABC123";
		String fromCityAirportCode = "YUL";
		String toCityAirportCode = "FRA";
		String operatingCarrierDesignator = "AC";
		String flightNumber = "0834";
		String compartmentCode = "F";
		String seatNumber = "001A";
		String chekinSequenceNumber = "0025";
		String passengerStatus = "1";

		MandatoryItemsRepeated mandatoryItemsRepeated = new MandatoryItemsRepeated(operatingCarrierPNRCode,
				fromCityAirportCode, toCityAirportCode, operatingCarrierDesignator, flightNumber, date,
				compartmentCode, seatNumber, chekinSequenceNumber, passengerStatus);

		assertEquals("ABC123 YULFRAAC 0834 226F001A0025 100", mandatoryItemsRepeated.toString());
	}

	@Test
	public void ifVariableFieldSizeIsSetWithStringShouldGenerateRightCodeTest() {
		String operatingCarrierPNRCode = "ABC123";
		String fromCityAirportCode = "YUL";
		String toCityAirportCode = "FRA";
		String operatingCarrierDesignator = "AC";
		String flightNumber = "0834";
		String compartmentCode = "F";
		String seatNumber = "001A";
		String chekinSequenceNumber = "0025";
		String passengerStatus = "1";
		String variableFieldSize = "20";

		MandatoryItemsRepeated mandatoryItemsRepeated = new MandatoryItemsRepeated(operatingCarrierPNRCode,
				fromCityAirportCode, toCityAirportCode, operatingCarrierDesignator, flightNumber, date,
				compartmentCode, seatNumber, chekinSequenceNumber, passengerStatus);

		mandatoryItemsRepeated.setHexadecimalVariableFieldSize(variableFieldSize);

		assertEquals("ABC123 YULFRAAC 0834 226F001A0025 120", mandatoryItemsRepeated.toString());
	}

	@Test
	public void ifVariableFieldSizeIsSetWithNumberShouldGenerateRightCodeTest() {
		String operatingCarrierPNRCode = "ABC123";
		String fromCityAirportCode = "YUL";
		String toCityAirportCode = "FRA";
		String operatingCarrierDesignator = "AC";
		String flightNumber = "0834";
		String compartmentCode = "F";
		String seatNumber = "001A";
		String chekinSequenceNumber = "0025";
		String passengerStatus = "1";
		int variableFieldSize = 20;

		MandatoryItemsRepeated mandatoryItemsRepeated = new MandatoryItemsRepeated(operatingCarrierPNRCode,
				fromCityAirportCode, toCityAirportCode, operatingCarrierDesignator, flightNumber, date,
				compartmentCode, seatNumber, chekinSequenceNumber, passengerStatus);

		mandatoryItemsRepeated.setVariableFieldSize(variableFieldSize);

		assertEquals("ABC123 YULFRAAC 0834 226F001A0025 114", mandatoryItemsRepeated.toString());
	}

	@Test
	public void ifVariableFieldSizeIsSetWithNumberTooHighShouldNotChangeVariableFieldSizeTest() {
		String operatingCarrierPNRCode = "ABC123";
		String fromCityAirportCode = "YUL";
		String toCityAirportCode = "FRA";
		String operatingCarrierDesignator = "AC";
		String flightNumber = "0834";
		String compartmentCode = "F";
		String seatNumber = "001A";
		String chekinSequenceNumber = "0025";
		String passengerStatus = "1";
		int validVariableFieldSize = 20;
		int invalidVariableFieldSize = 600;

		MandatoryItemsRepeated mandatoryItemsRepeated = new MandatoryItemsRepeated(operatingCarrierPNRCode,
				fromCityAirportCode, toCityAirportCode, operatingCarrierDesignator, flightNumber, date,
				compartmentCode, seatNumber, chekinSequenceNumber, passengerStatus);

		mandatoryItemsRepeated.setVariableFieldSize(validVariableFieldSize);

		assertEquals("ABC123 YULFRAAC 0834 226F001A0025 114", mandatoryItemsRepeated.toString());

		mandatoryItemsRepeated.setVariableFieldSize(invalidVariableFieldSize);

		assertEquals("ABC123 YULFRAAC 0834 226F001A0025 114", mandatoryItemsRepeated.toString());
	}

	@Test
	public void seatNumberPositionShouldFillWithCheckinSequenceWhenSeatNumberIsEmpty() {
		String operatingCarrierPNRCode = "ABC123";
		String fromCityAirportCode = "YUL";
		String toCityAirportCode = "FRA";
		String operatingCarrierDesignator = "AC";
		String flightNumber = "0834";
		String compartmentCode = "F";
		String seatNumber = "";
		String chekinSequenceNumber = "0025";
		String passengerStatus = "1";
		int validVariableFieldSize = 20;
		int invalidVariableFieldSize = 600;

		MandatoryItemsRepeated mandatoryItemsRepeated = new MandatoryItemsRepeated(operatingCarrierPNRCode,
				fromCityAirportCode, toCityAirportCode, operatingCarrierDesignator, flightNumber, date,
				compartmentCode, seatNumber, chekinSequenceNumber, passengerStatus);

		mandatoryItemsRepeated.setVariableFieldSize(validVariableFieldSize);

		assertEquals("ABC123 YULFRAAC 0834 226F00250025 114", mandatoryItemsRepeated.toString());

	}
}
