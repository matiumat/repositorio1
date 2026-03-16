package co.edu.poli.contexto4.model;

public class Bitacora {
    private String codigo;
    private int fecha;
    private String datos;
    private Registro registro;

    public Bitacora() {}

    public Bitacora(String codigo, int fecha, String datos, Registro registro) {
        this.codigo = codigo;
        this.fecha = fecha;
        this.datos = datos;
        this.registro = registro;
    }

    public String registrar_informacion_bitacora() {
        System.out.println("[Bitacora] Registrando información en bitácora código: " + codigo);
        return "Bitacora{codigo='" + codigo + "', fecha=" + fecha + ", datos='" + datos + "'}";
    }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }
    public int getFecha() { return fecha; }
    public void setFecha(int fecha) { this.fecha = fecha; }
    public String getDatos() { return datos; }
    public void setDatos(String datos) { this.datos = datos; }
    public Registro getRegistro() { return registro; }
    public void setRegistro(Registro registro) { this.registro = registro; }

    @Override
    public String toString() {
        return "Bitacora{codigo='" + codigo + "', fecha=" + fecha + ", datos='" + datos + "'}";
    }
}
