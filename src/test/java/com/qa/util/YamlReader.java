package com.qa.util;


import java.io.InputStream;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

public class YamlReader {
	

	public static Map<String,Object> loadData() 
	{
		Yaml yaml=new Yaml();
		try { InputStream inputStream = YamlReader.class.getClassLoader().getResourceAsStream("testdata/QA.yaml");
			if(inputStream == null)
			{
				throw new RuntimeException("QA.yaml not found on classpath"); 
			}
			return yaml.load(inputStream);
		} catch (Exception e) {
			throw new  RuntimeException("Failed to load QA.yaml", e);
			
		}
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T getValueByPath(String path ,  Class<T> type)
	{
		String[] parts = path.split("\\.");
		Object current =loadData();
		for(String part : parts)
		{
			if(!(current instanceof Map))
			{
				throw new IllegalArgumentException("path segment "+path+ "is not a map");
			}
			current = ((Map<String, Object>)current).get(part);
			if(current ==null)
			{
				throw new IllegalArgumentException("No value found at path  " +path);
			}
		}
		return type.cast(current);
	}
}
	
//private static Map<String, Object> data;
//static {
//	Yaml yaml=new Yaml();
//	InputStream inputStream = YamlReader.class.getClassLoader().getResourceAsStream("testdata/QA.yaml");
//	data= yaml.load(inputStream);
//	
//			
//			
//
//};
//
//public static Map<String , Object> getSection(String key)
//{
//	return (Map<String, Object>) data.get(key);
//}
//public static Object getValue(String key)
//{
//	return data.get(key);
//}
//public static Object getValueByPath(String path) {
//    String[] parts = path.split("\\.");
//    Object current = data;
//    for (String part : parts) {
//        if (current instanceof Map) {
//            current = ((Map<String, Object>) current).get(part);
//        } else {
//            return null;
//        }
//    }
//    return current;
//}
//	
//}
