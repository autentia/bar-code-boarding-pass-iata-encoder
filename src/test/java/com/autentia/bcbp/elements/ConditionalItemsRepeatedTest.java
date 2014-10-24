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

public class ConditionalItemsRepeatedTest {

	@Test
	public void emptyCodeIfNoInformationAddedTest() {
		ConditionalItemsRepeated code = new ConditionalItemsRepeated.ConditionalItemsRepeatedBuilder()
				.createConditionalItemsRepeated();

		assertEquals("", code.toString());
	}

	@Test
	public void doubleZeroIfEmptyCodeAskedTest() {
		ConditionalItemsRepeated code = new ConditionalItemsRepeated.ConditionalItemsRepeatedBuilder()
				.createConditionalItemsRepeated();

		assertEquals("00", code.emptyCode());
	}

	@Test
	public void codeCorrectIfOnlySerialNumberTest() {
		ConditionalItemsRepeated code = new ConditionalItemsRepeated.ConditionalItemsRepeatedBuilder().serialNumber(
				"1234567890").createConditionalItemsRepeated();

		assertEquals("0D   1234567890", code.toString());
	}

	@Test
	public void codeCorrectIfOnlyHalfSerialNumberTest() {
		ConditionalItemsRepeated code = new ConditionalItemsRepeated.ConditionalItemsRepeatedBuilder().serialNumber(
				"12345").createConditionalItemsRepeated();

		assertEquals("0D   0000012345", code.toString());
	}
}
