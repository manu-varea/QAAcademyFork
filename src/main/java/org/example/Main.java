import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class Main {
    String chromeDriverPath = "/Workspace/chromedriver/chromedriver.exe";
    public static void main(String[] args) {
        System.out.println("Todo listo para la ejecucion.");
        new Main();
    }

    public Main(){

        FormA usuario1 = new FormA("Niko","B", "QA Mentor","10+");
        FormA usuario2 = new FormA("juan1","mrtn1", "Dev mmsr","5-9");
        FormA usuario3 = new FormA("juan","mrtn", "Dev msr","2-4");
        FormA usuario4 = new FormA("juan12","mrtn12", "Dev jr","0-1");

        System.out.println("Lista de usuarios cargados.");

        List<FormA> usuarios = new ArrayList<>();
        usuarios.add(usuario1);/*
        usuarios.add(usuario2);
        usuarios.add(usuario3);
        usuarios.add(usuario4);*/

        for(FormA usuario: usuarios){
            System.out.println("Usuario creado. Nombre: "+usuario.getFirstName()+" "+usuario.getLastName());
            completeWebForm(usuario);
            keyboardAndMouse(usuario);
        }

    }

    public void completeWebForm(FormA usuario){
        // El absolute path de donde tienen descargado el webdriver
        // Pagina para bajar webdriver: https://googlechromelabs.github.io/chrome-for-testing/
        System.out.println("Comienza el Test 1");
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
        WebDriver driver = new ChromeDriver();
        driver.get("https://formy-project.herokuapp.com/form");

        String currentUrl = driver.getCurrentUrl();
        System.out.println("Abrimos Pagina: " + currentUrl);

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
        WebElement submitButton = driver.findElement(By.xpath("//a[text()='Submit']"));
        //WebElement submitButton = driver.findElement(By.xpath("//a[@role='button' and text()='Submit']"));

        submitButton.click();

        try {
            Thread.sleep(2000); // 2 segundos
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));

        //Validamos el resultado manualmente por ahora.
        System.out.println("Titulo de página luego de enviar: " + driver.getTitle());
        WebElement confirmationMessage = driver.findElement(By.xpath("//div[@class='container']/div[@role='alert']"));
        System.out.println("Mensaje esperado: " + "The form was successfully submitted!");
        System.out.println("Mensaje obtenido: " + confirmationMessage.getText());
        //Message que aparece: The form was successfully submitted!

        // Si lo cerras, es inmediato, por ende no podes ver los resultados
        //driver.quit();
        System.out.println("Termino el test del form.");
    }

    public void keyboardAndMouse(FormA usuario){
        System.out.println("Comienza el Test 2");
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
        WebDriver driver = new ChromeDriver();
        driver.get("https://formy-project.herokuapp.com/keypress");

        String currentUrl = driver.getCurrentUrl();
        System.out.println("Abrimos Pagina: " + currentUrl);

        //WebElement nameInput = driver.findElement(By.xpath("(//input[@id='name'])[1]"));
        //nameInput.sendKeys(usuario.getFirstName());
        System.out.println("Enviamos usuario previamente creado: "+usuario.getFirstName());
        rellenarCampo(driver, usuario.getFirstName(), "(//input[@id='name'])[1]");
        //driver.quit();
    }

    public void rellenarCampo(WebDriver driver, String valorCampo, String xpath) {
        WebElement nameInput = driver.findElement(By.xpath(xpath));
        nameInput.sendKeys(valorCampo);
    }


    public void function(){

        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
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
