package test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;

import modelo.Actor;
import modelo.Cliente;
import modelo.Comercio;
import modelo.Contacto;
import modelo.DiaRetiro;
import modelo.Turno;
import modelo.Ubicacion;

public class TestTPI {
	public static void main(String[] args) {
		Ubicacion u1 = new Ubicacion(12.0, 13.0);
		Contacto cn1 = new Contacto("martin.torrents35@gmail.com", "15-5988-0377", u1);
		Cliente c1 = new Cliente(1, cn1, "Torrents", "Martin", 39642511, 'M');
		Turno t2 = new Turno(LocalDate.now(), LocalTime.now(), false);
		LocalTime horaDesde1 = LocalTime.of(9, 00, 00);
		LocalTime horaHasta1 = LocalTime.of(22, 00, 00);
		DiaRetiro dr1 = new DiaRetiro(1, 7, horaDesde1, horaHasta1, 1);
		Comercio comercio1 = new Comercio(1, cn1, "Almacen Granate", 20328304334l, 24.0, 12.0, 7, 20, 30);
		System.out.println(comercio1.agregarDiaRetiro(1, horaDesde1, horaHasta1, 1));
		System.out.println(comercio1.traerHoraRetiro(LocalDate.now()));
		String[] cuil = { "2", "0", "3", "9", "6", "4", "2", "5", "1", "1", "5" };
		System.out.println(c1.validarIdentificadorUnico(cuil));
		try {
			System.out.println((t2.generarTurnosLibres(LocalDate.now())));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println(t2.generarAgenda(LocalDate.now()));

	}
}
