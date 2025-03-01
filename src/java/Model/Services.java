package Model;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Services {

    private int serviceID;
    private String serviceName;
    private String serviceDescribe;
    private String serviceImage;
}