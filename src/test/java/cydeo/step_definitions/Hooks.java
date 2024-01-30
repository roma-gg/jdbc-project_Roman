package cydeo.step_definitions;

import cydeo.utilities.Driver;
import io.cucumber.java.After;

public class Hooks {

    @After
    public void logout() {
        Driver.closeDriver();
    }

}
