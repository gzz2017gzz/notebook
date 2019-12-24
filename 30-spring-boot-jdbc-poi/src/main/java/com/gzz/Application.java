package com.gzz;

import java.io.File;
import java.io.IOException;

import javax.annotation.PostConstruct;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.gzz.sys.user.UserDao;

import lombok.extern.slf4j.Slf4j;
/**
 * @author https://www.jianshu.com/u/3bd57d5f1074
 * @date 2019-12-24 10:50:00
 **/
@Slf4j
@SpringBootApplication
public class Application {
	private int row = 0;

	public static void main(String[] args) throws Exception {
		SpringApplication.run(Application.class, args);
	}

	@Autowired
	private UserDao dao;

	@PostConstruct
	public void run() throws IOException {
		HSSFWorkbook workbook = new HSSFWorkbook(); // 创建工作簿对象
		HSSFSheet sheet = workbook.createSheet("数据库表"); // 创建工作表

		dao.queryTables().forEach(item -> {
			log.info(item.toString());
			row++;// 表信息
			HSSFRow rowm = sheet.createRow(row);
			rowm.createCell(0).setCellValue("表名");
			rowm.createCell(1).setCellValue(item.getTable_name());
			rowm.createCell(3).setCellValue("描述");
			rowm.createCell(4).setCellValue(item.getTable_comment());

			row++;// 表头
			rowm = sheet.createRow(row);
			rowm.createCell(0).setCellValue("字段名");
			rowm.createCell(1).setCellValue("字段描述");
			rowm.createCell(2).setCellValue("数据类型");
			rowm.createCell(3).setCellValue("可为空");
			rowm.createCell(4).setCellValue("是主键");

			dao.queryFields(item.getTable_name()).forEach(ite -> {
				row++;// 字段
//				log.info(ite.toString());
				HSSFRow rowf = sheet.createRow(row);
				rowf.createCell(0).setCellValue(ite.getColumn_name());
				rowf.createCell(1).setCellValue(ite.getColumn_comment());
				rowf.createCell(2).setCellValue(ite.getColumn_type());
				rowf.createCell(3).setCellValue(ite.getIs_nullable());
				rowf.createCell(4).setCellValue(ite.getPri());
			});
			row++;
		});

		workbook.write(new File("d:/数据库.xls"));
		workbook.close();
	}
}
