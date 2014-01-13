package cc.pp.account.captcha;

import java.util.Random;

public class RandomGenerator {

	private static final String RANGE = "0123456789abcdefghijklmnopqrstuvwxyz";

	public static synchronized String getRandomString() {

		Random random = new Random();
		StringBuffer result = new StringBuffer();
		for (int i = 0; i < 8; i++) {
			result.append(RANGE.charAt(random.nextInt(RANGE.length())));
		}

		return result.toString();
	}

}
