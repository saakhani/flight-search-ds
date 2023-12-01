public class Flight {
    City city;
    double cost;
    double distance;
    double duration; //in minutes

    public Flight(City city, double cost, double distance, double duration){
        this.city = city;
        this.cost = cost;
        this.distance = distance;
        this.duration = duration;
    }

    public String toString(){
        return city.name + ", $" + cost + ", " + distance + "km, " + duration + " minutes";
    }


}
