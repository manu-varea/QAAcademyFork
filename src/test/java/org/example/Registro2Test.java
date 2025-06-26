package org.example;

import org.example.elements.*;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Registro2Test {
    private WebDriver driver;
    private String chromeDriverPath = "src/test/resources/chromedriver.exe";
    private Map<String, Pagina> paginas = obtenerPaginas();
    private Navbar navbar = new Navbar();

    @BeforeEach
    public void setUp() {
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
        goToPage("home");
        clickear(navbar.getRegistrationButton());
        RegistroPage registroPage = (RegistroPage) paginas.get("registro");
        for (User usuario : obtenerUsuarios()) {
            completarRegistro(usuario.getAll(), obtenerCampos(registroPage));

            clickear(registroPage.getRegistroButton());

            String mensaje = "Registro exitoso!";

            verificarAlerta(mensaje);
        }

    }

    @Test
    public void campoVacioRegistroTest() {
        RegistroPage registroPage = (RegistroPage) goToPage("registro");
        String boton = "(//button[normalize-space()='Registrarse'])[1]";
        List<String> campos = obtenerCampos(registroPage);
        Map<String, String> mensajes = obtenerMensajes();
        String[] usuario = obtenerUsuarios().getFirst().getAll();
        int i = 0;

        for (String xpath : campos) {
            WebElement campo = buscarElemento(xpath);
            if(campo.getAccessibleName().equals("Usuario de Discord (opcional): ") || campo.getAccessibleName().equals("Rol Secundario: ")){
                i++;
                continue;
            }
            clickear(boton);
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
        clickear(boton);
        driver.switchTo().alert().accept();
    }

    @Test
    public void verificarJugadoresTest() {
        JugadoresPage jugadoresPage = (JugadoresPage) goToPage("jugadores");
        int i = 1;
        for(User usuario : obtenerUsuarios()) {
            clickear(jugadoresPage.getBotonVer(i));
            verificarDatosJugador(usuario, jugadoresPage);
            i++;
        }
    }

    @Test
    public void verificarMensajeTest() {
        RegistroPage registroPage = (RegistroPage) goToPage("registro");

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


    private List<String> obtenerCampos(RegistroPage registroPage) {
        List<String> campos = new ArrayList<>();

        campos.add(registroPage.getNombreField());
        campos.add(registroPage.getEmailField());
        campos.add(registroPage.getTelefonoField());

        return campos;
    }


    private Map<String, Pagina> obtenerPaginas() {
        Map<String, Pagina> paginas = new HashMap<>();

        paginas.put("home", new HomePage());
        paginas.put("registro", new RegistroPage());
        paginas.put("jugadores", new JugadoresPage());
        paginas.put("equipos", new EquiposPage());
        paginas.put("crear_equipo", new crearEquipoPage());
        paginas.put("admin", new panelAdminPage());

        return paginas;
    }

    private Map<String, String> obtenerMensajes() {

        Map<String, String> mensajes = new HashMap<>();

        mensajes.put("mensajeCampo", "Completa este campo");
        mensajes.put("mensajeMail", "Incluye un signo \"@\" en la dirección de correo electrónico. La dirección \"a\" no incluye el signo \"@\".");
        mensajes.put("mensajeMail2", "Ingresa texto después del signo \"@\". La dirección \"a@\" está incompleta.");

        return mensajes;
    }

    private void verificarMensajeValidacion(WebElement campo, String mensajeEsperado ) {
        String validationText = campo.getAttribute("validationMessage");
        assertEquals(mensajeEsperado, validationText);
    }

    private void completarRegistro(String[] usuario, List<String> campos)  {
        int i = 0;
        for(String campo : campos){
            enviarTexto(campo, usuario[i]);
            i++;
        }
    }

    private void verificarDatosJugador(User usuario, JugadoresPage jugadoresPage) {
        sleep(2);

        String[] datosJugador = buscarElemento(jugadoresPage.getCardJugador()).getText().split("\n");

        assertEquals("Nombre: " + usuario.getNombre(), datosJugador[0]);
        assertEquals("Email: " + usuario.getEmail(), datosJugador[1]);
        assertEquals("Teléfono: " + usuario.getTelefono(), datosJugador[2]);

        clickear(jugadoresPage.getBotonCard());
    }

    private void verificarAlerta(String mensaje) {
        sleep(1);
        Alert alerta = driver.switchTo().alert();
        String alertaText = alerta.getText();

        assertEquals(mensaje, alertaText);

        alerta.accept();
    }

    private Pagina goToPage(String pageName) {
        Pagina page = (Pagina) paginas.get(pageName);
        driver.get(page.url());
        return page;
    }

    private WebElement buscarElemento(String xpath) {
        return driver.findElement(By.xpath(xpath));
    }

    private void clickear(String xpath) {
        buscarElemento(xpath).click();
    }

    private void enviarTexto(String xpath, String texto) {
        buscarElemento(xpath).sendKeys(texto);
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
