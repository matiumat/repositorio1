package co.edu.poli.contexto4.model;

// PUNTO 3: Clase que NO se puede heredar (final)
public final class DatosConstantes {

    // PUNTO 3: Atributo que NO se puede cambiar (final)
    public static final double LIMITE_MAXIMO_RADIACION = 1.0;
    public static final String NOMBRE_SISTEMA = "Sistema de Monitoreo Espacial v1.0";

    // PUNTO 3: Método que NO se puede sobreescribir (final) — pero en clase final ya aplica implícitamente,
    // se marca explícitamente para evidenciarlo
    public final String obtener_info_sistema() {
        return "Sistema: " + NOMBRE_SISTEMA + " | Límite máximo radiación: " + LIMITE_MAXIMO_RADIACION + " Sv";
    }

    @Override
    public String toString() {
        return "DatosConstantes{LIMITE_MAXIMO_RADIACION=" + LIMITE_MAXIMO_RADIACION +
               ", NOMBRE_SISTEMA='" + NOMBRE_SISTEMA + "'}";
    }
}
