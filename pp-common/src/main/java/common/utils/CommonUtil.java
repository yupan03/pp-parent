package common.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonUtil {
	public static boolean isDouble(String str) {
		if (null == str || "".equals(str)) {
			return false;
		}

		Pattern pattern = Pattern.compile("^[-\\+]?[.\\d]*$");

		return pattern.matcher(str).matches();
	}

	public static boolean isInteger(String str) {
		if (null == str || "".equals(str)) {
			return false;
		}

		Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");

		return pattern.matcher(str).matches();
	}

	public static boolean isEmail(String str) {
		if (null == str || "".equals(str)) {
			return false;
		}

		String regEx1 = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";

		Pattern p = Pattern.compile(regEx1);

		Matcher m = p.matcher(str);

		if (m.matches()) {
			return true;
		}

		return false;
	}
}