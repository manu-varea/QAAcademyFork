package org.example.elements;

public class EquiposPage extends Pagina {
    private final String url = "http://localhost:4567/equipos.html";
    private final String reportBugButton = "(//button[normalize-space()='Reportar un bug'])[1]";
    private final String donateButton = "(//button[normalize-space()='Donar al QA Mentor'])[1]";

    public String url() {
        return url;
    }

    public String getReportBugButton() {
        return reportBugButton;
    }

    public String getDonateButton() {
        return donateButton;
    }

}
