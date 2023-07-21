package AutomationExercise;

import com.github.javafaker.Faker;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import utilities.TestBase;

public class C02_TestCase16 extends TestBase {
    @Test
    public void test01(){
        //1. Launch browser
        //2. Navigate to url 'http://automationexercise.com'
        //3. Verify that home page is visible successfully
        Assert.assertTrue(driver.findElement(By.xpath("//img[@src='/static/images/home/logo.png']")).isDisplayed());
        //4. Click 'Signup / Login' button
        driver.findElement(By.xpath("//*[@href='/login']")).click();
        //5. Fill email, password and click 'Login' button
        //String exampleUser = "example"; i regist
        String exampleMail = "example122@gmail.com";
        String examplePassWord = "123";
        driver.findElement(By.xpath("(//*[@name='email'])[1]")).sendKeys(exampleMail);
        driver.findElement(By.xpath("//*[@name='password']")).sendKeys(examplePassWord);
        driver.findElement(By.xpath("//*[@data-qa='login-button']")).click();
        //6. Verify 'Logged in as username' at top
        Assert.assertTrue(driver.findElement(By.xpath("//*[text()=' Logged in as ']")).isDisplayed());
        //7. Add products to cart
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.xpath("(//*[@class='btn btn-default add-to-cart'])[7]"))).perform();
        actions = new Actions(driver);
        actions.click(driver.findElement(By.xpath("(//*[text()='Add to cart'])[1]"))).perform();
        driver.findElement(By.xpath("//*[text()='Continue Shopping']")).click();

        //8. Click 'Cart' button
        actions = new Actions(driver);
        actions.sendKeys(Keys.PAGE_UP).perform();
        driver.findElement(By.xpath("//*[text()=' Cart']")).click();

        //9. Verify that cart page is displayed
        Assert.assertTrue(driver.findElement(By.xpath("//*[@class='product_image']")).isDisplayed());

        //10. Click Proceed To Checkout
        driver.findElement(By.xpath("//*[@class='btn btn-default check_out']")).click();

        //11. Verify Address Details and Review Your Order

        //12. Enter description in comment text area and click 'Place Order'
        Faker faker = new Faker();
        driver.findElement(By.xpath("//*[@name='message']")).sendKeys(faker.address().fullAddress());
        actions = new Actions(driver);
        actions.scrollToElement(driver.findElement(By.xpath("//*[text()='Place Order']"))).perform();
        driver.findElement(By.xpath("//*[text()='Place Order']")).click();
        //13. Enter payment details: Name on Card, Card Number, CVC, Expiration date
        driver.findElement(By.xpath("//*[@name='name_on_card']")).sendKeys(faker.name().fullName());
        driver.findElement(By.xpath("//*[@name='card_number']")).sendKeys(faker.business().creditCardNumber());
        driver.findElement(By.xpath("//*[@name='cvc']")).sendKeys(faker.numerify("123"));
        String expirationCard = faker.business().creditCardExpiry();
        String[] cardExp = expirationCard.split("-");
        driver.findElement(By.xpath("//*[@name='expiry_month']")).sendKeys(cardExp[1]);
        driver.findElement(By.xpath("//*[@name='expiry_year']")).sendKeys(cardExp[0]);

        //14. Click 'Pay and Confirm Order' button
        driver.findElement(By.xpath("//*[@data-qa='pay-button']")).click();

        //15. Verify success message 'Your order has been placed successfully!'
        Assert.assertTrue(driver.findElement(By.xpath("//*[text()='Congratulations! Your order has been confirmed!']")).isDisplayed());

        //16. Click 'Delete Account' button
        driver.findElement(By.xpath("//*[text()=' Delete Account']")).click();

        //17. Verify 'ACCOUNT DELETED!' and click 'Continue' button
        Assert.assertTrue(driver.findElement(By.xpath("//*[text()='Account Deleted!']")).isDisplayed());
        driver.findElement(By.xpath("//*[text()='Continue']")).click();

    }

}
