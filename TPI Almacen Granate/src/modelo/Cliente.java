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
		if(identificador > 9999999 && identificador < 99999999) {
			int aInt = (int)identificador;
			return true;
		}
		return false;
	}

}
