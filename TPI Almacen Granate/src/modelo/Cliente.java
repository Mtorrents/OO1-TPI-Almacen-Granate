package modelo;

public class Cliente extends Actor {
	private String apellido;
	private String nombre;
	private long dni;
	private char sexo;

	public Cliente(int id, Contacto contacto, String apellido, String nombre, long dni, char sexo) {
		super(id, contacto);
		this.apellido = apellido;
		this.nombre = nombre;
		this.dni = dni;
		this.sexo = sexo;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public long getDni() {
		return dni;
	}

	public void setDni(long dni) {
		this.dni = dni;
	}

	public char getSexo() {
		return sexo;
	}

	public void setSexo(char sexo) {
		this.sexo = sexo;
	}

	public boolean validarIdentificadorUnico(long identificador) {
		boolean esDniValido = false;
		if (identificador > 9999999 && identificador < 99999999) {
			esDniValido = true;
		} else {
			esDniValido = false;
		}
		return esDniValido;
	}

	public boolean validarIdentificadorUnico(String[] identificador) {
		boolean esValido = true;

		if (identificador[0] != "2") {
			esValido = false;
		} else {
			if ((this.sexo == 'M' || this.sexo == 'm')) {
				if ((identificador[1] != "0") && (identificador[1] != "3")) {
					esValido = false;
				}
			}
			if ((this.sexo == 'F' || this.sexo == 'f')) {
				if ((identificador[1] != "7") && (identificador[1] != "3")) {
					esValido = false;
				}
			}
		}
		int claveCuil[] = { 5, 4, 3, 2, 7, 6, 5, 4, 3, 2 };
		int resultado = 0;
		for (int i = 0; i < claveCuil.length; i++) {
			resultado = resultado + (Integer.parseInt(identificador[i]) * claveCuil[i]);
		}
		resultado = resultado % 11;
		int r = resultado;
		int z = 0;
		if (r == 0) {
			z = 0;
		}

		if (r == 1) {
			if ((this.sexo == 'M' || this.sexo == 'm')) {
				if (identificador[1] != "3") {
					esValido = false;
				} else {
					z = 9;
				}
			}
		}
		if (r == 1) {
			if ((this.sexo == 'F' || this.sexo == 'f')) {
				if (identificador[1] != "3") {
					esValido = false;
				} else {
					z = 4;
				}
			}
		}
		if (r != 0 && r != 1) {
			z = 11 - r;

		}

		if (z != Integer.parseInt(identificador[10])) {
			esValido = false;
		}

		return esValido;

	}

}