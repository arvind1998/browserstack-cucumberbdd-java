package parallel;

import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;
import io.cucumber.testng.AbstractTestNGCucumberTests;


@CucumberOptions(
        features="src/test/java/parallel/features"
        ,
        glue = {"parallel.stepdefs"}
)
public class RunParallel extends AbstractTestNGCucumberTests {


    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
