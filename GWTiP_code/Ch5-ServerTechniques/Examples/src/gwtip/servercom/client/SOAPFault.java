package gwtip.servercom.client;


public class SOAPFault extends Exception{
    
    private String details;
    
    public SOAPFault(String message, String details) {
        super( message );
        this.setDetails(details);
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
    
    
}
