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
package com.autentia.bcbp.segments;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.*;

import com.autentia.bcbp.elements.*;

public class PrimarySegmentTest {

	private static final String additionalInformationCode = "LX58Z";

	private static final String conditionalItemsRepeatedCode = "2A0141234567890 1AC AC 1234567890123    2PCY";

	private static final String conditionalItemsUniqueCode = ">5181WW6225BAC 0085123456003";

	private static final String mandatoryItemsRepeatedCode = "ABC123 YULFRAAC 0834 226F001A0025 167";

	private static final String mandatoryItemsUniqueCode = "M1DESMARAIS/LJC       E";

	private static final String conditionalItemsUniqueEmptyCode = ">500";

	private static final String conditionalItemsRepeatedEmptyCode = "00";

	private MandatoryItemsUnique mandatoryItemsUnique = mock(MandatoryItemsUnique.class);

	private MandatoryItemsRepeated mandatoryItemsRepeated = mock(MandatoryItemsRepeated.class);

	private ConditionalItemsUnique conditionalItemsUnique = mock(ConditionalItemsUnique.class);

	private ConditionalItemsRepeated conditionalItemsRepeated = mock(ConditionalItemsRepeated.class);

	private AdditionalInformation additionalInformation = mock(AdditionalInformation.class);

	@Before
	public void init() {
		when(mandatoryItemsUnique.toString()).thenReturn(mandatoryItemsUniqueCode);
		when(mandatoryItemsRepeated.toString()).thenReturn(mandatoryItemsRepeatedCode);
		when(conditionalItemsUnique.toString()).thenReturn(conditionalItemsUniqueCode);
		when(conditionalItemsRepeated.toString()).thenReturn(conditionalItemsRepeatedCode);
		when(conditionalItemsUnique.emptyCode()).thenReturn(conditionalItemsUniqueEmptyCode);
		when(conditionalItemsRepeated.emptyCode()).thenReturn(conditionalItemsRepeatedEmptyCode);
		when(additionalInformation.toString()).thenReturn(additionalInformationCode);
	}

	@Test
	public void correctPrimarySegmentShouldProvideCorrectCodeTest() {
		PrimarySegment segment = new PrimarySegment(mandatoryItemsUnique, mandatoryItemsRepeated);

		segment.addConditionalItemsUnique(conditionalItemsUnique);
		segment.addConditionalItemsRepeated(conditionalItemsRepeated);
		segment.addAdditionalInformation(additionalInformation);

		assertEquals(mandatoryItemsUniqueCode + mandatoryItemsRepeatedCode + conditionalItemsUniqueCode
				+ conditionalItemsRepeatedCode + additionalInformationCode, segment.toString());

		verify(mandatoryItemsUnique, times(1)).setNumberOfSegments(1);
		verify(mandatoryItemsRepeated, times(1)).setVariableFieldSize(
				conditionalItemsUniqueCode.length() + conditionalItemsRepeatedCode.length()
						+ additionalInformationCode.length());
	}

	@Test
	public void primarySegmentWithoutAdditionalInformationShouldProvideCorrectCodeTest() {
		PrimarySegment segment = new PrimarySegment(mandatoryItemsUnique, mandatoryItemsRepeated);

		segment.addConditionalItemsUnique(conditionalItemsUnique);
		segment.addConditionalItemsRepeated(conditionalItemsRepeated);

		assertEquals(mandatoryItemsUniqueCode + mandatoryItemsRepeatedCode + conditionalItemsUniqueCode
				+ conditionalItemsRepeatedCode, segment.toString());

		verify(mandatoryItemsUnique, times(1)).setNumberOfSegments(1);
		verify(mandatoryItemsRepeated, times(1)).setVariableFieldSize(
				conditionalItemsUniqueCode.length() + conditionalItemsRepeatedCode.length());
	}

	@Test
	public void primarySegmentWithoutConditionalItemsRepeatedShouldProvideCorrectCodeTest() {
		PrimarySegment segment = new PrimarySegment(mandatoryItemsUnique, mandatoryItemsRepeated);

		segment.addConditionalItemsUnique(conditionalItemsUnique);
		segment.addAdditionalInformation(additionalInformation);

		assertEquals(mandatoryItemsUniqueCode + mandatoryItemsRepeatedCode + conditionalItemsUniqueCode
				+ conditionalItemsRepeatedEmptyCode + additionalInformationCode, segment.toString());

		verify(mandatoryItemsUnique, times(1)).setNumberOfSegments(1);
		verify(mandatoryItemsRepeated, times(1)).setVariableFieldSize(
				conditionalItemsUniqueCode.length() + conditionalItemsRepeatedEmptyCode.length()
						+ additionalInformationCode.length());
	}

