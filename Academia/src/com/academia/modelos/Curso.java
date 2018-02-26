package com.academia.modelos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public abstract class Curso {

	private int id;
	private String titulo;
	private Calendar fInicio;
	private Calendar fFin;
	private int dias;
	private double precio;
	private ArrayList<Alumno> alumnosMatri;
	private ArrayList<Alumno> alumnosApto;

	public Curso(String titulo, Calendar fInicio, Calendar fFin, int dias, double precio) {
		super();
		this.titulo = titulo;
		this.fInicio = fInicio;
		this.fFin = fFin;
		this.dias = dias;
		this.precio = precio;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Calendar getfInicio() {
		return fInicio;
	}

	public void setfInicio(Calendar fInicio) {
		this.fInicio = fInicio;
	}

	public Calendar getfFin() {
		return fFin;
	}

	public void setfFin(Calendar fFin) {
		this.fFin = fFin;
	}

	public int getDias() {
		return dias;
	}

	public void setDias(int dias) {
		this.dias = dias;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public ArrayList<Alumno> getAlumnosMatri() {
		return alumnosMatri;
	}

	public void setAlumnosMatri(ArrayList<Alumno> alumnosMatri) {
		this.alumnosMatri = alumnosMatri;
	}

	public ArrayList<Alumno> getAlumnosApto() {
		return alumnosApto;
	}

	public void setAlumnosApto(ArrayList<Alumno> alumnosApto) {
		this.alumnosApto = alumnosApto;
	}

	public boolean consultarFinalizado() {
		Calendar now = new GregorianCalendar(GregorianCalendar.YEAR, GregorianCalendar.MONTH,
				GregorianCalendar.DAY_OF_MONTH);

		int sal = fFin.compareTo(now);

		if (sal > 0)
			return true;
		else
			return false;
	}
	
	public boolean consultarAlumnoApto(Alumno al) {
		for(Alumno alu : alumnosApto ) {
			if(alu.equals(al))
				return true;
		}
		return false;
	}
	
	public abstract boolean matricular (Alumno al);
	
	public abstract boolean calificar (Alumno al);
}
