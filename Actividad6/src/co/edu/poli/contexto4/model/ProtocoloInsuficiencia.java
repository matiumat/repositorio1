package co.edu.poli.contexto4.model;

/**
 * Subclase concreta de {@link Protocolo} que representa el protocolo
 * de manejo de <strong>insuficiencia de radiación</strong>.
 * Se activa cuando los niveles de radiación están por debajo del mínimo
 * aceptable para la misión.
 * <p>
 * Sobreescribe {@link #controlar_limites(double)} e implementa el método
 * abstracto {@link #leer_informacion()} heredado de {@link Protocolo}.
 * </p>
 *
 * @author Mateo Paredes
 * @since 06/04/2026
 * @see Protocolo
 */
public class ProtocoloInsuficiencia extends Protocolo {

    /**
     * Constructor vacío. Crea un ProtocoloInsuficiencia sin inicializar atributos.
     */
    public ProtocoloInsuficiencia() {}

    /**
     * Constructor con parámetros. Inicializa el protocolo de insuficiencia
     * delegando al constructor de la superclase {@link Protocolo}.
     *
     * @param codigo        Código único del protocolo (ID para el CRUD).
     * @param registro      Registro asociado al protocolo.
     * @param instrucciones Instrucciones del procedimiento de insuficiencia.
     * @param limites       Límites mínimos de radiación configurados.
     * @param mitigacion    Plan de mitigación asociado.
     * @param sensor        Sensor utilizado en el protocolo.
     * @param radiacion     Radiación monitoreada por este protocolo.
     */
    public ProtocoloInsuficiencia(String codigo, String registro, String instrucciones,
                                   String limites, Mitigacion mitigacion,
                                   Sensor sensor, Radiacion radiacion) {
        super(codigo, registro, instrucciones, limites, mitigacion, sensor, radiacion);
    }

    /**
     * Implementación del método abstracto de {@link Protocolo}.
     * Retorna información descriptiva específica del protocolo de insuficiencia.
     *
     * @return Cadena con el código, instrucciones y límites del protocolo de insuficiencia.
     */
    @Override
    public String leer_informacion() {
        return "ProtocoloInsuficiencia | Codigo: " + getCodigo()
                + " | Instrucciones: " + getInstrucciones()
                + " | Limites: " + getLimites();
    }

    /**
     * Sobreescritura del método {@link Protocolo#controlar_limites(double)}.
     * Evalúa si la cantidad de radiación está por debajo del mínimo aceptable
     * y emite una alerta de insuficiencia si es necesario.
     *
     * @param cantidad_radiacion Cantidad de radiación detectada en sieverts.
     * @return Mensaje con el resultado del control de insuficiencia.
     */
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

    /**
     * Retorna una representación textual del protocolo de insuficiencia.
     *
     * @return Cadena con código e instrucciones.
     */
    @Override
    public String toString() {
        return "ProtocoloInsuficiencia{codigo='" + getCodigo()
                + "', instrucciones='" + getInstrucciones() + "'}";
    }
}