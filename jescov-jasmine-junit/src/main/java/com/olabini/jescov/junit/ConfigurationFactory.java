/*
 * See LICENSE file in distribution for copyright and licensing information.
 */
package com.olabini.jescov.junit;

import be.klak.junit.jasmine.JasmineSuite;

import com.olabini.jescov.Configuration;

public class ConfigurationFactory {
    public static Configuration getConfiguration(JasmineSuite suite) {
        Configuration config = new Configuration();
        config.setEnabled(Boolean.getBoolean("com.olabini.jescov.enabled"));

        String jsDir = suite.jsRootDir();
		config.ignore("/lib/es5-shim-0.0.4.min.js");
        config.ignore("/lib/env.rhino.1.2.js");
		config.ignore("/lib/env.utils.js");
		config.ignore("/envJsOptions.js");
		config.ignore("/lib/jasmine-1.3.1/jasmine.js");
		config.ignore("/lib/jasmine-1.3.1/jasmine.delegator_reporter.js");
		config.ignore("script");

        for(String spec : suite.specs()) {
            config.ignore(jsDir +  "/specs/" + spec);
        }

        String[] ignoreFiles = System.getProperty("com.olabini.jescov.ignoreFiles", "").split(",");
        for(String files : ignoreFiles) {
            config.ignore(files.trim());
        }

        if(System.getProperty("com.olabini.jescov.jsonOutputFile") != null) {
            config.setJsonOutputFile(System.getProperty("com.olabini.jescov.jsonOutputFile"));
        } else {
        	config.setJsonOutputFile("target/jescpv.json.ser");
        }
        if(System.getProperty("com.olabini.jescov.htmlOutputDir") != null) {
            config.setHtmlOutputDir(System.getProperty("com.olabini.jescov.htmlOutputDir"));
        } else {
        	config.setHtmlOutputDir("target/coverage-report");
        }

        config.setJsonOutputMerge(Boolean.getBoolean("com.olabini.jescov.jsonMerge"));

        return config;
    }
}
