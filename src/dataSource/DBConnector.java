package dataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Tomoe
 */
public class DBConnector
{// connecting testDB

    private static final String id = "SEM2_GR21";
    private static final String pw = "SEM2_GR21";
    private static final String idtest = "SEM2_TEST_GR21";
    private static final String pwtest = "SEM2_TEST_GR21";

    private static Connection connection;

    private static void intializeConnection()
    {
        try
        {
            connection = DriverManager.getConnection("jdbc:oracle:thin:@datdb.cphbusiness.dk:1521:dat", id, pw);
        }
        catch (SQLException e)
        {
        }
    }

    private static void intializeTestConnection()
    {
        try
        {
            connection = DriverManager.getConnection("jdbc:oracle:thin:@datdb.cphbusiness.dk:1521:dat", idtest, pwtest);
        }
        catch (SQLException e)
        {
        }
    }

    public static Connection getConnection()
    {
        if (connection == null)
        {
            intializeConnection();
        }
        return connection;
    }

    public static Connection getTestConnection()
    {
        if (connection == null)
        {
            intializeTestConnection();
        }
        return connection;
    }

    public static void releaseConnection()
    {
        try
        {
            connection.close();
            connection = null;
        }
        catch (SQLException e)
        {
        }
    }
}
