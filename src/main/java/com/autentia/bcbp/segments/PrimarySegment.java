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
package com.autentia.bcbp.segments;

import org.apache.commons.lang3.*;

import com.autentia.bcbp.elements.*;

public class PrimarySegment {

	private static final int maximumVariableFieldSize = 255;

	private MandatoryItemsUnique mandatoryItemsUnique;

	private MandatoryItemsRepeated mandatoryItemsRepeated;

	private ConditionalItemsUnique conditionalItemsUnique;

	private ConditionalItemsRepeated conditionalItemsRepeated;

	private AdditionalInformation additionalInformation;

	private int numberOfSegments;

	public PrimarySegment(MandatoryItemsUnique mandatoryItemsUnique, MandatoryItemsRepeated mandatoryItemsRepeated) {
		this.mandatoryItemsUnique = mandatoryItemsUnique;
		this.mandatoryItemsRepeated = mandatoryItemsRepeated;
		this.conditionalItemsUnique = new ConditionalItemsUnique.ConditionalItemsUniqueBuilder()
				.createConditionalItemsUnique();
		this.conditionalItemsRepeated = new ConditionalItemsRepeated.ConditionalItemsRepeatedBuilder()
				.createConditionalItemsRepeated();
		this.additionalInformation = new AdditionalInformation("");
		numberOfSegments = 1;
	}

	public void addConditionalItemsUnique(ConditionalItemsUnique conditionalItemsUnique) {
		this.conditionalItemsUnique = conditionalItemsUnique;
	}

	public void addConditionalItemsRepeated(ConditionalItemsRepeated conditionalItemsRepeated) {
		this.conditionalItemsRepeated = conditionalItemsRepeated;
	}

	public void addAdditionalInformation(AdditionalInformation additionalInformation) {
		this.additionalInformation = additionalInformation;
	}

	public void setNumberOfSegments(int numberOfSegments) {
		this.numberOfSegments = numberOfSegments;
	}

	@Override
	public String toString() {
		String conditionalItemsRepeatedCode = conditionalItemsRepeated.toString();
		String conditionalItemsUniqueCode = conditionalItemsUnique.toString();

		if (StringUtils.isNotBlank(additionalInformation.toString())
				&& StringUtils.isBlank(conditionalItemsRepeatedCode)) {
			conditionalItemsRepeatedCode = conditionalItemsRepeated.emptyCode();
		}

		if (StringUtils.isNotBlank(conditionalItemsRepeatedCode) && StringUtils.isBlank(conditionalItemsUniqueCode)) {
			conditionalItemsUniqueCode = conditionalItemsUnique.emptyCode();
		}

		int variableFieldSize = conditionalItemsUniqueCode.length() + conditionalItemsRepeatedCode.length()
				+ additionalInformation.toString().length();
		if (variableFieldSize > maximumVariableFieldSize) {
			additionalInformation.setLength(maximumVariableFieldSize - conditionalItemsUniqueCode.length()
					- conditionalItemsRepeatedCode.length());
			variableFieldSize = maximumVariableFieldSize;
		}

		mandatoryItemsUnique.setNumberOfSegments(numberOfSegments);
		mandatoryItemsRepeated.setVariableFieldSize(conditionalItemsUniqueCode.length()
				+ conditionalItemsRepeatedCode.length() + additionalInformation.toString().length());

		final StringBuffer encoder = new StringBuffer();

		encoder.append(mandatoryItemsUnique.toString()).append(mandatoryItemsRepeated.toString())
				.append(conditionalItemsUniqueCode).append(conditionalItemsRepeatedCode)
				.append(additionalInformation.toString());

		return encoder.toString();
	}
}
