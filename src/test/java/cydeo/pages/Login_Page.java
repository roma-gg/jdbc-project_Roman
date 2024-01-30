package cydeo.pages;

import cydeo.utilities.ConfigurationReader;
import cydeo.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Login_Page {

    public Login_Page() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(id = "inputEmail")
    private WebElement emailInput;

    @FindBy(id = "inputPassword")
    private WebElement passwordInput;

    @FindBy(tagName = "button")
    private WebElement submitButton;

    public void login (String userEmail, String userPassword) {
        Driver.getDriver().get(ConfigurationReader.getProperty("libraryURL"));
        emailInput.sendKeys(userEmail);
        passwordInput.sendKeys(userPassword);
        submitButton.click();
    }

}
