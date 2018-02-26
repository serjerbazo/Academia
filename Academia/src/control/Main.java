package control;

import java.util.ArrayList;
import java.util.List;

import com.academia.DAO.AccesoBD;
import com.academia.modelos.Alumno;

public class Main {
	public static void main(String[] args) {
		
//		AccesoBD bd=new AccesoBD("academia");
		
		for(Alumno al:Alumno.parsearAlumnos()) {
			System.out.println(al.toString());
		}
		
	}

}
