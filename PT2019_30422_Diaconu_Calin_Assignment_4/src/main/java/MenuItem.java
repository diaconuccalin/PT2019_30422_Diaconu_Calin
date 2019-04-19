import java.io.Serializable;

public interface MenuItem extends Serializable {
    int computePrice();
    String getName();
}
