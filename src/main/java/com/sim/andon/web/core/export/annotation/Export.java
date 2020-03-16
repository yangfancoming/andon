package com.sim.andon.web.core.export.annotation;

import java.lang.annotation.*;

/**
 * 
* @ClassName: Export 
* @Description: 报表导出
* @author he.sun
* @date 2014年12月17日 上午10:41:43 
*
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
// 方法级别
public @interface Export {
	
	Column[] columns() default {};//列（含有标题、属性、格式化类）
	
	String template() default "";//模版文件名称
}
