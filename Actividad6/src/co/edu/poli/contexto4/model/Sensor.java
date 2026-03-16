package co.edu.poli.contexto4.model;

public class Sensor {
    private String serial;
    private String material;
    private String color;
    private double tamanio;
    private double peso;

    public Sensor() {}

    public Sensor(String serial, String material, String color, double tamanio, double peso) {
        this.serial = serial;
        this.material = material;
        this.color = color;
        this.tamanio = tamanio;
        this.peso = peso;
    }

    // Método original
    public int medir_ritmo_cardiaco(int latidos) {
        System.out.println("[Sensor] Midiendo ritmo cardíaco con " + latidos + " latidos base.");
        return latidos * 2;
    }

    // SOBRECARGA del método medir_ritmo_cardiaco (punto 3)
    public int medir_ritmo_cardiaco(int latidos, String modo) {
        System.out.println("[Sensor - SOBRECARGA] Midiendo ritmo cardíaco en modo '" + modo + "' con " + latidos + " latidos.");
        if (modo.equalsIgnoreCase("rapido")) {
            return latidos * 3;
        }
        return latidos * 2;
    }

    public String getSerial() { return serial; }
    public void setSerial(String serial) { this.serial = serial; }
    public String getMaterial() { return material; }
    public void setMaterial(String material) { this.material = material; }
    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }
    public double getTamanio() { return tamanio; }
    public void setTamanio(double tamanio) { this.tamanio = tamanio; }
    public double getPeso() { return peso; }
    public void setPeso(double peso) { this.peso = peso; }

    @Override
    public String toString() {
        return "Sensor{serial='" + serial + "', material='" + material + "', color='" + color + "'}";
    }
}
