package co.edu.poli.contexto4.model;

public class Nave {
    private String serial;
    private String material;
    private Astronauta[] astronauta;
    private double tamanio;
    private double peso;
    private double capacidad;
    private Registro registro;

    public Nave() {}

    public Nave(String serial, String material, Astronauta[] astronauta, double tamanio,
                double peso, double capacidad, Registro registro) {
        this.serial = serial;
        this.material = material;
        this.astronauta = astronauta;
        this.tamanio = tamanio;
        this.peso = peso;
        this.capacidad = capacidad;
        this.registro = registro;
    }

    public String getSerial() { return serial; }
    public void setSerial(String serial) { this.serial = serial; }
    public String getMaterial() { return material; }
    public void setMaterial(String material) { this.material = material; }
    public Astronauta[] getAstronauta() { return astronauta; }
    public void setAstronauta(Astronauta[] astronauta) { this.astronauta = astronauta; }
    public double getTamanio() { return tamanio; }
    public void setTamanio(double tamanio) { this.tamanio = tamanio; }
    public double getPeso() { return peso; }
    public void setPeso(double peso) { this.peso = peso; }
    public double getCapacidad() { return capacidad; }
    public void setCapacidad(double capacidad) { this.capacidad = capacidad; }
    public Registro getRegistro() { return registro; }
    public void setRegistro(Registro registro) { this.registro = registro; }

    @Override
    public String toString() {
        return "Nave{serial='" + serial + "', material='" + material + "', capacidad=" + capacidad + "}";
    }
}
