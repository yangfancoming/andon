/**
 * CopyRright @2014 SIM TECHNOLOGY ALL RIGHTS RESERVED
 * @Title: Column.java
 * @Package com.sim.machine.web.core.export.annotation
 * @Description: TODO
 * @author he.sun
 * @date 2014年12月17日 下午5:12:47
 * @version V1.0
 */
package com.sim.andon.web.core.export.annotation;

import com.sim.andon.web.core.export.format.Format;

/**
 * @ClassName: Column
 * @Description: TODO>
 * @author he.sun
 * @date 2014年12月17日 下午5:12:47
 * 
 */
public @interface Column {

	String value() default "";//属性

	String headname() default "";//标题

	Class<?> format() default Format.class;//格式化类
}
