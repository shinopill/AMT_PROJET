package bindings;



import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.util.concurrent.TimeUnit;
import static org.junit.Assert.assertNotEquals;
;

public class functional_test {
    WebDriver driver = new FirefoxDriver();

    @Given("^that i am on the website$")
    public void that_i_am_on_the_shopping_website() throws Throwable {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("http://localhost:8080/amt_project/");
        driver.manage().window().maximize();
    }

    @When("^i create an account$" )
    public void i_create_my_account() throws Throwable {
        WebElement account = driver.findElement(By.linkText("Create an account?"));
        account.click();
        Thread.sleep(1100);
        WebElement firstname = driver.findElement(By.xpath("//*[@name=\"firstname\"]"));
        firstname.sendKeys("Test");
        WebElement lastname = driver.findElement(By.xpath("//*[@name=\"lastname\"]"));
        lastname.sendKeys("Test");
        WebElement email = driver.findElement(By.xpath("//*[@name=\"email\"]"));
        email.sendKeys("test@test.com");
        WebElement password = driver.findElement(By.xpath("//*[@name=\"password\"]"));
        password.sendKeys("t");
        WebElement passwordconf = driver.findElement(By.xpath("//*[@name=\"confirmPassword\"]"));
        passwordconf.sendKeys("t");
        WebElement submit = driver.findElement(By.xpath("//*[@value=\"Register\"]"));
        Thread.sleep(1000);
        submit.click();
        Thread.sleep(10);


    }
    @When("^i log$")
    public void i_try_to_login() throws Throwable {
        WebElement email = driver.findElement(By.xpath("//*[@name=\"Email\"]"));
        email.sendKeys("admin@admin.com");
        WebElement passwd = driver.findElement(By.xpath("//*[@name=\"Password\"]"));
        passwd.sendKeys("a");
        WebElement submit = driver.findElement(By.xpath("//*[@value=\"Login\"]"));
        submit.submit();
    }

    @When("^i log2$")
    public void i_try_to_login2() throws Throwable {
        WebElement email = driver.findElement(By.xpath("//*[@name=\"Email\"]"));
        email.sendKeys("test@test.com");
        WebElement passwd = driver.findElement(By.xpath("//*[@name=\"Password\"]"));
        passwd.sendKeys("t");
        WebElement submit = driver.findElement(By.xpath("//*[@value=\"Login\"]"));
        submit.submit();


    }

    @When("^I am on my page i create 25 apps$")
    public void i_try_to_create_25_apps() throws Throwable{

        for(int i = 0 ; i < 25; i+=1){
            Thread.sleep(5);
            WebElement appregister = driver.findElement(By.linkText("Add an app"));
            appregister.click();
            WebElement name = driver.findElement(By.xpath("//*[@name=\"appName\"]"));
            name.sendKeys(String.valueOf(i));
            WebElement text = driver.findElement(By.xpath("//*[@name=\"descripton\"]"));
            text.sendKeys(String.valueOf(i));
            WebElement add = driver.findElement(By.xpath("//*[@name=\"Add\"]"));
            Thread.sleep(100);
            add.submit();
            Thread.sleep(100);
        }
    }

    @When("^I am on my page i scroll throught 3 pages$")
    public void i_scroll_though_my_pages() throws Throwable {
        WebElement next = driver.findElement(By.linkText("Next"));
        WebElement previous = null;
        Thread.sleep(1000);
        next.click();
        Thread.sleep(1000);
        next = driver.findElement(By.linkText("Next"));
        next.click();
        Thread.sleep(20000);
        WebElement logout =  driver.findElement(By.linkText("Logout"));
        logout.click();


    }

    @When("^i am on the login page i try to access a forbiden page$")
    public void i_try_to_access_forbiden_page() throws InterruptedException {
        driver.get("http://localhost:8080/amt_project/filtered/view");
        Thread.sleep(1000);
    }

    @Then("^i am still on login page$")
    public void i_am_still_on_login_page(){
        WebElement email = driver.findElement(By.xpath("//*[@name=\"Email\"]"));
        email.sendKeys("admin@admin.com");
        WebElement passwd = driver.findElement(By.xpath("//*[@name=\"Password\"]"));
        passwd.sendKeys("a");
        assertNotEquals(null,email);
        assertNotEquals(null,passwd);
    }

    @Then("^i am on my user page$")
    public void i_am_on_my_page() throws Throwable {

       driver.quit();
    }

}




