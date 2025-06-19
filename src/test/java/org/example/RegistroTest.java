package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class RegistroTest {

    private WebDriver driver;
    private String chromeDriverPath = "/Users/martinlequerica/Documents/webdriver/chromedriver-mac-arm64/chromedriver";

    @BeforeEach
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
        driver = new ChromeDriver();
    }

    @AfterEach
    public void destroy(){
        if (driver != null){
            driver.quit();
        }
    }



    @Test
    public void testFormWork() {
        FormA usuario = new FormA("barker","nikolasa","mid","None");

        driver.get("http://localhost:4567/registro.html");
        WebElement firstNameInput = driver.findElement(By.xpath("(//input[@id='nombre'])[1]"));
        WebElement email = driver.findElement(By.xpath("(//input[@id='email'])[1]"));
        WebElement telefono = driver.findElement(By.xpath("(//input[@id='telefono'])[1]"));

        firstNameInput.sendKeys(usuario.getFirstName());
        email.sendKeys(usuario.getFirstName());
        telefono.sendKeys(usuario.getFirstName());
    }


    @Test
    public void testCrearEquipo() throws InterruptedException {

        String nombreEquipo = "sarasa";
        driver.get("http://localhost:4567/crearEquipo.html");
        WebElement firstNameInput = driver.findElement(By.xpath("(//input[@id='nombre_equipo'])[1]"));
        firstNameInput.sendKeys(nombreEquipo);

        WebElement boton = driver.findElement(By.xpath("(//button[normalize-space()='Guardar Nombre'])[1]"));
        boton.click();
        Thread.sleep(2000);
    }
}
