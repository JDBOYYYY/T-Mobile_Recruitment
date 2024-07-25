package org.example.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import static com.codeborne.selenide.Selenide.*;

public class DevicesPage extends BasePage{
    SelenideElement deviceList = $(".grid-child:first-of-type");

    public void clickFirstElement() {
        $(By.xpath("(//div[@class=\"grid-child\"]//a)[1]")).click();
    }
    public boolean isDeviceListDisplayed(){
        return deviceList.isDisplayed();
    }
}
