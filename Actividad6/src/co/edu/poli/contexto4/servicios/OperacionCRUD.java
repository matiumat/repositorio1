package co.edu.poli.contexto4.servicios;

import co.edu.poli.contexto4.model.Protocolo;

/**
 * Interfaz que define las operaciones CRUD (Crear, Leer, Modificar, Eliminar)
 * sobre un arreglo de objetos de tipo {@link Protocolo} (supersuperclase).
 * <p>
 * Todos los métodos declaran {@code throws ProtocoloException}, lo que obliga
 * a los métodos que los invocan a manejar los errores con {@code try-catch}.
 * Los errores ya no se retornan como Strings sino que se propagan como excepciones.
 * </p>
 *
 * @author Equipo Contexto 4
 * @version 2.0
 * @see ImplementacionOperacionCRUD
 * @see ProtocoloException
 */
public interface OperacionCRUD {

    /**
     * Crea e inserta un nuevo protocolo en el arreglo.
     * Inserta en el primer espacio {@code null} de izquierda a derecha.
     * Si el arreglo está lleno, su tamaño se duplica antes de insertar.
     *
     * @param protocolo Objeto {@link Protocolo} a insertar.
     * @return Mensaje OK con la posición de inserción.
     * @throws ProtocoloException Si el protocolo es null, el numero_id es inválido,
     *                            o ya existe un protocolo con el mismo numero_id.
     */
    String crear(Protocolo protocolo) throws ProtocoloException;

    /**
     * Lee y retorna el protocolo ubicado en la posición indicada.
     *
     * @param indice Posición del arreglo a consultar.
     * @return El objeto {@link Protocolo} en esa posición.
     * @throws ProtocoloException Si el índice está fuera de rango o la posición está vacía.
     */
    Protocolo leer(int indice) throws ProtocoloException;

    /**
     * Retorna el arreglo completo de protocolos, incluyendo posiciones {@code null}.
     *
     * @return Arreglo de tipo {@link Protocolo} con todos los elementos actuales.
     * @throws ProtocoloException Si el arreglo está completamente vacío.
     */
    Protocolo[] leerTodos() throws ProtocoloException;

    /**
     * Modifica el protocolo en la posición indicada reemplazándolo con el nuevo objeto.
     *
     * @param indice    Posición del arreglo a modificar.
     * @param protocolo Nuevo objeto {@link Protocolo} con el que se reemplaza.
     * @return Mensaje OK confirmando el reemplazo.
     * @throws ProtocoloException Si el índice es inválido, la posición está vacía,
     *                            o el protocolo de reemplazo es null o tiene numero_id inválido.
     */
    String modificar(int indice, Protocolo protocolo) throws ProtocoloException;

    /**
     * Elimina el protocolo en la posición indicada dejándola como {@code null}.
     *
     * @param indice Posición del arreglo a eliminar.
     * @return Mensaje OK confirmando la eliminación.
     * @throws ProtocoloException Si el índice está fuera de rango o la posición ya está vacía.
     */
    String eliminar(int indice) throws ProtocoloException;
}