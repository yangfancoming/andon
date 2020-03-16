package com.sim.andon.web.core.jdbc.annotation;

import java.lang.annotation.*;

/** 
 * @author Kabruce E-mail: 770141401@qq.com
 * @version 创建时间：2013-3-24 下午11:16:55 
 *  类说明 
 */

@Target(ElementType.FIELD)  
@Retention(RetentionPolicy.RUNTIME)  
@Documented 
@Inherited
public @interface NotRecord {

}
