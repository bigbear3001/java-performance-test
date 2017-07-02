package com.perhab.napalm.implementations.string.match;

import com.perhab.napalm.statement.Execute;
import com.perhab.napalm.statement.Parameter;

/**
 * Created by bigbear3001 on 02.07.17.
 */
public interface Match {

    @Execute(parameters = {@Parameter("123...456")})
    boolean matches(String value);
}
