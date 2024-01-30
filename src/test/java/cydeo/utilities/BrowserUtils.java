package cydeo.utilities;

import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class BrowserUtils {
    public static void sleep(int seconds) {
        seconds *= 1000;
        try {
            Thread.sleep(seconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void dismissAlertIfPresent() {
        try {
            Driver.getDriver().switchTo().alert().dismiss();
        } catch (NoAlertPresentException e) {}
    }

    public static void moveTo(WebElement webElement) {
        var actions = new Actions(Driver.getDriver());
        actions.moveToElement(webElement);
    }
}
