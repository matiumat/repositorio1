package co.edu.poli.contexto4.model;

public class ProtocoloInsuficiencia extends Protocolo {
    private String codigo;

    public ProtocoloInsuficiencia() {}

    public ProtocoloInsuficiencia(String codigo, String registro, String instrucciones, String limites,
                                   Mitigacion mitigacion, Sensor sensor, Radiacion radiacion) {
        super(registro, instrucciones, limites, mitigacion, sensor, radiacion);
        this.codigo = codigo;
    }

    // SOBREESCRITURA del método controlar_limites (punto 3)
    @Override
    public String controlar_limites(double cantidad_radiacion) {
        System.out.println("[ProtocoloInsuficiencia - SOBREESCRITURA] Controlando límites de INSUFICIENCIA para "
                + cantidad_radiacion + " sieverts.");
        if (cantidad_radiacion < 0.1) {
            return "INSUFICIENCIA DETECTADA: Radiación " + cantidad_radiacion + " muy baja. Código: " + codigo;
        }
        return "Radiación " + cantidad_radiacion + " aceptable para protocolo de insuficiencia. Código: " + codigo;
    }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    @Override
    public String toString() {
        return "ProtocoloInsuficiencia{codigo='" + codigo + "'}";
    }
}
