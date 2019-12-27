package com.mycat.run;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mycat.dao.TableDao;
import com.mycat.model.Field;
import com.mycat.model.Table;
import com.mycat.util.FileUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring-config.xml" })
public class MyCatConfig {
	private final Log logger = LogFactory.getLog(MyCatConfig.class);// 日志类
	@Autowired
	private TableDao dao;

	public StringBuffer createXml() {
		StringBuffer sb = new StringBuffer();
		List<Table> tableList = dao.queryTable();
		for (Table table : tableList) {
			List<Field> columnList = dao.queryColumn(table.getTable_name());
			sb.append("\r\n\t\t<!--" + table.getComments() + "配置信息 -->");
			sb.append("\r\n\t\t<table name=\"" + table.getTable_name() + "\" primaryKey=\"" + columnList.get(0).getName().toLowerCase() + "\" dataNode=\"node_db02,node_db03\" rule=\"role1\" />");
		}
		return sb;
	}

	@Test
	public void Head() {
		String path = System.getProperty("user.dir") + "\\src\\main\\java\\com\\mycat\\run\\schema.xml";// Java类基本路径
		logger.info(path);
		StringBuffer sb = new StringBuffer();
		sb.append("<?xml version=\"1.0\"?>  ");
		sb.append("\r\n<!DOCTYPE mycat:schema SYSTEM \"schema.dtd\">");
		sb.append("\r\n<mycat:schema xmlns:mycat=\"http://io.mycat/\">");
		sb.append("\r\n	<schema name=\"TESTDB\" checkSQLschema=\"false\" sqlMaxLimit=\"100\">");
		sb.append(createXml());
		sb.append("\r\n	</schema>");
		sb.append("\r\n	<dataNode name=\"node_db01\" dataHost=\"dataHost01\" database=\"db01\" />");
		sb.append("\r\n	<dataNode name=\"node_db02\" dataHost=\"dataHost01\" database=\"db02\" />");
		sb.append("\r\n	<dataNode name=\"node_db03\" dataHost=\"dataHost01\" database=\"db03\" />");
		sb.append("\r\n	<dataHost name=\"dataHost01\" maxCon=\"1000\" minCon=\"10\" balance=\"0\" writeType=\"0\" dbType=\"mysql\" dbDriver=\"native\">");
		sb.append("\r\n		<heartbeat>select user()</heartbeat>");
		sb.append("\r\n		<writeHost host=\"server1\" url=\"127.0.0.1:3306\" user=\"root\" password=\"123456\" />");
		sb.append("\r\n	</dataHost>");
		sb.append("\r\n</mycat:schema>");
		FileUtil.writeFile(path, sb.toString());
	}

}
