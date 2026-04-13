package co.edu.poli.paredes.modelo;

public class Prestamo extends ProductosFinancieros{
	private String codigo;
	private String nombre;
	private double saldo;
	private String estado;
	

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	public Prestamo () {}
	public Prestamo (String codigo, String nombre, double saldo, String estado) {}
	
	
	public String obtener_resumen_producto() {
		return "Resumen del Producto | Nombre: " + getNombre() + "Saldo: " + getSaldo() + "Estado: " + getEstado();
		
		
	}
	}
	
