package com.perhab.napalm.implementations.simpledateformat;

import com.perhab.napalm.statement.Description;
import com.perhab.napalm.statement.ExecuteParallel;

public interface FormatDateParallel {
	
	@ExecuteParallel(parameters = { })
	@Description("Tests the performance of formatting the current date with 2 parallel threads.")
	String getDate();
}
