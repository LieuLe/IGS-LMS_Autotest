import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.WebElement;
import java.time.Duration;

public class Web_Login {
    public static void main(String[] args) {
        By txt_username = By.name("username");
        By txt_password = By.name("password");
        By lb_Noti01 = By.xpath("//*[@id=\"signin\"]/div/div/div[2]");
        By btn_login = By.xpath("//*[@id=\"create_form\"]/button");
        WebDriver driver;
        driver = new ChromeDriver();

        try {
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.get("http://igs-web-test.edunext.edu.vn/");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
            wait.until(ExpectedConditions.visibilityOfElementLocated(txt_username));
            wait.until(ExpectedConditions.visibilityOfElementLocated(txt_password));
            WebElement usernameField = driver.findElement(txt_username);
            WebElement passwordField = driver.findElement(txt_password);

            //Enter Username
            usernameField.sendKeys("auto.test01");
            //Enter Password
            passwordField.sendKeys("12345678");
            //Click login butoon
            WebElement loginButton = driver.findElement(btn_login);
            loginButton.click();
            /*

            // If no error message, check if login is successful
            WebElement warningButton = driver.findElement(By.xpath("//*[@id=\"mainContent\"]/div/div/div[2]/div/div/div/div[2]/button"));
            if (warningButton.isDisplayed()) {
                System.out.println("Login successful!");
            } else {
                System.out.println("Login failed!");
                }

             */
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Find the warning button
            WebElement warningButton = driver.findElement(By.className("warning-modal"));

            // Check if the warning btn
            if (warningButton.isDisplayed()) {

                // Click Pop-up Warning
                WebElement closeModalButton = driver.findElement(By.className("close-modal"));


                if (closeModalButton.isDisplayed()) {

                    closeModalButton.click();


                    System.out.println("Login successful!");
                }
            } else {

                System.out.println("Login failed!");
            }
        } catch (Exception e) {
           System.out.println("Error occurred during the login test.");
        } finally {
            //Close the browser
            driver.close();
            driver.quit();
        }
    }
}
