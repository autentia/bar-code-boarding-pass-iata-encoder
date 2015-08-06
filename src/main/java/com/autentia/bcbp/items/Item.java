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

import org.apache.commons.lang3.*;

public class Item {

	public enum PaddingType {
		String, Number, NumberFollowedByAlpha
	};

	private String original;

	private int size;

	@SuppressWarnings("unused")
	private final int number;

	private final PaddingType padding;

	public Item(String original, int size, int number, PaddingType padding) {
		this.original = original;
		this.size = size;
		this.number = number;
		this.padding = padding;
	}

	public String getEncoded() {
		if (padding == PaddingType.Number && StringUtils.isBlank(original)) {
			return StringUtils.leftPad(StringUtils.EMPTY, size).substring(0, size);
		}

		if (padding == PaddingType.Number) {
			return StringUtils.leftPad(original, size, "0").substring(0, size);
		}

		if (padding == PaddingType.NumberFollowedByAlpha && StringUtils.isBlank(original)) {
			return StringUtils.leftPad(StringUtils.EMPTY, size - 1, "0").substring(0, size - 1) + " ";
		}

		if (padding == PaddingType.NumberFollowedByAlpha) {
			final int lastPosition = original.length() - 1;
			if (StringUtils.isAlpha(original.substring(lastPosition))) {
				final String number = original.substring(0, lastPosition);
				return StringUtils.leftPad(number, size - 1, "0").substring(0, size - 1)
						+ original.substring(lastPosition);
			} else {
				return StringUtils.leftPad(original, size - 1, "0").substring(0, size - 1) + " ";
			}
		}
		if (padding == PaddingType.String) {
			return StringUtils.rightPad(original, size).substring(0, size);
		}

		return StringUtils.EMPTY;
	}

	public void setValue(String original) {
		this.original = original;
	}

	public void setSize(int size) {
		this.size = size;
	}
}
