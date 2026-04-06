package co.edu.poli.contexto4.model;

/**
 * Clase de constantes del sistema de monitoreo espacial.
 * Esta clase es {@code final}, lo que significa que <strong>no puede ser heredada</strong>
 * por ninguna otra clase.
 * <p>
 * Agrupa atributos {@code final} (constantes inmutables) y un método {@code final}
 * que no puede ser sobreescrito, garantizando la integridad de los datos del sistema.
 * </p>
 *
 * @author Mateo Paredes
 * @since 06/04/2026
 */
public final class DatosConstantes {

    /**
     * Límite máximo permitido de radiación en sieverts (Sv).
     * Atributo {@code final}: su valor no puede cambiar en tiempo de ejecución.
     */
    public static final double LIMITE_MAXIMO_RADIACION = 1.0;

    /**
     * Nombre oficial del sistema de monitoreo.
     * Atributo {@code final}: valor fijo definido en tiempo de compilación.
     */
    public static final String NOMBRE_SISTEMA = "Sistema de Monitoreo Espacial v1.0";

    /**
     * Retorna la información general del sistema incluyendo su nombre y el
     * límite máximo de radiación configurado.
     * <p>
     * Este método es {@code final}: aunque la clase ya es final, se marca
     * explícitamente para evidenciar que no puede sobreescribirse.
     * </p>
     *
     * @return Cadena con el nombre del sistema y el límite máximo de radiación.
     */
    public final String obtener_info_sistema() {
        return "Sistema: " + NOMBRE_SISTEMA
                + " | Limite maximo radiacion: " + LIMITE_MAXIMO_RADIACION + " Sv";
    }

    /**
     * Retorna una representación textual de las constantes del sistema.
     *
     * @return Cadena con LIMITE_MAXIMO_RADIACION y NOMBRE_SISTEMA.
     */
    @Override
    public String toString() {
        return "DatosConstantes{LIMITE_MAXIMO_RADIACION=" + LIMITE_MAXIMO_RADIACION
                + ", NOMBRE_SISTEMA='" + NOMBRE_SISTEMA + "'}";
    }
}