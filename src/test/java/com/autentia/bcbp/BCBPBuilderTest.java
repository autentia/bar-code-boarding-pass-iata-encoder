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
import static org.mockito.Mockito.*;

import java.util.*;

import org.junit.*;

import com.autentia.bcbp.elements.*;
import com.autentia.bcbp.segments.*;

public class BCBPBuilderTest {

	private static final String securityItemsCode = "^114GIWVC5EH7JNT684FVNJ9";

	private static final String additionalInformationCode = "LX58Z";

	private static final String conditionalItemsRepeatedCode = "2A0141234567890 1AC AC 1234567890123    2PCY";

	private static final String conditionalItemsUniqueCode = ">5181WW6225BAC 0085123456003";

	private static final String mandatoryItemsRepeatedCode = "ABC123 YULFRAAC 0834 226F001A0025 167";

	private static final String mandatoryItemsUniqueCode = "M1DESMARAIS/LJC       E";

	private static final String primarySegmentCode = mandatoryItemsUniqueCode + mandatoryItemsRepeatedCode
			+ conditionalItemsUniqueCode + conditionalItemsRepeatedCode + additionalInformationCode;

	private static final String secondSegmentCode = mandatoryItemsRepeatedCode + conditionalItemsRepeatedCode
			+ additionalInformationCode;

	private PrimarySegment primarySegment = mock(PrimarySegment.class);

	private SecondarySegment secondarySegment = mock(SecondarySegment.class);

	private SecurityItems securityItems = mock(SecurityItems.class);

	@Before
	public void init() {
		when(primarySegment.toString()).thenReturn(primarySegmentCode);
		when(secondarySegment.toString()).thenReturn(secondSegmentCode);
		when(securityItems.toString()).thenReturn(securityItemsCode);
	}

	@Test
	public void correctBCBPWithOnlyOneSegmentShouldProvideCorrectCode() {
		BCBPBuilder builder = new BCBPBuilder(primarySegment);

		builder.addSecurityItems(securityItems);

		assertEquals(primarySegmentCode + securityItemsCode, builder.toEncoded());

		verify(primarySegment, times(1)).setNumberOfSegments(1);
	}

	@Test
	public void correctBCBPWithOneSegmentAndWithoutSecurityItemsShouldProvideCorrectCode() {
		BCBPBuilder builder = new BCBPBuilder(primarySegment);

		assertEquals(primarySegmentCode, builder.toEncoded());

		verify(primarySegment, times(1)).setNumberOfSegments(1);
	}

	@Test
	public void correctBCBPWithTwoSegmentShouldProvideCorrectCode() {
		BCBPBuilder builder = new BCBPBuilder(primarySegment);

		builder.addSecondarySegment(secondarySegment);
		builder.addSecurityItems(securityItems);

		assertEquals(primarySegmentCode + secondSegmentCode + securityItemsCode, builder.toEncoded());

		verify(primarySegment, times(1)).setNumberOfSegments(2);
	}

	@Test
	public void correctBCBPShouldProvideCorrectCodeIT() {
		final String originalMessage = "M1GARCIAJEREZ/ANGEL   EFR4P48 PMIMADUX 6048 285Y006A0004 362>5320      B                                          2A99624021709610                           N";
		final Calendar date = Calendar.getInstance();

		date.set(2014, 9, 12);

		MandatoryItemsUnique mandatoryItemsUnique = new MandatoryItemsUnique("ANGEL", "GARCIAJEREZ", "E");
		MandatoryItemsRepeated mandatoryItemsRepeated = new MandatoryItemsRepeated("FR4P48", "PMI", "MAD", "UX",
				"6048", date.getTime(), "Y", "006A", "0004 ", "3");

		PrimarySegment primarySegment = new PrimarySegment(mandatoryItemsUnique, mandatoryItemsRepeated);
		ConditionalItemsUnique conditionalItemsUnique = new ConditionalItemsUnique.ConditionalItemsUniqueBuilder()
				.documentType("B").passengerDescription("0").createConditionalItemsUnique();

		ConditionalItemsRepeated conditionalItemsRepeated = new ConditionalItemsRepeated.ConditionalItemsRepeatedBuilder()
				.airlineNumericCode("996").serialNumber("2402170961").selecteeIndicator("0").fastTrack("N")
				.createConditionalItemsRepeated();

		primarySegment.addConditionalItemsUnique(conditionalItemsUnique);
		primarySegment.addConditionalItemsRepeated(conditionalItemsRepeated);

		BCBPBuilder builder = new BCBPBuilder(primarySegment);

		assertEquals(originalMessage, builder.toEncoded());
	}
}
