package co.edu.poli.contexto4.model;

public class ProtocoloRadiacion extends Protocolo {
    private String codigo;

    public ProtocoloRadiacion() {}

    public ProtocoloRadiacion(String codigo, String registro, String instrucciones, String limites,
                               Mitigacion mitigacion, Sensor sensor, Radiacion radiacion) {
        super(registro, instrucciones, limites, mitigacion, sensor, radiacion);
        this.codigo = codigo;
    }

    // SOBREESCRITURA del método controlar_limites (punto 3)
    @Override
    public String controlar_limites(double cantidad_radiacion) {
        System.out.println("[ProtocoloRadiacion - SOBREESCRITURA] Controlando límites de RADIACIÓN para "
                + cantidad_radiacion + " sieverts.");
        if (cantidad_radiacion > 1.0) {
            return "PELIGRO: Radiación " + cantidad_radiacion + " supera límite permitido. Código: " + codigo;
        }
        return "Radiación " + cantidad_radiacion + " dentro del rango. Código: " + codigo;
    }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    @Override
    public String toString() {
        return "ProtocoloRadiacion{codigo='" + codigo + "'}";
    }
}
