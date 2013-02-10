package gwtip.server;

public class HelloService {
    
    public HelloService(){
        super();
    }
    
    public String sayHello( Person p ){
        return "Hello "+p.getFirstName()+" "+p.getLastName() +"!";
    }
    
}
