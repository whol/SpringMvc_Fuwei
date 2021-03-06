package com.fuwei.service;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.fuwei.commons.SystemSettings;

@Component
public class SystemSettingService extends BaseService{
	private Logger log = org.apache.log4j.LogManager.getLogger(SystemSettingService.class);
	@Autowired
	JdbcTemplate jdbc;

	// 获取列表
	public void initSettings() throws Exception {
		try {
			List<Map<String,Object>> list = dao.queryForListMap(
					"SELECT * FROM tb_systemsetting");
			for(Map<String,Object> map : list){
				String name = (String)map.get("name");
				String value = (String)map.get("value");
				Field field = SystemSettings.class.getField(name);
				Class type = field.getType();
				String className = type.getName();
				field.setAccessible(true);
				if(className.equals("java.lang.String") ){
					field.set(null, value);
				}else if(className.equals("java.lang.Double")){
					field.set(null, Double.parseDouble(value));
				}else if(className.equals("java.lang.Boolean")){
					if(value.equals("1") || value.toLowerCase().equals("true")){
						field.set(null, true);
					}else if(value.equals("0") || value.toLowerCase().equals("false")){
						field.set(null, false);
					}else{
						field.set(null, null);
					}
					
				}
				
			}
			
		} catch (Exception e) {
			throw e;
		}
	}

	// 设置系统参数
	@Transactional
	public int setSettings(Map<String,Object> params) throws Exception {
		String sql = "update tb_systemsetting set value=? where name=?";

		List<Object[]> batchArgs = new ArrayList<Object[]>();
		for (String name : params.keySet()) {
			batchArgs.add(new Object[] { 
					params.get(name).equals("")? null:params.get(name).toString(),name
			});
		}
		try {
			int result[] = jdbc.batchUpdate(sql, batchArgs);
			return 1;
		} catch (Exception e) {
			throw e;
		}
	}
}
