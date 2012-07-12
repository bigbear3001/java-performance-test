package com.perhab.napalm.implementations.simpledateformat;

import com.perhab.napalm.statement.Execute;

public interface FormatDate {
	
	static final String DATE_FORMAT = "dd.MM.yyyy";
	
	@Execute(parameters = { })
	String getDate();
}