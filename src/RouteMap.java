import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

// A directed graph using
// adjacency list representation
public class RouteMap {
    ArrayList<City> cities;
    int count;
    ArrayList<ArrayList<City>> allRoutes;

    // Constructor
    public RouteMap() {
        cities = new ArrayList<City>();
    }

    public int getIndex(String name) {
        for (int i = 0; i < count; i++) {
            if (cities.get(i).name.equals(name)) {
                return i;
            }
        }
        return -1;
    }

    public void addCity(String name) {
        cities.add(new City(name));
        count++;
    }

    public void addFlight(String source, String destination, double cost, double distance, double duration) {
        City sourceCity = cities.get(getIndex(source));
        City destinationCity = cities.get(getIndex(destination));
        Flight flight = new Flight(destinationCity, cost, distance, duration);
        sourceCity.flights.add(flight);
    }

    public void deleteCity(String name) {
        for (City city : cities) {
            if (!city.name.equals(name)) {
                this.deleteFlight(name, city.name);
            }
        }
        cities.remove(getIndex(name));
    }

    public void deleteFlight(String source, String destination) {
        City sourceCity = cities.get(getIndex(source));
        City destinationCity = cities.get(getIndex(destination));
        sourceCity.flights.removeIf(flight -> flight.city.name.equals(destinationCity.name));
    }

    public City findCity(String name) {
        int index = getIndex(name);
        if (index == -1) {
            return null;
        }
        return cities.get(index);
    }

    public ArrayList<Flight> getFlights(ArrayList<City> cityList){
        ArrayList<Flight> flightsSOUDES = new ArrayList<>();


        for (int i = 0; i < cityList.size() - 1; i++){
            City current = cityList.get(i);
            City next = cityList.get(i+1);
            LinkedList<Flight> flightArrayList = current.flights;
            for (Flight flight : flightArrayList) {
                if (flight.city.name.equals(next.name)) {
                    flightsSOUDES.add(flight);
                    break;
                }
            }
        }

        return flightsSOUDES;
    }

    private void swapRow(double[][] arr, int i, int j){
        double[] temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private void swap(Object[] arr, int i, int j){
        Object temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public Object[] findRoute(String source, String destination, int type){
        if (type < 1 || type >3){
            return null;
        }
        ArrayList<ArrayList<City>> routeSOUDES = this.getAllRoutes(source, destination);
        int numRoutes = routeSOUDES.size();

        double[][] summedValues = new double[numRoutes][3];

        int i = 0;
        for (ArrayList<City> routeSOUDE : routeSOUDES) {
            ArrayList<Flight> flightArrayList = getFlights(routeSOUDE);
            int sumCost = 0; int sumDistance = 0; int sumDuration = 0;
            for (Flight flight : flightArrayList) {
                    sumCost += flight.cost;
                    sumDuration += flight.duration;
                    sumDistance += flight.distance;
                }

            summedValues[i][0] = sumCost;
            summedValues[i][1] = sumDuration;
            summedValues[i][2] = sumDistance;
            i++;
        }

            for (int p = 0; p < numRoutes - 1; p++) {

                int min_idx = p;
                for (int q = p + 1; q < numRoutes; q++)
                    if (summedValues[q][type - 1] < summedValues[min_idx][type - 1]) {
                        min_idx = q;
                    }

                swapRow(summedValues, p, min_idx);
                Collections.swap(routeSOUDES, p, min_idx);
            }

        return new Object[]{routeSOUDES, summedValues};



    }

    public ArrayList<ArrayList<City>> getAllRoutes(String source, String destination) {
        boolean[] isVisited = new boolean[count];
        ArrayList<Integer> routes = new ArrayList<>();
        allRoutes = new ArrayList<ArrayList<City>>();
        int sourceIndex = getIndex(source);
        int destinationIndex = getIndex(destination);

        // add source to path[]
        routes.add(sourceIndex);

        // Call recursive utility
        getAllRoutesUtil(sourceIndex, destinationIndex, isVisited, routes);
        return allRoutes;
    }

    // A recursive function to print
    // all paths from 'u' to 'd'.
    // isVisited[] keeps track of
    // vertices in current path.
    // localPathList<> stores actual
    // vertices in the current path

    private void getAllRoutesUtil(Integer u, Integer d, boolean[] isVisited, List<Integer> localRoutes) {
        if (localRoutes.size() > 4){
            return;
        }

        if (u.equals(d)) {
            ArrayList<City> temp = new ArrayList<City>();
            for (Integer localRoute : localRoutes) {
                temp.add(cities.get(localRoute));
            }
            allRoutes.add(temp);

            // if match found then no need to traverse more till depth
            return;
        }

        // Mark the current node
        isVisited[u] = true;

        // Recur for all the vertices
        // adjacent to current vertex
        for (Flight flight : cities.get(u).flights) {

            int i = getIndex(flight.city.name);
            if (!isVisited[i]) {

                // store current node
                // in path[]
                localRoutes.add(i);
                getAllRoutesUtil(i, d, isVisited, localRoutes);

                // remove current node
                // in path[]
                for (int j = 0; j < localRoutes.size(); j++) {
                    if (localRoutes.get(j) == i) {
                        localRoutes.remove(j);
                        break;
                    }
                }
            }
        }

        // Mark the current node
        isVisited[u] = false;
    }

    // Driver program
    public String toString() {
        String result = "";
        for (City city : cities) {
            if (!city.flights.isEmpty()) {
                String flightsString = "";
                for (int j = 0; j < city.flights.size(); j++) {
                    flightsString += city.flights.get(j) + " | ";
                }
                result += (city.name + ", Flights --> [" + flightsString + "\b\b]\n");
            } else {
                result += (city.name + ", [No Flights]\n");
            }
        }
        return result;
    }
}