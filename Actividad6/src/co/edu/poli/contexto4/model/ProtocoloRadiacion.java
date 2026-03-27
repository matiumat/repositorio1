package co.edu.poli.contexto4.model;

public class ProtocoloRadiacion extends Protocolo {

    public ProtocoloRadiacion() {}

    public ProtocoloRadiacion(String codigo, String registro, String instrucciones, String limites,
                               Mitigacion mitigacion, Sensor sensor, Radiacion radiacion) {
        super(codigo, registro, instrucciones, limites, mitigacion, sensor, radiacion);
    }

    // Implementación obligatoria del método ABSTRACTO de Protocolo
    @Override
    public String leer_informacion() {
        return "ProtocoloRadiacion | Codigo: " + getCodigo()
                + " | Instrucciones: " + getInstrucciones()
                + " | Limites: " + getLimites();
    }

    // SOBREESCRITURA de controlar_limites
    @Override
    public String controlar_limites(double cantidad_radiacion) {
        System.out.println("[ProtocoloRadiacion - SOBREESCRITURA] Controlando limites para "
                + cantidad_radiacion + " sieverts.");
        if (cantidad_radiacion > 1.0) {
            return "PELIGRO: Radiacion " + cantidad_radiacion
                    + " supera limite permitido. Codigo: " + getCodigo();
        }
        return "Radiacion " + cantidad_radiacion
                + " dentro del rango. Codigo: " + getCodigo();
    }

    @Override
    public String toString() {
        return "ProtocoloRadiacion{codigo='" + getCodigo()
                + "', instrucciones='" + getInstrucciones() + "'}";
    }
}
