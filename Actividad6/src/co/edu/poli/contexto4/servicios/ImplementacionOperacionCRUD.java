package co.edu.poli.contexto4.servicios;

import co.edu.poli.contexto4.model.Protocolo;
import co.edu.poli.contexto4.model.ProtocoloInsuficiencia;
import co.edu.poli.contexto4.model.ProtocoloRadiacion;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Implementación concreta de las interfaces {@link OperacionCRUD} y {@link OperacionArchivo}.
 * <p>
 * Gestiona un arreglo dinámico de objetos de tipo {@link Protocolo} (supersuperclase).
 * El arreglo inicia con tamaño 2 y se duplica automáticamente cuando se llena,
 * permitiendo almacenamiento infinito de protocolos.
 * </p>
 * <p>
 * Adicionalmente, implementa serialización y deserialización de los datos
 * en un archivo de texto plano {@code protocolos.txt}, con validaciones
 * completas en cada operación.
 * </p>
 *
* @author Mateo Paredes
 * @since 06/04/2026
 * @see OperacionCRUD
 * @see OperacionArchivo
 * @see Protocolo
 */
public class ImplementacionOperacionCRUD implements OperacionCRUD, OperacionArchivo {

    public void setArreglo_protocolos(Protocolo[] arreglo_protocolos) {
		this.arreglo_protocolos = arreglo_protocolos;
	}

	/** Nombre del archivo donde se persisten los protocolos. */
    private static final String NOMBRE_ARCHIVO = "protocolos.txt";

    /** Separador de campos dentro de cada línea del archivo. */
    private static final String SEP = ";";

    /**
     * Arreglo de tipo supersuperclase {@link Protocolo}.
     * Inicia con tamaño 2 según el diagrama UML.
     * Se duplica automáticamente al llenarse.
     */
    private Protocolo[] arreglo_protocolos;

    /**
     * Constructor por defecto. Inicializa el arreglo con tamaño 2.
     */
    public ImplementacionOperacionCRUD() {
        arreglo_protocolos = new Protocolo[2];
    }

    // =======================================================================
    // OPERACIONES CRUD
    // =======================================================================

    /**
     * {@inheritDoc}
     * <p>
     * Inserta en el primer espacio {@code null} de izquierda a derecha.
     * Si el arreglo está lleno, su tamaño se duplica antes de insertar.
     * </p>
     * <p><strong>Validaciones:</strong></p>
     * <ul>
     *   <li>El protocolo no puede ser {@code null}.</li>
     *   <li>El atributo {@code codigo} no puede ser nulo ni vacío.</li>
     *   <li>No se permiten códigos duplicados en el arreglo.</li>
     * </ul>
     *
     * @param protocolo Protocolo a insertar.
     * @return Mensaje OK con la posición de inserción, o mensaje ERROR con la causa.
     */
    @Override
    public String crear(Protocolo protocolo) {
        if (protocolo == null) {
            return "ERROR [CREAR]: No se puede insertar un protocolo null.";
        }
        if (protocolo.getCodigo() == null || protocolo.getCodigo().trim().isEmpty()) {
            return "ERROR [CREAR]: El protocolo debe tener un codigo valido.";
        }
        for (Protocolo p : arreglo_protocolos) {
            if (p != null && p.getCodigo().equals(protocolo.getCodigo())) {
                return "ERROR [CREAR]: Ya existe un protocolo con codigo '"
                        + protocolo.getCodigo() + "'.";
            }
        }

        // Buscar primer null de izquierda a derecha
        for (int i = 0; i < arreglo_protocolos.length; i++) {
            if (arreglo_protocolos[i] == null) {
                arreglo_protocolos[i] = protocolo;
                return "OK [CREAR]: Protocolo '" + protocolo.getCodigo()
                        + "' insertado en posicion [" + i + "].";
            }
        }

        // Arreglo lleno → duplicar tamaño
        Protocolo[] nuevo = new Protocolo[arreglo_protocolos.length * 2];
        for (int i = 0; i < arreglo_protocolos.length; i++) {
            nuevo[i] = arreglo_protocolos[i];
        }
        arreglo_protocolos = nuevo;

        for (int i = 0; i < arreglo_protocolos.length; i++) {
            if (arreglo_protocolos[i] == null) {
                arreglo_protocolos[i] = protocolo;
                return "OK [CREAR]: Arreglo lleno, se duplico a tamano "
                        + arreglo_protocolos.length
                        + ". Protocolo '" + protocolo.getCodigo()
                        + "' insertado en posicion [" + i + "].";
            }
        }
        return "ERROR [CREAR]: No se pudo insertar el protocolo.";
    }

