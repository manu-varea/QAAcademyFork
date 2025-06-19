package org.example;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.net.URISyntaxException;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

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

    @Test
    public void continuidadTest(){

        driver.get(pathTorneoHTML+"HomePage.html");
        clickear("(//a[normalize-space()='Registro de Jugador'])[1]");
        List<User> usuarios = obtenerUsuarios();

        //1. Cargo los 5 jugadores.
        for(User usuario : usuarios) {
            completarRegistro(usuario.getAll(), obtenerCampos());

            clickear("(//button[normalize-space()='Registrarse'])[1]");

            String mensaje = "Formulario enviado con éxito. ¡Gracias por registrarte!";

            verificarAlerta(mensaje);
        }
        //2.Voy a Equipos Asignados, me fijo que aparezcan.
        clickear("(//a[normalize-space()='Equipos asignados'])[1]");

        //3. Voy al header, Cambiar nombre.
        clickear("(//a[normalize-space()='Welcome QA Trainee'])[1]");

        clickear("(//a[normalize-space()='Nombre Equipo'])[1]");

        enviarTexto("(//input[@name='nombre_equipo'])[1]", "LosQA");

        //4. Le doy a guardar nombre.
        clickear("(//button[normalize-space()='Guardar Nombre'])[1]");

        //5.Valido mensaje exitoso
        verificarAlerta("Nombre del equipo guardado correctamente.");

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
        clickear("(//a[normalize-space()='Registro de Jugador'])[1]");

        for(User usuario : usuarios) {

            completarRegistro(usuario.getAll(), obtenerCampos());

            clickear("(//button[normalize-space()='Registrarse'])[1]");

            String mensaje = "Formulario enviado con éxito. ¡Gracias por registrarte!";

            verificarAlerta(mensaje);
        }
        clickear("(//a[normalize-space()='Equipos asignados'])[1]");
    }

    @Test
    @Order(2)
    public void equiposYNombreTest(){
        driver.get(pathTorneoHTML+"equipos.html");

        clickear("(//a[normalize-space()='Welcome QA Trainee'])[1]");

        clickear("(//a[normalize-space()='Nombre Equipo'])[1]");

        enviarTexto("(//input[@name='nombre_equipo'])[1]", "LosQA");

        clickear("(//button[normalize-space()='Guardar Nombre'])[1]");

        String mensaje = "Nombre del equipo guardado correctamente.";

        verificarAlerta(mensaje);

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
    @Order(4)
    public void abandonarEquipoTest() {
        driver.get(pathTorneoHTML+"mail_equipo_creado.html");

        //clickear dropdown
        clickear("(//a[normalize-space()='Welcome QA Trainee'])[1]");
        //clickear opcion en el dropdown
        clickear("(//a[normalize-space()='Abandonar Equipo'])[1]");

        enviarTexto("(//input[@name='nombre_equipo'])[1]", "CONFIRMO");
        //clickear boton abandonar
        clickear("(//button[normalize-space()='Abandonar Equipo'])[1]");

        verificarAlerta("Ha abandonado el equipo. No podrá unirse a otro en las proximas 24 horas.");

        //clickear boton volver
        clickear("(//button[@type='button'])[1]");

        String jugadorActual = driver.findElement(By.xpath("//div[@class='container']//li[4]")).getText();

        assertEquals("ADC: VACANTE", jugadorActual);

    }

    @Test
    public void campoVacioRegistroTest() {
        driver.get(pathTorneoHTML + "registro.html");
        WebElement boton = driver.findElement(By.xpath("(//button[normalize-space()='Registrarse'])[1]"));
        List<WebElement> campos = obtenerCampos();
        Map<String, String> mensajes = obtenerMensajes();
        String[] usuario = obtenerUsuarios().getFirst().getAll();
        int i = 0;

        for (WebElement campo : campos) {
            if(campo.getAccessibleName().equals("Usuario de Discord (opcional): ") || campo.getAccessibleName().equals("Rol Secundario: ")){
                i++;
                continue;
            }
            boton.click();
            if (campo.getAccessibleName().equals("Email: ")) {
                verificarMensajeValidacion(campo, mensajes.get("mensajeCampo"));
                campo.sendKeys("a");
                verificarMensajeValidacion(campo, mensajes.get("mensajeMail"));
                campo.sendKeys("@");
                verificarMensajeValidacion(campo, mensajes.get("mensajeMail2"));
                campo.clear();
            } else if (campo.getAccessibleName().equals("Nivel: ")) {
                verificarMensajeValidacion(campo, mensajes.get("mensajeCampo"));
                campo.sendKeys("-1");
                verificarMensajeValidacion(campo, mensajes.get("mensajeNivel"));
                campo.clear();
            } else if (campo.getAccessibleName().equals("Rol Principal: ") || campo.getAccessibleName().equals("País: ")) {
                verificarMensajeValidacion(campo, mensajes.get("mensajeLista"));
            } else {
                verificarMensajeValidacion(campo, mensajes.get("mensajeCampo"));
            }
            campo.sendKeys(usuario[i]);
            i++;
        }
        boton.click();
        driver.switchTo().alert().accept();
    }

    private List<User> obtenerUsuarios() {

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

    private List<WebElement> obtenerCampos() {

        List<WebElement> campos = new ArrayList<>();

        campos.add(driver.findElement(By.xpath("(//input[@name='nombre'])[1]")));
        campos.add(driver.findElement(By.xpath("(//input[@name='telefono'])[1]")));
        campos.add(driver.findElement(By.xpath("(//input[@name='email'])[1]")));
        campos.add(driver.findElement(By.xpath("(//input[@name='discord'])[1]")));
        campos.add(driver.findElement(By.xpath("(//input[@name='ign'])[1]")));
        campos.add(driver.findElement(By.xpath("(//input[@name='nivel'])[1]")));
        campos.add(driver.findElement(By.xpath("(//select[@name='rol_principal'])[1]")));
        campos.add(driver.findElement(By.xpath("(//select[@name='rol_secundario'])[1]")));
        campos.add(driver.findElement(By.xpath("(//select[@name='pais'])[1]")));

        return campos;
    }

    private Map<String, String> obtenerMensajes() {

        Map<String, String> mensajes = new HashMap<>();

        mensajes.put("mensajeCampo", "Completa este campo");
        mensajes.put("mensajeLista", "Selecciona un elemento de la lista");
        mensajes.put("mensajeMail", "Incluye un signo \"@\" en la dirección de correo electrónico. La dirección \"a\" no incluye el signo \"@\".");
        mensajes.put("mensajeMail2", "Ingresa texto después del signo \"@\". La dirección \"a@\" está incompleta.");
        mensajes.put("mensajeNivel", "El valor debe ser mayor de o igual a 1");

        return mensajes;
    }

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
        Alert alerta = driver.switchTo().alert();
        String alertaText = alerta.getText();

        assertEquals(mensaje, alertaText);

        alerta.accept();

        sleep(1);
    }

    private void clickear(String xpath) {
        driver.findElement(By.xpath(xpath)).click();
    }

    private void enviarTexto(String xpath, String texto) {
        driver.findElement(By.xpath(xpath)).sendKeys(texto);
    }

    //Metodo usado para que el test sea visible para el tester,
    // no se remueve por si es necesario utilizarlo nuevamente
    private void sleep(int seconds) {
        int ms = seconds * 1000;
        try{
          Thread.sleep(ms);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
