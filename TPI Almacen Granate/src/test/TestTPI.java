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
		
		LocalTime horaDesde1 = LocalTime.of(9, 00, 00);
		LocalTime horaHasta1 = LocalTime.of(22, 00, 00);
		LocalTime horaDesde2 = LocalTime.of(9, 30, 00);
		LocalTime horaHasta2 = LocalTime.of(22, 00, 00);
		Comercio comercio1 = new Comercio(1, cn1, "Almacen Granate", 20328304334l, 24.0, 12.0, 7, 20, 30);
		System.out.println(comercio1.agregarDiaRetiro(3, horaDesde1, horaHasta1, 2));
		System.out.println(comercio1.agregarDiaRetiro(7, horaDesde2, horaHasta2, 3));
		System.out.println(comercio1.traerHoraRetiro(LocalDate.now()));
		System.out.println(comercio1.generarTurnosLibres(LocalDate.now()));
		System.out.println(comercio1.generarTurnosLibres(LocalDate.of(2020, 4, 26)));
		System.out.println(comercio1.traerTurnosOcupados(LocalDate.now()));
		System.out.println(comercio1.traerTurnosOcupados(LocalDate.of(2020, 4, 26)));
	}
}
