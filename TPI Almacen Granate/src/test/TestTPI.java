package test;

import java.time.LocalDate;
import java.time.LocalTime;
import modelo.Cliente;
import modelo.Comercio;
import modelo.Contacto;
import modelo.Ubicacion;

public class TestTPI {
	public static void main(String[] args) {
		Ubicacion u1 = new Ubicacion(12.0, 13.0);
		Contacto cn1 = new Contacto("martin.torrents35@gmail.com", "15-5988-0377", u1);
		Cliente c1 = new Cliente(1, cn1, "Torrents", "Martin", 39642511, 'M');

		Comercio comercio1 = new Comercio(1, cn1, "Almacen Granate", 30 - 31781909 - 7, 320.0, 32.0, 1, 20, 35);
		LocalTime horaDesde1 = LocalTime.of(9, 00, 00);
		LocalTime horaHasta1 = LocalTime.of(22, 00, 00);
		System.out.println(comercio1.agregarDiaRetiro(1, horaDesde1, horaHasta1, 1));
		System.out.println(comercio1.traerHoraRetiro(LocalDate.now()));
		String[] cuil = { "2", "0", "3", "9", "6", "4", "2", "5", "1", "1", "5" };
		System.out.println(c1.validarIdentificadorUnico1(cuil));

	}
}
