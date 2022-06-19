package com.github.dbee;

import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;
import java.util.Random;

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
//        inst.setNativeMethodPrefix(transformer, randomMethodName(15) + "_");
        try {

            inst.retransformClasses(Class.forName("com.dbeaver.lm.embedded.LicenseServiceEmbedded"));
            inst.retransformClasses(Class.forName("com.dbeaver.lm.validate.PublicLicenseValidator"));
            inst.retransformClasses(Class.forName("com.dbeaver.lm.validate.PublicServiceClient"));
        } catch (ClassNotFoundException | UnmodifiableClassException e) {
            e.printStackTrace();
        }
    }

}