	@Test
	public void primarySegmentWithoutConditionalItemsUniqueShouldProvideCorrectCodeTest() {
		PrimarySegment segment = new PrimarySegment(mandatoryItemsUnique, mandatoryItemsRepeated);

		segment.addConditionalItemsRepeated(conditionalItemsRepeated);
		segment.addAdditionalInformation(additionalInformation);

		assertEquals(mandatoryItemsUniqueCode + mandatoryItemsRepeatedCode + conditionalItemsUniqueEmptyCode
				+ conditionalItemsRepeatedCode + additionalInformationCode, segment.toString());

		verify(mandatoryItemsUnique, times(1)).setNumberOfSegments(1);
		verify(mandatoryItemsRepeated, times(1)).setVariableFieldSize(
				conditionalItemsUniqueEmptyCode.length() + conditionalItemsRepeatedCode.length()
						+ additionalInformationCode.length());
	}

	@Test
	public void primarySegmentWithoutConditionalItemsUniqueNorConditionalItemsRepeatedShouldProvideCorrectCodeTest() {
		PrimarySegment segment = new PrimarySegment(mandatoryItemsUnique, mandatoryItemsRepeated);

		segment.addAdditionalInformation(additionalInformation);

		assertEquals(mandatoryItemsUniqueCode + mandatoryItemsRepeatedCode + conditionalItemsUniqueEmptyCode
				+ conditionalItemsRepeatedEmptyCode + additionalInformationCode, segment.toString());

		verify(mandatoryItemsUnique, times(1)).setNumberOfSegments(1);
		verify(mandatoryItemsRepeated, times(1)).setVariableFieldSize(
				conditionalItemsUniqueEmptyCode.length() + conditionalItemsRepeatedEmptyCode.length()
						+ additionalInformationCode.length());
	}

	@Test
	public void primarySegmentWithOnlyMandatoryItemsShouldProvideCorrectCodeTest() {
		PrimarySegment segment = new PrimarySegment(mandatoryItemsUnique, mandatoryItemsRepeated);

		assertEquals(mandatoryItemsUniqueCode + mandatoryItemsRepeatedCode, segment.toString());

		verify(mandatoryItemsUnique, times(1)).setNumberOfSegments(1);
		verify(mandatoryItemsRepeated, times(1)).setVariableFieldSize(0);
	}

	@Test
	public void changingNumberOfSegmentsShouldCallSetNumberOfSegmentsTest() {
		int numberOfSegments = 2;

		PrimarySegment segment = new PrimarySegment(mandatoryItemsUnique, mandatoryItemsRepeated);

		segment.setNumberOfSegments(numberOfSegments);
		assertEquals(mandatoryItemsUniqueCode + mandatoryItemsRepeatedCode, segment.toString());

		verify(mandatoryItemsUnique, times(1)).setNumberOfSegments(numberOfSegments);
	}

	@Test
	public void additionalInformationShouldBeTruncatedWhenIsTooLongTest() {
		int maximumVariableFieldSize = 255;

		String tooLargeAdditionalInformation = generateLargeInfo(400);
		when(additionalInformation.toString()).thenReturn(tooLargeAdditionalInformation);

		PrimarySegment segment = new PrimarySegment(mandatoryItemsUnique, mandatoryItemsRepeated);

		segment.addAdditionalInformation(additionalInformation);

		assertEquals(mandatoryItemsUniqueCode + mandatoryItemsRepeatedCode + conditionalItemsUniqueEmptyCode
				+ conditionalItemsRepeatedEmptyCode + tooLargeAdditionalInformation, segment.toString());

		verify(additionalInformation, times(1)).setLength(
				maximumVariableFieldSize - conditionalItemsUniqueEmptyCode.length()
						- conditionalItemsRepeatedEmptyCode.length());
	}

	public String generateLargeInfo(int size) {
		String info = "";
		for (int i = 0; i < size / additionalInformationCode.length(); i++) {
			info += additionalInformationCode;
		}
		return info;
	}
}
