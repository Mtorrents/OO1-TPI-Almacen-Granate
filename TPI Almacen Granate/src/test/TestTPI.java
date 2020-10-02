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
		LocalDate diaTurno1 = LocalDate.now();
		LocalDate diaTurno2 = LocalDate.of(2020, 4, 26);
		Comercio comercio1 = new Comercio(1, cn1, "Almacen Granate", 20328304334l, 24.0, 12.0, 7, 20, 30);
		System.out.println("**************************");
		System.out.println("Agrego dia retiro: ");
		System.out.println(comercio1.agregarDiaRetiro(diaTurno1.getDayOfWeek().getValue(), horaDesde1, horaHasta1, 20));
		System.out.println(comercio1.agregarDiaRetiro(diaTurno2.getDayOfWeek().getValue(), horaDesde2, horaHasta2, 20));
		System.out.println("**************************");
		System.out.println("Traigo hora retiro: ");
		System.out.println(comercio1.traerHoraRetiro(diaTurno1));
		System.out.println(comercio1.traerHoraRetiro(diaTurno2));
		System.out.println("**************************");
		System.out.println("Genero turnos libres: ");
		System.out.println(comercio1.generarTurnosLibres(diaTurno1));
		System.out.println(comercio1.generarTurnosLibres(diaTurno1));
		System.out.println(comercio1.generarTurnosLibres(diaTurno2));
		System.out.println(comercio1.generarTurnosLibres(diaTurno2));
		System.out.println("**************************");
		System.out.println("Traigo turnos ocupdados: ");
		System.out.println("**************************");
		System.out.println(comercio1.traerTurnosOcupados(diaTurno1));
		System.out.println(comercio1.traerTurnosOcupados(diaTurno2));
		System.out.println("**************************");
		System.out.println("Genero la agenda de turnos: ");
//		System.out.println(comercio1.generarAgenda(diaTurno1));
//		System.out.println(comercio1.generarAgenda(diaTurno2));
	}
}
