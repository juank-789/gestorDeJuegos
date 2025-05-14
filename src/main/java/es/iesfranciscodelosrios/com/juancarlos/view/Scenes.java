package es.iesfranciscodelosrios.com.juancarlos.view;

public enum Scenes {



    JUEGOFORM("view/JuegoForm.fxml");


    private final String url;

    Scenes(String url) {
        this.url = url;
    }

    public String getURL() {
        return url;
    }
}
