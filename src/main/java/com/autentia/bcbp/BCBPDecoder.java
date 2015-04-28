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
