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

import com.autentia.bcbp.items.*;
import com.autentia.bcbp.items.Item.PaddingType;

public class AdditionalInformation {

	private Item additionalInformation;

	public AdditionalInformation(String information) {
		this.additionalInformation = new Item(information, information.length(), 4, PaddingType.String);
	}

	@Override
	public String toString() {
		return additionalInformation.getEncoded();
	}

	public void setLength(int length) {
		this.additionalInformation.setSize(length);
	}
}
