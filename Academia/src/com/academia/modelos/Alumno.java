package com.academia.modelos;

public class Alumno {
	private int idAlumno;
	private String nombre;
	private String dni;
	private int crédito;

	public Alumno(String nombre, String dni) {
		super();
		this.nombre = nombre;
		this.dni = dni;
		this.crédito=100;
	}

	public Alumno(String nombre, String dni, int crédito) {
		super();
		this.nombre = nombre;
		this.dni = dni;
		this.crédito = crédito;
	}

	public void incrementarCrédito(int cantidad) {
		this.crédito+=cantidad;
	}
	
	public void decrementarCrédito(int cantidad) {
		this.crédito-=cantidad;
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
	//	System.out.println();
	}
}
