package com.academia.modelos;

import java.sql.PreparedStatement;
import java.util.ArrayList;

import com.academia.DAO.AccesoBD;

public class Alumno {
	private int idAlumno;
	private String nombre;
	private String dni;
	private int crédito;
	private AccesoBD accesoBD = new AccesoBD("academia");
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
		// TODO Hay que hacer este método

	}
}
