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

public class SecondarySegment {

	private static final int maximumVariableFieldSize = 255;

	private MandatoryItemsRepeated mandatoryItemsRepeated;

	private ConditionalItemsRepeated conditionalItemsRepeated;

	private AdditionalInformation additionalInformation;

	public SecondarySegment(MandatoryItemsRepeated mandatoryItemsRepeated) {
		this.mandatoryItemsRepeated = mandatoryItemsRepeated;
		this.conditionalItemsRepeated = new ConditionalItemsRepeated.ConditionalItemsRepeatedBuilder()
				.createConditionalItemsRepeated();
		this.additionalInformation = new AdditionalInformation("");
	}

	public void addConditionalItemsRepeated(ConditionalItemsRepeated conditionalItemsRepeated) {
		this.conditionalItemsRepeated = conditionalItemsRepeated;
	}

	public void addAdditionalInformation(AdditionalInformation additionalInformation) {
		this.additionalInformation = additionalInformation;
	}

	@Override
	public String toString() {
		String conditionalItemsRepeatedCode = conditionalItemsRepeated.toString();

		if (StringUtils.isNotBlank(additionalInformation.toString())
				&& StringUtils.isBlank(conditionalItemsRepeatedCode)) {
			conditionalItemsRepeatedCode = conditionalItemsRepeated.emptyCode();
		}

		int variableFieldSize = conditionalItemsRepeatedCode.length() + additionalInformation.toString().length();
		if (variableFieldSize > maximumVariableFieldSize) {
			additionalInformation.setLength(maximumVariableFieldSize - conditionalItemsRepeatedCode.length());
			variableFieldSize = maximumVariableFieldSize;
		}

		mandatoryItemsRepeated.setVariableFieldSize(variableFieldSize);

		final StringBuffer encoder = new StringBuffer();

		encoder.append(mandatoryItemsRepeated.toString()).append(conditionalItemsRepeatedCode)
				.append(additionalInformation.toString());

		return encoder.toString();
	}
}
