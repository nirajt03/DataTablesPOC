package io.testing.tables.datatables;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(
		dryRun = false,
		monochrome = true,
		features = {"src/test/resources/io/testing/tables/datatables/DataTables.feature"},
		plugin = { "pretty"},
		glue = {"classpath:io.testing.tables.datatables"}
		)
public class TestDataTableRunner extends AbstractTestNGCucumberTests {

}