    /**
     * {@inheritDoc}
     * <p><strong>Validaciones:</strong></p>
     * <ul>
     *   <li>El índice debe estar dentro del rango del arreglo.</li>
     *   <li>La posición no debe estar vacía ({@code null}).</li>
     * </ul>
     *
     * @param indice Posición a consultar.
     * @return El {@link Protocolo} encontrado, o {@code null} si hay error.
     */
    @Override
    public Protocolo leer(int indice) {
        if (indice < 0 || indice >= arreglo_protocolos.length) {
            System.out.println("ERROR [LEER]: Indice " + indice
                    + " fuera de rango. Tamano actual: " + arreglo_protocolos.length);
            return null;
        }
        if (arreglo_protocolos[indice] == null) {
            System.out.println("ERROR [LEER]: No hay protocolo en la posicion [" + indice + "].");
            return null;
        }
        return arreglo_protocolos[indice];
    }

    /**
     * {@inheritDoc}
     * <p>
     * Si el arreglo está completamente vacío, imprime un mensaje informativo.
     * </p>
     *
     * @return Arreglo completo de {@link Protocolo}, incluyendo posiciones {@code null}.
     */
    @Override
    public Protocolo[] leerTodos() {
        boolean hayDatos = false;
        for (Protocolo p : arreglo_protocolos) {
            if (p != null) { hayDatos = true; break; }
        }
        if (!hayDatos) {
            System.out.println("INFO [LEER TODOS]: El arreglo esta vacio.");
        }
        return arreglo_protocolos;
    }

    /**
     * {@inheritDoc}
     * <p><strong>Validaciones:</strong></p>
     * <ul>
     *   <li>El índice debe estar dentro del rango del arreglo.</li>
     *   <li>La posición debe contener un objeto (no puede ser {@code null}).</li>
     *   <li>El protocolo de reemplazo no puede ser {@code null}.</li>
     *   <li>El código del protocolo de reemplazo no puede ser nulo ni vacío.</li>
     * </ul>
     *
     * @param indice    Posición del arreglo a modificar.
     * @param protocolo Nuevo protocolo con el que se reemplaza.
     * @return Mensaje OK confirmando el reemplazo, o mensaje ERROR con la causa.
     */
    @Override
    public String modificar(int indice, Protocolo protocolo) {
        if (indice < 0 || indice >= arreglo_protocolos.length) {
            return "ERROR [MODIFICAR]: Indice " + indice
                    + " fuera de rango. Tamano actual: " + arreglo_protocolos.length;
        }
        if (arreglo_protocolos[indice] == null) {
            return "ERROR [MODIFICAR]: No existe protocolo en la posicion ["
                    + indice + "]. Use CREAR para insertar.";
        }
        if (protocolo == null) {
            return "ERROR [MODIFICAR]: El protocolo de reemplazo no puede ser null.";
        }
        if (protocolo.getCodigo() == null || protocolo.getCodigo().trim().isEmpty()) {
            return "ERROR [MODIFICAR]: El protocolo de reemplazo debe tener un codigo valido.";
        }
        String codigoAnterior = arreglo_protocolos[indice].getCodigo();
        arreglo_protocolos[indice] = protocolo;
        return "OK [MODIFICAR]: Posicion [" + indice + "] actualizada. '"
                + codigoAnterior + "' -> '" + protocolo.getCodigo() + "'.";
    }

