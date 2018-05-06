import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class TestClass extends BaseClass {
    @FindBy(xpath = ".//span[@class=\"region-list__name\"]")
    private WebElement regionList;

    @FindBy(xpath = "//input[@placeholder='Введите название региона']")
    private WebElement inputField;

    @FindBy(xpath = ".//span[@class=\"region-search-box__option\"]")
    private WebElement ourChoice;

    @FindBy(xpath = ".//div[@class=\"sbrf-div-list-inner --area bp-area footer-white\"]")
    private WebElement footer;

    @FindBy(xpath = ".//ul[@class=\"social__list\"]")
    private WebElement netWorks;

    @Before
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        dr = new ChromeDriver();
        dr.manage().window().maximize();    //полный экран
        dr.get("http://www.sberbank.ru/ru/person");      //заходим на сайт
    }

    @After
    public void tearDown(){dr.quit();}

    @Test
    public void test(){
        PageFactory.initElements(dr,this);

        regionList.click();
        inputField.sendKeys("Нижегородская");
        ourChoice.click();

        waitVisibilityOf(regionList,30);
        //проверяем, что установлена нужная область
        Assert.assertEquals("Верная область","Нижегородская область", regionList.getText());

        //проматываем вниз и проверяем наличие соцсетей
        ((JavascriptExecutor)dr).executeScript("arguments[0].scrollIntoView();",footer);
        if(!netWorks.isEnabled()){
            System.err.print("Соц. сети недоступны!");
        }

    }
}
