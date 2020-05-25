import java.io.IOException;

public interface Gateway {

    Boolean charge(Double amount, String description) throws IOException;
}
