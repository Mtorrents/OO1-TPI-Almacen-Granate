package modelo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Turno {
	private LocalDate dia;
	private LocalTime hora;
	private boolean ocupado;

	public Turno(LocalDate dia, LocalTime hora, boolean ocupado) {
		super();
		this.dia = dia;
		this.hora = hora;
		this.ocupado = ocupado;
	}

	public LocalDate getDia() {
		return dia;
	}

	public void setDia(LocalDate dia) {
		this.dia = dia;
	}

	public LocalTime getHora() {
		return hora;
	}

	public void setHora(LocalTime hora) {
		this.hora = hora;
	}

	public boolean isOcupado() {
		return ocupado;
	}

	public void setOcupado(boolean ocupado) {
		this.ocupado = ocupado;
	}

	@Override
	public String toString() {
		return "Turno [dia=" + dia + ", hora=" + hora + ", ocupado=" + ocupado + "]";
	}

	public List<Turno> generarTurnosLibres(LocalDate fecha) throws Exception {
		List<Turno> t1 = new ArrayList<Turno>();
		t1.add(new Turno(dia, hora, ocupado));
		boolean encontrado = false;
		for (int i = 0; i < t1.size(); i++) {
			if (t1.get(i).isOcupado() == false) {
				encontrado = true;
				if (t1.get(i).getDia().equals(fecha)) {
					t1.add(new Turno(dia, hora, ocupado));
				}
			}
		}
		if (!encontrado) {
			throw new Exception("No hay turnos libres para esa fecha");
		}
		return t1;
	}

	public List<Turno> traerTurnosOcupados(LocalDate fecha) throws Exception {
		List<Turno> t1 = new ArrayList<Turno>();
		t1.add(new Turno(dia, hora, ocupado));
		boolean encontrado = false;
		for (int i = 0; i < t1.size(); i++) {
			if (t1.get(i).isOcupado() == true) {
				encontrado = true;
				if (t1.get(i).getDia().equals(fecha)) {
					t1.add(new Turno(dia, hora, ocupado));
				}
			}
		}
		if (!encontrado) {
			throw new Exception("No hay turnos ocupados para esa fecha");
		}
		return t1;
	}

	public List<Turno> generarAgenda(LocalDate fecha) {
		List<Turno> t1 = new ArrayList<Turno>();
		t1.add(new Turno(dia, hora, ocupado));
		for (int i = 0; i < t1.size(); i++) {
			if (t1.get(i).getDia().equals(fecha)) {
				t1.add(new Turno(dia, hora, ocupado));
			}
		}
		return t1;
	}

}
