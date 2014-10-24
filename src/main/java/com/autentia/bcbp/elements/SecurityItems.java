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

import org.apache.commons.lang3.*;

import com.autentia.bcbp.items.*;
import com.autentia.bcbp.items.Item.PaddingType;

public class SecurityItems {

	private static int beginningOfSecurityDataLength = 1;

	private static int typeOfDataLength = 1;

	private static int variableSizeLength = 2;

	private Item beginningOfSecurityData;

	private Item typeOfData;

	private Item variableSize;

	private Item securityData;

	public SecurityItems(String typeOfData, String securityData) {
		super();

		String hexadecimalLength = Integer.toHexString(securityData.length()).toUpperCase();

		this.beginningOfSecurityData = new Item("^", beginningOfSecurityDataLength, 25, PaddingType.String);
		this.typeOfData = new Item(typeOfData, typeOfDataLength, 28, PaddingType.String);
		this.variableSize = new Item(hexadecimalLength, variableSizeLength, 29, PaddingType.Number);
		this.securityData = new Item(securityData, securityData.length(), 30, PaddingType.String);
	}

	@Override
	public String toString() {
		if (StringUtils.isBlank(securityData.getEncoded())) {
			return "";
		}

		final StringBuffer encoder = new StringBuffer();

		encoder.append(beginningOfSecurityData.getEncoded()).append(typeOfData.getEncoded())
				.append(variableSize.getEncoded()).append(securityData.getEncoded());

		return encoder.toString();
	}
}
