package ru.nshpakov.cucumber.steps;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(

        plugin = {"pretty"},
        features = "src\\test\\resources\\features\\",
        glue = {"ru.nshpakov.cucumber.steps.test"}
)
public class RunCucumberTest {

}
