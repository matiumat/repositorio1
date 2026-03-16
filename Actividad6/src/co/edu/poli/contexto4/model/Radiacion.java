package co.edu.poli.contexto4.model;

public class Radiacion {
    private double cantidad_sievert;
    private String nivel_de_peligro;
    private String tipo;
    private String intensidad;
    private String origen;

    public Radiacion() {}

    public Radiacion(double cantidad_sievert, String nivel_de_peligro, String tipo,
                     String intensidad, String origen) {
        this.cantidad_sievert = cantidad_sievert;
        this.nivel_de_peligro = nivel_de_peligro;
        this.tipo = tipo;
        this.intensidad = intensidad;
        this.origen = origen;
    }

    public double medir_radiacion() {
        System.out.println("[Radiacion] Midiendo radiación: " + cantidad_sievert + " sieverts.");
        return cantidad_sievert;
    }

    public double getCantidad_sievert() { return cantidad_sievert; }
    public void setCantidad_sievert(double cantidad_sievert) { this.cantidad_sievert = cantidad_sievert; }
    public String getNivel_de_peligro() { return nivel_de_peligro; }
    public void setNivel_de_peligro(String nivel_de_peligro) { this.nivel_de_peligro = nivel_de_peligro; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public String getIntensidad() { return intensidad; }
    public void setIntensidad(String intensidad) { this.intensidad = intensidad; }
    public String getOrigen() { return origen; }
    public void setOrigen(String origen) { this.origen = origen; }

    @Override
    public String toString() {
        return "Radiacion{cantidad_sievert=" + cantidad_sievert + ", nivel_de_peligro='" +
               nivel_de_peligro + "', tipo='" + tipo + "', origen='" + origen + "'}";
    }
}
