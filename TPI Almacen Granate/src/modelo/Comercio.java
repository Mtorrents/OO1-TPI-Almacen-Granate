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
		int i = 0;
		boolean encontrado = false;
		while (i < lstDiaRetiro.size() && encontrado == false) {
			if (lstDiaRetiro.get(i).getDiaSemana() == fecha.getDayOfWeek().getValue()) {
				horaRetiro = lstDiaRetiro.get(i).getHoraDesde();
				encontrado = true;
			}
			i++;
		}
		return horaRetiro;
	}

	public boolean agregarDiaRetiro(int diaSemana, LocalTime horaDesde, LocalTime horaHasta, int intervalo) {
		if (traerDiaRetiro(diaSemana) != null) {
			return false;
		} else {
			lstDiaRetiro.add(new DiaRetiro(traerIdDiaRetiro() +1, diaSemana, horaDesde, horaHasta, intervalo));
			return true;
		}
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

	public DiaRetiro traerDiaRetiro(int diaSemana) {
		DiaRetiro diaRetiro = null;
		boolean encontrado = false;
		int i = 0;
		while (i < lstDiaRetiro.size() && encontrado == false) {
			if (lstDiaRetiro.get(i).getDiaSemana() == diaSemana) {
				diaRetiro = lstDiaRetiro.get(i);
				encontrado = true;
			}
			i++;
		}
		return diaRetiro;
	}

	public DiaRetiro traerDiaRetiro(LocalDate fecha) {
		DiaRetiro diaRetiro = null;
		boolean encontrado = false;
		int i = 0;
		while(i < lstDiaRetiro.size() && encontrado == false) {
			if (lstDiaRetiro.get(i).getDiaSemana() == fecha.getDayOfWeek().getValue()) {
				diaRetiro = lstDiaRetiro.get(i);
				encontrado = true;
			}
			i++;
		}
		return diaRetiro;
	}


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

	public List<Turno> traerTurnosOcupados(LocalDate fecha) {
		List<Turno> turnosLibres = generarTurnosLibres(fecha);
		DiaRetiro diaRetiro = traerDiaRetiro(fecha);
		List<Turno> turnosOcupados = new ArrayList<Turno>();
		int i = 0;
		if (diaRetiro != null) {
			LocalTime horaDesde = diaRetiro.getHoraDesde();
			LocalTime horaHasta = diaRetiro.getHoraHasta();
			int intervalo = diaRetiro.getIntervalo();
			while(i < turnosLibres.size()) {
				if (turnosLibres.get(i).getDia().equals(fecha)) {
					if (horaDesde.isBefore(horaHasta)) {
						turnosOcupados.add(new Turno(fecha, horaDesde, true));
						horaDesde = horaDesde.plusMinutes(intervalo);
					}
				}
				i++;
			}
		}
		return turnosOcupados;

	}

	public List<Turno> generarAgenda(LocalDate fecha) {
		List<Turno> agendar = new ArrayList<Turno>();
		List<Turno> turnosOcupados = traerTurnosOcupados(fecha);
		DiaRetiro diaRetiro = traerDiaRetiro(fecha);
		List<Turno> turnosLibres = generarTurnosLibres(fecha);
		int i =0;
		boolean turnosOcupadosEncontrados = false;
		if (diaRetiro != null) {
			while(i < turnosLibres.size() && turnosOcupadosEncontrados == false) {
				if(turnosOcupados.isEmpty() == false) {
					agendar.add(turnosOcupados.get(i));
				} else if(turnosLibres.isEmpty() == false){
					agendar.add(turnosLibres.get(i));
				}
				i++;
			}
		}
		return agendar;
	}
	
	
}
