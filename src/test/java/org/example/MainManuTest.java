package org.example;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.Alert;
import org.openqa.selenium.chrome.ChromeDriver;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MainManuTest {
    private WebDriver driver;
    private String chromeDriverPath = "src/test/resources/chromedriver.exe";
    String pathTorneoHTML;

    {
        try {
            pathTorneoHTML = getClass().getClassLoader().getResource("TorneoHTML/").toURI().toString();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    @BeforeEach
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
        driver = new ChromeDriver();
    }

    @AfterEach
    public void destroy(){
        if(driver != null){
            driver.quit();
        }
    }

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
    //Metodo usado para que el testing sea visible para el tester,
    // no se remueve por si es necesario utilizarlo nuevamente
    private void sleep(int seconds){
        int ms = seconds * 1000;
        try{
          Thread.sleep(ms);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

        @Test
        public void continuidadTest(){

            driver.get(pathTorneoHTML+"HomePage.html");
            driver.findElement(By.xpath("(//a[normalize-space()='Registro de Jugador'])[1]")).click();
            List<User> usuarios = obtenerUsuarios();

            //1. Cargo los 5 jugadores.
            for(User usuario : usuarios) {

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
            //2.Voy a Equipos Asignados, me fijo que aparezcan.
            driver.findElement(By.xpath("(//a[normalize-space()='Equipos asignados'])[1]")).click();

            //3. Voy al header, Cambiar nombre.
            WebElement dropDown = driver.findElement(By.xpath("(//a[normalize-space()='Welcome QA Trainee'])[1]"));
            dropDown.click();

            WebElement botonNombre = driver.findElement(By.xpath("(//a[normalize-space()='Nombre Equipo'])[1]"));
            botonNombre.click();

            WebElement inputNombre = driver.findElement(By.xpath("(//input[@name='nombre_equipo'])[1]"));
            inputNombre.sendKeys("LosQA");

            //4. Le doy a guardar nombre.
            WebElement botonGuardar = driver.findElement(By.xpath("(//button[normalize-space()='Guardar Nombre'])[1]"));
            botonGuardar.click();

            //5.Valido mensaje exitoso
            Alert alerta = driver.switchTo().alert();
            String alertaText = alerta.getText();

            assertEquals("Nombre del equipo guardado correctamente.", alertaText);

            alerta.accept();

            //6. Valido que los datos del equipo sean correctos.
            String msgActual = driver.findElement(By.xpath("//div[@class='panel-body']/dl/p[2]")).getText();
            assertTrue(msgActual.contains("LosQA"));

            String jugadorExpected;
            String jugadorActual;
            int i = 1;

            for(User usuario : usuarios) {
                if(i == 1){
                    jugadorExpected = "Líder: " + usuario.getNombre() + " (IGN: " + usuario.getIgn() + ", Discord: " + usuario.getDiscordUser() + ")";
                    jugadorActual = driver.findElement(By.xpath("//div[@class='container']//li[" + i + "]")).getText();
                    assertEquals(jugadorExpected, jugadorActual);
                }
                jugadorExpected = usuario.getRolPrincipal().toUpperCase() + ": " + usuario.getNombre() + " (IGN: " + usuario.getIgn() + ")";
                jugadorActual = driver.findElement(By.xpath("//div[@class='container']//li[" + (i + 1) + "]")).getText();
                assertEquals(jugadorExpected, jugadorActual);
                i++;
            }
        }

        @Test
        @Order(1)
        public void cargaUsuariosTest(){

            List<User> usuarios = obtenerUsuarios();
            driver.get(pathTorneoHTML+"HomePage.html");
            driver.findElement(By.xpath("(//a[normalize-space()='Registro de Jugador'])[1]")).click();

            for(User usuario : usuarios) {

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
            driver.findElement(By.xpath("(//a[normalize-space()='Equipos asignados'])[1]")).click();
        }

        @Test
        @Order(2)
        public void equiposYNombreTest(){
            driver.get(pathTorneoHTML+"equipos.html");

            WebElement dropDown = driver.findElement(By.xpath("(//a[normalize-space()='Welcome QA Trainee'])[1]"));
            dropDown.click();

            WebElement botonNombre = driver.findElement(By.xpath("(//a[normalize-space()='Nombre Equipo'])[1]"));
            botonNombre.click();

            WebElement inputNombre = driver.findElement(By.xpath("(//input[@name='nombre_equipo'])[1]"));
            inputNombre.sendKeys("LosQA");

            WebElement botonGuardar = driver.findElement(By.xpath("(//button[normalize-space()='Guardar Nombre'])[1]"));
            botonGuardar.click();

            Alert alerta = driver.switchTo().alert();
            String alertaText = alerta.getText();

            assertEquals("Nombre del equipo guardado correctamente.", alertaText);

            alerta.accept();
        }

        @Test
        @Order(3)
        public void valoresTest(){
            //6. Valido que los datos del equipo sean correctos.
            List<User> usuarios = obtenerUsuarios();
            driver.get(pathTorneoHTML+"mail_equipo_creado.html");
            int i = 1;

            String msgExpected = "Te informamos que tu equipo, LosQA, ha sido conformado exitosamente. A continuación te compartimos los datos de tus compañeros:";
            String msgActual = driver.findElement(By.xpath("//div[@class='panel-body']/dl/p[2]")).getText();

            //dos maneras diferentes de testear el mensaje
            assertEquals(msgExpected, msgActual);
            assertTrue(msgActual.contains("LosQA"));

            String jugadorExpected;
            String jugadorActual;

            for(User usuario : usuarios) {
                if(i == 1){
                    jugadorExpected = "Líder: " + usuario.getNombre() + " (IGN: " + usuario.getIgn() + ", Discord: " + usuario.getDiscordUser() + ")";
                    jugadorActual = driver.findElement(By.xpath("//div[@class='container']//li[" + i + "]")).getText();
                    assertEquals(jugadorExpected, jugadorActual);
                }
                jugadorExpected = usuario.getRolPrincipal().toUpperCase() + ": " + usuario.getNombre() + " (IGN: " + usuario.getIgn() + ")";
                jugadorActual = driver.findElement(By.xpath("//div[@class='container']//li[" + (i + 1) + "]")).getText();
                assertEquals(jugadorExpected, jugadorActual);
                i++;
            }
        }

    @Test
    public void campoVacioRegistroTest() {
        driver.get(pathTorneoHTML + "registro.html");
        WebElement botonSubmit = driver.findElement(By.xpath("(//button[normalize-space()='Registrarse'])[1]"));
        botonSubmit.click();

        String validationText = driver.findElement(By.name("nombre")).getAttribute("validationMessage");

        assertEquals("Completa este campo", validationText);

    }
}