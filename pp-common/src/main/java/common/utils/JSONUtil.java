package common.utils;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.CollectionType;

public class JSONUtil {

	public static String ObjectToJson(Object object) {
		try {
			ObjectMapper mapper = new ObjectMapper();

			mapper.setDateFormat(SysConstant.DATE_FORMAT_DEFAULT);
			mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);

			return mapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static <T> T jsonToObject(String json, Class<T> object) {
		try {
			ObjectMapper mapper = new ObjectMapper();

			return mapper.readValue(json.getBytes(), object);
		} catch (IOException e) {
			return null;
		}
	}

	public static <T> List<T> jsonToList(String json, Class<T> cls) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			CollectionType listType = mapper.getTypeFactory().constructCollectionType(List.class, cls);
			return mapper.readValue(json, listType);
		} catch (IOException e) {
			return null;
		}
	}

	public static boolean isJson(String json) {
		ObjectMapper mapper = new ObjectMapper();

		try {
			mapper.readTree(json);
			return true;
		} catch (IOException e) {
			return false;
		}
	}
}