package com.test.app;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources",glue = {"com.test.app"},monochrome = true,
plugin = {"pretty","html:target/HtmlReport.html"})

public class RunCucumberClass {
}
