package org.example.config;

import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.example.Server;

public class TestConfig implements BeforeAllCallback, AfterAllCallback {
    private static final int PORT = 4567;

    @Override
    public void beforeAll(ExtensionContext context) throws Exception {
        // Configurar el puerto
        System.setProperty("server.port", String.valueOf(PORT));
        // Iniciar el servidor
        Server.main(new String[0]);
    }

    @Override
    public void afterAll(ExtensionContext context) throws Exception {
        // No hay un método stop() en el servidor, así que no podemos detenerlo
        // El servidor se detendrá automáticamente cuando termine la ejecución de los tests
    }
}
