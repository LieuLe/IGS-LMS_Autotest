import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        WebDriver driver;

        String excelFilePath = "D:\\Selenium Java\\IGS-LMS_Autotest\\src\\test\\data_login.xlsx";
        ExcelReader excelReader = new ExcelReader(excelFilePath);

        try {
            List<String[]> loginData = excelReader.readData();

            for (String[] credentials : loginData) {
                String username = credentials[0];
                String password = credentials[1];

                driver = new ChromeDriver();

                try {
                    TC_Login loginTest = new TC_Login(driver);
                    loginTest.login(username, password);

                    TC_Logout logoutTest = new TC_Logout(driver);
                    logoutTest.logout();

                } finally {
                    driver.quit();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
