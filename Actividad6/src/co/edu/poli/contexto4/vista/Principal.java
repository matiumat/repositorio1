package co.edu.poli.contexto4.vista;

import co.edu.poli.contexto4.model.*;
import co.edu.poli.contexto4.servicios.ImplementacionOperacionCRUD;

import java.util.Scanner;

/**
 * Clase principal del sistema de monitoreo astronautas.
 * Presenta un menú interactivo por consola para gestionar protocolos
 * mediante operaciones CRUD y persistencia en archivo plano.
 *
 * @author Equipo Contexto 4
 * @version 1.0
 */
public class Principal {

    // ---------------------------------------------------------------
    // Objetos base reutilizados en la creación de protocolos
    // ---------------------------------------------------------------
    private static final Sensor SENSOR_1 =
            new Sensor("SEN-001", "Titanio",  "Blanco", 5.5, 0.3);
    private static final Sensor SENSOR_2 =
            new Sensor("SEN-002", "Aluminio", "Gris",   4.0, 0.2);
    private static final Radiacion RAD_ALTA =
            new Radiacion(2.5,  "ALTO", "Gamma", "Alta",  "Solar");
    private static final Radiacion RAD_BAJA =
            new Radiacion(0.05, "BAJO", "Alpha", "Baja",  "Cosmica");
    private static final Mitigacion MIT_1 =
            new Mitigacion("MIT-001", "Blindaje reforzado", 1, "Modulo A", RAD_ALTA);

    // ---------------------------------------------------------------
    // Métodos con polimorfismo (puntos anteriores)
    // ---------------------------------------------------------------

    /**
     * Método polimórfico que recibe un parámetro de tipo supersuperclase Protocolo.
     *
     * @param protocolo Objeto de tipo Protocolo (o cualquier subclase).
     * @param radiacion Cantidad de radiación a evaluar.
     */
    public static void metodo_polimorfismo_param(Protocolo protocolo, double radiacion) {
        System.out.println("  [Polimorfismo - param] Clase: "
                + protocolo.getClass().getSimpleName());
        System.out.println("  [Polimorfismo - param] "
                + protocolo.controlar_limites(radiacion));
    }

    /**
     * Método polimórfico que retorna un objeto de tipo supersuperclase Protocolo.
     *
     * @param tipo       "insuficiencia" o cualquier otro valor para radiación.
     * @param mitigacion Mitigación asociada.
     * @param sensor     Sensor asociado.
     * @param radiacion  Radiación asociada.
     * @return Objeto Protocolo (ProtocoloInsuficiencia o ProtocoloRadiacion).
     */
    public static Protocolo metodo_polimorfismo_retorno(String tipo, Mitigacion mitigacion,
                                                         Sensor sensor, Radiacion radiacion) {
        if (tipo.equalsIgnoreCase("insuficiencia")) {
            return new ProtocoloInsuficiencia("PINS-RET", "REG-RET",
                    "Control insuficiencia", "0.1 Sv", mitigacion, sensor, radiacion);
        } else {
            return new ProtocoloRadiacion("PRAD-RET", "REG-RET",
                    "Control radiacion", "1.0 Sv", mitigacion, sensor, radiacion);
        }
    }

    // ---------------------------------------------------------------
    // Utilitarios de impresión
    // ---------------------------------------------------------------

    /** Imprime una línea separadora en consola. */
    private static void separador() {
        System.out.println("-------------------------------------------------------------");
    }

    /** Imprime una línea separadora gruesa en consola. */
    private static void separadorGrueso() {
        System.out.println("=============================================================");
    }

