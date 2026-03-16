package co.edu.poli.contexto4.model;

public class Traje {
    private String serial;
    private String calidad;
    private String blindaje;
    private String tipo;
    // ATRIBUTO ESTÁTICO (punto 4) - subrayado en el diagrama
    public static String color;
    private String funcion;
    private Sensor sensor;

    public Traje() {}

    public Traje(String serial, String calidad, String blindaje, String tipo, String funcion, Sensor sensor) {
        this.serial = serial;
        this.calidad = calidad;
        this.blindaje = blindaje;
        this.tipo = tipo;
        this.funcion = funcion;
        this.sensor = sensor;
    }

    public String getSerial() { return serial; }
    public void setSerial(String serial) { this.serial = serial; }
    public String getCalidad() { return calidad; }
    public void setCalidad(String calidad) { this.calidad = calidad; }
    public String getBlindaje() { return blindaje; }
    public void setBlindaje(String blindaje) { this.blindaje = blindaje; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public static String getColor() { return color; }
    public static void setColor(String nuevoColor) { color = nuevoColor; }
    public String getFuncion() { return funcion; }
    public void setFuncion(String funcion) { this.funcion = funcion; }
    public Sensor getSensor() { return sensor; }
    public void setSensor(Sensor sensor) { this.sensor = sensor; }

    @Override
    public String toString() {
        return "Traje{serial='" + serial + "', calidad='" + calidad + "', color(static)='" + color + "', tipo='" + tipo + "'}";
    }
}
