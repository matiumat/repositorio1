package co.edu.poli.contexto4.vista;
 
import co.edu.poli.contexto4.model.*;
 
public class Principal {
 
    // ===========================================================================
    // PUNTO 2: Métodos con polimorfismo — definidos fuera del main
    // ===========================================================================
 
    // PUNTO 2 - Método que RECIBE parámetro de tipo supersuperclase (Protocolo)
    public static void metodo_polimorfismo_param(Protocolo protocolo, double radiacion) {
        System.out.println("  [Polimorfismo - param] Clase recibida: " + protocolo.getClass().getSimpleName());
        String resultado = protocolo.controlar_limites(radiacion);
        System.out.println("  [Polimorfismo - param] Resultado: " + resultado);
    }
 
    // PUNTO 2 - Método que RETORNA tipo supersuperclase (Protocolo)
    public static Protocolo metodo_polimorfismo_retorno(String tipo, Mitigacion mitigacion,
                                                         Sensor sensor, Radiacion radiacion) {
        System.out.println("  [Polimorfismo - retorno] Creando protocolo tipo: " + tipo);
        if (tipo.equalsIgnoreCase("insuficiencia")) {
            return new ProtocoloInsuficiencia("PINS-RET", "REG-RET", "Control insuficiencia",
                    "0.1 Sv", mitigacion, sensor, radiacion);
        } else {
            return new ProtocoloRadiacion("PRAD-RET", "REG-RET", "Control radiacion",
                    "1.0 Sv", mitigacion, sensor, radiacion);
        }
    }
 
