package com.sleightholme.jeetut.mysql;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;

import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.sql.DataSource;

import com.sun.istack.logging.Logger;
import java.sql.DatabaseMetaData;

@RequestScoped
public class Queries implements Serializable {

    Logger LOGGER = Logger.getLogger(this.getClass());
    
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Resource(lookup="jdbc/mysqlpool")
	DataSource ds;
	
	private static final String dropTable = "DROP TABLE IF EXISTS rivers";
	private static final String createTable = "CREATE TABLE IF NOT EXISTS rivers (id SERIAL, name VARCHAR(50),"
			+ " length INT, PRIMARY KEY (id))";
	
	
	//@PostConstruct
	public void postConstruct() throws SQLException{
		if (ds == null){
			LOGGER.log(Level.SEVERE, "Data source is null!");
			return;
		}
                com.sun.gjc.spi.jdbc40.DataSource40 mysqlds = (com.sun.gjc.spi.jdbc40.DataSource40) ds;
                DatabaseMetaData mdm = mysqlds.getConnection().getMetaData();
                LOGGER.log(Level.SEVERE, mdm.getDatabaseProductName() + mdm.getDatabaseProductVersion());
                
            try (Connection connection = ds.getConnection()) {
                Statement state = connection.createStatement();
                state.execute(dropTable);
                state.execute(createTable);
            }
		
	}
	
	public void insertData(String riverName, int riverLength){
		try (Connection conn = ds.getConnection()){
			PreparedStatement state = conn.prepareStatement("INSERT INTO rivers (name, length ) VALUES (? , ?)");
			state.setString(1, riverName);
			state.setInt(2, riverLength);
			state.executeQuery();
		} catch (SQLException e) {
			Logger.getLogger(getClass()).log(Level.SEVERE,e.getMessage());
			return;
		}
		Logger.getLogger(getClass()).log(Level.FINE, "Query executed succesfully");
		
	}
	
	public ResultSet getData(){
		try (Connection conn = ds.getConnection()){
			Statement state = conn.createStatement();
			ResultSet rs = state.executeQuery("SELECT * FROM rivers");
			return rs;
		} catch (SQLException e) {
			Logger.getLogger(getClass()).log(Level.SEVERE,e.getMessage());
		}
		return null;
	}
	
}
