package com.example.common;

import org.apache.commons.codec.digest.DigestUtils;

public final class Sha2Encoder {

	private Sha2Encoder() {
	}

	public static final String encode(String rawPassword) {
		return DigestUtils.sha256Hex(rawPassword.trim()).toLowerCase();
	}

	public static final boolean matches(String rawPassword, String encodedPassword) {
		return DigestUtils.sha256Hex(rawPassword).compareTo(encodedPassword) == 0;
	}

}
