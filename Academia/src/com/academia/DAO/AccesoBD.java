package com.academia.DAO;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
	public static AccesoBD accesoBD = new AccesoBD("academia");
	// private static final Logger log = LogManager.getLogger(AccesoBD.class);

	/**
	 * Creates a new connection with MySQL on localhost user root blank pwd
	 * 
	 * @throws SQLException
	 */
	public AccesoBD(String databaseName) {
		this.user = "root";
		this.databaseName = databaseName;
		try {
			// Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://192.168.1.156:3306/" + databaseName, user, password);
		} catch (SQLException
		// |ClassNotFoundException
		e) {
			// log.error(e.getMessage());
			e.printStackTrace();
		}
		// log.info("Conexión establecida");
	}

	public AccesoBD(String ip, String port, String dbType, String user, String password, String databaseName) {
		this.ip = ip;
		this.port = port;
		this.dbType = dbType;
		this.user = user;
		this.password = password;
		this.databaseName = databaseName;
		if (this.dbType.equalsIgnoreCase("oracle")) {
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
			} catch (ClassNotFoundException e1) {
				// log.error(e1.getMessage());
				e1.printStackTrace();
			}
			try {
				con = DriverManager.getConnection("jdbc:oracle:thin:@" + this.ip + ":" + this.port + ":xe", user,
						password);
			} catch (SQLException e) {
				// log.error(e.getMessage());
				e.printStackTrace();
			}
		} else if (this.dbType.equalsIgnoreCase("mysql")) {
			try {
				con = DriverManager.getConnection("jdbc:mysql://" + this.ip + ":" + this.port + "/" + databaseName,
						user, password);
			} catch (SQLException e) {
				// log.error(e.getMessage());
			}
		}

	}

	public PreparedStatement insert(String nombretabla, int numerocampos) {
		String query = "";
		query += "INSERT INTO " + this.databaseName + "\\." + nombretabla + " VALUES (?";
		for (int i = 1; i < numerocampos; i++) {
			query += ",?";
		}
		query += "\\)";
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ps;

	}

	public boolean rellenarPs(PreparedStatement ps, ArrayList<Object> parametros) {
		for (int i = 0; i < parametros.size(); i++) {
			try {
				ps.setObject(i, parametros.get(i));
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
			try {
				ps.execute();
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
		return true;

	}

	public List<Object> select(String query) {
		ResultSet rs = null;
		Statement st = null;
		List<Object> al = new ArrayList<>();
		Object[] obj = null;
		try {
			st = con.createStatement();
			rs = st.executeQuery(query);
			
		} catch (SQLException e) {
			// log.error(e.getMessage());
			e.printStackTrace();
		}

		try {
			while (rs.next()) {
				obj = new Object[rs.getMetaData().getColumnCount()];
				for (int j = 1; j < rs.getMetaData().getColumnCount()+1; j++) {					
					obj[j-1]=rs.getObject(j);
				}
				al.add(obj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return al;
	}

	/**
	 * <p>
	 * Este método recibe una query completa y le añade parámetros
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
	 * El resultado sería:<br/>
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
			if (all[i].getName().startsWith("get")) {
				try {
					String valor = (String) all[i].invoke(object, "");
					if (StringUtils.isBlank(valor)) {
						System.out.println("El valor " + valor + " no es válido para el parámetro "
								+ all[i].toString().substring(3));
					} else {
						parameters.put(all[i].toString().substring(3), valor);
					}
				} catch (IllegalArgumentException e) {
					// log.error(e.getMessage());
				} catch (IllegalAccessException e) {
					// log.error(e.getMessage());
				} catch (InvocationTargetException e) {
					// log.error(e.getMessage());
				}

			}
		}
		return parameters;
	}

	public Connection getCon() {
		return con;
	}

}
