package de.cdelmonte.fds.datagenerator.orchestrator;
import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;


@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty", "io.cucumber.pro.JsonReporter:all"})
public class RunCukesIT {

}
