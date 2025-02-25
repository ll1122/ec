/**
 *  Copyright (c) 2012-2024 https://www.eryansky.com
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.eryansky.common.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 数据库访问层异常,继承自BaseException.
 * 
 * @author Eryan
 * @date 2013-43-10 上午12:08:55
 */
@SuppressWarnings("serial")
public class DaoException extends BaseException {

	private Throwable rootCause;

	public DaoException() {
		super();
	}

	public DaoException(String message) {
		super(message);
	}

	public DaoException(Throwable cause) {
		super(cause);
		this.rootCause = cause;
	}

	public DaoException(String message, Throwable cause) {
		super(message, cause);
		this.rootCause = cause;
	}

	public String getTraceInfo() {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		printStackTrace(pw);
		pw.flush();
		sw.flush();
		return sw.toString();
	}

	public Throwable getRootCause() {
		return rootCause;
	}

}
