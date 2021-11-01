package citel_task;

class Vehicle {
    private double load;
    private double maxLoad;
    public Vehicle(double max_load){
        maxLoad=kiloToNewts(max_load);
    }
    public double getLoad(){
        return newtsToKilo(load);
    }
    public double getMaxLoad(){
        return newtsToKilo(maxLoad);
    }
    public boolean addBox(double weight){
        if(load+kiloToNewts(weight)<=maxLoad){
            load=load+kiloToNewts(weight);
            return true;
        }
        return false;
    }
    private double kiloToNewts(double weight) { return (weight * 9.8); }
    private double newtsToKilo(double weight) { return (weight / 9.8); }
}
