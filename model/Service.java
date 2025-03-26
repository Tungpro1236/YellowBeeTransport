package model;

public class Service {
    private int serviceID;
    private String serviceName;
    private String serviceDescribe;
    private String serviceImage;
    
    // Getters and Setters
    public int getServiceID() {
        return serviceID;
    }
    
    public void setServiceID(int serviceID) {
        this.serviceID = serviceID;
    }
    
    public String getServiceName() {
        return serviceName;
    }
    
    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
    
    public String getServiceDescribe() {
        return serviceDescribe;
    }
    
    public void setServiceDescribe(String serviceDescribe) {
        this.serviceDescribe = serviceDescribe;
    }
    
    public String getServiceImage() {
        return serviceImage;
    }
    
    public void setServiceImage(String serviceImage) {
        this.serviceImage = serviceImage;
    }
} 