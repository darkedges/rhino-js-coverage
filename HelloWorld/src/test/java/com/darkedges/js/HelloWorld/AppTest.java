package com.darkedges.js.HelloWorld;

import org.junit.runner.RunWith;

import com.olabini.jescov.junit.JasmineTestRunner;

import be.klak.junit.jasmine.JasmineSuite;

/**
 * Unit test for simple App.
 */
@RunWith(JasmineTestRunner.class)
@JasmineSuite(sources = {
		"one.js" }, sourcesRootDir = "src/main/javascript", jsRootDir = "src/test/javascript", specs = { "oneSpec.js", "twoSpec.js" })
public class AppTest {
}
