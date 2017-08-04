package com.perhab.napalm.implementations.simpledateformat;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Synchronized implements FormatDate, FormatDateParallel {
	
	private SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);

	@Override
	public String getDate() {
		synchronized (dateFormat) {
			return dateFormat.format(new Date());
		}
	}

}
