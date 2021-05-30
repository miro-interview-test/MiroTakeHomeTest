package support;

import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty", "html:target/miro_report.html", "json:target/miro_report.json"},
                glue = {"steps", "support"},
                features = {"src/test/resources/features"})
public class TestRunner {

}
