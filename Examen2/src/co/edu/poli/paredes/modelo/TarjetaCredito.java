package co.edu.poli.paredes.modelo;

public class TarjetaCredito extends Prestamo {
	private double cupo;
	private Cuotas cuota;
	
	public double getCupo() {
		return cupo;
	}
	public void setCupo(double cupo) {
		this.cupo = cupo;
	}
	public Cuotas getCuota() {
		return cuota;
	}
	public void setCuota(Cuotas cuota) {
		this.cuota = cuota;
	}
	public TarjetaCredito() {}
	public TarjetaCredito(String codigo, String nombre, double saldo, String estado, double cupo, Cuotas cuota) {
		super(codigo, nombre, saldo, estado);
	}
	

}
