package HomePage;

import BaseApi.Base;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by HP on 9/18/2015.
 */
public class HomeMenu extends Base {

    @Test
    public void homeMenu() throws InterruptedException {
        List<String> list=getListOfTextByXpath(".//*[@id='hdMenuDiv']/ul/li");
        displayText(list);
        for(int i=1;i<list.size()+1;i++){
            clickByXpath(".//*[@id='hdMenuDiv']/ul/li["+i+"]/a");
            sleepFor(2);
        }

    }

}