    /**
     * {@inheritDoc}
     * <p>
     * La posición eliminada queda como {@code null} y puede ser reutilizada
     * por una operación {@link #crear(Protocolo)} posterior.
     * </p>
     * <p><strong>Validaciones:</strong></p>
     * <ul>
     *   <li>El índice debe estar dentro del rango del arreglo.</li>
     *   <li>La posición no debe estar ya vacía ({@code null}).</li>
     * </ul>
     *
     * @param indice Posición del arreglo a eliminar.
     * @return Mensaje OK confirmando la eliminación, o mensaje ERROR con la causa.
     */
    @Override
    public String eliminar(int indice) {
        if (indice < 0 || indice >= arreglo_protocolos.length) {
            return "ERROR [ELIMINAR]: Indice " + indice
                    + " fuera de rango. Tamano actual: " + arreglo_protocolos.length;
        }
        if (arreglo_protocolos[indice] == null) {
            return "ERROR [ELIMINAR]: No existe protocolo en la posicion ["
                    + indice + "]. Ya esta vacia.";
        }
        String codigoEliminado = arreglo_protocolos[indice].getCodigo();
        arreglo_protocolos[indice] = null;
        return "OK [ELIMINAR]: Protocolo '" + codigoEliminado
                + "' eliminado de la posicion [" + indice + "].";
    }

    // =======================================================================
    // OPERACIONES DE ARCHIVO
    // =======================================================================

