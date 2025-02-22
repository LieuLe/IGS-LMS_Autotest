import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class TC_Login {
    private WebDriver driver;
    private By txt_username = By.name("username");
    private By txt_password = By.name("password");
    private By lb_Noti01 = By.xpath("//*[@id=\"signin\"]/div/div/div[2]");
    private By btn_login = By.xpath("//*[@id=\"create_form\"]/button");

    public TC_Login(WebDriver driver) {
        this.driver = driver;
    }

    public void login(String username, String password) {
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("http://igs-web-test.edunext.edu.vn/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOfElementLocated(txt_username));
        wait.until(ExpectedConditions.visibilityOfElementLocated(txt_password));
        WebElement usernameField = driver.findElement(txt_username);
        WebElement passwordField = driver.findElement(txt_password);

        // Enter Username
        usernameField.sendKeys(username);
        // Enter Password
        passwordField.sendKeys(password);
        // Click login button
        WebElement loginButton = driver.findElement(btn_login);
        loginButton.click();

        // Check if login is successful
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Find the warning button
        WebElement warningButton = driver.findElement(By.className("warning-modal"));

        // Check if the warning button is displayed
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
    }

    public void performLogin(String username, String password) {
        this.login(username, password);
    }
}
