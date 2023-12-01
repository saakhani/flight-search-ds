import java.util.ArrayList;
import java.util.Scanner;

public class Main {
     static RouteMap routeMap;
     public static void main(String[] args) {
         routeMap = new RouteMap();

         { //adding cities
              routeMap.addCity("Karachi");
              routeMap.addCity("Lahore");
              routeMap.addCity("Islamabad");
              routeMap.addCity("Toronto");
              routeMap.addCity("Montreal");
              routeMap.addCity("Doha");
              routeMap.addCity("New York");
              routeMap.addCity("London");
              routeMap.addCity("Sydney");
              routeMap.addCity("Istanbul");
         }

         {//adding routes
              routeMap.addFlight("Karachi", "Islamabad", 250, 1108, 115);
              routeMap.addFlight("Karachi", "Lahore", 230, 1020, 100);
              routeMap.addFlight("Karachi", "London", 650, 6313, 480);
              routeMap.addFlight("Karachi", "Doha", 400, 1563, 150);
              routeMap.addFlight("Karachi", "Istanbul", 550, 3944, 355);
              routeMap.addFlight("Karachi", "Sydney", 999, 11019, 856);
              routeMap.addFlight("Lahore", "Karachi", 250, 1029, 120);
              routeMap.addFlight("Lahore", "Islamabad", 100, 261, 55);
              routeMap.addFlight("Lahore", "London", 700, 6289, 735);
              routeMap.addFlight("Islamabad", "Lahore", 110, 269, 49);
              routeMap.addFlight("Islamabad", "Doha", 400, 2270, 215);
              routeMap.addFlight("Doha", "Islamabad", 500, 4288, 180);
              routeMap.addFlight("Doha", "Karachi", 400, 4288, 180);
              routeMap.addFlight("Doha", "Sydney", 1500, 4288, 180);
              routeMap.addFlight("Doha", "Istanbul", 399, 4288, 180);
              routeMap.addFlight("Doha", "London", 499, 4288, 180);
              routeMap.addFlight("Doha", "Montreal", 780, 4288, 180);
              routeMap.addFlight("Doha", "Toronto", 1120, 4288, 180);
              routeMap.addFlight("Doha", "New York", 400, 4288, 180);
              routeMap.addFlight("Sydney", "Doha", 400, 4288, 180);
              routeMap.addFlight("Sydney", "Lahore", 400, 4288, 180);
              routeMap.addFlight("London", "Islamabad", 400, 4288, 180);
              routeMap.addFlight("London", "Lahore", 400, 4288, 180);
              routeMap.addFlight("London", "Istanbul", 400, 4288, 180);
              routeMap.addFlight("London", "Montreal", 400, 4288, 180);
              routeMap.addFlight("London", "Toronto", 400, 4288, 180);
              routeMap.addFlight("London", "New York", 400, 4288, 180);
              routeMap.addFlight("Istanbul", "Doha", 400, 4288, 180);
              routeMap.addFlight("Istanbul", "London", 400, 4288, 180);
              routeMap.addFlight("Istanbul", "New York", 400, 4288, 180);
              routeMap.addFlight("Montreal", "London", 400, 4288, 180);
              routeMap.addFlight("Montreal", "Doha", 400, 4288, 180);
              routeMap.addFlight("Montreal", "Toronto", 400, 4288, 180);
              routeMap.addFlight("Toronto", "Montreal", 400, 4288, 180);
              routeMap.addFlight("Toronto", "London", 400, 4288, 180);
              routeMap.addFlight("Toronto", "Doha", 400, 4288, 180);
              routeMap.addFlight("Toronto", "New York", 400, 4288, 180);
              routeMap.addFlight("New York", "London", 400, 4288, 180);
              routeMap.addFlight("New York", "Montreal", 400, 4288, 180);
              routeMap.addFlight("New York", "Toronto", 400, 4288, 180);
              routeMap.addFlight("New York", "Istanbul", 400, 4288, 180);
              routeMap.addFlight("New York", "Doha", 400, 4288, 180);
         }

         printRoutes();


    }

       public static void printRoutes() {
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter your source city: ");
            String source = sc.nextLine();
            System.out.print("Enter your destination city: ");
            String destination = sc.nextLine();
            if (routeMap.getIndex(source) == -1 || routeMap.getIndex(destination) == -1){
                 System.out.println("City not found!");
                 return;
            }
            System.out.print("Enter 1 to sort by cost, 2 to sort by duration, 3 to sort by distance: ");
            int type = sc.nextInt();
            Object[] KHILHECost = routeMap.findRoute(source, destination, type);
            ArrayList<ArrayList<City>> routes = (ArrayList<ArrayList<City>>) KHILHECost[0];
            double[][] values = (double[][]) KHILHECost[1];
            for (int i = 0; i < routes.size(); i++) {
                 double cost = values[i][0];
                 double duration = values[i][1];
                 int minutes = (int) (duration % 60);
                 int hours = (int) duration / 60;
                 int days = (int) (duration / 60) / 24;
                 double distance = values[i][2];
                 System.out.println(routes.get(i) + " Cost: $" + values[i][0] + ", Duration: " + days + " Days, " + hours + " Hours " + minutes + " Minutes, " + "Distance: " + distance + "km");
            }
       }


}
