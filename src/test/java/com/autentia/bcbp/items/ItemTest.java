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
package com.autentia.bcbp.items;

import static org.junit.Assert.*;

import org.junit.*;

import com.autentia.bcbp.items.Item.PaddingType;

public class ItemTest {

	@Test
	public void leftPaddingShouldGenerateRightEncodedTest() {
		int size = 5;
		int number = 2;

		Item item = new Item("123", size, number, PaddingType.Number);

		assertEquals("00123", item.getEncoded());
	}

	@Test
	public void rightPaddingShouldGenerateRightEncodedTest() {
		int size = 5;
		int number = 2;

		Item item = new Item("123", size, number, PaddingType.String);

		assertEquals("123  ", item.getEncoded());
	}

	@Test
	public void whenSizeIsLongerThanOriginalStringShoudlBeCutTest() {
		int size = 5;
		int number = 2;

		Item item = new Item("1234567890", size, number, PaddingType.String);

		assertEquals("12345", item.getEncoded());
		item = new Item("1234567890", size, number, PaddingType.Number);

		assertEquals("12345", item.getEncoded());
	}

	@Test
	public void emptyNumberMustBeCodedToSpacesTest() {
		int size = 5;
		int number = 2;

		Item item = new Item("", size, number, PaddingType.Number);

		assertEquals("     ", item.getEncoded());
	}

	@Test
	public void paddingTypeNumberFollowedByAlphaShouldAddSpaceWhenNoAlphaIsGivenTest() {
		int size = 5;
		int number = 2;

		Item item = new Item("0834", size, number, PaddingType.NumberFollowedByAlpha);

		assertEquals("0834 ", item.getEncoded());
	}

	@Test
	public void paddingTypeNumberFollowedByAlphaShouldFillZerosWhenAlphaIsGivenTest() {
		int size = 5;
		int number = 2;

		Item item = new Item("123A", size, number, PaddingType.NumberFollowedByAlpha);

		assertEquals("0123A", item.getEncoded());
	}
}
