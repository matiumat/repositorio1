package co.edu.poli.paredes.modelo;

public class Cuotas {
	private int numero_cuota;
	private double valor;
	private String fecha_pago;
	public int getNumero_cuota() {
		return numero_cuota;
	}
	public void setNumero_cuota(int numero_cuota) {
		this.numero_cuota = numero_cuota;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public String getFecha_pago() {
		return fecha_pago;
	}
	public void setFecha_pago(String fecha_pago) {
		this.fecha_pago = fecha_pago;
	}
	
	public Cuotas(int numero_cuota, double valor, String fecha_pago) {
this.numero_cuota = numero_cuota;
this.valor = valor;

	}
}
