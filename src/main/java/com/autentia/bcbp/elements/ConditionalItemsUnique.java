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

public class ConditionalItemsUnique {

	private static boolean removeEndingEmptyElements = false;

	private static int BCBPversion = 5;

	private static int variableSizeLength = 2;

	private static int passengerDescriptionLength = 1;

	private static int sourceCheckinLength = 1;

	private static int sourceBoardingPassIssuanceLength = 1;

	private static int dateOfIssueOfBoardingPassLength = 4;

	private static int documentTypeLength = 1;

	private static int airlineDesignatorOfBoardingPassIssuerLength = 3;

	private static int baggageTagLicencePlateNumberLength = 13;

	private static int firstNCBaggageTagLicencePlateNumberLength = 13;

	private static int secondNCBaggageTagLicencePlateNumberLength = 13;

	private String code;

	public ConditionalItemsUnique(String passengerDescription, String sourceCheckin, String sourceBoardingPassIssuance,
			String dateOfIssueOfBoardingPass, String documentType, String airlineDesignatorOfBoardingPassIssuer,
			String baggageTagLicencePlateNumber, String firstNCBaggageTagLicencePlateNumber,
			String secondNCBaggageTagLicencePlateNumber) {
		super();

		Stack<Item> items = new Stack<Item>();

		items.push(new Item(passengerDescription, passengerDescriptionLength, 142, PaddingType.String));
		items.push(new Item(sourceCheckin, sourceCheckinLength, 143, PaddingType.String));
		items.push(new Item(sourceBoardingPassIssuance, sourceBoardingPassIssuanceLength, 18, PaddingType.String));
		items.push(new Item(dateOfIssueOfBoardingPass, dateOfIssueOfBoardingPassLength, 108, PaddingType.Number));
		items.push(new Item(documentType, documentTypeLength, 19, PaddingType.String));
		items.push(new Item(airlineDesignatorOfBoardingPassIssuer, airlineDesignatorOfBoardingPassIssuerLength, 20,
				PaddingType.String));
		items.push(new Item(baggageTagLicencePlateNumber, baggageTagLicencePlateNumberLength, 236, PaddingType.String));
		items.push(new Item(firstNCBaggageTagLicencePlateNumber, firstNCBaggageTagLicencePlateNumberLength, 89,
				PaddingType.String));
		items.push(new Item(secondNCBaggageTagLicencePlateNumber, secondNCBaggageTagLicencePlateNumberLength, 118,
				PaddingType.String));

		final StringBuilder codeBuilder = new StringBuilder();
		boolean starting = true;
		while (!items.isEmpty()) {
			Item item = items.pop();
			if (starting && StringUtils.isNotBlank(item.getEncoded()) || !removeEndingEmptyElements) starting = false;
			if (!starting) codeBuilder.insert(0, item.getEncoded());
		}

		final String baseCode = codeBuilder.toString();

		if (StringUtils.isBlank(baseCode))
			code = "";
		else
			code = ">"
					+ BCBPversion
					+ StringUtils.leftPad(Integer.toHexString(baseCode.length()), variableSizeLength, "0")
							.toUpperCase() + baseCode;
	}

	@Override
	public String toString() {
		return code;
	}

	public String emptyCode() {
		return ">" + BCBPversion + "00";
	}

	public static class ConditionalItemsUniqueBuilder {

		private String passengerDescription;

		private String sourceCheckin;

		private String sourceBoardingPassIssuance;

		private String dateOfIssueOfBoardingPass;

		private String documentType;

		private String airlineDesignatorOfBoardingPassIssuer;

		private String baggageTagLicencePlateNumber;

		private String firstNCBaggageTagLicencePlateNumber;

		private String secondNCBaggageTagLicencePlateNumber;

		public ConditionalItemsUniqueBuilder() {
			passengerDescription = "";

			sourceCheckin = "";

			sourceBoardingPassIssuance = "";

			dateOfIssueOfBoardingPass = "";

			documentType = "";

			airlineDesignatorOfBoardingPassIssuer = "";

			baggageTagLicencePlateNumber = "";

			firstNCBaggageTagLicencePlateNumber = "";

			secondNCBaggageTagLicencePlateNumber = "";
		}

		public ConditionalItemsUniqueBuilder passengerDescription(String data) {
			this.passengerDescription = data;
			return this;
		}

		public ConditionalItemsUniqueBuilder sourceCheckin(String data) {
			this.sourceCheckin = data;
			return this;
		}

		public ConditionalItemsUniqueBuilder sourceBoardingPassIssuance(String data) {
			this.sourceBoardingPassIssuance = data;
			return this;
		}

		public ConditionalItemsUniqueBuilder dateOfIssueOfBoardingPass(String data) {
			this.dateOfIssueOfBoardingPass = data;
			return this;
		}

		public ConditionalItemsUniqueBuilder documentType(String data) {
			this.documentType = data;
			return this;
		}

		public ConditionalItemsUniqueBuilder airlineDesignatorOfBoardingPassIssuer(String data) {
			this.airlineDesignatorOfBoardingPassIssuer = data;
			return this;
		}

		public ConditionalItemsUniqueBuilder baggageTagLicencePlateNumber(String data) {
			this.baggageTagLicencePlateNumber = data;
			return this;
		}

		public ConditionalItemsUniqueBuilder firstNCBaggageTagLicencePlateNumber(String data) {
			this.firstNCBaggageTagLicencePlateNumber = data;
			return this;
		}

		public ConditionalItemsUniqueBuilder secondNCBaggageTagLicencePlateNumber(String data) {
			this.secondNCBaggageTagLicencePlateNumber = data;
			return this;
		}

		public ConditionalItemsUnique createConditionalItemsUnique() {
			return new ConditionalItemsUnique(passengerDescription, sourceCheckin, sourceBoardingPassIssuance,
					dateOfIssueOfBoardingPass, documentType, airlineDesignatorOfBoardingPassIssuer,
					baggageTagLicencePlateNumber, firstNCBaggageTagLicencePlateNumber,
					secondNCBaggageTagLicencePlateNumber);
		}
	}
}
