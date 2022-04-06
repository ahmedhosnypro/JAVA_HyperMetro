import java.io.Serial;
import java.io.Serializable;

class User implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    String name;
    transient String password;
}