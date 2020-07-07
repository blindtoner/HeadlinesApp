package mainPackage;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;

public class GsonWrapper {
	
	private Gson _gson;

	public GsonWrapper() {
		this._gson = new Gson();
	}

	public List<String> fromJson(String json, Type listType) {
		return _gson.fromJson(json, listType);
	}
	
	public <T> T fromJson(String json, Class<T> classOfT) {
		return _gson.fromJson(json, classOfT);
	}
	
	

}
