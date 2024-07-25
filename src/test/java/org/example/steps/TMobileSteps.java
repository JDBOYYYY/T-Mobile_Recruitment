package org.example.steps;

import com.codeborne.selenide.Configuration;
import io.cucumber.java.en.*;
import org.example.pages.HomePage;
import org.example.pages.DevicesPage;
import org.example.pages.ProductPage;
import org.example.pages.BasketPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TMobileSteps {
    private static final Logger logger = LoggerFactory.getLogger(TMobileSteps.class);

    HomePage homePage = new HomePage();
    DevicesPage devicesPage = new DevicesPage();
    ProductPage productPage = new ProductPage();
    BasketPage basketPage = new BasketPage();

    private String totalUpfront;
    private String monthlyRate;

    @Given("Otwórz odpowiednią przeglądarkę")
    public void otworzPrzegladarke() {
        logger.info("Otwieranie przeglądarki");
        System.setProperty("selenide.browser", "Chrome");
        Configuration.browserSize = "1920x1080";
        Configuration.headless =true;
    }

    @When("Przejdź na stronę {string}")
    public void przejdzNaStrone(String url) {
        homePage.openHomePage(url);
        logger.info("Opened URL: {}", url);
        assertTrue(homePage.isMainPageDisplayed(), "Main page should be displayed");
        homePage.acceptCookies();
    }

    @And("Z górnej belki wybierz Urządzenia")
    public void wybierzUrzadzenia() {
        homePage.hoverOverDevices();
        assertTrue(homePage.isDropDownMenuDisplayed(), "Devices dropdown should be displayed");
    }

    @And("Kliknij {string} z kolumny {string}")
    public void kliknijOpcjaZKolumny(String option, String column) {
        homePage.clickOptionFromColumn(option, column);
        logger.info("Clicked option: {} from column: {}", option, column);
        assertTrue(devicesPage.isDeviceListDisplayed(), "Devices list should be displayed");
    }

    @And("Kliknij w pierwszy element z listy")
    public void kliknijPierwszyElement() {
        devicesPage.clickFirstElement();
        productPage.waitForNetworkIdle(2000);
        assertTrue(productPage.isDeviceHeaderDisplayed(), "Device header should be displayed");

        totalUpfront = productPage.getTotalUpfront();
        monthlyRate = productPage.getMonthlyRate();
        logger.info("Captured prices: Total Upfront = {}, Monthly Rate = {}", totalUpfront, monthlyRate);
    }

    @And("Dodaj produkt do koszyka")
    public void dodajProduktDoKoszyka(){
        productPage.addToBasket();
        basketPage.waitForNetworkIdle(2000);
        basketPage.verifyBasketContent();
        String basketTotalUpfront = basketPage.getBasketTotalUpfront();
        String basketMonthlyRate = basketPage.getBasketMonthlyRate();
        logger.info("Basket prices: Total Upfront = {}, Monthly Rate = {}", basketTotalUpfront, basketMonthlyRate);

        boolean pricesMatch = basketPage.comparePrices(totalUpfront, monthlyRate);
        assertTrue(pricesMatch, String.format("Prices should match between product page and basket page. Expected Total Upfront: %s, Basket Total Upfront: %s, Expected Monthly Rate: %s, Basket Monthly Rate: %s",
                totalUpfront, basketTotalUpfront, monthlyRate, basketMonthlyRate));
    }

    @Then("Przejdź na stronę główną T-Mobile i sprawdz ze w koszyku jest {int} element")
    public void przejdźNaStronęGłównąTMobileISprawdzZeWKoszykuJestElement(int elementNumber) {
        basketPage.clickBackButton();
        int actualItemCount = homePage.getBasketItemCount();
        logger.info("Expected item count: {}, Actual item count: {}", elementNumber, actualItemCount);
        assertEquals(elementNumber, actualItemCount, "Basket item count should match expected count");
    }
}
