package org.example.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static com.codeborne.selenide.Selenide.*;

public class BasePage {
    private static final Logger logger = LoggerFactory.getLogger(BasePage.class);

    public void waitForNetworkIdle(long maxWaitTimeMillis) {
        long startTime = System.currentTimeMillis();
        boolean networkIsIdle = false;

        while (!networkIsIdle) {
            networkIsIdle = (Boolean) executeJavaScript(
                    "return window.performance.getEntriesByType('resource')" +
                            ".filter(entry => entry.initiatorType === 'xmlhttprequest' || entry.initiatorType === 'fetch')" +
                            ".length === 0;"
            );

            if (System.currentTimeMillis() - startTime > maxWaitTimeMillis) {
                logger.info("Stopped waiting for network idle after " + maxWaitTimeMillis + " milliseconds.");
                break;
            }

            if (!networkIsIdle) {
                sleep(100); // Wait for 100 milliseconds before checking again
            }
        }
    }
}
