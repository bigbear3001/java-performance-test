package com.perhab.napalm;

import java.util.Collection;

public class SystemOutPrinter implements Printer {

	private static final long STARTTIME_NOT_SET = -1L;

	public void print(Collection<Result> results) {
		Class<?> lastImplementedInterface = null;
		for (Result result : results) {
			Class<?> implementedInterface = result.getStatement().getGroup().getImplementedInterface();
			if (implementedInterface != lastImplementedInterface) {
				System.out.print("\n" + implementedInterface.getCanonicalName() + "\n");
				lastImplementedInterface = implementedInterface;
			}
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
