package org.example.steps;

import io.cucumber.java.en.*;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import com.google.gson.Gson;
import java.io.IOException;
import org.junit.jupiter.api.extension.ExtendWith;
import org.example.config.TestConfig;
import com.google.gson.reflect.TypeToken;

import java.util.List;
import java.util.Map;

@ExtendWith(TestConfig.class)
public class DemoSteps {
    private static final String SERVER_URL = "http://localhost:4567";
    private String playerNombre;

    @Given("the server is running")
    public void theServerIsRunning() {
        // El servidor ya está inicializado por TestConfig
    }

    @When("I create a jugador with name {string} mail {string} and phone {string}")
    public void createJugador(String name, String mail, String phone) throws IOException {
        // Crear el objeto jugador
        Jugador jugador = new Jugador(name, mail, phone);
        
        // Convertir a JSON
        Gson gson = new Gson();
        String json = gson.toJson(jugador);
        
        // Crear el request HTTP
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(SERVER_URL + "/api/jugadores");
        httpPost.setHeader("Content-type", "application/json");
        httpPost.setEntity(new StringEntity(json));
        
        // Ejecutar el request
        CloseableHttpResponse response = httpClient.execute(httpPost);
        
        // Verificar que la creación fue exitosa
        if (response.getStatusLine().getStatusCode() != 200) {
            throw new AssertionError("Expected status code 200, but got " + response.getStatusLine().getStatusCode());
        }
        
        // Obtener el nombre del jugador creado
        String responseJson = EntityUtils.toString(response.getEntity());
        Map<String, String> responseMap = new Gson().fromJson(responseJson, new TypeToken<Map<String, String>>(){}.getType());
        this.playerNombre = responseMap.get("nombre");
    }

    @Then("I should receive a valid response with name {string} mail {string} and phone {string}")
    public void verifyResponse(String expectedName, String expectedMail, String expectedPhone) throws IOException {
        // Crear la petición HTTP para verificar
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(SERVER_URL + "/api/jugadores?nombre=" + this.playerNombre);

        // Ejecutar la petición
        CloseableHttpResponse response = httpClient.execute(httpGet);

        // Verificar el código de respuesta
        if (response.getStatusLine().getStatusCode() != 200) {
            throw new AssertionError("Expected status code 200, but got " + response.getStatusLine().getStatusCode());
        }

        // Obtener la respuesta y parsear como lista
        String responseJson = EntityUtils.toString(response.getEntity());
        List<Jugador> jugadores = new Gson().fromJson(responseJson, new TypeToken<List<Jugador>>(){}.getType());

        if (jugadores.isEmpty()) {
            throw new AssertionError("No se recibió ningún jugador en la respuesta.");
        }

        // Tomar el primer jugador y verificar los datos
        Jugador createdJugador = jugadores.get(0);

        if (!createdJugador.getNombre().equals(expectedName) ||
                !createdJugador.getEmail().equals(expectedMail) ||
                !createdJugador.getTelefono().equals(expectedPhone)) {
            throw new AssertionError("Jugador data does not match expected values");
        }
    }


    private static class Jugador {
        private String nombre;
        private String email;
        private String telefono;

        public Jugador(String nombre, String email, String telefono) {
            this.nombre = nombre;
            this.email = email;
            this.telefono = telefono;
        }

        public String getNombre() { return nombre; }
        public String getEmail() { return email; }
        public String getTelefono() { return telefono; }
    }

}