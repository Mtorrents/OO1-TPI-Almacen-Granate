package modelo;

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
		DiaRetiro dr1 = null;
		for (int i = 0; i < lstDiaRetiro.size(); i++) {
			if (lstDiaRetiro.get(i).getHoraHasta().atDate(fecha) != null) {
				dr1 = lstDiaRetiro.get(i);
			}
		}
		return dr1.getHoraHasta();
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

	public DiaRetiro traerDiaRetiro(LocalDate fecha) {
		DiaRetiro dr = null;
		for (int i = 0; i < lstDiaRetiro.size(); i++) {
			if (lstDiaRetiro.get(i).getDiaSemana() == fecha.getDayOfWeek().getValue()) {
				dr = lstDiaRetiro.get(i);
			}
		}
		return dr;
	}

	public List<Turno> generarTurnosLibres(LocalDate fecha) {
		DiaRetiro dr = traerDiaRetiro(fecha);
		List<Turno> turno = new ArrayList<Turno>();
		if (dr != null) {
			for (int i = 0; i < lstDiaRetiro.size(); i++) {
				if (dr.getHoraDesde().isBefore(dr.getHoraHasta())) {
					turno.add(new Turno(fecha, dr.getHoraDesde(), false));
					dr.setHoraDesde(dr.getHoraDesde().plusMinutes(60 / dr.getIntervalo()));
				}
			}
		}
		return turno;
	}

	public List<Turno> traerTurnosOcupados(LocalDate fecha) {
		DiaRetiro dr = traerDiaRetiro(fecha);
		List<Turno> turnosOcupados = new ArrayList<Turno>();
		List<Turno> turnosLibres = generarTurnosLibres(fecha);
		if (turnosOcupados != null) {
			for (int i = 0; i < lstDiaRetiro.size(); i++) {
				if (turnosLibres.get(i).getDia().equals(fecha)) {
					if (turnosLibres.get(i).getHora().isAfter(dr.getHoraDesde())) {
						dr.setHoraDesde(dr.getHoraDesde().minusMinutes(60 / dr.getIntervalo()));
						if (dr.getHoraDesde().isBefore(dr.getHoraHasta())) {
							turnosOcupados.add(new Turno(fecha, dr.getHoraDesde(), true));
						}
					}
				}
			}
		}
		return turnosOcupados;

	}

	public Turno traerAgenda(LocalDate fecha) {
		Turno agenda = null;
		List<Turno> turnosOcupados = traerTurnosOcupados(fecha);
		List<Turno> turnosLibres = generarTurnosLibres(fecha);
		for (int i = 0; i < lstDiaRetiro.size(); i++) {
			if (lstDiaRetiro.get(i).getDiaSemana() == fecha.getDayOfWeek().getValue()) {
				if (turnosOcupados != null) {
					agenda = new Turno(turnosOcupados.get(i).getDia(), turnosOcupados.get(i).getHora(),
							turnosOcupados.get(i).isOcupado());
				}
				if (turnosLibres != null) {
					agenda = new Turno(turnosLibres.get(i).getDia(), turnosLibres.get(i).getHora(),
							turnosLibres.get(i).isOcupado());
				}
			}
		}
		return agenda;
	}

	public List<Turno> generarAgenda(LocalDate fecha) {
		List<Turno> agendar = new ArrayList<Turno>();
		Turno agenda = traerAgenda(fecha);
		if (agenda != null) {
			for (int i = 0; i < lstDiaRetiro.size(); i++) {
				if (agenda.getDia().equals(fecha)) {
					agendar.add(new Turno(agenda.getDia(), agenda.getHora(), agenda.isOcupado()));
				}
			}
		}
		return agendar;
	}

}
