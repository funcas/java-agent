package com.github.dbee;

import java.lang.instrument.Instrumentation;

/**
 * TODO
 *
 * @author shane
 * @since 1.0
 */
public class DbeeAgent {

    public static void premain(String args, Instrumentation inst) {
        DbeeTransformer transformer = new DbeeTransformer();
        inst.addTransformer(transformer, true);
    }

}
