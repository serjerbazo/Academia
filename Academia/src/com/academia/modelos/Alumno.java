package com.academia.modelos;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import com.academia.DAO.AccesoBD;

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
	
	public void anadir(Curso curso) {
		PreparedStatement ps = AccesoBD.accesoBD.insert("Clase", 5);
		ArrayList<Object> parametros = new ArrayList<>();
		parametros.add(this.idAlumno);
		parametros.add(curso.getId());
		parametros.add(0);
		parametros.add(false);
		AccesoBD.accesoBD.rellenarPs(ps, parametros);
	}
	

	public ArrayList<Curso> consultarCurso(Curso curso) {
		return null;
	}
	public static ArrayList<Alumno> parsearAlumnos() {
		List<Object> alumnosObj=AccesoBD.accesoBD.select("select * from alumnos");
		ArrayList<Alumno> alumnos = new ArrayList<>();
		Alumno a = null;
		for (int i = 0; i < alumnosObj.size(); i++) {
			
			a = new Alumno((String)((Object[])alumnosObj.get(i))[1],
					(String)((Object[])alumnosObj.get(i))[2],
					(int)((Object[])alumnosObj.get(i))[3]);
			alumnos.add(a);
		}
		return alumnos;	

	}

	@Override
	public String toString() {
		return "Alumno [idAlumno=" + idAlumno + ", nombre=" + nombre + ", dni=" + dni + ", cr�dito=" + cr�dito + "]";
	}
	
	

}
