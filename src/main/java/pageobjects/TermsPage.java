package pageobjects;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TermsPage {
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(xpath = "//strong[contains(text(),'Lending Partners of Scapic Innovations Private Lim')]")
    private WebElement termsHeader;

    public TermsPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }

    public boolean isTermsHeaderDisplayed() {
        return wait.until(ExpectedConditions.visibilityOf(termsHeader)).isDisplayed();
    }
}
