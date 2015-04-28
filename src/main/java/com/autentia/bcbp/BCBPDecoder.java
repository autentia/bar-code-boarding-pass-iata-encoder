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

public class BCBPDecoder {

	private String code;

	public BCBPDecoder(String code) {
		this.code = code;
	}

	public String getDepartureAirportCode() {
		return code.substring(30, 33);
	}

	public String getArrivalAirportCode() {
		return code.substring(33, 36);
	}
}
