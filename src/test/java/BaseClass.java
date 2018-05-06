import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseClass {
    WebDriver dr;

    public void waitVisibilityOf(WebElement loc, int timeout){
        WebDriverWait wait = new WebDriverWait(dr, timeout);
        wait.until(ExpectedConditions.visibilityOf(loc));
    }
}
