package sistema_biblioteca;

public abstract class Usuario {

	String nombre;
	String apellido;
	String mail;
	String psw;
	String estado;
	Prestamo prestamo[];

	public Usuario(String nombre, String apellido, String mail, String psw, String estado) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.mail = mail;
		this.psw = psw;
		this.estado = estado;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPsw() {
		return psw;
	}

	public void setPsw(String psw) {
		this.psw = psw;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

}
