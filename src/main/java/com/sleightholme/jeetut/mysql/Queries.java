package com.sleightholme.jeetut.mysql;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.sql.DataSource;
import java.sql.DatabaseMetaData;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class Queries implements Serializable {

    private final Logger LOGGER = Logger.getLogger(this.getClass().getName());

    private static final long serialVersionUID = 1L;

    @Resource(name = "java:app/jdbc/mysqlpool")
    DataSource ds;

    private static final String dropTable = "DROP TABLE IF EXISTS rivers";
    private static final String createTable = "CREATE TABLE IF NOT EXISTS rivers (id SERIAL, name VARCHAR(50),"
            + " length INT, PRIMARY KEY (id))";

    //@PostConstruct
    public void postConstruct() throws SQLException {
        if (ds == null) {
            LOGGER.log(Level.SEVERE, "Data source is null!");
            return;
        }
        try {
            com.sun.gjc.spi.jdbc40.DataSource40 unwrappedDS = (com.sun.gjc.spi.jdbc40.DataSource40) ds;
            
            com.mysql.cj.jdbc.MysqlDataSource mysqlds = unwrappedDS.unwrap(com.mysql.cj.jdbc.MysqlDataSource.class);
            //DatabaseMetaData mdm = mysqlds.getConnection().getMetaData();
            //LOGGER.log(Level.SEVERE, "{0} {1}", new Object[]{mdm.getDatabaseProductName(), mdm.getDatabaseProductVersion()});
        } catch (ClassCastException e){
            LOGGER.log(Level.SEVERE, "failed to get cast class {0}", ds.getClass().getCanonicalName());
        }
        try (Connection connection = ds.getConnection()) {
            Statement state = connection.createStatement();
            state.execute(dropTable);
            state.execute(createTable);
        }

    }

    public void insertData(String riverName, int riverLength) {
        try (Connection conn = ds.getConnection()) {
            PreparedStatement state = conn.prepareStatement("INSERT INTO rivers (name, length ) VALUES (? , ?)");
            state.setString(1, riverName);
            state.setInt(2, riverLength);
            state.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, e.getMessage());
            return;
        }
        LOGGER.log(Level.FINE, "Query executed succesfully");

    }

    public ResultSet getData() {
        try (Connection conn = ds.getConnection()) {
            Statement state = conn.createStatement();
            state.setLargeMaxRows(5);
            ResultSet rs = state.executeQuery("SELECT * FROM rivers");
            return rs;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, e.getMessage());
        }
        return null;
    }

}
