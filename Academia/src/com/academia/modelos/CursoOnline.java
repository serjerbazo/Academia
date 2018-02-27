package com.academia.modelos;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import com.academia.DAO.AccesoBD;

public class CursoOnline extends Curso {

	private int nivelMax;
	private HashMap<Alumno, Integer> seguimiento;
	List cursosPrevios;
		
	public CursoOnline(String titulo, Calendar fInicio, Calendar fFin, int dias, double precio, int nivelMax,
			List<Curso> cursosPrevios) {
		super(titulo, fInicio, fFin, dias, precio);
		this.nivelMax = nivelMax;
		this.cursosPrevios = cursosPrevios;
	}

	@Override
	public boolean matricular(Alumno al) {
		// TODO Sin hacer
		return false;
	}

	@Override
	public boolean calificar(Alumno al) {
		// TODO Hacer cosas
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
	
	public static List<Curso> parsearCursoOnline(List<Object> cursos) {
		List<Object> requeridos = AccesoBD.accesoBD
				.select("SELECT cursos_alumnos.idAlumnos,      cursos_alumnos.Nombre,      cursos_alumnos.DNI,      cursos_alumnos.Credito,      cursos_alumnos.Nivel,      cursos_alumnos.Apto,      cursos_alumnos.idCurso,      cursos_alumnos.Titulo,      cursos_alumnos.FechaInicio,      cursos_alumnos.FechaFin,      cursos_alumnos.NumeroDias,      cursos_alumnos.Precio,      cursos_alumnos.Tipo  FROM cursos_alumnos;");
		List<Curso> curso = new ArrayList<>();
		Curso c = null;
		
		for (int i = 0; i < cursos.size(); i++) {
			Object[] registro = (Object[])cursos.get(i);
			c = new CursoOnline((String)registro[1], (Calendar)registro[2], (Calendar)registro[3], (int)registro[4], (double)registro[5], (int)registro[6], curso);
			c.setId((int)registro[1]);
			curso.add(c);
		}
		return curso;
		
	}
}
