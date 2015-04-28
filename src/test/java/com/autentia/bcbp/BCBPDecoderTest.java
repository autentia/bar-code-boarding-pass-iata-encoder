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

import static org.junit.Assert.*;

import java.util.*;

import org.junit.*;

import com.autentia.bcbp.elements.*;
import com.autentia.bcbp.segments.*;

public class BCBPDecoderTest {

	private final String passengerName = "MANU";

	private final String passengerSurname = "SOTOMONTE";

	private final String electronicTicketIndicator = "E";

	private final String operatingCarrierPNRCode = "FR4P48";

	private final String compartmentCode = "Y";

	private final String seatNumber = "006A";

	private final String checkinSequenceNumber = "0004 ";

	private final String passengerStatus = "3";

	private final String flightNumber = "6048";

	private final String operatingCarrierDesignator = "UX";

	private final String passengerDescription = "0";

	private final String documentType = "B";

	private final String serialNumber = "2402170961";

	private final String airlineNumericCode = "996";

	private final String fastTrack = "N";

	private final String departureAirport = "PMI";

	private final String arrivalAirport = "MAD";

	private BCBPDecoder decoder;

	@Before
	public void setUpBeforeTest() {
		final Calendar date = Calendar.getInstance();

		date.set(2014, 9, 12);

		final MandatoryItemsUnique mandatoryItemsUnique = new MandatoryItemsUnique(passengerName, passengerSurname,
				electronicTicketIndicator);
		final MandatoryItemsRepeated mandatoryItemsRepeated = new MandatoryItemsRepeated(operatingCarrierPNRCode,
				departureAirport, arrivalAirport, operatingCarrierDesignator, flightNumber, date.getTime(),
				compartmentCode, seatNumber, checkinSequenceNumber, passengerStatus);

		final ConditionalItemsUnique conditionalItemsUnique = new ConditionalItemsUnique.ConditionalItemsUniqueBuilder()
				.documentType(documentType).passengerDescription(passengerDescription).createConditionalItemsUnique();
		final ConditionalItemsRepeated conditionalItemsRepeated = new ConditionalItemsRepeated.ConditionalItemsRepeatedBuilder()
				.airlineNumericCode(airlineNumericCode).serialNumber(serialNumber)
				.selecteeIndicator(passengerDescription).fastTrack(fastTrack).createConditionalItemsRepeated();

		final PrimarySegment primarySegment = new PrimarySegment(mandatoryItemsUnique, mandatoryItemsRepeated);
		primarySegment.addConditionalItemsUnique(conditionalItemsUnique);
		primarySegment.addConditionalItemsRepeated(conditionalItemsRepeated);

		final String code = new BCBPBuilder(primarySegment).toEncoded();
		decoder = new BCBPDecoder(code);
	}

	@Test
	public void getDepartureAirportCodeShouldReturnCorrectAirport() {
		assertEquals(departureAirport, decoder.getDepartureAirportCode());
	}

	@Test
	public void getArrivalAirportCodeShouldReturnCorrectAirport() {
		assertEquals(arrivalAirport, decoder.getArrivalAirportCode());
	}
}
