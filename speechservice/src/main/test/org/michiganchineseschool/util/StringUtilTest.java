package org.michiganchineseschool.util;

import static org.junit.Assert.*;

import org.junit.Test;

public class StringUtilTest {

	@Test
	public void testIsEnglishName() {
		assertTrue(StringUtil.isEnglishName("Juan Cristobal DePena"));
		assertFalse(StringUtil.isEnglishName("余嘉樂"));
	}
}
