package com.academia.modelos;

public class Alumno {
	private int idAlumno;
	private String nombre;
	private String dni;
	private int cr�dito;

	public Alumno(String nombre, String dni) {
		super();
		this.nombre = nombre;
		this.dni = dni;
		this.cr�dito=100;
	}

	public Alumno(String nombre, String dni, int cr�dito) {
		super();
		this.nombre = nombre;
		this.dni = dni;
		this.cr�dito = cr�dito;
	}

	public void incrementarCr�dito(int cantidad) {
		this.cr�dito+=cantidad;
	}
	
	public void decrementarCr�dito(int cantidad) {
		this.cr�dito-=cantidad;
	}

	public int getIdAlumno() {
		return idAlumno;
	}

	public void setIdAlumno(int idAlumno) {
		this.idAlumno = idAlumno;
	}
	
	public void anadir() {
		
	}
	
	private void ConsultarCurso(Curso curso) {
		// TODO Auto-generated method stub

	}
}
