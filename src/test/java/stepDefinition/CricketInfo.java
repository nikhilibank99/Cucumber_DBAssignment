package stepDefinition;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class CricketInfo {

    private static final Logger log = Logger.getLogger(CricketInfo.class);
    public static WebDriver driver;


        @Given("I launch web application")
        public void i_launch_web_application() throws IOException {
//        	System.setProperty("window.chrome.driver","C:\\Users\\246416\\CUCUMBERCOMPLETEPOMOCT\\Drivers");
        	System.getProperty(("user.dir")+"\\CUCUMBERCOMPLETEPOMOCT\\Drivers\\chromedriver.exe");
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            Properties props=new Properties();
            props.load(new FileInputStream(("log4j.properties")));
            PropertyConfigurator.configure(props);
            log.info("###Browser Launched###");


        }
        @When("I Entered Url")
        public void i_entered_url() throws FileNotFoundException {
            FileReader reader=new FileReader("C:\\Users\\246416\\CUCUMBERCOMPLETEPOMOCT\\config\\object.properties");
            Properties prop = new Properties();
            try{
                prop.load(reader);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            driver.navigate().to(prop.getProperty("url"));
            log.info("###Url Entered###");
            driver.manage().timeouts().implicitlyWait(2000L, TimeUnit.SECONDS);
            WebElement poup= driver.findElement(By.xpath("//div[text()='Latest cricket updates']"));
            if(poup.isDisplayed()){
                driver.findElement(By.xpath("//button[text()=\"Yes! I'm in\"]")).click();

          }

        }
        @Then("I should be able to capture cricket score update")
        public void i_should_be_able_to_capture_cricket_score_update() {
        	String text=driver.findElement(By.xpath("//a[text()='Live Scores']")).getText();
        	Assert.assertEquals(text, "Live Scores");
        	log.info("Assertion Passed");
            driver.findElement(By.xpath("//a[text()='Live Scores']")).click();
            log.info("###Clicked on Live score link###");
        //    log.info(pagetitle);
            List<WebElement> matchinfo= driver.findElements(By.xpath("//div[@class='ds-px-4 ds-py-3']"));
            for(int i=0;i<matchinfo.size();i++) {
                log.info("Match: "+i+"");
                log.info(matchinfo.get(i).getText());

            }


    }
    @Then("I close browser")
    public void i_close_browser() {
       driver.close();
        log.info("$$$Browser Closed$$$");
    }
}


