package HomePage;
import BaseApi.Base;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by HP on 9/18/2015.
 */
public class CustomerLogin extends Base{
    String Expected="MetLife – Insurance, Benefits, Retirement- Get a Quote Today";
    @Test
    public void customerLogin() throws InterruptedException {
        System.out.println(driver.getCurrentUrl());
        String actualTitle=driver.getTitle();
        System.out.println(actualTitle);
        Assert.assertEquals(actualTitle,Expected);

        typeByXpath(".//*[@id='alphaNumericType_RRGenericForm108329_username']", "Rajib");
        sleepFor(2);
        typeAndAEnterByXpath(".//*[@id='password_RRGenericForm108329_password']","rajib0078");
        sleepFor(2);
    }
}
