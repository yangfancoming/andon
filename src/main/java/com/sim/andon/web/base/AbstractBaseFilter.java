package com.sim.andon.web.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

@SuppressWarnings("serial")
public abstract class AbstractBaseFilter implements Serializable{
	
	/**
	   * @Fields logger : log4j日志对象
	  */
	public final Logger logger = LoggerFactory.getLogger(getClass());
}

