package org.example;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class EverNoteLoginTest {

    private WebDriver driver ;
    private String baseURL = "https://www.evernote.com";
    /*
     * 	Please Enter Valid username and password here
     */
    private String validUsername = "";
    private String validPassword = "";

    @BeforeClass
    public void beforeClass () {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void Test1_NoCredentials() {
        driver.get(baseURL);
        driver.findElement(By.xpath("//a[@href='https://www.evernote.com/Login.action']")).click();
        clickWhenReady(By.id("loginButton"),20);
        WebElement responseMsg = waitForElement(By.xpath("//div[@id='responseMessage']"),20);
        String msg = responseMsg.getText();
        assertEquals(msg,"Required data missing");
    }

    @Test
    public void Test2_ValidCredentials() {
        boolean status;
        driver.get(baseURL);
        driver.findElement(By.xpath("//a[@href='https://www.evernote.com/Login.action']")).click();
        waitForElement(By.id("username"),20).sendKeys(validUsername);
        clickWhenReady(By.id("loginButton"),20);
        waitForElement(By.id("password"),20).sendKeys(validPassword);
        clickWhenReady(By.id("loginButton"),20);
        status = waitForElement(By.id("qa-HOME_TITLE"),20).isDisplayed();
        assertTrue(status);
    }

    @AfterClass
    public void afterClass () {
        driver.quit();
    }

    public WebElement waitForElement (By locator , int timeout) {
        WebElement element = null;
        try {
            System.out.println("waiting for maximum of "+ timeout + "seconds");
            WebDriverWait wait = new WebDriverWait (driver, timeout);
            element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        }
        catch (Exception e){
            System.out.println("Element not present on the WebPage");
        }
        return element;
    }

    public void clickWhenReady(By locator, int timeout) {
        WebElement element = null;
        try {
            System.out.println("waiting for maximum of " + timeout + "seconds");
            WebDriverWait wait = new WebDriverWait(driver, timeout);
            element = wait.until(ExpectedConditions.elementToBeClickable(locator));
            element.click();
        } catch (Exception e) {
            System.out.println("Element not present on the WebPage");
        }
    }
}
