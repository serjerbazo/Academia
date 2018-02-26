package com.academia.DAO;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AccesoBD {
	private Connection con;
	private String ip;
	private String port;
	private String dbType;
	private String user;
	private String password = "";
	private String databaseName;
	private static final Logger log = LogManager.getLogger(AccesoBD.class);

	/**
	 * Creates a new connection with MySQL on localhost user root blank pwd
	 * 
	 * @throws SQLException
	 */
	public AccesoBD(String databaseName) {
		this.user = "root";
		try {
			// Class.forName("oracle.jdbc.driver.Driver");
			con = DriverManager.getConnection("jdbc:mysql://192.168.1.156:3306/" + databaseName, user, password);
		} catch (SQLException
		// |ClassNotFoundException
		e) {
			log.error(e.getMessage());
		}
		log.info("Conexi�n establecida");
	}

	public AccesoBD(String ip, String port, String dbType, String user, String password, String databaseName) {
		this.ip = ip;
		this.port = port;
		this.dbType = dbType;
		this.user = user;
		this.password = password;
		if (this.dbType.equalsIgnoreCase("oracle")) {
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
			} catch (ClassNotFoundException e1) {
				log.error(e1.getMessage());
			}
			try {
				con = DriverManager.getConnection("jdbc:oracle:thin:@" + this.ip + ":" + this.port + ":xe", user,
						password);
			} catch (SQLException e) {
				log.error(e.getMessage());
			}
		}else if(this.dbType.equalsIgnoreCase("mysql")) {
			try {
				con = DriverManager.getConnection("jdbc:mysql://" + this.ip + ":" + this.port +"/"+ databaseName, user,
						password);
			} catch (SQLException e) {
				log.error(e.getMessage());
			}
		}

	}

	public List<Object> select(String query) {
		ResultSet rs = null;
		List<Object> al = new ArrayList<>();
		Object[] obj=null;
		try {
			rs = con.createStatement().executeQuery(query);
			obj =new Object[rs.getMetaData().getColumnCount()];
		} catch (SQLException e) {
			log.error(e.getMessage());
		}
		
		try {
			while (rs.next()) {
				for (int j = 0; j < rs.getMetaData().getColumnCount(); j++) {
					obj[j]=rs.getObject(j);
				}
				al.add(obj);
			}
		} catch (SQLException e) {
			log.error(e.getMessage());
		}
		return al;
	}

	/**
	 * <p>
	 * Este m�todo recibe una query completa y le a�ade par�metros
	 * </p>
	 * <p>
	 * Ejemplo:
	 * </p>
	 * String query ="Select d.id #select#<br/>
	 * <p>
	 * from dual d#tables#
	 * </p>
	 * where d.name like ?#conditions#";<br/>
	 * String select = "t.name";<br/>
	 * String tables = "tablenames t";<br/>
	 * String conditions = "d.id = t.id";<br/>
	 * query = UtilidadesBD.modifySelect(query, select, tables, conditions,
	 * true);<br/>
	 * <br/>
	 * 
	 * El resultado ser�a:<br/>
	 * Select d.id,t.name #select#<br/>
	 * from dual d,tablenames t#tables#<br/>
	 * where d.name like ?<br/>
	 * and d.id = t.id#conditions#
	 * 
	 * @param query
	 *            - Sentencia select
	 * @param select
	 *            - Valores a recoger
	 * @param tables
	 *            - Tablas a usar
	 * @param conditions
	 *            - Relaciones entre tablas
	 * @param forceNevaTabla
	 *            - Si se quiere agregar una tabla
	 * @return query
	 */
	public static String modifySelect(String query, String select, String tables, String conditions,
			boolean forceNuevaTabla) {

		if (query.contains(tables.trim().split(" ")[1]) || forceNuevaTabla) {
			query = query.replace("#select#", ", " + select + "#select#");
			query = query.replace("#tables#", ", " + tables + "#tables#");
			if (query.toUpperCase().contains("where".toUpperCase())) {
				query = query.replace("#conditions#", " and " + conditions + "#conditions#");
			} else {
				query = query.replace("#conditions#", " where " + conditions + "#conditions#");
			}
		}
		return query;
	}

	/**
	 * This method invokes all the getters and returns its not null values as a Map
	 * 
	 * @param object
	 * @return
	 */
	public Map<String, Object> getGettersValue(Object object) {
		Class<? extends Object> clase = object.getClass();
		Map<String, Object> parameters = new HashMap<>();
		Method all[] = clase.getDeclaredMethods();
		// List<Method> getters = new ArrayList<Method>();
		for (int i = 0; i < all.length; i++) {
			if (all[i].toString().startsWith("get")) {
				try {
					String valor = (String) all[i].invoke(object, "");
					if (StringUtils.isBlank(valor)) {
						System.out.println("El valor " + valor + " no es v�lido para el par�metro "
								+ all[i].toString().substring(3));
					} else {
						parameters.put(all[i].toString().substring(3), valor);
					}
				} catch (IllegalArgumentException e) {
					log.error(e.getMessage());
				} catch (IllegalAccessException e) {
					log.error(e.getMessage());
				} catch (InvocationTargetException e) {
					log.error(e.getMessage());
				}

			}
		}
		return parameters;
	}

}