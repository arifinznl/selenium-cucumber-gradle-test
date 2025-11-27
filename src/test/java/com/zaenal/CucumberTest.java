package com.zaenal;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        glue = {"com.zaenal"},
        features = {"src/test/resources"},
        plugin = {"pretty",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
                "html:reports/cucumber.html",
                "json:reports/cucumber.json"

        }
)
public class CucumberTest {
}
