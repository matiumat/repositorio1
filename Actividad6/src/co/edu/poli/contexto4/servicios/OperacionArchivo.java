package co.edu.poli.contexto4.servicios;

import co.edu.poli.contexto4.model.Protocolo;

/**
 * Interfaz que define las operaciones de persistencia en archivo plano
 * para objetos de tipo {@link Protocolo}.
 * <p>
 * Define dos operaciones principales:
 * <ul>
 *   <li>{@link #serializar()}: guarda el arreglo de protocolos en un archivo {@code .txt}.</li>
 *   <li>{@link #deserializar()}: carga y reconstruye los protocolos desde el archivo {@code .txt}.</li>
 * </ul>
 * La clase {@link ImplementacionOperacionCRUD} implementa esta interfaz
 * junto con {@link OperacionCRUD}.
 * </p>
 *
 * @author Mateo Paredes
 * @since 06/04/2026
 * @see ImplementacionOperacionCRUD
 * @see OperacionCRUD
 */
public interface OperacionArchivo {

    /**
     * Serializa el arreglo de protocolos escribiendo cada objeto no-null
     * en una línea del archivo {@code protocolos.txt}.
     * <p>
     * Formato de cada línea: {@code TIPO;codigo;registro;instrucciones;limites}<br>
     * donde {@code TIPO} es {@code INS} para {@code ProtocoloInsuficiencia}
     * o {@code RAD} para {@code ProtocoloRadiacion}.
     * </p>
     *
     * @return Mensaje indicando el resultado de la operación (OK o ERROR).
     */
    String serializar();

    /**
     * Deserializa el archivo {@code protocolos.txt} y reconstruye un arreglo
     * de objetos {@link Protocolo} con base en la información almacenada.
     * <p>
     * Lee línea por línea e identifica el tipo de subclase a instanciar
     * ({@code ProtocoloInsuficiencia} o {@code ProtocoloRadiacion}) según
     * el prefijo {@code TIPO} de cada línea.
     * </p>
     *
     * @return Arreglo de {@link Protocolo} reconstruido desde el archivo.
     *         Retorna un arreglo vacío si el archivo no existe o está vacío.
     */
    Protocolo[] deserializar();
}