package co.edu.poli.contexto4.model;

public class Mitigacion {
    private String codigo;
    private String estrategia;
    private int limite;
    private String localizacion;
    private Radiacion radiacion;

    public Mitigacion() {}

    public Mitigacion(String codigo, String estrategia, int limite, String localizacion, Radiacion radiacion) {
        this.codigo = codigo;
        this.estrategia = estrategia;
        this.limite = limite;
        this.localizacion = localizacion;
        this.radiacion = radiacion;
    }

    public String mitigar_radiacion(double cantidad_radiacion) {
        System.out.println("[Mitigacion] Mitigando radiación de " + cantidad_radiacion + " sieverts con estrategia: " + estrategia);
        if (cantidad_radiacion > limite) {
            return "ALERTA: Radiación " + cantidad_radiacion + " supera límite " + limite + ". Aplicando " + estrategia;
        }
        return "Radiación " + cantidad_radiacion + " dentro del límite. Estrategia: " + estrategia;
    }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }
    public String getEstrategia() { return estrategia; }
    public void setEstrategia(String estrategia) { this.estrategia = estrategia; }
    public int getLimite() { return limite; }
    public void setLimite(int limite) { this.limite = limite; }
    public String getLocalizacion() { return localizacion; }
    public void setLocalizacion(String localizacion) { this.localizacion = localizacion; }
    public Radiacion getRadiacion() { return radiacion; }
    public void setRadiacion(Radiacion radiacion) { this.radiacion = radiacion; }

    @Override
    public String toString() {
        return "Mitigacion{codigo='" + codigo + "', estrategia='" + estrategia + "', limite=" + limite + "}";
    }
}
