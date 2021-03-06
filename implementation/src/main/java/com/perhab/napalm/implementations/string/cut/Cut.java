package com.perhab.napalm.implementations.string.cut;

import com.perhab.napalm.statement.Description;
import com.perhab.napalm.statement.Execute;
import com.perhab.napalm.statement.Parameter;

/**
 * Created by bigbear3001 on 02.07.17.
 */
public interface Cut {
    @Execute(parameters = {@Parameter("[Ljava.lang.String;")})
    @Description("Tests the performance of cutting a value out of a String")
    String cut(String value);
}
