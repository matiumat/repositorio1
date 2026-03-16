package co.edu.poli.contexto4.model;

public class Astronauta {
    private String id;
    private String nombre;
    private double peso;
    private int edad;
    private String nacionalidad;
    private String tipo_sangre;
    private int anios_experiencia;
    private String sexo;
    private Traje traje;

    public Astronauta() {}

    public Astronauta(String id, String nombre, double peso, int edad, String nacionalidad,
                      String tipo_sangre, int anios_experiencia, String sexo, Traje traje) {
        this.id = id;
        this.nombre = nombre;
        this.peso = peso;
        this.edad = edad;
        this.nacionalidad = nacionalidad;
        this.tipo_sangre = tipo_sangre;
        this.anios_experiencia = anios_experiencia;
        this.sexo = sexo;
        this.traje = traje;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public double getPeso() { return peso; }
    public void setPeso(double peso) { this.peso = peso; }
    public int getEdad() { return edad; }
    public void setEdad(int edad) { this.edad = edad; }
    public String getNacionalidad() { return nacionalidad; }
    public void setNacionalidad(String nacionalidad) { this.nacionalidad = nacionalidad; }
    public String getTipo_sangre() { return tipo_sangre; }
    public void setTipo_sangre(String tipo_sangre) { this.tipo_sangre = tipo_sangre; }
    public int getAnios_experiencia() { return anios_experiencia; }
    public void setAnios_experiencia(int anios_experiencia) { this.anios_experiencia = anios_experiencia; }
    public String getSexo() { return sexo; }
    public void setSexo(String sexo) { this.sexo = sexo; }
    public Traje getTraje() { return traje; }
    public void setTraje(Traje traje) { this.traje = traje; }

    @Override
    public String toString() {
        return "Astronauta{id='" + id + "', nombre='" + nombre + "', edad=" + edad +
               ", nacionalidad='" + nacionalidad + "', sexo='" + sexo + "'}";
    }
}
