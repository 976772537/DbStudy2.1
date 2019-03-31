package utils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

public class SqlUtils {
	/**
	 * @param map
	 *            ����sql �� param �ļ���
	 * @param cond//条件
	 * @param tableName
	 *            ����
	 * @return
	 */
	public static HashMap<String, Object[]> getUpdateSqlWithParam(
			HashMap<String, String> map, String cond,String method, String tableName) {
		Set<Entry<String, String>> es = map.entrySet();
		Iterator<Entry<String, String>> it = es.iterator();
		StringBuffer sb = new StringBuffer();
		Object[] param = new Object[map.size() + 1]; // �����е�����Ԫ�أ����һ������Ԫ��c_id
		param[map.size()] = cond;// ��c_id�������һλ
		int count = 0;
		while (it.hasNext()) {
			Entry<String, String> en = it.next();
			sb.append(en.getKey() + "=?,");
			param[count++] = en.getValue();
		}
		String str = sb.substring(0, sb.toString().length() - 1);
		String sql = "update " + tableName + " set " + str + " where "+method+"= ?";
		HashMap<String, Object[]> hm = new HashMap<>();
		hm.put(sql, param);
		return hm;
	}

	/**
	 * @param <T>
	 * @return ���ز����sql���
	 */
	public static <T> String getInsertSql(T obj, String tableName) {
		Field[] fields = obj.getClass().getDeclaredFields();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < fields.length; i++) {
			if (i < fields.length - 1) {
				sb.append("?,");
			} else {
				sb.append("?)");
			}
		}
		String sql = "insert into " + tableName + " values(" + sb.toString();
		return sql;
	}

	/**
	 * ��ȡ������ѯ��sql���
	 * 
	 * @param method
	 * @return
	 */
	public static String getSelectSql(String method, String tableName) {
		String sql = "select * from " + tableName + " where " + method + "=?";
		return sql;
	}

	public static String getDeleteSql(String method, String tableName) {
		String sql = "delete from " + tableName + " where " + method + "=" + "?";
		return sql;
	}
	
	public static String getDeleteParamSql(String[] method, String tableName) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < method.length; i++) {
			if (i < method.length - 1) {
				sb.append(method[i]+"=?" + " and ");
			} else {
				sb.append(method[i]+"=?");
			}
		}
		return "delete  from " + tableName +" where "+sb.toString();
	}

	/**
	 * ��ȡ������ѯ�̶���Ϣ��sql���
	 * 
	 * @param method
	 * @param param
	 * @return
	 */
	public static String getSelectParamConSql(String method, String tableName,
			Object... param) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < param.length; i++) {
			if (i < param.length - 1) {
				sb.append(param[i] + ",");
			} else {
				sb.append(param[i]);
			}
		}
		return "select " + sb.toString() + " from " + tableName + " where"
				+ method + "=?";
	}
	
	public static String getSelectParamSql(String method, String tableName,
			Object... param) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < param.length; i++) {
			if (i < param.length - 1) {
				sb.append(param[i] + ",");
			} else {
				sb.append(param[i]);
			}
		}
		return "select " + sb.toString() + " from " + tableName;
	}
	

	public static String getSelectParamPageSql(String method, String tableName,
			Object... param) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < param.length; i++) {
			if (i < param.length - 1) {
				sb.append(param[i] + ",");
			} else {
				sb.append(param[i]);
			}
		}
		return "select " + sb.toString() + " from " + tableName+" Order by "+method+" limit ?,?";
	}
}
