import java.sql.Connection;
import java.sql.SQLException;

public interface Database {

    Boolean charge(Connection con, Double amount, String description) throws SQLException;
}