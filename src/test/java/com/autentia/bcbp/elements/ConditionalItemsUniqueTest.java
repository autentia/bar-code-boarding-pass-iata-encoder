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

public class ConditionalItemsUniqueTest {

	@Test
	public void emptyCodeIfNoInformationAddedTest() {
		ConditionalItemsUnique code = new ConditionalItemsUnique.ConditionalItemsUniqueBuilder()
				.createConditionalItemsUnique();

		assertEquals("", code.toString());
	}

	@Test
	public void correctCodeIfEmptyCodeAskedTest() {
		ConditionalItemsUnique code = new ConditionalItemsUnique.ConditionalItemsUniqueBuilder()
				.createConditionalItemsUnique();

		assertEquals(">500", code.emptyCode());
	}

	@Test
	public void correctSizeTest() {
		ConditionalItemsUnique code = new ConditionalItemsUnique.ConditionalItemsUniqueBuilder()
				.baggageTagLicencePlateNumber("012345678").createConditionalItemsUnique();

		assertEquals(54, code.toString().length());
		assertEquals("32", Integer.toHexString(code.toString().length() - 4));
		assertEquals(">532           012345678                              ", code.toString());
	}

}
