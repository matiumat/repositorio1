package co.edu.poli.contexto4.model;

public class ProtocoloInsuficiencia extends Protocolo {

    public ProtocoloInsuficiencia() {}

    public ProtocoloInsuficiencia(String codigo, String registro, String instrucciones, String limites,
                                   Mitigacion mitigacion, Sensor sensor, Radiacion radiacion) {
        super(codigo, registro, instrucciones, limites, mitigacion, sensor, radiacion);
    }

    // Implementación obligatoria del método ABSTRACTO de Protocolo
    @Override
    public String leer_informacion() {
        return "ProtocoloInsuficiencia | Codigo: " + getCodigo()
                + " | Instrucciones: " + getInstrucciones()
                + " | Limites: " + getLimites();
    }

    // SOBREESCRITURA de controlar_limites
    @Override
    public String controlar_limites(double cantidad_radiacion) {
        System.out.println("[ProtocoloInsuficiencia - SOBREESCRITURA] Controlando limites para "
                + cantidad_radiacion + " sieverts.");
        if (cantidad_radiacion < 0.1) {
            return "INSUFICIENCIA DETECTADA: Radiacion " + cantidad_radiacion
                    + " muy baja. Codigo: " + getCodigo();
        }
        return "Radiacion " + cantidad_radiacion
                + " aceptable para protocolo insuficiencia. Codigo: " + getCodigo();
    }

    @Override
    public String toString() {
        return "ProtocoloInsuficiencia{codigo='" + getCodigo()
                + "', instrucciones='" + getInstrucciones() + "'}";
    }
}