    // ===========================================================================
    // MAIN
    // ===========================================================================
    public static void main(String[] args) {
 
        System.out.println("=============================================================");
        System.out.println("         SISTEMA DE MONITOREO ASTRONAUTAS - CONTEXTO 4      ");
        System.out.println("=============================================================");
 
        // --- Objetos base necesarios ---
        Sensor sensor1  = new Sensor("SEN-001", "Titanio",  "Blanco", 5.5, 0.3);
        Sensor sensor2  = new Sensor("SEN-002", "Aluminio", "Gris",   4.0, 0.2);
        Radiacion rad1  = new Radiacion(2.5,  "ALTO", "Gamma", "Alta",  "Solar");
        Radiacion rad2  = new Radiacion(0.05, "BAJO", "Alpha", "Baja",  "Cosmica");
        Mitigacion mit1 = new Mitigacion("MIT-001", "Blindaje reforzado", 1, "Modulo A", rad1);
 
        // ===========================================================
        // PUNTO 1: Arreglo de tipo supersuperclase (Protocolo[5])
        //          con 3 objetos de subclases distintas
        // ===========================================================
        System.out.println("\n=============================================================");
        System.out.println("  PUNTO 1: ARREGLO DE SUPERSUPERCLASE Protocolo[5]");
        System.out.println("=============================================================");
 
        // PUNTO 1 - Arreglo de la supersuperclase con 5 posiciones
        Protocolo[] protocolos = new Protocolo[5];
 
        // PUNTO 1 - 3 objetos de subclases diferentes (posiciones 3 y 4 quedan null)
        protocolos[0] = new ProtocoloInsuficiencia("PINS-01", "REG-01",
                "Monitoreo nivel bajo", "0.1 Sv", mit1, sensor1, rad2);
        protocolos[1] = new ProtocoloRadiacion("PRAD-01", "REG-02",
                "Monitoreo radiacion alta", "1.0 Sv", mit1, sensor1, rad1);
        protocolos[2] = new ProtocoloInsuficiencia("PINS-02", "REG-03",
                "Alerta insuficiencia critica", "0.05 Sv", mit1, sensor2, rad2);
        // posiciones [3] y [4] quedan null intencionalmente
 
        // PUNTO 1 - Recorrer arreglo e imprimir sobreescritura de controlar_limites
        System.out.println("\n  Recorriendo arreglo — cada objeto ejecuta SU versión sobreescrita:");
        for (int i = 0; i < protocolos.length; i++) {
            if (protocolos[i] != null) {
                System.out.println("\n  [Posicion " + i + "] Clase: "
                        + protocolos[i].getClass().getSimpleName());
                // PUNTO 1 - sobreescritura en acción
                System.out.println("  [Posicion " + i + "] "
                        + protocolos[i].controlar_limites(rad1.getCantidad_sievert()));
            } else {
                System.out.println("\n  [Posicion " + i + "] -> null (posición vacía)");
            }
        }
 
        // ===========================================================
        // PUNTO 2: Dos métodos con polimorfismo invocados desde main
        //   - metodo_polimorfismo_param   → recibe Protocolo (supersuperclase)
        //   - metodo_polimorfismo_retorno → retorna Protocolo (supersuperclase)
        // (Definidos arriba del main en esta misma clase Principal)
        // ===========================================================
        System.out.println("\n=============================================================");
        System.out.println("  PUNTO 2: POLIMORFISMO — DOS MÉTODOS");
        System.out.println("=============================================================");
 
        // PUNTO 2 - Invocación del método que RECIBE parámetro de tipo supersuperclase
        System.out.println("\n  --- Método que RECIBE Protocolo como parámetro ---");
        metodo_polimorfismo_param(protocolos[0], 0.05);
        metodo_polimorfismo_param(protocolos[1], 2.5);
 
        // PUNTO 2 - Invocación del método que RETORNA tipo supersuperclase
        System.out.println("\n  --- Método que RETORNA Protocolo (supersuperclase) ---");
        Protocolo retornado1 = metodo_polimorfismo_retorno("insuficiencia", mit1, sensor1, rad2);
        System.out.println("  Clase real: " + retornado1.getClass().getSimpleName());
        System.out.println("  Resultado : " + retornado1.controlar_limites(0.02));
 
        Protocolo retornado2 = metodo_polimorfismo_retorno("radiacion", mit1, sensor1, rad1);
        System.out.println("  Clase real: " + retornado2.getClass().getSimpleName());
        System.out.println("  Resultado : " + retornado2.controlar_limites(3.0));
 
        // ===========================================================
        // PUNTO 3: final — atributo, método y clase
        //
        //   ATRIBUTO final → DatosConstantes.LIMITE_MAXIMO_RADIACION  (no se puede cambiar)
        //   MÉTODO final   → Protocolo.obtener_descripcion_protocolo() (no se puede sobreescribir)
        //   CLASE final    → DatosConstantes (no se puede heredar) — ver DatosConstantes.java
        // ===========================================================
        System.out.println("\n=============================================================");
        System.out.println("  PUNTO 3: final — ATRIBUTO, MÉTODO Y CLASE");
        System.out.println("=============================================================");
 
        // PUNTO 3 - Atributo final: valor constante, no puede cambiar
        System.out.println("\n  --- Atributo final en DatosConstantes ---");
        System.out.println("  LIMITE_MAXIMO_RADIACION = "
                + DatosConstantes.LIMITE_MAXIMO_RADIACION + " Sv  (constante, no modificable)");
        System.out.println("  NOMBRE_SISTEMA          = " + DatosConstantes.NOMBRE_SISTEMA);
 
        // PUNTO 3 - Clase final: DatosConstantes no puede ser heredada
        DatosConstantes datos = new DatosConstantes();
        System.out.println("\n  --- Clase final DatosConstantes (no se puede heredar) ---");
        System.out.println("  " + datos.obtener_info_sistema());
 
        // PUNTO 3 - Método final: obtener_descripcion_protocolo() no puede sobreescribirse
        System.out.println("\n  --- Método final obtener_descripcion_protocolo() en Protocolo ---");
        System.out.println("  Desde ProtocoloInsuficiencia : " + protocolos[0].obtener_descripcion_protocolo());
        System.out.println("  Desde ProtocoloRadiacion     : " + protocolos[1].obtener_descripcion_protocolo());
        System.out.println("  (Ambas subclases ejecutan la misma versión final — no sobreescrita)");
 
        System.out.println("\n=============================================================");
        System.out.println("         FIN DE LA EJECUCIÓN - TODOS LOS PUNTOS OK          ");
        System.out.println("=============================================================");
    }
}