package com.perhab.napalm;

import java.util.Collection;

public class SystemOutPrinter implements Printer {

	private static final long STARTTIME_NOT_SET = -1L;

	public void print(Collection<Result> results) {
		for (Result result : results) {
			System.out.print(result.getStatement());
			System.out.print(':');
			long starttime = STARTTIME_NOT_SET;
			for (long time : result.getTimes()) {
				if (starttime == STARTTIME_NOT_SET) {
					starttime = time;
				} else {
					System.out.print(' ');
					System.out.print(time - starttime);
					System.out.print("ms");
				}
			}
			System.out.println();
		}
	}

}
