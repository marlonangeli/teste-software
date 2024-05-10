package br.com.aula.leilao;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginTest {

    private String URL = "http://localhost:8080/";
    WebDriver driver;

    @BeforeEach
    void setup() {
        System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
        driver = new ChromeDriver();
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }

    @Test
    void loginWithSuccess() throws InterruptedException {
        // Act
        driver.navigate().to( URL +"login");

        driver.findElement(By.ByXPath.xpath("//input[@name='username']")).sendKeys("fulano");
        driver.findElement(By.ByXPath.xpath("//input[@name='password']")).sendKeys("pass");
        driver.findElement(By.ByXPath.xpath("//button")).click();

        // Assert
        Assert.assertTrue(driver.getCurrentUrl().equals(URL + "leiloes"));
        Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[1]/span/span")).getText().equals("fulano"));

        Thread.sleep(2000);
    }

    @Test
    void shouldBeInvalid() throws InterruptedException {
        // Act
        driver.navigate().to("http://localhost:8080/login");

        driver.findElement(By.ByXPath.xpath("//input[@name='username']")).sendKeys("invalid");
        driver.findElement(By.ByXPath.xpath("//input[@name='password']")).sendKeys("invalid");
        driver.findElement(By.ByXPath.xpath("//button")).click();

        // Assert
        Assert.assertTrue(driver.getCurrentUrl().equals(URL + "login?error"));
        Assert.assertTrue(driver.getPageSource().contains("Usuário e senha inválidos."));

        Thread.sleep(2000);
    }

}
