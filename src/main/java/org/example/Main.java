package org.example;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        new Main();
    }

    public Main(){

        FormA usuario1 = new FormA("Liam","Molina", "Dev Sr","0-1");
        FormA usuario2 = new FormA("juan","mrtn", "Dev msr","2-4");
        FormA usuario3 = new FormA("juan1","mrtn1", "Dev mmsr","5-9");
        FormA usuario4 = new FormA("juan12","mrtn12", "Dev mmmsr","10+");

        List<FormA> usuarios = new ArrayList<>();
        usuarios.add(usuario1);
        usuarios.add(usuario2);
        usuarios.add(usuario3);
        usuarios.add(usuario4);


        for(FormA usuario: usuarios){
            System.out.println("nombre: "+usuario.getFirstName());
            completeWebForm(usuario);
            keyboardAndMouse(usuario);
        }

    }

    public void keyboardAndMouse(FormA usuario){

        System.setProperty("webdriver.chrome.driver", "/Users/martinlequerica/Documents/webdriver/chromedriver-mac-arm64/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.get("https://formy-project.herokuapp.com/keypress?");

        String currentUrl = driver.getCurrentUrl();
        System.out.println("URL actual: " + currentUrl);

        //WebElement nameInput = driver.findElement(By.xpath("(//input[@id='name'])[1]"));
        //nameInput.sendKeys(usuario.getFirstName());
        rellenarCampo(driver, usuario.getFirstName(), "(//input[@id='name'])[1]");
        driver.quit();
    }

    public void rellenarCampo(WebDriver driver, String valorCampo, String xpath) {
        WebElement nameInput = driver.findElement(By.xpath(xpath));
        nameInput.sendKeys(valorCampo);
    }

    public void completeWebForm(FormA usuario){


        // El absolute path de donde tienen descargado el webdriver
        // Pagina para bajar webdriver: https://googlechromelabs.github.io/chrome-for-testing/
        System.setProperty("webdriver.chrome.driver", "/Users/martinlequerica/Documents/webdriver/chromedriver-mac-arm64/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.get("https://formy-project.herokuapp.com/form");

        String currentUrl = driver.getCurrentUrl();
        System.out.println("URL actual: " + currentUrl);

        //opcion de wait
        //WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Completar el formulario
        WebElement firstNameInput = driver.findElement(By.xpath("(//input[@id='first-name'])[1]"));
        firstNameInput.sendKeys(usuario.getFirstName());

        WebElement lastNameInput = driver.findElement(By.xpath("(//input[@id='last-name'])[1]"));
        lastNameInput.sendKeys(usuario.getLastName());

        WebElement jobTitle = driver.findElement(By.xpath("(//input[@id='job-title'])[1]"));
        jobTitle.sendKeys(usuario.getJobTitle());

        WebElement radioButton = driver.findElement(By.xpath("(//input[@id='radio-button-2'])[1]"));
        radioButton.click();

        WebElement dropdown = driver.findElement(By.xpath("(//select[@id='select-menu'])[1]"));
        Select select = new Select(dropdown);
        select.selectByVisibleText(usuario.getYearsOfExp());

        // Enviar el formulario
        //WebElement submitButton = driver.findElement(By.xpath("//input[@type='submit']"));
        //submitButton.click();

        //Validamos el resultado manualmente por ahora.
        System.out.println("Página luego de enviar: " + driver.getTitle());

        // Si lo cerras es inmediato, no podes ver los resultados
        driver.quit();
    }


    public void function(){

        System.setProperty("webdriver.chrome.driver", "/Users/martinlequerica/Documents/webdriver/chromedriver-mac-arm64/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.get("https://formy-project.herokuapp.com/keypress");
        WebElement textField = driver.findElement(By.xpath("(//input[@id='name'])[1]"));
        textField.sendKeys("Juan Martin");
        WebElement button = driver.findElement(By.xpath("(//button[normalize-space()='Button'])[1]"));
        button.click();

        String texto = textField.getAttribute("value");
        System.out.println("Texto: "+ texto);

        String headerValue = driver.findElement(By.xpath("(//h1[normalize-space()='Keyboard and Mouse Input'])[1]")).getAttribute("value");
        System.out.println("HeaderValue: "+ headerValue);

    }

}