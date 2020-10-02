package modelo;

import java.sql.Time;
import java.text.Format;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
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

	public LocalTime traerHoraRetiro(LocalDate fecha) {
		LocalTime horaRetiro = null;
		for (int i = 0; i < lstDiaRetiro.size(); i++) {
			if (lstDiaRetiro.get(i).getDiaSemana() == fecha.getDayOfWeek().getValue()) {
				horaRetiro = lstDiaRetiro.get(i).getHoraDesde();
			}
		}
		return horaRetiro;
	}

	public boolean agregarDiaRetiro(int diaSemana, LocalTime horaDesde, LocalTime horaHasta, int intervalo) {
		lstDiaRetiro.add(new DiaRetiro(traerIdDiaRetiro() + 1, diaSemana, horaDesde, horaHasta, intervalo));
		return true;
	}

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

	public int traerIdDiaRetiro() {
		int mayor = 0;
		if (lstDiaRetiro.size() != 0) {
			mayor = lstDiaRetiro.get(0).getId();
		}
		int actual;
		for (int i = 0; i < lstDiaRetiro.size(); i++) {
			actual = lstDiaRetiro.get(i).getId();
			if (actual > mayor) {
				mayor = actual;
			}
		}
		return mayor;
	}

//	public List<Turno> generarAgenda(LocalDate fecha) {
//		List<Turno> lstTurnos = new ArrayList<Turno>();
//		List<Turno> turnosLibres = new ArrayList<Turno>();
//		List<Turno> turnosOcupados = new ArrayList<Turno>();
//		
//		lstTurno.add(new Turno(fecha, ));
//	}

	public List<Turno> generarTurnosLibres(LocalDate fecha) {
		DiaRetiro diaRetiro = null;
		for (int i = 0; i < lstDiaRetiro.size(); i++) {
			if (lstDiaRetiro.get(i).getDiaSemana() == fecha.getDayOfWeek().getValue()) {
				diaRetiro = lstDiaRetiro.get(i);
			}
		}
		List<Turno> turno = new ArrayList<Turno>();
		if (diaRetiro != null) {
			LocalTime horaDesde = diaRetiro.getHoraDesde();
			LocalTime horaHasta = diaRetiro.getHoraHasta();
			int intervalo = diaRetiro.getIntervalo();
			for (int i = 0; i < 1; i++) {
				if (lstDiaRetiro.get(i).getDiaSemana() == fecha.getDayOfWeek().getValue()) {
					if (horaDesde.isBefore(horaHasta)) {
						turno.add(new Turno(fecha, horaDesde, false));
						horaDesde = horaDesde.plusMinutes(intervalo);
					}
				}
			}
		}
		return turno;
	}

//	public List<Pelicula> traerPelicula(Genero genero) {
//		List<Pelicula> p3 = new ArrayList<Pelicula>();
//		for (int i = 0; i < catalogo.size(); i++) {
//			if (catalogo.get(i).getGenero().equals(genero)) {
//				p3.add(new Pelicula(catalogo.get(i).getIdPelicula(), catalogo.get(i).getPelicula(),
//						catalogo.get(i).getGenero()));
//			}
//		}
//		return p3;
//
//	}

	public List<Turno> traerTurnosOcupados(LocalDate fecha) {
		DiaRetiro diaRetiro = null;
		for (int i = 0; i < lstDiaRetiro.size(); i++) {
			if (lstDiaRetiro.get(i).getDiaSemana() == fecha.getDayOfWeek().getValue()) {
				diaRetiro = lstDiaRetiro.get(i);
			}
		}
		List<Turno> turnosOcupados = new ArrayList<Turno>();
		if (diaRetiro != null) {
			LocalTime horaDesde = diaRetiro.getHoraDesde();
			LocalTime horaHasta = diaRetiro.getHoraHasta();
			int intervalo = diaRetiro.getIntervalo();
			for (int i = 0; i < lstDiaRetiro.size(); i++) {
				if (lstDiaRetiro.get(i).getDiaSemana() == fecha.getDayOfWeek().getValue()) {
					if (horaDesde.isBefore(horaHasta)) {
						turnosOcupados.add(new Turno(fecha, horaDesde, true));
						horaDesde = horaDesde.plusMinutes(intervalo);
					}
				}
			}
		}
		return turnosOcupados;

	}

	public List<Turno> generarAgenda(LocalDate fecha) {
		List<Turno> agendar = new ArrayList<Turno>();
		DiaRetiro diaRetiro = null;
		for (int i = 0; i < lstDiaRetiro.size(); i++) {
			if (lstDiaRetiro.get(i).getDiaSemana() == fecha.getDayOfWeek().getValue()) {
				diaRetiro = lstDiaRetiro.get(i);
				List<Turno> turno = new ArrayList<Turno>();
				if (diaRetiro != null) {
					LocalTime horaDesde = diaRetiro.getHoraDesde();
					LocalTime horaHasta = diaRetiro.getHoraHasta();
					int intervalo = diaRetiro.getIntervalo();
					for (int i = 0; i < 1; i++) {
						if (lstDiaRetiro.get(i).getDiaSemana() == fecha.getDayOfWeek().getValue()) {
							if (horaDesde.isBefore(horaHasta)) {
								agendar.add(new Turno(fecha, horaDesde, true));
								horaDesde = horaDesde.plusMinutes(intervalo);
							}
						}
					}
				}
			}
		}
		return agendar;
	}
}
