package com.example.common;

import com.google.common.base.Strings;

public final class MoneyTool {

	static final String DOT = ".";

	private MoneyTool() {
	}

	public static Long yuanToFen(String priceYuan) {
		if (Strings.isNullOrEmpty(priceYuan)) {
			return null;
		}
		final int index = priceYuan.indexOf(DOT);
		long decimal = 0;
		if (index > 0 && (priceYuan.length() - 1 > index)) {
			String decimalStr = priceYuan.substring(index + 1);
			decimal = Long.parseLong(decimalStr);
		}
		long lng;
		if (index > 0) {
			String intStr = priceYuan.substring(0, index);
			lng = Long.parseLong(intStr) * 100;
		} else {
			lng = Long.parseLong(priceYuan) * 100;
		}
		final long totalFen = lng + decimal;
		return totalFen;
	}

}
