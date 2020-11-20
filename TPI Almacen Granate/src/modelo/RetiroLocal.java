package modelo;

import java.time.LocalDate;
import java.time.LocalTime;

public class RetiroLocal extends Entrega {
	private LocalTime horaEntrega;

	public RetiroLocal(int id, LocalDate fecha, boolean efectivo, LocalTime horaEntrega) {
		super(id, fecha, efectivo);
		this.horaEntrega = horaEntrega;
	}

	public LocalTime getHoraEntrega() {
		return horaEntrega;
	}

	public void setHoraEntrega(LocalTime horaEntrega) {
		this.horaEntrega = horaEntrega;
	}
	//*********************** punto 2 *******************************
	public LocalTime traerHoraRetiro(LocalDate fecha) {
		LocalTime horaRetiro = null;
		if(this.fecha == fecha) {
			horaRetiro = this.horaEntrega;
		}
		return horaRetiro;
	}

	@Override
	public String toString() {
		return "RetiroLocal [ id=" + id + ", fecha=" + fecha + ", efectivo=" + efectivo + ", horaEntrega=" + horaEntrega 	+ "]";
	}

}
