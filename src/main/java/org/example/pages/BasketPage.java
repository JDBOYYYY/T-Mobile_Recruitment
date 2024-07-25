package org.example.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BasketPage extends BasePage{
    private SelenideElement basketTotalUpfrontElement = $(By.xpath("//span[@data-qa = \"BKT_TotalupFront\"]"));
    private SelenideElement basketMonthlyRateElement = $(By.xpath("//span[@data-qa = \"BKT_TotalMonthly\"]"));

    private SelenideElement basketTotalUpfrontElementSymbol = $(By.xpath("//span[@data-qa = \"BKT_TotalMonthlySymbol\"]"));
    private SelenideElement basketMonthlyRateElementSymbol = $(By.xpath("//span[@data-qa = \"BKT_TotalupFrontSymbol\"]"));
    private  SelenideElement backButton = $(By.xpath("//div[text()=\"Wróć\"]"));

    public String getBasketTotalUpfront() {
        return basketTotalUpfrontElement.getText()+" "+basketTotalUpfrontElementSymbol.getText();
    }

    public String getBasketMonthlyRate() {
        return basketMonthlyRateElement.getText()+" "+basketMonthlyRateElementSymbol.getText();
    }

    public boolean comparePrices(String totalUpfront, String monthlyRate) {
        boolean isTotalUpfrontEqual = getBasketTotalUpfront().equals(totalUpfront);
        boolean isMonthlyRateEqual = getBasketMonthlyRate().equals(monthlyRate);
        return isTotalUpfrontEqual && isMonthlyRateEqual;
    }
    public void verifyBasketContent() {
        Wait().until(webDriver -> url().equals("https://www.t-mobile.pl/sklep/basket"));
        Wait().until(webDriver -> executeJavaScript("return document.readyState").equals("complete"));
        boolean isDisplayed = $(".basketContent").isDisplayed();
        assertTrue(isDisplayed, "Basket content should be displayed");
    }
    public void clickBackButton(){
        backButton.click();
    }
}
