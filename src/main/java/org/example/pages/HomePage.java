package org.example.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import static com.codeborne.selenide.Selenide.*;

public class HomePage extends BasePage{
    private SelenideElement devicesButton = $(By.xpath("//button[text()=\"Urządzenia\"]"));
    private SelenideElement acceptCookiesButton = $(By.xpath("//button/span[text()=\"Zaakceptuj wszystkie\"]"));

    private SelenideElement mainPageMain = $("#content-main");
    private SelenideElement dropDownMenu = $(By.xpath("(//div[contains(@class, 'hidden menu-dropdown-submenu')])[1]"));

    private  SelenideElement basketItemCountElement = $(By.xpath("//span[@class='count']"));

    public void openHomePage(String url) {
        open(url);
    }

    public boolean isMainPageDisplayed(){
        return mainPageMain.isDisplayed();
    }

    public void acceptCookies() {
        acceptCookiesButton.click();
    }

    public void hoverOverDevices() {
        devicesButton.hover();
    }

    public boolean isDropDownMenuDisplayed(){
        return dropDownMenu.isDisplayed();
    }

    public void clickOptionFromColumn(String option, String column) {
        String xpathExpression = String.format("//p[contains(text(), '%s')]/../following-sibling::ul//span[contains(text(), '%s')]", column, option);
        $(By.xpath(xpathExpression)).click();
    }
    public int getBasketItemCount(){
        return Integer.parseInt(basketItemCountElement.getText());
    }
}
