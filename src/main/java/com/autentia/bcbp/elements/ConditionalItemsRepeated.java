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

import java.util.*;

import org.apache.commons.lang3.*;

import com.autentia.bcbp.items.*;
import com.autentia.bcbp.items.Item.PaddingType;

public class ConditionalItemsRepeated {

	private static boolean removeEndingEmptyElements = true;

	private static int variableSizeLength = 2;

	private static int airlineNumericCodeLength = 3;

	private static int serialNumberLength = 10;

	private static int selecteeIndicatorLength = 1;

	private static int internationalDocumentVerificationLength = 1;

	private static int marketingCarrierDesignatorLength = 3;

	private static int frecuentFlyerAirlineDesignatorLength = 3;

	private static int frecuentFlyerNumberLength = 16;

	private static int IDADIndicatorLength = 1;

	private static int freeBaggageAllowanceLength = 3;

	private static int fastTrackLength = 1;

	private String code;

	private ConditionalItemsRepeated(String airlineNumericCode, String serialNumber, String selecteeIndicator,
			String internationalDocumentVerification, String marketingCarrierDesignator,
			String frecuentFlyerAirlineDesignator, String frecuentFlyerNumber, String iDADIndicator,
			String freeBaggageAllowance, String fastTrack) {

		Stack<Item> items = new Stack<Item>();

		items.push(new Item(airlineNumericCode, airlineNumericCodeLength, 142, PaddingType.Number));
		items.push(new Item(serialNumber, serialNumberLength, 143, PaddingType.Number));
		items.push(new Item(selecteeIndicator, selecteeIndicatorLength, 18, PaddingType.String));
		items.push(new Item(internationalDocumentVerification, internationalDocumentVerificationLength, 108,
				PaddingType.String));
		items.push(new Item(marketingCarrierDesignator, marketingCarrierDesignatorLength, 19, PaddingType.String));
		items.push(new Item(frecuentFlyerAirlineDesignator, frecuentFlyerAirlineDesignatorLength, 20,
				PaddingType.String));
		items.push(new Item(frecuentFlyerNumber, frecuentFlyerNumberLength, 236, PaddingType.String));
		items.push(new Item(iDADIndicator, IDADIndicatorLength, 89, PaddingType.String));
		items.push(new Item(freeBaggageAllowance, freeBaggageAllowanceLength, 118, PaddingType.String));
		items.push(new Item(fastTrack, fastTrackLength, 254, PaddingType.String));

		final StringBuilder codeBuilder = new StringBuilder();
		boolean starting = true;
		while (!items.isEmpty()) {
			Item item = items.pop();
			if (starting && StringUtils.isNotBlank(item.getEncoded()) || !removeEndingEmptyElements) starting = false;
			if (!starting) codeBuilder.insert(0, item.getEncoded());
		}

		final String baseCode = codeBuilder.toString();
		if (StringUtils.isBlank(baseCode)) {
			code = "";
		} else {
			code = StringUtils.leftPad(Integer.toHexString(baseCode.length()), variableSizeLength, "0").toUpperCase()
					+ baseCode;
		}
	}

	@Override
	public String toString() {
		return code;
	}

	public static class ConditionalItemsRepeatedBuilder {

		private String airlineNumericCode;

		private String serialNumber;

		private String selecteeIndicator;

		private String internationalDocumentVerification;

		private String marketingCarrierDesignator;

		private String frecuentFlyerAirlineDesignator;

		private String frecuentFlyerNumber;

		private String IDADIndicator;

		private String freeBaggageAllowance;

		private String fastTrack;

		public ConditionalItemsRepeatedBuilder() {
			airlineNumericCode = "";

			serialNumber = "";

			selecteeIndicator = "";

			internationalDocumentVerification = "";

			marketingCarrierDesignator = "";

			frecuentFlyerAirlineDesignator = "";

			frecuentFlyerNumber = "";

			IDADIndicator = "";

			freeBaggageAllowance = "";

			fastTrack = "";
		}

		public ConditionalItemsRepeatedBuilder airlineNumericCode(String data) {
			this.airlineNumericCode = data;
			return this;
		}

		public ConditionalItemsRepeatedBuilder airlineNumericCode(int data) {
			return this.airlineNumericCode(data + "");
		}

		public ConditionalItemsRepeatedBuilder serialNumber(String data) {
			this.serialNumber = data;
			return this;
		}

		public ConditionalItemsRepeatedBuilder selecteeIndicator(String data) {
			this.selecteeIndicator = data;
			return this;
		}

		public ConditionalItemsRepeatedBuilder internationalDocumentVerification(String data) {
			this.internationalDocumentVerification = data;
			return this;
		}

		public ConditionalItemsRepeatedBuilder marketingCarrierDesignator(String data) {
			this.marketingCarrierDesignator = data;
			return this;
		}

		public ConditionalItemsRepeatedBuilder frecuentFlyerAirlineDesignator(String data) {
			this.frecuentFlyerAirlineDesignator = data;
			return this;
		}

		public ConditionalItemsRepeatedBuilder frecuentFlyerNumber(String data) {
			this.frecuentFlyerNumber = data;
			return this;
		}

		public ConditionalItemsRepeatedBuilder IDADIndicator(String data) {
			this.IDADIndicator = data;
			return this;
		}

		public ConditionalItemsRepeatedBuilder freeBaggageAllowance(String data) {
			this.freeBaggageAllowance = data;
			return this;
		}

		public ConditionalItemsRepeatedBuilder fastTrack(String data) {
			this.fastTrack = data;
			return this;
		}

		public ConditionalItemsRepeated createConditionalItemsRepeated() {
			return new ConditionalItemsRepeated(airlineNumericCode, serialNumber, selecteeIndicator,
					internationalDocumentVerification, marketingCarrierDesignator, frecuentFlyerAirlineDesignator,
					frecuentFlyerNumber, IDADIndicator, freeBaggageAllowance, fastTrack);
		}
	}

	public String emptyCode() {
		return "00";
	}
}
