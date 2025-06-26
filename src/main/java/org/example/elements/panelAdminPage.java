package org.example.elements;

public class panelAdminPage extends Pagina {
    private final String url = "http://localhost:4567/panel_admin";
    private final String datePicker = "(//input[@name='fecha'])[1]";
    private final String timePicker = "(//input[@name='hora'])[1]";
    private final String notifyButton = "(//button[normalize-space()='Notificar Participantes'])[1]";
    private final String previewButton = "(//a[normalize-space()='Vista Previa'])[1]";

    public String url(){
        return url;
    }

    public String getDatePicker() {
        return datePicker;
    }

    public String getTimePicker() {
        return timePicker;
    }

    public String getNotifyButton() {
        return notifyButton;
    }

    public String getPreviewButton() {
        return previewButton;
    }

}