    /**
     * {@inheritDoc}
     * <p>
     * Escribe cada protocolo no-null en una línea del archivo {@code protocolos.txt}
     * con el siguiente formato:
     * </p>
     * <pre>
     *   TIPO;codigo;registro;instrucciones;limites
     * </pre>
     * <p>
     * Donde {@code TIPO} es {@code INS} para {@link ProtocoloInsuficiencia}
     * o {@code RAD} para {@link ProtocoloRadiacion}.
     * </p>
     * <p><strong>Validaciones:</strong></p>
     * <ul>
     *   <li>El arreglo no puede estar completamente vacío.</li>
     *   <li>Si ocurre un error de I/O, retorna un mensaje ERROR detallado.</li>
     * </ul>
     *
     * @return Mensaje OK con la cantidad de protocolos guardados, o ERROR con la causa.
     */
    @Override
    public String serializar() {
        boolean hayDatos = false;
        for (Protocolo p : arreglo_protocolos) {
            if (p != null) { hayDatos = true; break; }
        }
        if (!hayDatos) {
            return "ERROR [SERIALIZAR]: El arreglo esta vacio. No hay datos para guardar.";
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(NOMBRE_ARCHIVO))) {
            int escritos = 0;
            for (Protocolo p : arreglo_protocolos) {
                if (p != null) {
                    String tipo         = (p instanceof ProtocoloInsuficiencia) ? "INS" : "RAD";
                    String codigo       = p.getCodigo()        != null ? p.getCodigo()        : "";
                    String registro     = p.getRegistro()      != null ? p.getRegistro()      : "";
                    String instrucciones= p.getInstrucciones() != null ? p.getInstrucciones() : "";
                    String limites      = p.getLimites()       != null ? p.getLimites()       : "";

                    bw.write(tipo + SEP + codigo + SEP + registro + SEP + instrucciones + SEP + limites);
                    bw.newLine();
                    escritos++;
                }
            }
            return "OK [SERIALIZAR]: " + escritos + " protocolo(s) guardados en '"
                    + NOMBRE_ARCHIVO + "'.";
        } catch (IOException e) {
            return "ERROR [SERIALIZAR]: No se pudo escribir el archivo. " + e.getMessage();
        }
    }

    /**
     * {@inheritDoc}
     * <p>
     * Lee el archivo {@code protocolos.txt} línea por línea y reconstruye
     * los objetos {@link Protocolo} según el tipo indicado en cada línea
     * ({@code INS} → {@link ProtocoloInsuficiencia}, {@code RAD} → {@link ProtocoloRadiacion}).
     * </p>
     * <p><strong>Validaciones:</strong></p>
     * <ul>
     *   <li>Si el archivo no existe o no puede leerse, retorna arreglo vacío.</li>
     *   <li>Si el archivo está vacío, informa y retorna arreglo vacío.</li>
     *   <li>Las líneas con formato incorrecto (menos de 5 campos) son omitidas con aviso.</li>
     *   <li>Los tipos desconocidos son omitidos con aviso.</li>
     * </ul>
     *
     * @return Arreglo de {@link Protocolo} reconstruido, o arreglo vacío si hay error.
     */
    @Override
    public Protocolo[] deserializar() {
        int totalLineas = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(NOMBRE_ARCHIVO))) {
            while (br.readLine() != null) totalLineas++;
        } catch (IOException e) {
            System.out.println("ERROR [DESERIALIZAR]: No se pudo leer el archivo '"
                    + NOMBRE_ARCHIVO + "'. " + e.getMessage());
            return new Protocolo[0];
        }

        if (totalLineas == 0) {
            System.out.println("ERROR [DESERIALIZAR]: El archivo '"
                    + NOMBRE_ARCHIVO + "' esta vacio.");
            return new Protocolo[0];
        }

        Protocolo[] resultado = new Protocolo[totalLineas];
        int idx = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(NOMBRE_ARCHIVO))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(SEP, -1);

                if (partes.length < 5) {
                    System.out.println("AVISO [DESERIALIZAR]: Linea con formato incorrecto, se omite: ["
                            + linea + "]");
                    continue;
                }

                String tipo          = partes[0];
                String codigo        = partes[1];
                String registro      = partes[2];
                String instrucciones = partes[3];
                String limites       = partes[4];

                if (tipo.equals("INS")) {
                    resultado[idx] = new ProtocoloInsuficiencia(
                            codigo, registro, instrucciones, limites, null, null, null);
                } else if (tipo.equals("RAD")) {
                    resultado[idx] = new ProtocoloRadiacion(
                            codigo, registro, instrucciones, limites, null, null, null);
                } else {
                    System.out.println("AVISO [DESERIALIZAR]: Tipo desconocido '"
                            + tipo + "', se omite.");
                    continue;
                }
                idx++;
            }
        } catch (IOException e) {
            System.out.println("ERROR [DESERIALIZAR]: Fallo durante la lectura. " + e.getMessage());
            return new Protocolo[0];
        }

        System.out.println("OK [DESERIALIZAR]: " + idx + " protocolo(s) cargados desde '"
                + NOMBRE_ARCHIVO + "'.");
        return resultado;
    }

    // =======================================================================
    // MÉTODOS UTILITARIOS
    // =======================================================================

    /**
     * Busca el índice de un protocolo en el arreglo según su código (ID).
     * Recorre el arreglo de izquierda a derecha y retorna la primera coincidencia.
     *
     * @param codigo Código del protocolo a buscar. No puede ser nulo ni vacío.
     * @return Índice de la posición del protocolo en el arreglo,
     *         o {@code -1} si no se encuentra o el código es inválido.
     */
    public int buscarIndicePorCodigo(String codigo) {
        if (codigo == null || codigo.trim().isEmpty()) return -1;
        for (int i = 0; i < arreglo_protocolos.length; i++) {
            if (arreglo_protocolos[i] != null
                    && arreglo_protocolos[i].getCodigo().equals(codigo)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Retorna el arreglo completo de protocolos para uso externo (ej: imprimir estado).
     *
     * @return Arreglo actual de {@link Protocolo}.
     */
    public Protocolo[] getArreglo_protocolos() { return arreglo_protocolos; }

    /**
     * Retorna el tamaño actual del arreglo de protocolos.
     *
     * @return Tamaño del arreglo (incluyendo posiciones vacías).
     */
    public int getTamano() { return arreglo_protocolos.length; }
}