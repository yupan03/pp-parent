package common.utils;

import java.security.MessageDigest;

public class EncryptUtil {
	public static String getMD5String(String str) {
		try {
			StringBuffer sb = new StringBuffer();

			MessageDigest digest = MessageDigest.getInstance("MD5");

			byte[] array = digest.digest(str.getBytes("UTF-8"));

			for (byte b : array) {
				sb.append(Integer.toHexString(b & 0xFF).toUpperCase());
			}

			return sb.toString();
		} catch (Exception e) {
			return null;
		}
	}

	public static void main(String[] args) {
		System.out.println(getMD5String("134"));
		System.out.println(141);
	}
}