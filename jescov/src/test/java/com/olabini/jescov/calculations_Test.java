/*
 * See LICENSE file in distribution for copyright and licensing information.
 */
package com.olabini.jescov;

import org.junit.runner.RunWith;

import cucumber.junit.Cucumber;

@RunWith(Cucumber.class)
@Cucumber.Options(features = "features/calculations.feature")
public class calculations_Test {
}
