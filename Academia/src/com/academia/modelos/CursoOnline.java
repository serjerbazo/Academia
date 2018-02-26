package com.academia.modelos;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class CursoOnline extends Curso {

	private int nivelMax;
	private HashMap<Alumno, Integer> seguimiento;
	private ArrayList cursosPrevios;
		
	public CursoOnline(String titulo, Calendar fInicio, Calendar fFin, int dias, double precio, int nivelMax,
			ArrayList cursosPrevios) {
		super(titulo, fInicio, fFin, dias, precio);
		this.nivelMax = nivelMax;
		this.cursosPrevios = cursosPrevios;
	}

	@Override
	public boolean matricular(Alumno al) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean calificar(Alumno al) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public int consultarNivel (Alumno al) {
		return seguimiento.get(al);
	}

	public boolean superarNivel (Alumno al) {
		
		// TODO comprobar si existe el alumno matriculado
		
		// subirle de nivel y que evolucione
		
		return false;
	}
}
