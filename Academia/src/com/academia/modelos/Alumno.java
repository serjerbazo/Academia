package com.academia.modelos;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import com.academia.DAO.AccesoBD;

public class Alumno {
	private int idAlumno;
	private String nombre;
	private String dni;
	private double crédito;

	public Alumno(String nombre, String dni) {
		super();
		this.nombre = nombre;
		this.dni = dni;
		this.crédito = 100;
	}

	public Alumno(String nombre, String dni, int crédito) {
		super();
		this.nombre = nombre;
		this.dni = dni;
		this.crédito = crédito;
	}

	public void incrementarCrédito(double cantidad) {
		this.crédito += cantidad;
	}

	public void decrementarCrédito(double cantidad) {
		this.crédito -= cantidad;
	}

	public int getIdAlumno() {
		return idAlumno;
	}

	public void setIdAlumno(int idAlumno) {
		this.idAlumno = idAlumno;
	}

	public boolean anadir(Curso curso) {
		PreparedStatement ps = AccesoBD.accesoBD.insert("Clase", 5);
		ArrayList<Object> parametros = new ArrayList<>();
		parametros.add(null);
		parametros.add(this.idAlumno);
		parametros.add(curso.getId());
		parametros.add(0);
		parametros.add(false);
		return AccesoBD.accesoBD.rellenarPs(ps, parametros);
	}

	public ArrayList<Curso> consultarCurso() {
		List<Object> alumnosObj = AccesoBD.accesoBD.select("SELECT * FROM cursos_alumnos where DNI =" + this.dni + ";");
		ArrayList<Curso> cursos = new ArrayList<>();
		Curso curso = null;
		if (alumnosObj.size() > 0) {
			for (Object object : alumnosObj) {
				Object[] registro = (Object[])object;
				for (int i = 0; i < registro.length; i++) {
					if(!((String)registro[registro.length-1]).equals("Presencial")) {
//						curso = new CursoOnline(registro[3], registro[4], registro[5], registro[6], registro[7], registro[8], registro[3]);
					}else {
//TODO parsearCursos						curso = new CursoPresencial(registro[3], registro[4], registro[5], registro[6], registro[7], cupo, asistMin);
					}
				}
			}
		}
		return cursos;
	}

	public static ArrayList<Alumno> parsearAlumnos() {
		List<Object> alumnosObj = AccesoBD.accesoBD
				.select("SELECT idAlumnos,    Nombre,    DNI,    Credito FROM alumnos;");
		ArrayList<Alumno> alumnos = new ArrayList<>();
		Alumno a = null;
		for (int i = 0; i < alumnosObj.size(); i++) {

			a = new Alumno((String) ((Object[]) alumnosObj.get(i))[1], (String) ((Object[]) alumnosObj.get(i))[2],
					(int) ((Object[]) alumnosObj.get(i))[3]);
			a.setIdAlumno((int)((Object[]) alumnosObj.get(i))[0]);
			alumnos.add(a);
		}
		return alumnos;

	}

	@Override
	public String toString() {
		return "Alumno [idAlumno=" + idAlumno + ", nombre=" + nombre + ", dni=" + dni + ", crédito=" + crédito + "]";
	}

	public double getCrédito() {
		return crédito;
	}

}
