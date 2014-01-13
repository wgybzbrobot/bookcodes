package cc.pp.captcha;

import static org.junit.Assert.assertFalse;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import cc.pp.account.captcha.RandomGenerator;

public class RandomGeneratorTest {

	@Test
	public void testGetRandomString() {

		Set<String> randoms = new HashSet<>(100);

		for (int i = 0; i < 100; i++) {
			String random = RandomGenerator.getRandomString();
			assertFalse(randoms.contains(random));
			randoms.add(random);
		}
	}

}
