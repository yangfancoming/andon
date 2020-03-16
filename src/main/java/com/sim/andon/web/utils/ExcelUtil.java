/**
 * CopyRright @2014 SIM TECHNOLOGY ALL RIGHTS RESERVED
 * @Title: ExcelUtils.java
 * @Package com.sim.machine.web.utils
 * @Description: TODO
 * @author he.sun
 * @date 2014年12月18日 上午10:15:18
 * @version V1.0
 */
package com.sim.andon.web.utils;

import com.sim.andon.web.core.export.annotation.Column;
import com.sim.andon.web.core.export.exception.ExportException;
import net.sf.jxls.exception.ParsePropertyException;
import net.sf.jxls.transformer.XLSTransformer;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDataFormatter;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName: ExcelUtils
 * @Description: TODO>
 * @author he.sun
 * @date 2014年12月18日 上午10:15:18
 * 
 */
public class ExcelUtil {

	public static <T> String exportExcel(String fileSavePath, List<?> datas,
			Column[] columns) throws ExportException {

		String filename = UUID.randomUUID() + ".xls";
		if (!FileUtil.IsExist(fileSavePath)) {
			FileUtil.createFolders(fileSavePath);
		}

		OutputStream out = null;
		try {
			out = new FileOutputStream(fileSavePath + filename);
			// 创建工作区(97-2003)
			Workbook workbook = new HSSFWorkbook();
			// 生成一个表格
			Sheet sheet = workbook.createSheet("sheet1");
			// 设置表格默认列宽度为15个字节
			sheet.setDefaultColumnWidth((short) 15);

			int rowindex = 0;
			// 产生表格标题行
			Row rowHead = sheet.createRow(rowindex++);
			for (short i = 0; i < columns.length; i++) {
				rowHead.createCell(i).setCellValue(columns[i].headname());
			}

			// 遍历集合数据，产生数据行
			for (int index = 0; index < datas.size(); index++) {

				Row row = sheet.createRow(rowindex++);
				T t = (T) datas.get(index);

				for (int i = 0; i < columns.length; i++) {
					Column column = columns[i];

					// 利用反射，根据javabean属性的先后顺序，动态调用getXxx()方法得到属性值
					String fieldName = column.value();

					Object value = ReflectUtil.invokeGet(t, fieldName);
					// 判断值的类型后进行强制类型转换
					String textValue = null;

					if (column.format() != null) {
						Class<?> format = column.format();
						Method method = format
								.getMethod("format", Object.class);
						Object ro = method.invoke(format.newInstance(), value);
						textValue = (ro == null) ? "" : ro.toString();
					}
					// 如果不是图片数据，就利用正则表达式判断textValue是否全部由数字组成
					if (textValue != null) {
						Pattern p = Pattern.compile("^//d+(//.//d+)?$");
						Matcher matcher = p.matcher(textValue);
						if (matcher.matches()) {
							// 是数字当作double处理
							row.createCell(i).setCellValue(
									Double.parseDouble(textValue));
						} else {
							row.createCell(i).setCellValue(textValue);
						}
					}

				}

			}
			// 写文件
			workbook.write(out);
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			throw new ExportException(e.getMessage());
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			throw new ExportException(e.getMessage());
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			throw new ExportException(e.getMessage());
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			throw new ExportException(e.getMessage());
		} catch (Exception e) {
			throw new ExportException(e.getMessage());
		} finally {
			// 关闭输出流
			try {
				if (null != out) {
					out.close();
				}
			} catch (IOException e) {
				throw new ExportException(e.getMessage());
			}
		}
		return filename;

	}

