package BaseApi;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by HP on 9/18/2015.
 */
public class Base {
    public WebDriver driver;

    @Parameters({"UseSauceLabs","userName","Key","BrowserName","BrowserVersion","os","url"})
    @BeforeMethod
    public void setUp(Boolean UseSauceLabs,String userName,String Key,String BrowserName,
                      String BrowserVersion,String os,String url) throws IOException{
        if(UseSauceLabs==true){
            setUpCloudEnvironment(userName,Key,BrowserName,BrowserVersion,os,url);
        }
        else{
            setUpLocalEnvironment(BrowserName,BrowserVersion,url);
        }

    }

    public void setUpCloudEnvironment(String userName,String Key,String BrowserName,
                                      String BrowserVersion,String os,String url)throws IOException{

        DesiredCapabilities cap=new DesiredCapabilities();
        cap.setBrowserName(BrowserName);
        cap.setCapability("version",BrowserVersion);
        cap.setCapability("platform",os);

        this.driver=new RemoteWebDriver(new URL("http://"+userName+":"+Key+"@ondemand.saucelabs.com:80/wd/hub"),cap);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get(url);
        driver.manage().window().maximize();
    }

    public void setUpLocalEnvironment(String BrowserName,String BrowserVersion,String url){
        if(BrowserName.equalsIgnoreCase("firefox")){
            driver=new FirefoxDriver();
        }

        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get(url);
        driver.manage().window().maximize();

    }

    @AfterMethod
    public void cleanUp(){
        driver.close();
        driver.quit();
    }


    public void clickByCss(String locator){
        driver.findElement(By.cssSelector(locator)).click();
    }
    public void clickByXpath(String locator){
        driver.findElement(By.xpath(locator)).click();
    }
    public void typeByCss(String locator,String value){
        driver.findElement(By.cssSelector(locator)).sendKeys(value);
    }
    public void typeByXpath(String locator,String value){
        driver.findElement(By.xpath(locator)).sendKeys(value);
    }
    public void typeAndAEnterByCss(String locator,String value){
        driver.findElement(By.cssSelector(locator)).sendKeys(value, Keys.ENTER);
    }
    public void typeAndAEnterByXpath(String locator,String value){
        driver.findElement(By.xpath(locator)).sendKeys(value, Keys.ENTER);
    }
    public void clickByText(String locator){
        driver.findElement(By.linkText(locator)).click();
    }
    public void getTextByCss(String locator){
        driver.findElement(By.cssSelector(locator)).getText();
    }
    public void getTextByXpath(String locator){
        driver.findElement(By.xpath(locator)).getText();
    }

    public List<String> getListOfTextByCss(String locator){
        List<WebElement> elements=driver.findElements(By.cssSelector(locator));
        ArrayList<String> list=new ArrayList<String>();
        for(WebElement wb:elements){
            list.add(wb.getText());
        }
        return list;
    }

    public List<String> getListOfTextByXpath(String locator){
        List<WebElement> elements=driver.findElements(By.xpath(locator));
        ArrayList<String> list=new ArrayList<String>();
        for(WebElement wb:elements){
            list.add(wb.getText());
        }
        return list;
    }

    public void displayText(List<String> list){
        for(String st:list){
            System.out.println(st);
        }
    }

    public List<WebElement> getListOFElementsByCss(String locator){
        List<WebElement> elements=driver.findElements(By.cssSelector(locator));
        return elements;
    }
    public List<WebElement> getListOFElementsByXpath(String locator){
        List<WebElement> elements=driver.findElements(By.xpath(locator));
        return elements;
    }
    public void navigateBack(){
        driver.navigate().back();
    }
    public void sleepFor(int sec) throws InterruptedException {
        Thread.sleep(1000*sec);
    }

    public void mouseOver(List<WebElement> elements) throws InterruptedException {
        for(WebElement st:elements) {
            Actions action = new Actions(driver);
            action.moveToElement(st).build().perform();
            Thread.sleep(2000);
        }
    }

    public void takeScreenShot(String locator) throws IOException {
        File file= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file, new File(locator), true);
    }

}
