package co.edu.poli.contexto4.model;

public abstract class Protocolo {
    private String registro;
    private String instrucciones;
    private String limites;
    private Mitigacion mitigacion;
    private Sensor sensor;
    private Radiacion radiacion;

    public Protocolo() {}

    public Protocolo(String registro, String instrucciones, String limites,
                     Mitigacion mitigacion, Sensor sensor, Radiacion radiacion) {
        this.registro = registro;
        this.instrucciones = instrucciones;
        this.limites = limites;
        this.mitigacion = mitigacion;
        this.sensor = sensor;
        this.radiacion = radiacion;
    }

    // PUNTO 3 (nuevo): Método que NO se puede sobreescribir (final)
    public final String obtener_descripcion_protocolo() {
        return "Protocolo base | Instrucciones: " + instrucciones + " | Límites: " + limites;
    }

    // Método sobreescrito por subclases (polimorfismo)
    public String controlar_limites(double cantidad_radiacion) {
        System.out.println("[Protocolo - BASE] Controlando límites para " + cantidad_radiacion + " sieverts.");
        return "Límite base verificado: " + limites;
    }

    public String modificar_protocolo() {
        return "Protocolo modificado: " + instrucciones;
    }

    public String modificar_protocolo(String vigencia) {
        return "Protocolo modificado con vigencia '" + vigencia + "': " + instrucciones;
    }

    public String getRegistro() { return registro; }
    public void setRegistro(String registro) { this.registro = registro; }
    public String getInstrucciones() { return instrucciones; }
    public void setInstrucciones(String instrucciones) { this.instrucciones = instrucciones; }
    public String getLimites() { return limites; }
    public void setLimites(String limites) { this.limites = limites; }
    public Mitigacion getMitigacion() { return mitigacion; }
    public void setMitigacion(Mitigacion mitigacion) { this.mitigacion = mitigacion; }
    public Sensor getSensor() { return sensor; }
    public void setSensor(Sensor sensor) { this.sensor = sensor; }
    public Radiacion getRadiacion() { return radiacion; }
    public void setRadiacion(Radiacion radiacion) { this.radiacion = radiacion; }
}
