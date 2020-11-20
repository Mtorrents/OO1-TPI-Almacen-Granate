package modelo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Comercio extends Actor {
	private String nombreComercio;
	private long cuit;
	private double costoFijo;
	private double costoPorKm;
	private int diaDescuento;
	private int porcentajeDescuentoDia;
	private int porcentajeDescuentoEfectivo;
	private List<DiaRetiro> lstDiaRetiro = new ArrayList<DiaRetiro>();
	private List<Articulo> lstArticulo = new ArrayList<Articulo>();
	private List<Carrito> lstCarrito = new ArrayList<Carrito>();

	public Comercio(int id, Contacto contacto, String nombreComercio, long cuit, double costoFijo, double costoPorKm,
			int diaDescuento, int porcentajeDescuentoDia, int porcentajeDescuentoEfectivo) {
		super(id, contacto);
		this.nombreComercio = nombreComercio;
		this.cuit = cuit;
		this.costoFijo = costoFijo;
		this.costoPorKm = costoPorKm;
		this.diaDescuento = diaDescuento;
		this.porcentajeDescuentoDia = porcentajeDescuentoDia;
		this.porcentajeDescuentoEfectivo = porcentajeDescuentoEfectivo;
	}

	public String getNombreComercio() {
		return nombreComercio;
	}

	public void setNombreComercio(String nombreComercio) {
		this.nombreComercio = nombreComercio;
	}

	public long getCuit() {
		return cuit;
	}

	public void setCuit(long cuit) {
		this.cuit = cuit;
	}

	public double getCostoFijo() {
		return costoFijo;
	}

	public void setCostoFijo(double costoFijo) {
		this.costoFijo = costoFijo;
	}

	public double getCostoPorKm() {
		return costoPorKm;
	}

	public void setCostoPorKm(double costoPorKm) {
		this.costoPorKm = costoPorKm;
	}

	public int getDiaDescuento() {
		return diaDescuento;
	}

	public void setDiaDescuento(int diaDescuento) {
		this.diaDescuento = diaDescuento;
	}

	public int getPorcentajeDescuentoDia() {
		return porcentajeDescuentoDia;
	}

	public void setPorcentajeDescuentoDia(int porcentajeDescuentoDia) {
		this.porcentajeDescuentoDia = porcentajeDescuentoDia;
	}

	public int getPorcentajeDescuentoEfectivo() {
		return porcentajeDescuentoEfectivo;
	}

	public void setPorcentajeDescuentoEfectivo(int porcentajeDescuentoEfectivo) {
		this.porcentajeDescuentoEfectivo = porcentajeDescuentoEfectivo;
	}

	public List<DiaRetiro> getLstDiaRetiro() {
		return lstDiaRetiro;
	}

	public void setLstDiaRetiro(List<DiaRetiro> lstDiaRetiro) {
		this.lstDiaRetiro = lstDiaRetiro;
	}

	public List<Articulo> getLstArticulo() {
		return lstArticulo;
	}

	public void setLstArticulo(List<Articulo> lstArticulo) {
		this.lstArticulo = lstArticulo;
	}

	public List<Carrito> getLstCarrito() {
		return lstCarrito;
	}

	public void setLstCarrito(List<Carrito> lstCarrito) {
		this.lstCarrito = lstCarrito;
	}
	
	//*********************** punto 1 *******************************
	protected boolean validarIdentificadorUnico(long identificador) {
		boolean esValido = false;
		if (esValido == false) {
			if (identificador > 9999999l && identificador < 99999999l) {
				esValido = true;
			} else {
				esValido = false;
			}
		}
		if (esValido == false) {
			if (String.valueOf(identificador).length() == 11) {
				esValido = true;
			} else {
				esValido = false;
			}
		}
		return esValido;
	}
	// Punto 2 en RetiroLocal
	//*********************** punto 3 *******************************
		public List<Turno> generarTurnosLibres(LocalDate fecha) {
			DiaRetiro diaRetiro = traerDiaRetiro(fecha);
			List<Turno> turno = new ArrayList<Turno>();
			if (diaRetiro != null) {
				LocalTime horaDesde = diaRetiro.getHoraDesde();
				LocalTime horaHasta = diaRetiro.getHoraHasta();
				int intervalo = diaRetiro.getIntervalo();
				while (horaDesde.isBefore(horaHasta)) {
					turno.add(new Turno(fecha, horaDesde, false));
					horaDesde = horaDesde.plusMinutes(intervalo);
				}
			}
			return turno;

		}
		//*********************** punto 4 *******************************
		public List<Turno> traerTurnosOcupados(LocalDate fecha) {
			List<Turno> turnosOcupados = new ArrayList<Turno>();
			for (int i=0; i<this.lstCarrito.size();i++) {
				if(this.lstCarrito.get(i).getFecha().equals(fecha)) {
				turnosOcupados.add(new Turno(this.lstCarrito.get(i).getFecha(),this.lstCarrito.get(i).getHora(),true));
				}
			}
			return turnosOcupados;
		}
		//*********************** punto 5 *******************************
		public List<Turno> generarAgenda(LocalDate fecha) {
			List<Turno> agenda = new ArrayList<Turno>();
			List<Turno> turnosOcupados = traerTurnosOcupados(fecha);
			List<Turno> turnosLibres = generarTurnosLibres(fecha);
			if (!turnosOcupados.isEmpty()) {
				agenda.addAll(turnosOcupados);
			} else if (!turnosLibres.isEmpty() && turnosOcupados.isEmpty()) {
				agenda.addAll(turnosLibres);
			}
			return agenda;
		}
		//*********************** punto 6 *******************************
			public boolean agregarDiaRetiro(LocalDate diaSemana, LocalTime horaDesde, LocalTime horaHasta, int intervalo) {
				if (traerDiaRetiro(diaSemana) != null) {
					return false;
				} else {
					lstDiaRetiro.add(new DiaRetiro(traerIdDiaRetiro() + 1, diaSemana.getDayOfWeek().getValue(), horaDesde, horaHasta, intervalo));
					return true;
				}
			}
		//*********************** punto 7 *******************************
		public boolean validarCodBarras(String codBarras) {
			boolean esValido = false;
			int i = 0;
			int chequeoSuma = 0;
			char[] codBarrasToChar = codBarras.toCharArray();
			int[] ean = { 1, 3 };
			int suma = 0;
			if (codBarrasToChar.length == 12) {
				while (i < codBarrasToChar.length) {
					suma = suma + Character.getNumericValue(codBarrasToChar[i]) * ean[i % 2];
					i++;
				}
				chequeoSuma = 10 - suma % 10;
				if (chequeoSuma == 10) {
					chequeoSuma = 0;
					esValido = true;
				}
				esValido = true;
			}
			return esValido;
		}
	//*********************** punto 8 *******************************
	public boolean agregar(Articulo articulo, int cantidad) {
		int i = 0;
		boolean agregado = false;
		if (traerItemCarrito(articulo) != null) {
			traerItemCarrito(articulo).setCantidad(traerItemCarrito(articulo).getCantidad() + cantidad);
		} else {
			while (i < lstCarrito.size() && agregado == false) {
				lstCarrito.get(i).getLstItemCarrito().add(new ItemCarrito(articulo, cantidad));
				agregado = true;
				i++;
			}
		}
		return agregado;
	}
	//punto 9 en ItemCarrito
	//*********************** punto 10 *******************************
		public double calcularTotalCarrito() {
			double total = 0;
			int i = 0;
			int j = 0;
			while (i < lstCarrito.size()) {
				while (j < lstCarrito.get(i).getLstItemCarrito().size()) {
					total = total + lstCarrito.get(i).getLstItemCarrito().get(j).calcularSubTotalItem();
					j++;
				}
				i++;
			}
			return total;
		}
		//*********************** punto 11 *******************************
		public double calcularDescuentoDia(int diaDescuento, double porcentajeDescuentoDia) {
			double descuento = 0;
			int i = 0;
			int j = 0;
			int cantidad;
			double desc;
			double precio;
			double suma = 0;
			while (i < lstCarrito.size()) {
				if (this.diaDescuento == diaDescuento) {
					while (j < lstCarrito.get(i).getLstItemCarrito().size()) {
						cantidad = lstCarrito.get(i).getLstItemCarrito().get(j).getCantidad();
						precio = lstCarrito.get(i).getLstItemCarrito().get(j).getArticulo().getPrecio();
						if (cantidad > 1) {
							desc = cantidad / 2;
							suma = (int) desc;
							suma = suma * precio * porcentajeDescuentoDia / 100;
						}
						descuento = descuento + suma;
						j++;
					}

				}
				i++;
			}
			return descuento;
		}
		//*********************** punto 12 *******************************
		public double calcularDescuentoEfectivo(double porcentajeDescuentoEfectivo) {
			return calcularTotalCarrito() * porcentajeDescuentoEfectivo / 100;
		}
		//*********************** punto 13 *******************************
		public double calcularDescuentoCarrito(int diaDescuento, double porcentajeDescuento,
				double porcentajeDescuentoEfectivo) {
			double descuento = 0;
			if (this.diaDescuento == diaDescuento) {
				if (porcentajeDescuento > porcentajeDescuentoEfectivo) {
					descuento = porcentajeDescuento;
				} else {
					descuento = porcentajeDescuentoEfectivo;
				}
			}
			return descuento;
		}
		//*********************** punto 14 *******************************
		protected void setDescuento(double descuento) {
			int i = 0;
			while (i < lstCarrito.size()) {
				lstCarrito.get(i).setDescuento(descuento);
				i++;
			}
		}
		//*********************** punto 15 *******************************
		public double totalAPagarCarrito(int diaDescuento, double porcentajeDescuento, double porcentajeDescuentoEfectivo) {
			double total = 0;
			total = calcularTotalCarrito()
					- calcularDescuentoCarrito(diaDescuento, porcentajeDescuento, porcentajeDescuentoEfectivo);

			return total;
		}
		//*********************** punto 16 *******************************
		public double setcosto(Ubicacion ubicacion, double costoFijo, double costoPorKm) {
	        double costo=0;
	        costo= (distanciaCoord(ubicacion.getLatitud(),ubicacion.getLongitud(),84,72)*costoPorKm)+costoFijo;
	        return costo;
	    }
		//punto 17 en Envio
		
		// Funciones Auxiliares
	public int traerIdDiaRetiro() {
		int mayor = 0;
		if (lstDiaRetiro.size() != 0) {
			mayor = lstDiaRetiro.get(0).getId();
		}
		int actual;
		boolean encontrado = false;
		int i = 0;
		while (i < lstDiaRetiro.size() && encontrado == false) {
			actual = lstDiaRetiro.get(i).getId();
			if (actual > mayor) {
				mayor = actual;
				encontrado = true;
			}
			i++;
		}
		return mayor;
	}

	public DiaRetiro traerDiaRetiro(LocalDate fecha) {
		DiaRetiro diaRetiro = null;
		boolean encontrado = false;
		int i = 0;
		while (i < lstDiaRetiro.size() && encontrado == false) {
			if (lstDiaRetiro.get(i).getDiaSemana() == fecha.getDayOfWeek().getValue()) {
				diaRetiro = lstDiaRetiro.get(i);
				encontrado = true;
			}
			i++;
		}
		return diaRetiro;
	}

	public Carrito traerCarrito(LocalDate fecha) {
		Carrito carrito = null;
		int i = 0;
		while (i < lstCarrito.size()) {
			if (lstCarrito.get(i).getFecha().equals(fecha)) {
				carrito = lstCarrito.get(i);
				}
			i++;
		}
		return carrito;
	}

	public ItemCarrito traerItemCarrito(Articulo articulo) {
		ItemCarrito item = null;
		int i = 0;
		int j = 0;
		while (i < lstCarrito.size()) {
			while (j < lstCarrito.get(i).getLstItemCarrito().size()) {
				if (lstCarrito.get(i).getLstItemCarrito().get(j).getArticulo().equals(articulo)) {
					item = lstCarrito.get(i).getLstItemCarrito().get(j);
				}
				j++;
			}
			i++;
		}
		return item;
	}
	
public boolean eliminarCantidad(Articulo articulo, int cantidad) {
	boolean eliminado = false;
	ItemCarrito itemEncontrado = traerItemCarrito(articulo);
	if(cantidad < itemEncontrado.getCantidad()) {
		itemEncontrado.setCantidad(itemEncontrado.getCantidad() - cantidad);
	} else if(cantidad == itemEncontrado.getCantidad()) {
		int i = 0;
		while (i < lstCarrito.size()) {
			lstCarrito.get(i).getLstItemCarrito().remove(itemEncontrado);
			eliminado = true;
			i++;
		}
	}
	return eliminado;
}
	public Articulo traerArticulo(String nombre) {
		Articulo articulo = null;
		int i = 0;
		boolean encontrado = false;
		while (i < lstArticulo.size() && encontrado == false) {
			if (lstArticulo.get(i).getNombre().equals(nombre)) {
				articulo = lstArticulo.get(i);
				encontrado = true;
			}
			i++;
		}

		return articulo;
	}

	public boolean agregarArticulo(String nombre, String codBarras, double precio) {
		boolean agregado = false;
		if (traerArticulo(nombre) != null) {
			agregado = false;
		} else {
			lstArticulo.add(new Articulo(traerIdArticulo() + 1, nombre, codBarras, precio));
			agregado = true;
		}
		return agregado;
	}

	public boolean agregarCarrito(LocalDate fecha, LocalTime hora, boolean cerrado, double descuento, Cliente cliente, Entrega entrega) {
		if (traerCarrito(fecha) != null) {
			return false;
		}
		return lstCarrito.add(new Carrito(traerIdCarrito() + 1, fecha, hora, cerrado, descuento, cliente, entrega));
	}
	
	public int traerIdArticulo() {
		int mayor = 0;
		if (lstArticulo.size() != 0) {
			mayor = lstArticulo.get(0).getId();
		}
		int actual;
		boolean encontrado = false;
		int i = 0;
		while (i < lstArticulo.size() && encontrado == false) {
			actual = lstArticulo.get(i).getId();
			if (actual > mayor) {
				mayor = actual;
				encontrado = true;
			}
			i++;
		}
		return mayor;
	}

	public int traerIdCarrito() {
		int mayor = 0;
		if (lstCarrito.size() != 0) {
			mayor = lstCarrito.get(0).getId();
		}
		int actual;
		boolean encontrado = false;
		int i = 0;
		while (i < lstCarrito.size() && encontrado == false) {
			actual = lstCarrito.get(i).getId();
			if (actual > mayor) {
				mayor = actual;
				encontrado = true;
			}
			i++;
		}
		return mayor;
	}
	
	public double distanciaCoord(double lat1, double lng1, double lat2, double lng2) {
		double radioTierra = 6371;
		double dLat = Math.toRadians(lat2 - lat1);
		double dLng = Math.toRadians(lng2 - lng1);
		double sindLat = Math.sin(dLat / 2);
		double sindLng = Math.sin(dLng / 2);
		double va1 = Math.pow(sindLat, 2)
				+ Math.pow(sindLng, 2) * Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2));
		double va2 = 2 * Math.atan2(Math.sqrt(va1), Math.sqrt(1 - va1));
		return radioTierra * va2;
	}
}
