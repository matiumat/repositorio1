package co.edu.poli.contexto4.vista;

import co.edu.poli.contexto4.model.*;
import co.edu.poli.contexto4.servicios.ImplementacionOperacionCRUD;

public class Principal {

    // ---------------------------------------------------------------
    // PUNTO 2 anterior: Métodos con polimorfismo
    // ---------------------------------------------------------------
    public static void metodo_polimorfismo_param(Protocolo protocolo, double radiacion) {
        System.out.println("  [Polimorfismo - param] Clase: " + protocolo.getClass().getSimpleName());
        System.out.println("  [Polimorfismo - param] " + protocolo.controlar_limites(radiacion));
    }

    public static Protocolo metodo_polimorfismo_retorno(String tipo, Mitigacion mitigacion,
                                                         Sensor sensor, Radiacion radiacion) {
        if (tipo.equalsIgnoreCase("insuficiencia")) {
            return new ProtocoloInsuficiencia("PINS-RET", "REG-RET", "Control insuficiencia",
                    "0.1 Sv", mitigacion, sensor, radiacion);
        } else {
            return new ProtocoloRadiacion("PRAD-RET", "REG-RET", "Control radiacion",
                    "1.0 Sv", mitigacion, sensor, radiacion);
        }
    }

    // ---------------------------------------------------------------
    // Método utilitario para imprimir el estado del arreglo CRUD
    // ---------------------------------------------------------------
    public static void imprimirArreglo(ImplementacionOperacionCRUD crud) {
        Protocolo[] arr = crud.getArreglo_protocolos();
        System.out.println("  Estado arreglo [tamano=" + arr.length + "]:");
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != null) {
                System.out.println("    [" + i + "] " + arr[i].getClass().getSimpleName()
                        + " | codigo='" + arr[i].getCodigo() + "'");
            } else {
                System.out.println("    [" + i + "] null");
            }
        }
    }

    // ---------------------------------------------------------------
    // MAIN
    // ---------------------------------------------------------------
    public static void main(String[] args) {

        System.out.println("=============================================================");
        System.out.println("         SISTEMA DE MONITOREO ASTRONAUTAS - CONTEXTO 4      ");
        System.out.println("=============================================================");

        // --- Objetos base ---
        Sensor sensor1  = new Sensor("SEN-001", "Titanio",  "Blanco", 5.5, 0.3);
        Sensor sensor2  = new Sensor("SEN-002", "Aluminio", "Gris",   4.0, 0.2);
        Radiacion rad1  = new Radiacion(2.5,  "ALTO", "Gamma", "Alta",  "Solar");
        Radiacion rad2  = new Radiacion(0.05, "BAJO", "Alpha", "Baja",  "Cosmica");
        Mitigacion mit1 = new Mitigacion("MIT-001", "Blindaje reforzado", 1, "Modulo A", rad1);

        // ===============================================================
        //  CRUD completo con ImplementacionOperacionCRUD
        // ===============================================================
        System.out.println("\n=============================================================");
        System.out.println("   CRUD — ImplementacionOperacionCRUD");
        System.out.println("  Arreglo inicial tamano: 2 | Crece al duplicarse si se llena");
        System.out.println("=============================================================");

        ImplementacionOperacionCRUD crud = new ImplementacionOperacionCRUD();

        // Protocolo de prueba para CRUD
        Protocolo p1 = new ProtocoloInsuficiencia("CRUD-INS-01", "REG-C01",
                "Protocolo CRUD insuficiencia 1", "0.1 Sv", mit1, sensor1, rad2);
        Protocolo p2 = new ProtocoloRadiacion("CRUD-RAD-01", "REG-C02",
                "Protocolo CRUD radiacion 1", "1.0 Sv", mit1, sensor1, rad1);
        Protocolo p3 = new ProtocoloInsuficiencia("CRUD-INS-02", "REG-C03",
                "Protocolo CRUD insuficiencia 2", "0.2 Sv", mit1, sensor2, rad2);
        Protocolo p4 = new ProtocoloRadiacion("CRUD-RAD-02", "REG-C04",
                "Protocolo CRUD radiacion 2", "2.0 Sv", mit1, sensor2, rad1);

        // -----------------------------------------------------------
        // CREATE
        // -----------------------------------------------------------
        System.out.println("\n--- CREATE ---");
        System.out.println("  " + crud.crear(p1));
        System.out.println("  " + crud.crear(p2));
        imprimirArreglo(crud);

        // Arreglo lleno (tamano 2) → debe duplicarse al insertar p3
        System.out.println("\n  Insertando p3 con arreglo lleno (debe duplicarse a tamano 4):");
        System.out.println("  " + crud.crear(p3));
        System.out.println("  " + crud.crear(p4));
        imprimirArreglo(crud);

        // Validación: codigo duplicado
        System.out.println("\n  Validacion - codigo duplicado:");
        System.out.println("  " + crud.crear(p1));

        // Validación: objeto null
        System.out.println("\n  Validacion - objeto null:");
        System.out.println("  " + crud.crear(null));

        // -----------------------------------------------------------
        // READ por índice
        // -----------------------------------------------------------
        System.out.println("\n--- READ por indice ---");
        Protocolo leido = crud.leer(1);
        if (leido != null) {
            System.out.println("  Posicion [1]: " + leido.getClass().getSimpleName()
                    + " | " + leido.leer_informacion());
        }

        // Validación: índice inválido
        System.out.println("\n  Validacion - indice negativo:");
        crud.leer(-1);
        System.out.println("  Validacion - indice fuera de rango:");
        crud.leer(99);

        // -----------------------------------------------------------
        // READ ALL
        // -----------------------------------------------------------
        System.out.println("\n--- READ ALL ---");
        Protocolo[] todos = crud.leerTodos();
        for (int i = 0; i < todos.length; i++) {
            if (todos[i] != null) {
                System.out.println("  [" + i + "] " + todos[i].leer_informacion());
            } else {
                System.out.println("  [" + i + "] null");
            }
        }

        // -----------------------------------------------------------
        // READ por codigo (ID) — búsqueda antes de modificar/eliminar
        // -----------------------------------------------------------
        System.out.println("\n--- BUSCAR POR CODIGO (ID) ---");
        String codigoBuscar = "CRUD-RAD-01";
        int idx = crud.buscarIndicePorCodigo(codigoBuscar);
        if (idx >= 0) {
            System.out.println("  Codigo '" + codigoBuscar + "' encontrado en posicion [" + idx + "]: "
                    + crud.leer(idx).leer_informacion());
        } else {
            System.out.println("  ERROR: Codigo '" + codigoBuscar + "' no encontrado.");
        }

        // Validación: codigo inexistente
        System.out.println("\n  Validacion - codigo inexistente:");
        int idxInexistente = crud.buscarIndicePorCodigo("CODIGO-FALSO");
        System.out.println("  Indice retornado: " + idxInexistente
                + (idxInexistente == -1 ? " → No encontrado." : ""));

        // -----------------------------------------------------------
        // UPDATE — modificar por codigo (ID)
        // -----------------------------------------------------------
        System.out.println("\n--- UPDATE (modificar por codigo) ---");
        String codigoModificar = "CRUD-INS-01";
        int idxMod = crud.buscarIndicePorCodigo(codigoModificar);
        if (idxMod >= 0) {
            Protocolo nuevoProtocolo = new ProtocoloInsuficiencia("CRUD-INS-01-MOD", "REG-MOD",
                    "Protocolo MODIFICADO", "0.05 Sv", mit1, sensor2, rad2);
            System.out.println("  " + crud.modificar(idxMod, nuevoProtocolo));
        } else {
            System.out.println("  ERROR: Codigo '" + codigoModificar + "' no encontrado para modificar.");
        }

        // Validación: índice inválido en modificar
        System.out.println("\n  Validacion - indice invalido en modificar:");
        System.out.println("  " + crud.modificar(99, p1));

        // Validación: posición null en modificar
        System.out.println("\n  Validacion - modificar posicion null:");
        System.out.println("  " + crud.modificar(2, p1)); // posicion [2] tiene p3

        imprimirArreglo(crud);

        // -----------------------------------------------------------
        // DELETE — eliminar por codigo (ID)
        // -----------------------------------------------------------
        System.out.println("\n--- DELETE (eliminar por codigo) ---");
        String codigoEliminar = "CRUD-RAD-02";
        int idxDel = crud.buscarIndicePorCodigo(codigoEliminar);
        if (idxDel >= 0) {
            System.out.println("  " + crud.eliminar(idxDel));
        } else {
            System.out.println("  ERROR: Codigo '" + codigoEliminar + "' no encontrado para eliminar.");
        }

        // Validación: eliminar posición ya null
        System.out.println("\n  Validacion - eliminar posicion ya null:");
        System.out.println("  " + crud.eliminar(idxDel)); // ya quedo null

        // Validación: índice fuera de rango
        System.out.println("\n  Validacion - indice fuera de rango en eliminar:");
        System.out.println("  " + crud.eliminar(-5));

        imprimirArreglo(crud);

        // CREATE tras eliminar: debe reutilizar el null dejado por la eliminación
        System.out.println("\n  Crear nuevo tras eliminacion (debe ocupar el null liberado):");
        Protocolo p5 = new ProtocoloRadiacion("CRUD-RAD-NUEVO", "REG-C05",
                "Protocolo nuevo tras eliminacion", "0.5 Sv", mit1, sensor1, rad1);
        System.out.println("  " + crud.crear(p5));
        imprimirArreglo(crud);

        System.out.println("\n=============================================================");
        System.out.println("         FIN DE LA EJECUCION - TODOS LOS PUNTOS OK          ");
        System.out.println("=============================================================");
    }
}
