package co.edu.poli.contexto4.model;

public class Registro {
    private int numero_registro;
    private int fecha;
    private Astronauta astronauta;
    private String datos;

    public Registro() {}

    public Registro(int numero_registro, int fecha, Astronauta astronauta, String datos) {
        this.numero_registro = numero_registro;
        this.fecha = fecha;
        this.astronauta = astronauta;
        this.datos = datos;
    }

    public String registrar_datos_astronauta() {
        System.out.println("[Registro] Registrando datos del astronauta: " +
                (astronauta != null ? astronauta.getNombre() : "Sin astronauta"));
        return "Registro #" + numero_registro + " | Fecha: " + fecha +
               " | Astronauta: " + (astronauta != null ? astronauta.getNombre() : "N/A") +
               " | Datos: " + datos;
    }

    public int getNumero_registro() { return numero_registro; }
    public void setNumero_registro(int numero_registro) { this.numero_registro = numero_registro; }
    public int getFecha() { return fecha; }
    public void setFecha(int fecha) { this.fecha = fecha; }
    public Astronauta getAstronauta() { return astronauta; }
    public void setAstronauta(Astronauta astronauta) { this.astronauta = astronauta; }
    public String getDatos() { return datos; }
    public void setDatos(String datos) { this.datos = datos; }

    @Override
    public String toString() {
        return "Registro{numero_registro=" + numero_registro + ", fecha=" + fecha +
               ", astronauta='" + (astronauta != null ? astronauta.getNombre() : "N/A") +
               "', datos='" + datos + "'}";
    }
}
