package com.perhab.napalm;

import java.util.Collection;

public class SystemOutPrinter implements Printer {

	public void print(Collection<Result> results) {
		for (Result result : results) {
			System.out.print(result.getStatement());
			System.out.print(": ");
			System.out.print(result.getTime());
			System.out.println("ms");
			
		}
	}

}
