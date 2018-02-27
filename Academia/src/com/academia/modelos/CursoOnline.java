package com.academia.modelos;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import com.academia.DAO.AccesoBD;

public class CursoOnline extends Curso {

	private int nivelMax;
	private HashMap<Alumno, Integer> seguimiento;
	private List<?> cursosPrevios;

	public CursoOnline(String titulo, Calendar fInicio, Calendar fFin, int dias, double precio, int nivelMax,
			List<?> cursosPrevios) {
		super(titulo, fInicio, fFin, dias, precio);
		this.nivelMax = nivelMax;
		this.cursosPrevios = cursosPrevios;
	}

	@Override
	public boolean matricular(Alumno al) {
		if (al.getCrédito() >= this.getPrecio()) {
			for (int i = 0; i < al.getListaCursos().size(); i++) {

				if (!cursosPrevios.contains(al.getListaCursos().get(i))) {
					return false;
				}
				if (!al.getListaCursos().get(i).getAlumnosApto().contains(al)) {

					return false;
				}

			}
			al.decrementarCrédito(this.getPrecio());
			al.anadir(this);
			this.getAlumnosMatri().add(al);
			
			return true;

		} else {
			return false;
		}

	}

	@Override
	public boolean calificar(Alumno al) {
		if (consultarNivel(al) != -1) {
			int nota = seguimiento.get(al);
			if (nota < nivelMax) {
				nota++;
				seguimiento.put(al, nota);
				return true;
			}

		}
		return false;
	}

	public int consultarNivel(Alumno al) {

		if (!getAlumnosMatri().contains(al)) {
			return -1;
		}
		return seguimiento.get(al);
	}

}
