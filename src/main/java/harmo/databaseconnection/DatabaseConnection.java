package harmo.databaseconnection;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseConnection {

    public static Connection getConnection() {
        Connection con;
        var dataSource = new MysqlDataSource();
        dataSource.setURL("jdbc:mysql://localhost:3306/shopping");
        try{
            con = dataSource.getConnection("harmouser","YUSAKI-399kim");
            return con;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
