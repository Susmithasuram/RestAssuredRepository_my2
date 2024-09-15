package utils;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.response.Response;

public class JsonUtils 
{
	static ObjectMapper mapper=new ObjectMapper();
	public static <T>T deserialize(Response resp,Class<T> cls)
	{
		try
		{
			return mapper.readValue(resp.asString(),cls);
		} catch (Exception e)
		{
			System.out.println("Exception while parsing "+e.getMessage());
			return null;
		}
		
	}
	
	
	
	public static String getString(Response resp,String jsonPath)
	{
	  return	resp.jsonPath().getString(jsonPath);
	}
	
	public static Integer getInteger(Response resp,String jsonPath)
	{
	  return	resp.jsonPath().getInt(jsonPath);
	}
	public static boolean getBoolean(Response resp,String jsonPath)
	{
	  return	resp.jsonPath().getBoolean(jsonPath);
	}
	public static <T> List<T> getList(Response resp,String jsonPath)
	{
		return resp.jsonPath().getList(jsonPath);
	}
	public static <K,V> Map<K, V> getMap(Response resp,String jsonPath)
	{
		return  resp.jsonPath().getMap(jsonPath);
	}

}
