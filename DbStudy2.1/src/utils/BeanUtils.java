package utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

public class BeanUtils {
	public static <T> T toBean(Map map, Class<T> clazz) {
		T bean=null;
		try {
			bean = clazz.newInstance();
			org.apache.commons.beanutils.BeanUtils.populate(bean, map);
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}return bean;
	}

	public static <T> T getBean(T obj,Object[] param){
		for (int i = 0; i < param.length; i++) {
			try {
				getMethods(obj)[i].invoke(obj, param[i]);//ִ��set����
			} catch (Exception e) {
				e.printStackTrace();
				}
		}
		return obj;
	}
	
	public static <T> Method[] getMethods(T obj) {
		Field[] fields=obj.getClass().getDeclaredFields();
		Method[] param=new Method[fields.length];
		Method[] methods=obj.getClass().getDeclaredMethods();
		for (int j = 0; j < fields.length; j++) {
			StringBuffer methodName=new StringBuffer();
			String str=fields[j].getName();
			String start=str.substring(0,1).toUpperCase();
			String ends=str.substring(1);
			methodName.append("set"+start+ends);
			for (int i = 0; i < methods.length; i++) {
				if(methods[i].getName().equals(methodName.toString())){
					try {
						param[j]=methods[i];
					} catch (Exception e) {
						e.printStackTrace();
					} 
				}
			}
		}
		return param;
	}
	
	public static <T> Object[] getParam(T obj) {
		Field[] fields=obj.getClass().getDeclaredFields();
		Object[] param=new Object[fields.length];
		Method[] methods=obj.getClass().getDeclaredMethods();
		for (int j = 0; j < fields.length; j++) {
			StringBuffer methodName=new StringBuffer();
			String str=fields[j].getName();
			String start=str.substring(0,1).toUpperCase();
			String ends=str.substring(1);
			methodName.append("get"+start+ends);
			for (int i = 0; i < methods.length; i++) {
				if(methods[i].getName().equals(methodName.toString())){
					try {
						param[j]=methods[i].invoke(obj);
					} catch (Exception e) {
						e.printStackTrace();
					} 
				}
			}
		}
		return param;
	}
}

