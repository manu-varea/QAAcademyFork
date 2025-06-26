package org.example;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

/**
 * Clase principal para ejecutar los tests de Cucumber
 * Esta clase configura y ejecuta los tests de Cucumber usando JUnit Platform
 */
@Suite // Indica que esta clase es un contenedor de tests JUnit
@IncludeEngines("cucumber") // Especifica que queremos usar el motor de Cucumber
@SelectClasspathResource("features") // Indica dónde están los archivos .feature con los tests
@ConfigurationParameter(key = "cucumber.glue", value = "org.example.steps") // Especifica dónde están las definiciones de pasos (step definitions)
@ConfigurationParameter(key = "cucumber.features", value = "classpath:features") // Otra forma de indicar dónde están los archivos de feature
@ConfigurationParameter(key = "cucumber.plugin", value = "pretty") // Configura el formato de salida de los resultados (bonito y legible)
public class CucumberTest {
}