	/**
	 * @Title：exportExcel
	 * @Description: TODO
	 * @author he.sun
	 * @date 2014年12月18日 下午2:02:17
	 * @param string
	 * @param in
	 * @param columns
	 * @param template
	 * @return
	 */
	public static String exportExcel(String string, List<?> in,
			Column[] columns, String template) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @Title：exportExcel
	 * @Description: 根据模版输出
	 * @author he.sun
	 * @date 2014年12月22日 下午2:36:46
	 * @param fileSavePath
	 * @param templateDir
	 * @param data
	 * @return
	 */
	public static String exportExcel(String fileSavePath, String templateDir,
			Object data) throws ExportException {

		String filename = UUID.randomUUID() + ".xls";
		if (!FileUtil.IsExist(fileSavePath)) {
			FileUtil.createFolders(fileSavePath);
		}
		OutputStream os = null;
		try {
			InputStream is = new FileInputStream(templateDir);
			Map beans = new HashMap();
			
			//如果是null 则使数组为{}
			if(data==null){
				data = new Object[0];
			}
			beans.put("edata", data);
			// 关联模板
			XLSTransformer transformer = new XLSTransformer();
			Workbook workBook = transformer.transformXLS(is, beans);
			os = new FileOutputStream(fileSavePath + filename);
			workBook.write(os);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			throw new ExportException(e.getMessage());
		} catch (ParsePropertyException e) {
			// TODO Auto-generated catch block
			throw new ExportException(e.getMessage());
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			throw new ExportException(e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new ExportException(e.getMessage());
		} finally {
			// 关闭输出流
			try {
				if (null != os) {
					os.close();
				}
			} catch (IOException e) {
				throw new ExportException(e.getMessage());
			}
		}

		return filename;
	}
	
	public static List<List<Map<String, Object>>> readExcel(InputStream is, String[] names, int readline) {
		Workbook wb = null;
		List<List<Map<String, Object>>> list = null;
		List<Map<String, Object>> listMap = null;
		Map<String, Object> map = null;
		try {
			wb = WorkbookFactory.create(is);
			int Sheets = wb.getNumberOfSheets();
			if (Sheets > 0) {
				list = new ArrayList<List<Map<String, Object>>>();
			}
			//for (int i = 0; i < Sheets; i++) {
				listMap = new ArrayList<Map<String, Object>>();
				Sheet sheet = wb.getSheetAt(0);
				int rowNum = sheet.getLastRowNum();
				for (int j = readline; j <= rowNum; j++) {
					map = new HashMap<String, Object>();
					Row row = sheet.getRow(j);
					if(row != null) {
						int cessNum = row.getLastCellNum();
						for (int x = 0; x < cessNum; x++) {
							Cell cell = row.getCell(x);
							String value = "";
							int cellType;
							if(cell != null) {
								cellType = cell.getCellType();
								if (cellType == HSSFCell.CELL_TYPE_NUMERIC) {
						        	if (HSSFDateUtil.isCellDateFormatted(cell)) {
						        		Date date = cell.getDateCellValue();
						        		SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						        		value = dateFormater.format(date);
						        	} else {
						        		HSSFDataFormatter dataFormatter = new HSSFDataFormatter();
						        		value = dataFormatter.formatCellValue(cell);
						        	}
								}else{
								    value = cell.getRichStringCellValue().getString();
						        }
							}
							if(!value.equals("")) {
								map.put(names[x], value);
							}
						}
					} else {
						continue;
					}
					listMap.add(map);
				}
				list.add(listMap);
			//}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void exportExcel(List list, FileInputStream in, String name, HttpServletResponse response) throws Exception {
		Map<String, List<Map<String, Object>>> excelMap=new HashMap();
		excelMap.put("list", list); 
		XLSTransformer transformer = new XLSTransformer();
		Workbook workBook = transformer.transformXLS(in, excelMap);
		workBook.setSheetName(0, "Sheet1");
		String filename = name;  
		downloadExcel(workBook,response,filename); 
	}
	
	private static void downloadExcel(Workbook workbook, HttpServletResponse response, String fileName){
		try{  
			response.setContentType("application/x-excel");
            response.setHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes("gb2312"),"ISO-8859-1")  +".xlsx");
		    OutputStream os = response.getOutputStream(); 
		    workbook.write(os);     
		    os.flush();
		    os.close();
		}catch (Exception e) {  
			e.printStackTrace();
		}
	}
	
}
