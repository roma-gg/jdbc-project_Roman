package cydeo.utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class Driver {
    private static InheritableThreadLocal<WebDriver> driverPool = new InheritableThreadLocal<>();
    private Driver(){};

    public static WebDriver getDriver() {
        if (driverPool.get() == null) {
            String browserType = ConfigurationReader.getProperty("browser");
            if (browserType.equalsIgnoreCase("chrome")) {
                var options = new ChromeOptions();
                //to get rid of ads by using uBlock
                var extensionPath = "C:\\Users\\Roman\\AppData\\Local\\Google\\Chrome\\User Data\\Default\\Extensions\\cjpalhdlnbpafiamejdnhcphjbkeiagm\\1.54.0_0";
                options.addArguments("load-extension=" + extensionPath);
                driverPool.set(new ChromeDriver(options));
            }
            else if (browserType.equalsIgnoreCase("firefox"))
                driverPool.set(new FirefoxDriver());
            else if (browserType.equalsIgnoreCase("edge"))
                driverPool.set(new EdgeDriver());
            else if (browserType.equalsIgnoreCase("headless-chrome")) {
                var options = new ChromeOptions();
                options.addArguments("--headless=new");
                driverPool.set(new ChromeDriver(options));
            }
            else
                throw new IllegalArgumentException();

            driverPool.get().manage().window().maximize();
            driverPool.get().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        }

        return driverPool.get();
    }

    public static void closeDriver() {
        if (driverPool.get() != null) {
            driverPool.get().quit();
            driverPool.remove();
        }
    }
}
