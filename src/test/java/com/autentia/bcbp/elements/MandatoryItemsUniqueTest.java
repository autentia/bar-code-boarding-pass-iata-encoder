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

import org.junit.*;

public class MandatoryItemsUniqueTest {

	@Test
	public void ifInitilizationIsCorrectShouldGenerateRightCodeTest() {
		String passengerName = "Manolo";
		String electronicTicketIndicator = "E";

		MandatoryItemsUnique mandatoryItemsUnique = new MandatoryItemsUnique(passengerName, electronicTicketIndicator);

		assertEquals("M1Manolo              E", mandatoryItemsUnique.toString());
	}

	@Test
	public void ifNameTooLongShouldCutItTest() {
		String passengerName = "0123456789012345678901234";
		String electronicTicketIndicator = "E";

		MandatoryItemsUnique mandatoryItemsUnique = new MandatoryItemsUnique(passengerName, electronicTicketIndicator);

		assertEquals("M101234567890123456789E", mandatoryItemsUnique.toString());
	}

	@Test
	public void ifElectronicTicketIndicatorTooLongShouldCutItTest() {
		String passengerName = "Manolo";
		String electronicTicketIndicator = "ABCD";

		MandatoryItemsUnique mandatoryItemsUnique = new MandatoryItemsUnique(passengerName, electronicTicketIndicator);

		assertEquals("M1Manolo              A", mandatoryItemsUnique.toString());
	}

	@Test
	public void ifNumberOfLegsIsChangedShouldGenerateCorrectCodeTest() {
		String passengerName = "Manolo";
		String electronicTicketIndicator = "E";
		int newNumberOfLegs = 4;

		MandatoryItemsUnique mandatoryItemsUnique = new MandatoryItemsUnique(passengerName, electronicTicketIndicator);

		mandatoryItemsUnique.setNumberOfSegments(newNumberOfLegs);

		assertEquals("M4Manolo              E", mandatoryItemsUnique.toString());
	}

	@Test
	public void ifNewNumberOfLegsIsTooHighShouldNotChangeItTest() {
		String passengerName = "Manolo";
		String electronicTicketIndicator = "E";
		int validNewNumberOfLegs = 2;
		int invalidNewNumberOfLegs = 5;

		MandatoryItemsUnique mandatoryItemsUnique = new MandatoryItemsUnique(passengerName, electronicTicketIndicator);

		mandatoryItemsUnique.setNumberOfSegments(validNewNumberOfLegs);

		assertEquals("M2Manolo              E", mandatoryItemsUnique.toString());

		mandatoryItemsUnique.setNumberOfSegments(invalidNewNumberOfLegs);

		assertEquals("M2Manolo              E", mandatoryItemsUnique.toString());
	}

	@Test
	public void ifPassengerNameIsTooLongShouldBeTruncatedTest() {
		String passengerName = "NameTooLongToFitInName";
		String passengerSurname = "Surname";
		String electronicTicketIndicator = "E";

		MandatoryItemsUnique mandatoryItemsUnique = new MandatoryItemsUnique(passengerName, passengerSurname,
				electronicTicketIndicator);

		assertEquals("M1Surname/NameTooLongTE", mandatoryItemsUnique.toString());
	}

	@Test
	public void ifPassengerSurnameIsTooLongShouldBeTruncatedTest() {
		String passengerName = "Name";
		String passengerSurname = "SurnameTooLongToFitInName";
		String electronicTicketIndicator = "E";

		MandatoryItemsUnique mandatoryItemsUnique = new MandatoryItemsUnique(passengerName, passengerSurname,
				electronicTicketIndicator);

		assertEquals("M1SurnameTooLongToFi/NE", mandatoryItemsUnique.toString());
	}

	@Test
	public void ifPassengerNameAndSurnameAreOKShouldReturnCorrectCodeTest() {
		String passengerName = "Name";
		String passengerSurname = "Surname";
		String electronicTicketIndicator = "E";

		MandatoryItemsUnique mandatoryItemsUnique = new MandatoryItemsUnique(passengerName, passengerSurname,
				electronicTicketIndicator);

		assertEquals("M1Surname/Name        E", mandatoryItemsUnique.toString());
	}
}
