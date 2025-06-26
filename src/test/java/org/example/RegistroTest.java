package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RegistroTest {
    private WebDriver driver;
    private String chromeDriverPath = "src/test/resources/chromedriver.exe";

    @BeforeEach
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
        driver = new ChromeDriver();
    }

//    @AfterEach
//    public void destroy(){
//        if (driver != null){
//            driver.quit();
//        }
//    }

    @Test
    public void testFormWork() {

        driver.get("http://localhost:4567/");
        clickear("(//a[normalize-space()='Registro de Jugador'])[1]");
        List<User> usuarios = obtenerUsuarios();

        for (User usuario : usuarios) {
            completarRegistro(usuario.getAll(), obtenerCampos());

            clickear("(//button[normalize-space()='Registrar Jugador'])[1]");

            String mensaje = "Registro exitoso!";

            verificarAlerta(mensaje);
        }
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

    private List<User> obtenerUsuarios() {

        List<User> usuarios = new ArrayList<>();

        usuarios.add(new User("Andres","andres@test.com", "1122227788"));
        usuarios.add( new User("Chris","chris@test.com", "1122227188"));
        usuarios.add(new User("Sele","sele@test.com", "1122227299"));
        usuarios.add(new User("Ivan","ivan@test.com", "1122261788"));
        usuarios.add(new User("Manu","manu@test.com", "1133227788"));

        return usuarios;
    }


    private List<WebElement> obtenerCampos() {

        List<WebElement> campos = new ArrayList<>();

        campos.add(driver.findElement(By.xpath("(//input[@id='nombre'])[1]")));
        campos.add(driver.findElement(By.xpath("(//input[@id='email'])[1]")));
        campos.add(driver.findElement(By.xpath("(//input[@id='telefono'])[1]")));

        return campos;
    }

/*
    private Map<String, String> obtenerMensajes() {

        Map<String, String> mensajes = new HashMap<>();

        mensajes.put("mensajeCampo", "Completa este campo");
        mensajes.put("mensajeLista", "Selecciona un elemento de la lista");
        mensajes.put("mensajeMail", "Incluye un signo \"@\" en la dirección de correo electrónico. La dirección \"a\" no incluye el signo \"@\".");
        mensajes.put("mensajeMail2", "Ingresa texto después del signo \"@\". La dirección \"a@\" está incompleta.");
        mensajes.put("mensajeNivel", "El valor debe ser mayor de o igual a 1");

        return mensajes;
    }
*/

    private void verificarMensajeValidacion(WebElement campo, String mensajeEsperado ) {
        String validationText = campo.getAttribute("validationMessage");
        assertEquals(mensajeEsperado, validationText);
    }

    private void completarRegistro(String[] usuario, List<WebElement> campos)  {
        int i = 0;
        for(WebElement campo : campos){
            campo.sendKeys(usuario[i]);
            i++;
        }
    }

    private void verificarAlerta(String mensaje) {
        sleep(1);
        Alert alerta = driver.switchTo().alert();
        String alertaText = alerta.getText();

        assertEquals(mensaje, alertaText);

        alerta.accept();

    }

    private void clickear(String xpath) {
        driver.findElement(By.xpath(xpath)).click();
    }

    private void enviarTexto(String xpath, String texto) {
        driver.findElement(By.xpath(xpath)).sendKeys(texto);
    }

    private void sleep(int seconds) {
        int ms = seconds * 1000;
        try{
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
