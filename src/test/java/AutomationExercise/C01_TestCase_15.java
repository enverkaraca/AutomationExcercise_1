package AutomationExercise;

import com.github.javafaker.Faker;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.TestBase;


import java.time.Duration;

public class C01_TestCase_15 extends TestBase {
    @Test
    public void test15() throws InterruptedException {
        //Test Case 15: Place Order: Register before Checkout
        //1. Launch browser
        //2. Navigate to url 'http://automationexercise.com'
        //3. Verify that home page is visible successfully
        Assert.assertTrue(driver.findElement(By.xpath("//img[@src='/static/images/home/logo.png']")).isDisplayed());
        //4. Click 'Signup / Login' button
        driver.findElement(By.xpath("//*[@href='/login']")).click();
        //5. Fill all details in Signup and create account
        Faker faker = new Faker();
        WebElement nameElement = driver.findElement(By.xpath("//input[@placeholder='Name']"));
        nameElement.sendKeys(faker.name().firstName());

        WebElement lastNameElement = driver.findElement(By.xpath("(//input[@placeholder='Email Address'])[2]"));
       lastNameElement.sendKeys(faker.internet().emailAddress());

        WebElement signUpElement = driver.findElement(By.xpath("//button[@data-qa='signup-button']"));
        signUpElement.click();

        WebElement passWordElement = driver.findElement(By.id("password"));
        passWordElement.sendKeys("123yyyyaaaa"+ Keys.PAGE_DOWN);

        WebElement daysForBirthElement = driver.findElement(By.id("days"));
        Select selectDay = new Select(daysForBirthElement);
        selectDay.selectByValue("4");

        WebElement monthsForBirthElement = driver.findElement(By.id("months"));
        Select selectMonth = new Select(monthsForBirthElement);
        selectMonth.selectByValue("1");

        WebElement yearsForBirthelement = driver.findElement(By.id("years"));
        Select selectYear = new Select(yearsForBirthelement);
        selectYear.selectByValue("1999");

        WebElement addElement = driver.findElement(By.xpath("//div[@class='grippy-host']"));
        addElement.click();

        driver.findElement(By.id("newsletter")).click();

        WebElement firstNameElement = driver.findElement(By.xpath("//input[@id='first_name']"));
        firstNameElement.sendKeys("Murat Metin");

        WebElement lastNameElementElement = driver.findElement(By.xpath("//input[@id='last_name']"));
        lastNameElementElement.sendKeys("Akkus");

        WebElement companyElement = driver.findElement(By.xpath("//input[@id='company']"));
        companyElement.sendKeys("WiseQuarter");

        WebElement adressElement = driver.findElement(By.id("address1"));
        String adress = "Wageningselan 2 Veneendaal 3093LA Utrecht The Nederlands";
        adressElement.sendKeys(adress);

        WebElement adress2Element = driver.findElement(By.id("address2"));
        String adress2 = "Turkey Izmir";
        adress2Element.sendKeys(adress2+Keys.PAGE_DOWN);

        WebElement countryElement = driver.findElement(By.id("country"));
        Select selectCountry = new Select(countryElement);
        selectCountry.selectByValue("Canada");

        Thread.sleep(3000);

        WebElement stateElement = driver.findElement(By.xpath("//input[@data-qa='state']"));
        stateElement.sendKeys("North America");

        WebElement cityElement = driver.findElement(By.xpath("//input[@id='city']"));
        cityElement.sendKeys("Toronto");

        WebElement zipcodeElement = driver.findElement(By.xpath("//input[@id='zipcode']"));
        zipcodeElement.sendKeys("3903LA");

        WebElement mobileNumberElement = driver.findElement(By.xpath("//input[@id='mobile_number']"));
        mobileNumberElement.sendKeys("0684167143");

        driver.findElement(By.xpath("//*[@data-qa='create-account']")).click();

        //6. Verify 'ACCOUNT CREATED!' and click 'Continue' button
        Assert.assertTrue(driver.findElement(By.xpath("//h2[@data-qa='account-created']")).isDisplayed());
        driver.findElement(By.xpath("//a[@data-qa='continue-button']")).click();

        //7. Verify ' Logged in as username' at top
        Assert.assertTrue(driver.findElement(By.xpath("//i[@class='fa fa-user']")).isDisplayed());

        //8. Add products to cart
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.xpath("(//*[@class='btn btn-default add-to-cart'])[7]"))).perform();
        actions = new Actions(driver);
        actions.click(driver.findElement(By.xpath("(//*[text()='Add to cart'])[1]"))).perform();
        driver.findElement(By.xpath("//*[text()='Continue Shopping']")).click();

        //9. Click 'Cart' button
        actions = new Actions(driver);
        actions.sendKeys(Keys.PAGE_UP).perform();
        driver.findElement(By.xpath("//*[text()=' Cart']")).click();

        //10. Verify that cart page is displayed
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[@class='product_image']"))));
        Assert.assertTrue(driver.findElement(By.xpath("//*[@class='product_image']")).isDisplayed());

        //11. Click Proceed To Checkout
        driver.findElement(By.xpath("//*[@class='btn btn-default check_out']")).click();

        //12. Verify Address Details and Review Your Order

        //13. Enter description in comment text area and click 'Place Order'
        driver.findElement(By.xpath("//*[@name='message']")).sendKeys(faker.address().fullAddress());
        actions = new Actions(driver);
        actions.scrollToElement(driver.findElement(By.xpath("//*[text()='Place Order']"))).perform();
        driver.findElement(By.xpath("//*[text()='Place Order']")).click();

        //14. Enter payment details: Name on Card, Card Number, CVC, Expiration date

        driver.findElement(By.xpath("//*[@name='name_on_card']")).sendKeys(faker.name().fullName());

       driver.findElement(By.xpath("//*[@name='card_number']")).sendKeys(faker.business().creditCardNumber());
       driver.findElement(By.xpath("//*[@name='cvc']")).sendKeys(faker.numerify("123"));
       String expirationCard = faker.business().creditCardExpiry();
       String[] cardExp = expirationCard.split("-");
       driver.findElement(By.xpath("//*[@name='expiry_month']")).sendKeys(cardExp[1]);
       driver.findElement(By.xpath("//*[@name='expiry_year']")).sendKeys(cardExp[0]);

        //15. Click 'Pay and Confirm Order' button
        driver.findElement(By.xpath("//*[@data-qa='pay-button']")).click();

        //16. Verify success message 'Your order has been placed successfully!'
        Assert.assertTrue(driver.findElement(By.xpath("//*[text()='Congratulations! Your order has been confirmed!']")).isDisplayed());

        //17. Click 'Delete Account' button
        driver.findElement(By.xpath("//*[text()=' Delete Account']")).click();

        //18. Verify 'ACCOUNT DELETED!' and click 'Continue' button
        Assert.assertTrue(driver.findElement(By.xpath("//*[text()='Account Deleted!']")).isDisplayed());
        driver.findElement(By.xpath("//*[text()='Continue']")).click();

    }
}
