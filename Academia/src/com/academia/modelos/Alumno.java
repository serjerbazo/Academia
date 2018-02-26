package com.academia.modelos;

import java.sql.PreparedStatement;
import java.util.ArrayList;

import com.academia.DAO.AccesoBD;

public class Alumno {
	private int idAlumno;
	private String nombre;
	private String dni;
	private int cr�dito;
	private AccesoBD accesoBD = new AccesoBD("academia");

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
	
	public void anadir(Curso curso) {
		PreparedStatement ps = accesoBD.insert("Clase", 5);
		ArrayList<Object> parametros = new ArrayList<>();
		parametros.add(this.idAlumno);
		parametros.add(curso.getId());
		parametros.add(0);
		parametros.add(false);
		accesoBD.rellenarPs(ps, parametros);
	}
	
	private void ConsultarCurso(Curso curso) {
		// TODO Hay que hacer este m�todo

	}
}
