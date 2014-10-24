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

public class SecondarySegmentTest {

	private static final String additionalInformationCode = "LX58Z";

	private static final String conditionalItemsRepeatedCode = "2A0141234567890 1AC AC 1234567890123    2PCY";

	private static final String mandatoryItemsRepeatedCode = "ABC123 YULFRAAC 0834 226F001A0025 167";

	private static final String conditionalItemsRepeatedEmptyCode = "00";

	private MandatoryItemsRepeated mandatoryItemsRepeated = mock(MandatoryItemsRepeated.class);

	private ConditionalItemsRepeated conditionalItemsRepeated = mock(ConditionalItemsRepeated.class);

	private AdditionalInformation additionalInformation = mock(AdditionalInformation.class);

	@Before
	public void init() {
		when(mandatoryItemsRepeated.toString()).thenReturn(mandatoryItemsRepeatedCode);
		when(conditionalItemsRepeated.toString()).thenReturn(conditionalItemsRepeatedCode);
		when(conditionalItemsRepeated.emptyCode()).thenReturn(conditionalItemsRepeatedEmptyCode);
		when(additionalInformation.toString()).thenReturn(additionalInformationCode);
	}

	@Test
	public void correctSecondarySegmentShouldProvideCorrectCodeTest() {
		SecondarySegment segment = new SecondarySegment(mandatoryItemsRepeated);

		segment.addConditionalItemsRepeated(conditionalItemsRepeated);
		segment.addAdditionalInformation(additionalInformation);

		assertEquals(mandatoryItemsRepeatedCode + conditionalItemsRepeatedCode + additionalInformationCode,
				segment.toString());

		verify(mandatoryItemsRepeated, times(1)).setVariableFieldSize(
				conditionalItemsRepeatedCode.length() + additionalInformationCode.length());
	}

	@Test
	public void secondarySegmentWithoutAdditionalInformationShouldProvideCorrectCodeTest() {
		SecondarySegment segment = new SecondarySegment(mandatoryItemsRepeated);

		segment.addConditionalItemsRepeated(conditionalItemsRepeated);

		assertEquals(mandatoryItemsRepeatedCode + conditionalItemsRepeatedCode, segment.toString());

		verify(mandatoryItemsRepeated, times(1)).setVariableFieldSize(conditionalItemsRepeatedCode.length());
	}

	@Test
	public void secondarySegmentWithoutConditionalItemsRepeatedShouldProvideCorrectCodeTest() {
		SecondarySegment segment = new SecondarySegment(mandatoryItemsRepeated);

		segment.addAdditionalInformation(additionalInformation);

		assertEquals(mandatoryItemsRepeatedCode + conditionalItemsRepeatedEmptyCode + additionalInformationCode,
				segment.toString());

		verify(mandatoryItemsRepeated, times(1)).setVariableFieldSize(
				conditionalItemsRepeatedEmptyCode.length() + additionalInformationCode.length());
	}

	@Test
	public void secondarySegmentWithoutConditionalItemsRepeatedNorAdditionalInformationShouldProvideCorrectCodeTest() {
		SecondarySegment segment = new SecondarySegment(mandatoryItemsRepeated);

		assertEquals(mandatoryItemsRepeatedCode, segment.toString());

		verify(mandatoryItemsRepeated, times(1)).setVariableFieldSize(0);
	}

	@Test
	public void additionalInformationShouldBeTruncatedWhenIsTooLongTest() {
		int maximumVariableFieldSize = 255;

		String tooLargeAdditionalInformation = generateLargeInfo(400);
		when(additionalInformation.toString()).thenReturn(tooLargeAdditionalInformation);

		SecondarySegment segment = new SecondarySegment(mandatoryItemsRepeated);

		segment.addAdditionalInformation(additionalInformation);

		assertEquals(mandatoryItemsRepeatedCode + conditionalItemsRepeatedEmptyCode + tooLargeAdditionalInformation,
				segment.toString());

		verify(additionalInformation, times(1)).setLength(
				maximumVariableFieldSize - conditionalItemsRepeatedEmptyCode.length());
	}

	public String generateLargeInfo(int size) {
		String info = "";
		for (int i = 0; i < size / additionalInformationCode.length(); i++) {
			info += additionalInformationCode;
		}
		return info;
	}
}
