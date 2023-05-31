import io.qameta.allure.Step;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class LoginEmailPassword {
    String urlLogin = "https://stg.shaboten-log.jp/guild/teacher/login";
    String urlLoginSussces = "https://stg.shaboten-log.jp/guild/teacher/all_dashboard";
    WebDriver driver;

    public void login(String email, String password) throws Exception {
        Thread.sleep(3000);
        driver.findElement(By.cssSelector("[type='text'][placeholder='メールアドレス']")).sendKeys(email);
        Thread.sleep(3000);
        driver.findElement(By.cssSelector("[type='password'][placeholder='パスワード']")).sendKeys(password);
        Thread.sleep(3000);
        driver.findElement(By.cssSelector("[href='#'")).click();
        Thread.sleep(5000);
    }

    @BeforeMethod
    public void setUp() {
        driver = new EdgeDriver();
        driver.get(urlLogin);
    }

    @Test
    @Step
    public void loginEmtyEmail_01() throws Exception {
        login("", "");
        String error_message_email = driver.findElement(By.xpath("//*[@id=\"__layout\"]/div/div/div/div/div/div/div[1]/p")).getText();
        String expected_error_message = "メールアドレスを入力してください";
        Assert.assertEquals(error_message_email, expected_error_message, "Thất bại");
        //.assertEquals(error_message_email, expected_error_message,"thanhf coong");
    }

    @Test
    @Step
    public void loginEmtypassword_02() throws Exception {
        login("", "");
        String error_message_password = driver.findElement(By.xpath("//*[@id=\"__layout\"]/div/div/div/div/div/div/div[2]/p")).getText();
        String expected_error_message_password = "パスワードを入力してください";
        Assert.assertEquals(error_message_password, expected_error_message_password, "Thất bại");
    }

    @Test
    @Step
    public void loginUnformat_03() throws Exception {
        login("anhgmail.com", "abcdf");
        String error_message_email_invalid_format = driver.findElement(By.xpath("//*[@id=\"__layout\"]/div/div/div/div/div/div/div[1]/p")).getText();
        String expected_error_message_email_invalid_format = "正しい形式で入力してください";
        Assert.assertEquals(error_message_email_invalid_format, expected_error_message_email_invalid_format, "Thất bại");
    }

    @Test
    @Step
    public void loginInvalidPasswrod_04() throws Exception {
        login("teacher1@email.com", "abc");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        if (alert != null) {
            Alert alertError = driver.switchTo().alert();
            String actual_mess_invalid_passwod = alertError.getText();
//            System.out.println("Alert text: " + alertText);
            String Expect_mess_invalid_password = "メールアドレス\n" +
                    "もしくはパスワードが正しくありません";
            Assert.assertEquals(actual_mess_invalid_passwod, Expect_mess_invalid_password, "Lỗi rồi");
            alertError.dismiss();
        } else {
            System.out.println("No alert is present.");
        }


    }

    @Test
    @Step
    public void loginSucsess_05() throws Exception {
        login("teacher1@email.com", "abcdef");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        String loginSuccses = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[contains(text(),'1年1組のダッシュボード')]"))).getText();
        String expect_title_login_success = "1年1組のダッシュボード";
        Assert.assertEquals(loginSuccses, expect_title_login_success, "Lỗi rồi");

    }


    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}

