package co.edu.poli.contexto4.servicios;

import co.edu.poli.contexto4.model.Protocolo;

/**
 * Interfaz que define las operaciones CRUD (Crear, Leer, Modificar, Eliminar)
 * sobre un arreglo de objetos de tipo {@link Protocolo} (supersuperclase).
 * <p>
 * La clase {@link ImplementacionOperacionCRUD} implementa esta interfaz
 * y proporciona la lógica concreta de cada operación, incluyendo validaciones
 * y crecimiento dinámico del arreglo.
 * </p>
 *
 * @author Mateo Paredes
 * @since 06/04/2026
 * @see ImplementacionOperacionCRUD
 */
public interface OperacionCRUD {

    /**
     * Crea e inserta un nuevo protocolo en el arreglo.
     * La inserción se realiza en el primer espacio {@code null} encontrado
     * de izquierda a derecha. Si el arreglo está lleno, su tamaño se duplica
     * antes de insertar, permitiendo un crecimiento infinito.
     *
     * @param protocolo Objeto {@link Protocolo} a insertar. No puede ser null.
     * @return Mensaje indicando el resultado de la operación (OK o ERROR).
     */
    String crear(Protocolo protocolo);

    /**
     * Lee y retorna el protocolo ubicado en la posición indicada del arreglo.
     *
     * @param indice Posición del arreglo a consultar (debe estar dentro del rango).
     * @return El objeto {@link Protocolo} en esa posición, o {@code null} si no existe
     *         o el índice es inválido.
     */
    Protocolo leer(int indice);

    /**
     * Retorna el arreglo completo de protocolos, incluyendo posiciones {@code null}.
     *
     * @return Arreglo de tipo {@link Protocolo} con todos los elementos actuales.
     */
    Protocolo[] leerTodos();

    /**
     * Modifica el protocolo ubicado en la posición indicada, reemplazándolo
     * con el nuevo objeto proporcionado.
     *
     * @param indice   Posición del arreglo a modificar.
     * @param protocolo Nuevo objeto {@link Protocolo} con el que se reemplaza.
     * @return Mensaje indicando el resultado de la operación (OK o ERROR).
     */
    String modificar(int indice, Protocolo protocolo);

    /**
     * Elimina el protocolo ubicado en la posición indicada,
     * dejando esa posición como {@code null} para reutilización futura.
     *
     * @param indice Posición del arreglo a eliminar.
     * @return Mensaje indicando el resultado de la operación (OK o ERROR).
     */
    String eliminar(int indice);
}