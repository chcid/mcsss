package org.michiganchineseschool.util;

import java.util.regex.Pattern;

public class StringUtil {
	private static final String NameRegex = "^[a-zA-Z '\\t\\.]*";
	private static final Pattern NamePattern = Pattern.compile(NameRegex);

	static public boolean isEnglishName(String in) {
		return NamePattern.matcher(in).matches();
	}
}
