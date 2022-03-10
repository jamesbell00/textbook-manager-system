import java.sql.*;

public class EstablishConnection {

    private Connection connection;
    private Statement statement;
    static final String url = "JB_NJ_HJ_JFP_TextbookManager";
    static final String user = "root";
    static final String pass = "password";

    public EstablishConnection() {

        try {

        this.connection =  DriverManager.getConnection("jdbc:mysql://localhost:3306/" + url, user, pass);
        this.statement = connection.createStatement();
        } catch (Exception e) {

            System.out.println(e);
            System.exit(1);
        }
    }

    public Statement getStatement() {

        return this.statement;
    }
    
    public Connection getConnection() {

        return this.connection;
    }
}
