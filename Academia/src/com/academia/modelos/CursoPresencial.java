package com.academia.modelos;

import java.util.ArrayList;
import java.util.Calendar;

public class CursoPresencial extends Curso {

	private int cupo;
	private int plazas;
	private int asistMin;
	private ArrayList<ArrayList<Alumno>> asistencia;

	public CursoPresencial(String titulo, Calendar fInicio, Calendar fFin, int dias, double precio, int cupo,
			int asistMin) {
		super(titulo, fInicio, fFin, dias, precio);
		this.cupo = cupo;
		this.plazas = this.cupo - this.getAlumnosMatri().size();
		this.asistMin = asistMin;
	}

	@Override
	public boolean matricular(Alumno al) {
		if (al.getCrédito() >= this.getPrecio() && this.plazas > 0) {
			if (super.consultarAlumnoApto(al)) {
				return false;
			} else {
				al.decrementarCrédito(this.getPrecio());
				return al.anadir(this);
			}
		}
		return false;
	}

	@Override
	public boolean calificar(Alumno al) {
		int contador = 0;
		for (int i = 0; i < this.asistencia.size(); i++) {// días
			if (this.asistencia.get(i).contains(al)) {
				contador++;
			}
		}
		if (contador >= this.asistMin) {
			this.getAlumnosApto().add(al);
			return true;
		} else {
			return false;
		}
	}

	public void registrarAsist(int dia, ArrayList<Alumno> alus) {
		asistencia.add(dia--, alus);
	}

	public int consultarAsist(Alumno al) {
		int cont = 0;
		for (ArrayList<Alumno> dias : asistencia) {
			for (Alumno alu : dias) {
				if (alu.equals(al)) {
					cont++;
				}
			}
		}
		return cont;
	}

}
