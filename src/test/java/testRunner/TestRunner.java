package testRunner;
import org.junit.runner.RunWith;

//import com.github.mkolisnyk.cucumber.runner.ExtendedCucumber;
import com.github.mkolisnyk.cucumber.runner.ExtendedCucumberOptions;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@ExtendedCucumberOptions(jsonReport="target/cucumber.json",
                          detailedReport=true,
                          detailedAggregatedReport=true,
                          overviewReport=true,
                          coverageReport=true,
                          jsonUsageReport="target/cucumber-usage.json",
                          usageReport=true,
                          toPDF=true,
                          outputFolder="target"
		)
@CucumberOptions(
		features= "Features",glue="stepDefinition",
				plugin={"html:target/cucumber-html-report",
						"json:target/cucumber.json",
						"pretty:target/cucumber-pretty.txt",
						"usage:target/cucumber-usage.json",
						"junit:target/cucumber-results.xml"},
				strict=true,
				monochrome=true,
				dryRun=false
		)

public class TestRunner {

}
