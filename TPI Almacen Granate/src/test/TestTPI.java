package test;

import java.time.LocalDate;
import java.time.LocalTime;
import modelo.Articulo;
import modelo.Cliente;
import modelo.Comercio;
import modelo.Contacto;
import modelo.Entrega;
import modelo.Envio;
import modelo.RetiroLocal;
import modelo.Ubicacion;
public class TestTPI {
	public static void main(String[] args) {
		//Creo ubicacion
		Ubicacion u1 = new Ubicacion(12.0, 13.0);
		//creo contacto
		Contacto cn1 = new Contacto("martin.torrents35@gmail.com", "15-5988-0377", u1);
		//creo cliente
		Cliente c1 = new Cliente(1, cn1, "Torrents", "Martin", 39642511, 'M');
		//creo comercio
		Comercio comercio1 = new Comercio(1, cn1, "Almacen Granate", 20328304334l, 24.0, 12.0, 3, 20, 30);
		//creo 2 articulos
		Articulo articulo1 = new Articulo(1, "Papas", "123456789041", 12.0);
		Articulo articulo2 = new Articulo(2, "Carne", "123456789041", 12.0);
		//creo 2 horas desde y hasta
		LocalTime horaDesde1 = LocalTime.of(9, 00, 00);
		LocalTime horaHasta1 = LocalTime.of(22, 00, 00);
		LocalTime horaDesde2 = LocalTime.of(9, 30, 00);
		LocalTime horaHasta2 = LocalTime.of(22, 00, 00);
		//creo 2 dias turno
		LocalDate diaTurno1 = LocalDate.of(2019, 5, 20);
		LocalDate diaTurno2 = LocalDate.of(2020, 4, 26);
		//creo una ubicacion
		Ubicacion ubicacion1 = new Ubicacion(85,74);
		//creo 2 entregas una retiro y la otra a domicilo
		Entrega entrega1=new Envio(1, diaTurno1, false, horaDesde1, horaHasta1, 12.0, ubicacion1);
		Entrega entrega2= new RetiroLocal(2,diaTurno2,true,horaDesde1);
		
		System.out.println("**************************");
		System.out.println("Agrego carritos: ");
		System.out.println(comercio1.agregarCarrito(LocalDate.now(), LocalTime.now(), true, 20.0, c1, entrega1));
		System.out.println(comercio1.agregarCarrito(diaTurno2, LocalTime.now(), true, 20.0, c1, entrega2));
		
		System.out.println("**************************");
		System.out.println("Agrego dia retiro: ");
		System.out.println(comercio1.agregarDiaRetiro(diaTurno1, horaDesde1, horaHasta1, 20));
		System.out.println(comercio1.agregarDiaRetiro(diaTurno2, horaDesde2, horaHasta2, 20));
		System.out.println("**************************");
	
		System.out.println("Traigo hora retiro: ");
		System.out.println(((RetiroLocal)entrega2).traerHoraRetiro(diaTurno1));
		System.out.println(((RetiroLocal)entrega2).traerHoraRetiro(diaTurno2));
		
		System.out.println("**************************");
		System.out.println("Genero turnos libres: ");
		System.out.println(comercio1.generarTurnosLibres(diaTurno1));
		System.out.println(comercio1.generarTurnosLibres(diaTurno2));
		
		System.out.println("**************************");
		System.out.println("Traigo turnos ocupdados: ");
		System.out.println(comercio1.traerTurnosOcupados(diaTurno1));
		System.out.println(comercio1.traerTurnosOcupados(diaTurno2));
		
		System.out.println("**************************");
		System.out.println("Genero la agenda de turnos: ");
		System.out.println(comercio1.generarAgenda(diaTurno1));
		System.out.println(comercio1.generarAgenda(diaTurno2));
		
		System.out.println("**************************");
		System.out.println("Valido codigo de barras");
		System.out.println(comercio1.validarCodBarras("779123456789"));
		
		System.out.println("**************************");
		System.out.println("Agrego articulos:");
		System.out.println(comercio1.agregarArticulo("Papas", "123456789041", 12.0));
		
		System.out.println("**************************");
		System.out.println("Agrego items carrito: ");
		System.out.println(comercio1.agregar(articulo1, 30));
		System.out.println(comercio1.agregar(articulo2, 40));
		
		System.out.println("**************************");
		System.out.println("Calcular subtotal item: ");
		System.out.println(comercio1.traerItemCarrito(articulo1).calcularSubTotalItem());
		System.out.println(comercio1.traerItemCarrito(articulo2).calcularSubTotalItem());
		
		System.out.println("**************************");
		System.out.println("Calcular total carrito: ");
		System.out.println(comercio1.calcularTotalCarrito());
		
		System.out.println("**************************");
		System.out.println("Calcular descuento dia: ");
		System.out.println(comercio1.calcularDescuentoDia(3, 20));
		
		System.out.println("**************************");
		System.out.println("Calcular descuento efectivo: ");
		System.out.println(comercio1.calcularDescuentoEfectivo(20));
		
		System.out.println("**************************");
		System.out.println("Calcular descuento carrito: ");
		System.out.println(comercio1.calcularDescuentoCarrito(3, comercio1.calcularDescuentoDia(3, 20), comercio1.calcularDescuentoEfectivo(20)));
		
		System.out.println("**************************");
		System.out.println("Total a pagar carrito: ");
		System.out.println(comercio1.totalAPagarCarrito(3, comercio1.calcularDescuentoDia(3, 20), comercio1.calcularDescuentoEfectivo(20)));
		
		System.out.println("**************************");
		System.out.println("Setear costo: ");
		System.out.println(comercio1.setcosto(ubicacion1, 300, 20));
		
		System.out.println("**************************");
		System.out.println("Traer Ubicacion: ");
		System.out.println(((Envio)entrega1).traerUbicacion());
		
		System.out.println("**************************");
		System.out.println("Traer carrito: ");
		System.out.println(comercio1.traerCarrito(LocalDate.now()));
		System.out.println(comercio1.traerCarrito(diaTurno2));
		System.out.println(comercio1.calcularTotalCarrito()-comercio1.calcularDescuentoDia(3, 20));
		
		System.out.println("**************************");
		System.out.println("Eliminar el carrito: ");
		System.out.println(comercio1.eliminarCantidad(articulo1, 30));
		
		
		
	}
}
