package co.edu.poli.contexto4.model;

// Clase ABSTRACTA - no se puede instanciar directamente
public abstract class Protocolo {

    // codigo sube al padre para que el CRUD pueda identificar objetos por ID
    private String codigo;
    private String registro;
    private String instrucciones;
    private String limites;
    private Mitigacion mitigacion;
    private Sensor sensor;
    private Radiacion radiacion;

    public Protocolo() {}

    public Protocolo(String codigo, String registro, String instrucciones, String limites,
                     Mitigacion mitigacion, Sensor sensor, Radiacion radiacion) {
        this.codigo = codigo;
        this.registro = registro;
        this.instrucciones = instrucciones;
        this.limites = limites;
        this.mitigacion = mitigacion;
        this.sensor = sensor;
        this.radiacion = radiacion;
    }

    // MÉTODO ABSTRACTO - cada subclase debe implementarlo obligatoriamente
    public abstract String leer_informacion();

    // Método sobreescrito por subclases (polimorfismo)
    public String controlar_limites(double cantidad_radiacion) {
        System.out.println("[Protocolo - BASE] Controlando limites para " + cantidad_radiacion + " sieverts.");
        return "Limite base verificado: " + limites;
    }

    // PUNTO 3 anterior: Método FINAL - no se puede sobreescribir
    public final String obtener_descripcion_protocolo() {
        return "Protocolo[" + codigo + "] | Instrucciones: " + instrucciones + " | Limites: " + limites;
    }

    public String modificar_protocolo() {
        return "Protocolo modificado: " + instrucciones;
    }

    public String modificar_protocolo(String vigencia) {
        return "Protocolo modificado con vigencia '" + vigencia + "': " + instrucciones;
    }

    // Getters y Setters
    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }
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

