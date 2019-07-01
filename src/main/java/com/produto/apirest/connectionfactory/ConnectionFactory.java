package com.produto.apirest.connectionfactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionFactory {
	public static Connection getConnection(){
        String path = System.getProperty("user.dir") + "/config/database.properties";
        Properties prop = new Properties();
        
        Connection connection = null;
        
        try {
            InputStream input = new FileInputStream(path);
            prop.load(input);
            Class.forName( "com.mysql.cj.jdbc.Driver" );
            String url ="jdbc:mysql://localhost:3306/produtoapi?useTimezone=true&serverTimezone=UTC";
            String user = "root";
            String senha = "123";
            connection =  DriverManager.getConnection(url, user, senha);
            System.out.println("Successfully connection");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return connection;
    }
}
