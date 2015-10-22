package com.example.common;

import org.apache.commons.codec.digest.DigestUtils;

public final class Sha2Encoder {

	private Sha2Encoder() {
	}

	public static final String encode(String rawPassword) {
		rawPassword = rawPassword.trim();
		return DigestUtils.sha256Hex(rawPassword).toLowerCase();
	}

	public static final boolean matches(String rawPassword,
			String encodedPassword) {
		boolean b = DigestUtils.sha256Hex(rawPassword).compareTo(
				encodedPassword) == 0;
		return b;
	}

}
