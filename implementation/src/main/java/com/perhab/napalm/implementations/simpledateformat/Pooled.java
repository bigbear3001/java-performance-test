package com.perhab.napalm.implementations.simpledateformat;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Pooled implements FormatDate, FormatDateParallel {
	
	private SimpleDateFormatPool dateFormat = new SimpleDateFormatPool(DATE_FORMAT);

	@Override
	public String getDate() {
		return dateFormat.format(new Date());
	}
	
	private class SimpleDateFormatPool {

		private ArrayList<SimpleDateFormat> pool = new ArrayList<SimpleDateFormat>();
		
		private String dateFormat;
		
		public SimpleDateFormatPool(String dateFormat) {
			this.dateFormat = dateFormat;
		}

		public String format(Date date) {
			SimpleDateFormat format;
			synchronized (pool) {
				if (pool.size() == 0) {
					format = new SimpleDateFormat(dateFormat);
				} else {
					format = pool.remove(0);
				}
			}
			String result = format.format(date);
			synchronized (pool) {
				pool.add(format);
			}
			return result;
		}
		
		
	}

}
