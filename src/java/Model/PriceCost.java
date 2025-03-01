package Model;
import lombok.Builder;
import lombok.Data;
@Data
@Builder

public class PriceCost {
    private int PriceCostID;
    private String Description;
    private double Cost;
}
