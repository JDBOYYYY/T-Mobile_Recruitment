package org.example.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public class ProductPage extends BasePage{
    SelenideElement deviceHeader = $(By.xpath("//h1"));
    private SelenideElement totalUpfrontElement = $(By.xpath("(//div[@data-qa = 'PRD_TotalUpfront']/div/div)[2]"));
    private SelenideElement monthlyRateElement = $(By.xpath("(//div[contains(@class, 'dt_price_change')]//div)[4]"));
    public void addToBasket() {
        $(By.xpath("(//div[text() =\"Dodaj do koszyka\"]/../..)[2]")).click();
        SelenideElement loadingElement = $x("//*[contains(text(), 'Ładowanie...')]");
        loadingElement.should(Condition.disappear);
    }
    public boolean isDeviceHeaderDisplayed(){
        return deviceHeader.isDisplayed();
    }

    public String getTotalUpfront() {
        return totalUpfrontElement.getText();
    }

    public String getMonthlyRate() {
        return monthlyRateElement.getText();
    }
}