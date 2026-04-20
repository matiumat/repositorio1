package co.edu.poli.contexto4.servicios;

/**
 * Excepción personalizada del sistema de protocolos espaciales.
 * <p>
 * Se lanza cuando ocurre un error de validación en las operaciones CRUD:
 * protocolo nulo, numero_id inválido, índice fuera de rango,
 * posición vacía, o numero_id duplicado.
 * </p>
 * <p>
 * Al ser una <strong>checked exception</strong> (extiende {@link Exception}),
 * obliga a los métodos que la lanzan a declararla con {@code throws},
 * y a los métodos que los invocan a manejarla con {@code try-catch}.
 * </p>
 *
 * @author Equipo Contexto 4
 * @version 1.0
 */
public class ProtocoloException extends Exception {

    /**
     * Crea una nueva ProtocoloException con el mensaje de error indicado.
     *
     * @param mensaje Descripción del error ocurrido.
     */
    public ProtocoloException(String mensaje) {
        super(mensaje);
    }
}