package Kopius.Academy;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class Registros {
    //String chromeDriverPath = "/Workspace/chromedriver/chromedriver.exe";
    //para mac
    String chromeDriverPath = "/Users/martinlequerica/Documents/webdriver/chromedriver-mac-arm64/chromedriver";
//    String pathTorneoHTML = "file:///C:/Workspace/KopiusAcademy/src/main/resources/TorneoHTML/";
    String pathTorneoHTML;

    {
        try {
            pathTorneoHTML = getClass().getClassLoader().getResource("TorneoHTML/").toURI().toString();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    //String webRegistro = "file:///C:/Workspace/KopiusAcademy/src/main/resources/TorneoHTML/registro.html";
        public static void main(String[] args) {
            System.out.println("Todo listo para la ejecucion.");
            new Registros();
        }

public Registros(){
    System.out.println("Comienza el Test 1");
    System.setProperty("webdriver.chrome.driver", chromeDriverPath);
    WebDriver driver = new ChromeDriver();
    driver.get(pathTorneoHTML+"registro.html");

    String currentUrl = driver.getCurrentUrl();
    System.out.println("Abrimos Pagina: " + currentUrl);

}
}