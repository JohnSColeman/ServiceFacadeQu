import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class Payment {

    private ConnectionFactory factory;
    private Gateway gateway;
    private Database database;

    public Payment(ConnectionFactory factory, Gateway gateway, Database database) {
        this.factory = factory;
        this.gateway = gateway;
        this.database = database;
    }

    /* If the connection/database call fails pass up the exception, if the gateway fails rollback and throw. */
    public void charge(Double amount, String description)  throws SQLException, IOException {
        Connection con = factory.getConnection();
        try {
            database.charge(con, amount, description);
            if (gateway.charge(amount, description)) {
                con.commit();
            } else {
                con.rollback();
            }
        } catch (IOException ioex) {
            con.rollback();
            throw ioex;
        } finally {
            con.close();
        }
    }
}
