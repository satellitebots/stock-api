package com.alwaysup.stock.stockApi.util;

public class Verifier {
	public boolean verifyNotNull(Object inputObj) throws IllegalArgumentException {
		if (inputObj == null)
			throw new IllegalArgumentException();
		return true;
	}

	public boolean verifyString(String inputStr) throws IllegalArgumentException {
		if (inputStr == null)
			throw new IllegalArgumentException();
		if (inputStr.trim().equals(""))
			throw new IllegalArgumentException();
		return true;
	}
}
