package com.perhab.napalm.statement;

import java.lang.reflect.Method;

import com.perhab.napalm.Result;

public interface Statement {

	public abstract Result execute();

	public abstract Method getMethod();

}