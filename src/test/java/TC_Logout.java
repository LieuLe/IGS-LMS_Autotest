import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class TC_Logout {
    private WebDriver driver;
    private By profileAccount = By.id("account");
    private By profileMenu_Account = By.className("profile-modal");
    private By logoutText = By.xpath("//*[contains(text(), ' LOG OUT ')]");
    private By popUp_LogOut = By.className("change-password-modal");
    private By confirmLogoutText = By.xpath("//*[contains(text(), 'YES, LOG OUT NOW')]");
    private By welcomeText = By.xpath("//*[contains(text(), 'Welcome to LMS')]");

    public TC_Logout(WebDriver driver) {
        this.driver = driver;
    }

    public void logout() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOfElementLocated(profileAccount));

        // Click vào profileAccount
        WebElement profileAccountElement = driver.findElement(profileAccount);
        profileAccountElement.click();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement profileMenuAccountElement = driver.findElement(profileMenu_Account);
        if (profileMenuAccountElement.isDisplayed()) {
            WebElement logoutTextElement = driver.findElement(logoutText);
            logoutTextElement.click();

            wait.until(ExpectedConditions.visibilityOfElementLocated(popUp_LogOut));
            WebElement popUpLogOutElement = driver.findElement(popUp_LogOut);
            if (popUpLogOutElement.isDisplayed()) {
                WebElement confirmLogoutTextElement = driver.findElement(confirmLogoutText);
                confirmLogoutTextElement.click();

                wait.until(ExpectedConditions.visibilityOfElementLocated(welcomeText));
                WebElement welcomeTextElement = driver.findElement(welcomeText);
                if (welcomeTextElement.isDisplayed()) {
                    System.out.println("Logout thành công!");
                } else {
                    System.out.println("Logout thất bại tại bước Welcome to LMS!");
                }
            } else {
                System.out.println("Logout thất bại tại bước PopUp_LogOut!");
            }
        } else {
            System.out.println("Logout thất bại!");
        }
    }
}
