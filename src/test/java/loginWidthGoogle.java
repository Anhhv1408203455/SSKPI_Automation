import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Set;

public class loginWidthGoogle {
    String urlLogin = "https://stg.shaboten-log.jp/shaboten/teacher/login";
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new EdgeDriver();
        driver.get(urlLogin);
    }
    @Test
    @Step
    public void loginEmtyEmail_01() throws Exception {
        Thread.sleep(3000);
        WebElement loginButton = driver.findElement(By.xpath("//*[@id=\"__layout\"]/div/div/div/div/div/div[2]"));
        loginButton.click();
        String mainWindowHandle = driver.getWindowHandle();

        Set<String> windowHandles = driver.getWindowHandles();
        for (String windowHandle : windowHandles) {
            if (!windowHandle.equals(mainWindowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
        WebElement Email = driver.findElement(By.id("identifierId"));
        Email.sendKeys("anhhv@sskpi.com");
        WebElement btnNext = driver.findElement(By.xpath("//*[@id=\"identifierNext\"]/div/button/span"));
        btnNext.click();
        Thread.sleep(5000);
        WebElement Password = driver.findElement(By.cssSelector("input[name='Passwd']"));
        Password.sendKeys("Hoanganh!123");
        WebElement btnNextPassword = driver.findElement(By.xpath("//*[@id=\"passwordNext\"]/div/button/span"));
        btnNextPassword.click();
        driver.switchTo().window(mainWindowHandle);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        String welcomeMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[contains(text(),'全体ダッシュボード')]"))).getText();
        String expect_title_login_success = "全体ダッシュボード";
        Assert.assertEquals(welcomeMessage,expect_title_login_success,"fail rồi");
    }
    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

}
