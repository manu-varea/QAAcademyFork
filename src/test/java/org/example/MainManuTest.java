package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainManuTest {
    private WebDriver driver;
    private String chromeDriverPath = "src/main/resources/chromedriver.exe";

    @BeforeEach
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
        driver = new ChromeDriver();
    }

//    @AfterEach
//    public void destroy(){
//        if(driver != null){
//            driver.quit();
//        }
//    }

    private List<User> obtenerUsuarios(){
        List<User> usuarios = new ArrayList<>();

        usuarios.add(new User("Andres", "1122227788","andres@test.com","axndy#1337",
                "axdny","85","Top","","Argentina"));
        usuarios.add( new User("Chris", "1122227188","chris@test.com","",
                "ChrisZ","75","Jungla","","Argentina"));
        usuarios.add(new User("Sele", "1122227299","sele@test.com","",
                "SeleBF","82","Mid","","Argentina"));
        usuarios.add(new User("Ivan", "1122261788","ivan@test.com","",
                "IvanGG","70","ADC","","Argentina"));
        usuarios.add(new User("Manu", "1133227788","manu@test.com","",
                "ManuV","78","Soporte","","Argentina"));

        return usuarios;
    }

    @Test
    public void testContinuidadQA(){

        List<User> usuarios = obtenerUsuarios();

        driver.get("http://127.0.0.1:5500/HomePage.html");
        driver.findElement(By.xpath("(//a[normalize-space()='Registro de Jugador'])[1]")).click();

        //#1. cargar los 5 usuarios
        for(User usuario : usuarios) {
            // para registrar multiples usuarios sin contar el primero,
            // ya que este ingreso clickeando en la page
            if(usuario.getDiscordUser().isEmpty()) {
                driver.get("http://127.0.0.1:5500/registro.html");
            }
            WebElement fieldNombre = driver.findElement(By.xpath("(//input[@name='nombre'])[1]"));
            fieldNombre.sendKeys(usuario.getNombre());

            WebElement fieldTelefono = driver.findElement(By.xpath("(//input[@name='telefono'])[1]"));
            fieldTelefono.sendKeys(usuario.getTelefono());

            WebElement fieldEmail = driver.findElement(By.xpath("(//input[@name='email'])[1]"));
            fieldEmail.sendKeys(usuario.getEmail());

            WebElement fieldDiscord = driver.findElement(By.xpath("(//input[@name='discord'])[1]"));
            fieldDiscord.sendKeys(usuario.getDiscordUser());

            WebElement fieldIgn = driver.findElement(By.xpath("(//input[@name='ign'])[1]"));
            fieldIgn.sendKeys(usuario.getIgn());

            WebElement fieldNivel = driver.findElement(By.xpath("(//input[@name='nivel'])[1]"));
            fieldNivel.sendKeys(usuario.getNivel());

            WebElement fieldRol1 = driver.findElement(By.xpath("(//select[@name='rol_principal'])[1]"));
            fieldRol1.sendKeys(usuario.getRolPrincipal());

            WebElement fieldRol2 = driver.findElement(By.xpath("(//select[@name='rol_secundario'])[1]"));
            fieldRol2.sendKeys(usuario.getRolSecundario());

            WebElement fieldPais = driver.findElement(By.xpath("(//select[@name='pais'])[1]"));
            fieldPais.sendKeys(usuario.getPais());

            WebElement botonSubmit = driver.findElement(By.xpath("(//button[normalize-space()='Registrarse'])[1]"));
            botonSubmit.click();

            Alert alerta = driver.switchTo().alert();
            String alertaText = alerta.getText();

            assertEquals("Formulario enviado con éxito. ¡Gracias por registrarte!", alertaText);

            alerta.accept();
        }

        // debido al error de la entrega del form,
        // vuelvo a entrar a la page de registro para seguir con el test
        driver.get("http://127.0.0.1:5500/registro.html");
        driver.findElement(By.xpath("(//a[normalize-space()='Equipos asignados'])[1]")).click();

    }

    @Test
    public void equiposTest(){
        List<User> usuarios = obtenerUsuarios();
        driver.get("http://127.0.0.1:5500/equipos.html");
        int i = 1;
        for(User usuario : usuarios) {
            String textoPage = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/div[3]/dl[1]/ul[1]/li["+i+"]")).getText();
            String textoArmado = usuario.getRolPrincipal().toUpperCase() + ": " + usuario.getNombre() + " (Nivel: " + usuario.getNivel() + ")";
            System.out.println(textoPage);
            System.out.println(textoArmado);
            i++;
            assertEquals(textoArmado, textoPage);
        }

    }

}
