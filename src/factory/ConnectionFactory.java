package factory;
import java.sql.*;
public class ConnectionFactory {
    
    public Connection getConnection(){
        try {       
            return DriverManager.getConnection("jdbc:mysql://localhost/projetojava", "root", "");
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    
}