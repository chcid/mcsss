package org.michiganchineseschool.speech.model;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.michiganchineseschool.util.StringUtil;
import org.springframework.util.StringUtils;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class NameParser {
	private String chineseFirstname;
	private String chineseLastname;
	private String englishFirstname;
	private String englishLastname;

	public String getChineseFirstname() {
		if (StringUtils.isEmpty(chineseFirstname)) {
			return englishFirstname;
		}
		return chineseFirstname;
	}

	public void setChineseFirstname(String chineseFirstname) {
		this.chineseFirstname = chineseFirstname;
	}

	public String getChineseLastname() {
		if (StringUtils.isEmpty(chineseLastname)) {
			return englishLastname;
		}
		return chineseLastname;
	}

	public void setChineseLastname(String chineseLastname) {
		this.chineseLastname = chineseLastname;
	}

	public String getEnglishFirstname() {
		if (StringUtils.isEmpty(englishFirstname)) {
			return chineseFirstname;
		}
		return englishFirstname;
	}

	public void setEnglishFirstname(String englishFirstname) {
		this.englishFirstname = englishFirstname;
	}

	public String getEnglishLastname() {
		if (StringUtils.isEmpty(englishLastname)) {
			return chineseLastname;
		}
		return englishLastname;
	}

	public void setEnglishLastname(String englishLastname) {
		this.englishLastname = englishLastname;
	}

	public NameParser() {
	}

	private void doEnglish(String name) {
		if (StringUtils.isEmpty(name) || 0 == name.trim().length()) {
			return;
		}
		// set up English name
		String[] eNames = name.trim().split(" ");
		setEnglishFirstname(eNames[0].trim());
		setEnglishLastname(eNames[0].trim());
		if (eNames.length > 1) {
			setEnglishLastname(eNames[eNames.length - 1].trim());
			setEnglishFirstname(name.substring(0, name.indexOf(getEnglishLastname())));
			//if (eNames.length > 2) {
			//	setEnglishFirstname(name.substring(0, name.length()
			//			- getEnglishLastname().length()));
			//}
		}
	}

	private void doChinese(String name) {
		if (StringUtils.isEmpty(name) || name.trim().length() == 0) {
			return;
		}
		// set up Chinese Name
		int index = 0;
		if (name.length() >= 2 && name.length() < 4) {
			index = 1;
		} else {
			// name length >= 4
			index = 2;
		}
		setChineseLastname(name.substring(0, index));
		setChineseFirstname(name.substring(index));
	}

	public NameParser(String line) {
		if (null == line || line.trim().length() == 0) {
			return;
		}
		String[] names = line.split("\\|");
		if (null == names) {
			return;
		}
		for (String name : names) {
			if (StringUtil.isEnglishName(name)) {
				doEnglish(name);
			} else {
				doChinese(name);
			}
		}
	}

}
