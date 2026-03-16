package co.edu.poli.contexto4.servicios;

import co.edu.poli.contexto4.model.Mitigacion;

public class ProtocoloEmergencia {
    private int nivel_peligro;

    public ProtocoloEmergencia() {}

    public ProtocoloEmergencia(int nivel_peligro) {
        this.nivel_peligro = nivel_peligro;
    }

    public String aplicar_protocolo(double cantidad_radiacion, Mitigacion mitigacion, String dosis) {
        System.out.println("[ProtocoloEmergencia] Aplicando protocolo de emergencia. Nivel peligro: " + nivel_peligro);
        return "Protocolo Emergencia aplicado | Radiación: " + cantidad_radiacion +
               " | Mitigación: " + (mitigacion != null ? mitigacion.getEstrategia() : "N/A") +
               " | Dosis: " + dosis;
    }

    public int getNivel_peligro() { return nivel_peligro; }
    public void setNivel_peligro(int nivel_peligro) { this.nivel_peligro = nivel_peligro; }

    @Override
    public String toString() {
        return "ProtocoloEmergencia{nivel_peligro=" + nivel_peligro + "}";
    }
}
