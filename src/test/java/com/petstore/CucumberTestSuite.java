package com.petstore;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features")
@ConfigurationParameter(key = "cucumber.glue", value = "com.petstore")
@ConfigurationParameter(key = "cucumber.plugin", value = "io.cucumber.core.plugin.SerenityReporterParallel,pretty,html:target/cucumber-reports/cucumber.html,json:target/cucumber-reports/cucumber-report.json")
public class CucumberTestSuite {
}
