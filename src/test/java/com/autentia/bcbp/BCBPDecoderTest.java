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
