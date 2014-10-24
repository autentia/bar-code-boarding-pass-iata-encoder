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

public class SecurityItemsTest {

	private static int securityHeaderSize = 4;

	@Test
	public void emptyCodeIfNoInfoAddedTest() {
		SecurityItems code = new SecurityItems("t", "");

		assertEquals("", code.toString());
	}

	@Test
	public void checkTrimOfTypeTest() {
		SecurityItems code = new SecurityItems("typeofdata", "random information");

		assertEquals("^t12random information", code.toString());
	}

	@Test
	public void checkCorrectSizeTest() {
		SecurityItems code = new SecurityItems("typeofdata", "1234567890");

		assertEquals(10, code.toString().length() - securityHeaderSize);
		assertEquals("^t0A1234567890", code.toString());
	}
}
