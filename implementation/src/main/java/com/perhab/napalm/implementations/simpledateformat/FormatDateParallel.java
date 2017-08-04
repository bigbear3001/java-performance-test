package com.perhab.napalm.implementations.simpledateformat;

import com.perhab.napalm.statement.ExecuteParallel;

public interface FormatDateParallel {
	
	@ExecuteParallel(parameters = { })
	String getDate();
}
