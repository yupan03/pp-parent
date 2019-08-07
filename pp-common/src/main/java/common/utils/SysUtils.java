package common.utils;

import java.util.Date;
import java.util.UUID;

public class SysUtils {
	// 获取uuid
	public synchronized static String getUUID() {
		return UUID.randomUUID().toString().replace("-", "").toUpperCase();
	}

	// 获取当前时间
	public static String getCurrentTime() {
		return SysConstant.DATE_FORMAT_DEFAULT.format(new Date());
	}
}