package co.edu.poli.contexto4.model;

/**
 * Subclase concreta de {@link Protocolo} que representa el protocolo
 * de manejo de <strong>exceso de radiación</strong>.
 * Se activa cuando los niveles de radiación superan el límite máximo
 * permitido para la misión.
 * <p>
 * Sobreescribe {@link #controlar_limites(double)} e implementa el método
 * abstracto {@link #leer_informacion()} heredado de {@link Protocolo}.
 * </p>
 *
 * @author Mateo Paredes
 * @since 06/04/2026
 * @see Protocolo
 */
public class ProtocoloRadiacion extends Protocolo {

    /**
     * Constructor vacío. Crea un ProtocoloRadiacion sin inicializar atributos.
     */
    public ProtocoloRadiacion() {}

    /**
     * Constructor con parámetros. Inicializa el protocolo de radiación
     * delegando al constructor de la superclase {@link Protocolo}.
     *
     * @param codigo        Código único del protocolo (ID para el CRUD).
     * @param registro      Registro asociado al protocolo.
     * @param instrucciones Instrucciones del procedimiento de control de radiación.
     * @param limites       Límites máximos de radiación configurados.
     * @param mitigacion    Plan de mitigación asociado.
     * @param sensor        Sensor utilizado en el protocolo.
     * @param radiacion     Radiación monitoreada por este protocolo.
     */
    public ProtocoloRadiacion(String codigo, String registro, String instrucciones,
                               String limites, Mitigacion mitigacion,
                               Sensor sensor, Radiacion radiacion) {
        super(codigo, registro, instrucciones, limites, mitigacion, sensor, radiacion);
    }

    /**
     * Implementación del método abstracto de {@link Protocolo}.
     * Retorna información descriptiva específica del protocolo de radiación.
     *
     * @return Cadena con el código, instrucciones y límites del protocolo de radiación.
     */
    @Override
    public String leer_informacion() {
        return "ProtocoloRadiacion | Codigo: " + getCodigo()
                + " | Instrucciones: " + getInstrucciones()
                + " | Limites: " + getLimites();
    }

    /**
     * Sobreescritura del método {@link Protocolo#controlar_limites(double)}.
     * Evalúa si la cantidad de radiación supera el límite máximo permitido
     * y emite una alerta de peligro si es necesario.
     *
     * @param cantidad_radiacion Cantidad de radiación detectada en sieverts.
     * @return Mensaje con el resultado del control de exceso de radiación.
     */
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

    /**
     * Retorna una representación textual del protocolo de radiación.
     *
     * @return Cadena con código e instrucciones.
     */
    @Override
    public String toString() {
        return "ProtocoloRadiacion{codigo='" + getCodigo()
                + "', instrucciones='" + getInstrucciones() + "'}";
    }
}