import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

public class Object {
    public static void main(String[] args) {

        EdgeOptions edgeOptions = new EdgeOptions();
        System.setProperty("webdriver.edge.driver", "C:\\Test_Auto_VA\\src\\main\\msedgedriver.exe");
        WebDriver driver = new EdgeDriver(edgeOptions);
        driver.get("https://stg.shaboten-log.jp/guild/teacher/login#");


        WebElement title = driver.findElement(By.xpath("//*[@id=\"__layout\"]/div/div/div/div/div/div/h2"));
        String text_title_exxpect = "ギルド小学校\n" + "先生ログイン";
        if (title.getText().equals(text_title_exxpect)){
            System.out.println("TC 01 - Pass - Show title exactly ");
        }
       String color_titile =  title.getCssValue("color");
        // Tách giá trị màu R, G, B từ giá trị RGB
        String[] rgb_title = color_titile.replace("rgba(", "").replace("rgb(", "").replace(")", "").split(",");

        int r_title = Integer.parseInt(rgb_title[0].trim());
        int g_title = Integer.parseInt(rgb_title[1].trim());
        int b_title = Integer.parseInt(rgb_title[2].trim());
        String hexColor_title_actual = String.format("#%02X%02X%02X", r_title, g_title, b_title);

        String hexColor_title_expect = "#39AED9";
        if(hexColor_title_actual.equals(hexColor_title_expect)){
            System.out.println("TC 02 - Pass - Color title exactly");
        }else{
            System.out.println("TC 02 - Fail - Color title exactly");
        }
        WebElement Email = driver.findElement(By.cssSelector("[type='text'][placeholder='メールアドレス']"));
        WebElement Password = driver.findElement(By.cssSelector("[type='password'][placeholder='パスワード']"));
        WebElement btnLogin = driver.findElement(By.cssSelector("[href='#'"));

        // TC - 03 - Check email  bank
        if (Email.getAttribute("value").equals("")) {
            System.out.println("TC 03 - Pass - Email blank");
        } else {
            System.out.println("TC 03 - Fail - Email not  blank");
        }

        // TC - 04 - Check password  bank
        if (Password.getAttribute("value").equals("")) {
            System.out.println("TC 04 - Pass - Password blank");
        } else {
            System.out.println("TC 04 - Pass - Password blank");
        }


        // TC - 05 -



    }
}
