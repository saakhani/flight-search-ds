import com.sun.nio.sctp.AbstractNotificationHandler;

import java.util.LinkedList;

public class City {
    String name;
    LinkedList<Flight> flights = new LinkedList<Flight>();
    City(String name){
        this.name = name;
    }

    public String toString(){
        return this.name;
    }


}
