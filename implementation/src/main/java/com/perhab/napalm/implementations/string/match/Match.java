package com.perhab.napalm.implementations.string.match;

import com.perhab.napalm.statement.Description;
import com.perhab.napalm.statement.Execute;
import com.perhab.napalm.statement.Parameter;

/**
 * Created by bigbear3001 on 02.07.17.
 */
public interface Match {

    @Execute(parameters = {@Parameter("123...456")})
    @Description("Tests the performance of matching a String agains a certain pattern")
    boolean matches(String value);
}
