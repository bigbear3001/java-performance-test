package com.perhab.napalm.implementations.simpledateformat;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Simple implements FormatDate, FormatDateParallel {

	@Override
	public String getDate() {
		return new SimpleDateFormat(DATE_FORMAT).format(new Date());
	}

}