    /**
     * Imprime el estado completo del arreglo del CRUD mostrando
     * el tipo, código e información de cada protocolo.
     *
     * @param crud Instancia de ImplementacionOperacionCRUD a imprimir.
     */
    private static void imprimirArreglo(ImplementacionOperacionCRUD crud) {
        Protocolo[] arr = crud.getArreglo_protocolos();
        System.out.println("  Estado del arreglo [tamano=" + arr.length + "]:");
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != null) {
                System.out.println("    [" + i + "] "
                        + arr[i].getClass().getSimpleName()
                        + " | codigo='" + arr[i].getCodigo() + "'"
                        + " | instrucciones='" + arr[i].getInstrucciones() + "'"
                        + " | limites='" + arr[i].getLimites() + "'");
            } else {
                System.out.println("    [" + i + "] -- vacío (null) --");
            }
        }
    }

    // ---------------------------------------------------------------
    // Submenús del menú principal
    // ---------------------------------------------------------------

    /**
     * Muestra el menú principal en consola.
     */
    private static void mostrarMenu() {
        separadorGrueso();
        System.out.println("   SISTEMA DE MONITOREO ASTRONAUTAS - MENU PRINCIPAL");
        separadorGrueso();
        System.out.println("  1. CREAR   protocolo");
        System.out.println("  2. LEER    protocolo por indice");
        System.out.println("  3. LEER    todos los protocolos");
        System.out.println("  4. BUSCAR  protocolo por codigo");
        System.out.println("  5. MODIFICAR protocolo por codigo");
        System.out.println("  6. ELIMINAR  protocolo por codigo");
        separador();
        System.out.println("  7. SERIALIZAR   (guardar en archivo .txt)");
        System.out.println("  8. DESERIALIZAR (cargar desde archivo .txt)");
        separador();
        System.out.println("  9. VER estado actual del arreglo");
        System.out.println("  0. SALIR");
        separadorGrueso();
        System.out.print("  Seleccione una opcion: ");
    }

    /**
     * Solicita al usuario los datos necesarios para crear un protocolo
     * y lo inserta en el CRUD.
     *
     * @param crud    Instancia del CRUD.
     * @param scanner Scanner para leer entrada del usuario.
     */
    private static void menuCrear(ImplementacionOperacionCRUD crud, Scanner scanner) {
        separadorGrueso();
        System.out.println("  CREAR PROTOCOLO");
        separador();

        System.out.println("  Tipo de protocolo:");
        System.out.println("    1. ProtocoloInsuficiencia");
        System.out.println("    2. ProtocoloRadiacion");
        System.out.print("  Seleccione tipo: ");
        String opTipo = scanner.nextLine().trim();

        if (!opTipo.equals("1") && !opTipo.equals("2")) {
            System.out.println("  ERROR: Tipo invalido. Debe ser 1 o 2.");
            return;
        }

        System.out.print("  Codigo (ID unico): ");
        String codigo = scanner.nextLine().trim();
        if (codigo.isEmpty()) {
            System.out.println("  ERROR: El codigo no puede estar vacio.");
            return;
        }

        System.out.print("  Registro: ");
        String registro = scanner.nextLine().trim();

        System.out.print("  Instrucciones: ");
        String instrucciones = scanner.nextLine().trim();

        System.out.print("  Limites (ej: 1.0 Sv): ");
        String limites = scanner.nextLine().trim();

        Protocolo nuevo;
        if (opTipo.equals("1")) {
            nuevo = new ProtocoloInsuficiencia(codigo, registro, instrucciones,
                    limites, MIT_1, SENSOR_1, RAD_BAJA);
        } else {
            nuevo = new ProtocoloRadiacion(codigo, registro, instrucciones,
                    limites, MIT_1, SENSOR_1, RAD_ALTA);
        }

        String resultado = crud.crear(nuevo);
        separador();
        System.out.println("  Resultado: " + resultado);
    }

    /**
     * Solicita un índice al usuario y muestra el protocolo en esa posición.
     *
     * @param crud    Instancia del CRUD.
     * @param scanner Scanner para leer entrada del usuario.
     */
    private static void menuLeerPorIndice(ImplementacionOperacionCRUD crud, Scanner scanner) {
        separadorGrueso();
        System.out.println("  LEER PROTOCOLO POR INDICE");
        separador();

        System.out.print("  Ingrese el indice: ");
        String entrada = scanner.nextLine().trim();
        int indice;
        try {
            indice = Integer.parseInt(entrada);
        } catch (NumberFormatException e) {
            System.out.println("  ERROR: Debe ingresar un numero entero valido.");
            return;
        }

        separador();
        Protocolo p = crud.leer(indice);
        if (p != null) {
            System.out.println("  Tipo        : " + p.getClass().getSimpleName());
            System.out.println("  Informacion : " + p.leer_informacion());
            System.out.println("  Descripcion : " + p.obtener_descripcion_protocolo());
        }
    }

    /**
     * Muestra todos los protocolos actualmente almacenados en el arreglo.
     *
     * @param crud Instancia del CRUD.
     */
    private static void menuLeerTodos(ImplementacionOperacionCRUD crud) {
        separadorGrueso();
        System.out.println("  TODOS LOS PROTOCOLOS");
        separador();

        Protocolo[] todos = crud.leerTodos();
        boolean hayAlguno = false;
        for (int i = 0; i < todos.length; i++) {
            if (todos[i] != null) {
                hayAlguno = true;
                System.out.println("  [" + i + "] " + todos[i].leer_informacion());
            } else {
                System.out.println("  [" + i + "] -- vacio (null) --");
            }
        }
        if (!hayAlguno) {
            System.out.println("  No hay protocolos registrados.");
        }
    }

    /**
     * Solicita un código al usuario y busca el protocolo correspondiente.
     *
     * @param crud    Instancia del CRUD.
     * @param scanner Scanner para leer entrada del usuario.
     */
    private static void menuBuscarPorCodigo(ImplementacionOperacionCRUD crud, Scanner scanner) {
        separadorGrueso();
        System.out.println("  BUSCAR PROTOCOLO POR CODIGO");
        separador();

        System.out.print("  Ingrese el codigo a buscar: ");
        String codigo = scanner.nextLine().trim();

        int idx = crud.buscarIndicePorCodigo(codigo);
        separador();
        if (idx >= 0) {
            Protocolo p = crud.leer(idx);
            System.out.println("  Encontrado en posicion [" + idx + "]");
            System.out.println("  Tipo        : " + p.getClass().getSimpleName());
            System.out.println("  Informacion : " + p.leer_informacion());
            System.out.println("  Descripcion : " + p.obtener_descripcion_protocolo());
        } else {
            System.out.println("  ERROR: No se encontro ningun protocolo con codigo '"
                    + codigo + "'.");
        }
    }

    /**
     * Solicita el código del protocolo a modificar y los nuevos datos,
     * luego aplica la modificación en el CRUD.
     *
     * @param crud    Instancia del CRUD.
     * @param scanner Scanner para leer entrada del usuario.
     */
    private static void menuModificar(ImplementacionOperacionCRUD crud, Scanner scanner) {
        separadorGrueso();
        System.out.println("  MODIFICAR PROTOCOLO POR CODIGO");
        separador();

        System.out.print("  Ingrese el codigo del protocolo a modificar: ");
        String codigoBuscar = scanner.nextLine().trim();

        int idx = crud.buscarIndicePorCodigo(codigoBuscar);
        if (idx < 0) {
            System.out.println("  ERROR: No se encontro protocolo con codigo '"
                    + codigoBuscar + "'.");
            return;
        }

        System.out.println("  Protocolo encontrado en posicion [" + idx + "]: "
                + crud.leer(idx).leer_informacion());
        separador();
        System.out.println("  Ingrese los nuevos datos:");

        System.out.println("  Tipo de protocolo:");
        System.out.println("    1. ProtocoloInsuficiencia");
        System.out.println("    2. ProtocoloRadiacion");
        System.out.print("  Seleccione tipo: ");
        String opTipo = scanner.nextLine().trim();

        if (!opTipo.equals("1") && !opTipo.equals("2")) {
            System.out.println("  ERROR: Tipo invalido. Debe ser 1 o 2.");
            return;
        }

        System.out.print("  Nuevo codigo (ID): ");
        String nuevoCodigo = scanner.nextLine().trim();
        if (nuevoCodigo.isEmpty()) {
            System.out.println("  ERROR: El codigo no puede estar vacio.");
            return;
        }

        System.out.print("  Nuevo registro: ");
        String nuevoRegistro = scanner.nextLine().trim();

        System.out.print("  Nuevas instrucciones: ");
        String nuevasInstrucciones = scanner.nextLine().trim();

        System.out.print("  Nuevos limites (ej: 1.0 Sv): ");
        String nuevosLimites = scanner.nextLine().trim();

        Protocolo nuevoProtocolo;
        if (opTipo.equals("1")) {
            nuevoProtocolo = new ProtocoloInsuficiencia(nuevoCodigo, nuevoRegistro,
                    nuevasInstrucciones, nuevosLimites, MIT_1, SENSOR_1, RAD_BAJA);
        } else {
            nuevoProtocolo = new ProtocoloRadiacion(nuevoCodigo, nuevoRegistro,
                    nuevasInstrucciones, nuevosLimites, MIT_1, SENSOR_1, RAD_ALTA);
        }

        String resultado = crud.modificar(idx, nuevoProtocolo);
        separador();
        System.out.println("  Resultado: " + resultado);
    }

    /**
     * Solicita el código del protocolo a eliminar y lo elimina del CRUD.
     *
     * @param crud    Instancia del CRUD.
     * @param scanner Scanner para leer entrada del usuario.
     */
    private static void menuEliminar(ImplementacionOperacionCRUD crud, Scanner scanner) {
        separadorGrueso();
        System.out.println("  ELIMINAR PROTOCOLO POR CODIGO");
        separador();

        System.out.print("  Ingrese el codigo del protocolo a eliminar: ");
        String codigo = scanner.nextLine().trim();

        int idx = crud.buscarIndicePorCodigo(codigo);
        if (idx < 0) {
            System.out.println("  ERROR: No se encontro protocolo con codigo '"
                    + codigo + "'.");
            return;
        }

        System.out.println("  Protocolo encontrado en posicion [" + idx + "]: "
                + crud.leer(idx).leer_informacion());
        System.out.print("  Confirmar eliminacion (s/n): ");
        String confirmacion = scanner.nextLine().trim();

        separador();
        if (confirmacion.equalsIgnoreCase("s")) {
            System.out.println("  Resultado: " + crud.eliminar(idx));
        } else {
            System.out.println("  Operacion cancelada por el usuario.");
        }
    }

    /**
     * Serializa el arreglo de protocolos al archivo protocolos.txt.
     *
     * @param crud Instancia del CRUD.
     */
    private static void menuSerializar(ImplementacionOperacionCRUD crud) {
        separadorGrueso();
        System.out.println("  SERIALIZAR — Guardar protocolos en archivo .txt");
        separador();
        String resultado = crud.serializar();
        System.out.println("  Resultado: " + resultado);
    }

    /**
     * Deserializa los protocolos desde el archivo protocolos.txt
     * y los muestra por consola.
     *
     * @param crud Instancia del CRUD.
     */
    private static void menuDeserializar(ImplementacionOperacionCRUD crud) {
        separadorGrueso();
        System.out.println("  DESERIALIZAR — Cargar protocolos desde archivo .txt");
        separador();
        Protocolo[] cargados = crud.deserializar();
        crud.setArreglo_protocolos(cargados);
        if (cargados.length == 0) {
            System.out.println("  No se cargaron protocolos.");
            return;
        }
        System.out.println("  Protocolos cargados desde archivo:");
        for (int i = 0; i < cargados.length; i++) {
            if (cargados[i] != null) {
                System.out.println("    [" + i + "] " + cargados[i].getClass().getSimpleName()
                        + " | " + cargados[i].leer_informacion());
            }
        }
    }

    // ---------------------------------------------------------------
    // MAIN
    // ---------------------------------------------------------------

    /**
     * Método principal. Lanza el menú interactivo por consola.
     *
     * @param args Argumentos de línea de comandos (no utilizados).
     */
    public static void main(String[] args) {

        ImplementacionOperacionCRUD crud = new ImplementacionOperacionCRUD();
        Scanner scanner = new Scanner(System.in);
        String opcion;

        separadorGrueso();
        System.out.println("   BIENVENIDO AL SISTEMA DE MONITOREO ASTRONAUTAS");
        System.out.println("   Contexto 4 — Gestion de Protocolos Espaciales");
        separadorGrueso();

        do {
            mostrarMenu();
            opcion = scanner.nextLine().trim();

            switch (opcion) {

                case "1":
                    menuCrear(crud, scanner);
                    break;

                case "2":
                    menuLeerPorIndice(crud, scanner);
                    break;

                case "3":
                    menuLeerTodos(crud);
                    break;

                case "4":
                    menuBuscarPorCodigo(crud, scanner);
                    break;

                case "5":
                    menuModificar(crud, scanner);
                    break;

                case "6":
                    menuEliminar(crud, scanner);
                    break;

                case "7":
                    menuSerializar(crud);
                    break;

                case "8":
                    menuDeserializar(crud);
                    break;

                case "9":
                    separadorGrueso();
                    System.out.println("  VER ESTADO DEL ARREGLO");
                    separador();
                    imprimirArreglo(crud);
                    break;

                case "0":
                    separadorGrueso();
                    System.out.println("  Saliendo del sistema. Hasta luego.");
                    separadorGrueso();
                    break;

                default:
                    System.out.println("  ERROR: Opcion invalida. Ingrese un numero del 0 al 9.");
                    break;
            }

            if (!opcion.equals("0")) {
                System.out.println("\n  Presione ENTER para continuar...");
                scanner.nextLine();
            }

        } while (!opcion.equals("0"));

        scanner.close();
    }
}