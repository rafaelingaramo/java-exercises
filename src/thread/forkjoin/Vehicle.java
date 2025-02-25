package thread.forkjoin;

class Vehicle {
    private Tire[] tires;
    private Engine engine;
    private FuelLine fuelLine;

    public static Vehicle from(Tire[] tires, Engine engine, FuelLine fuelLine) {
        Vehicle vehicle = new Vehicle();
        vehicle.tires = tires;
        vehicle.engine = engine;
        vehicle.fuelLine = fuelLine;
        return vehicle;
    }

    public static Vehicle tires(Tire[] tires) {
        Vehicle vehicle = new Vehicle();
        vehicle.tires = tires;
        return vehicle;
    }

    public static Vehicle engine(String engineModel) {
        Vehicle vehicle = new Vehicle();
        vehicle.engine = new Engine(engineModel);
        return vehicle;
    }

    public static Vehicle fuelLine(VehicleType vehicleType) {
        Vehicle vehicle = new Vehicle();
        vehicle.fuelLine = new FuelLine(vehicleType.getFuelCapacity());
        return vehicle;
    }

    public Tire[] getTires() {
        return tires;
    }

    public Engine getEngine() {
        return engine;
    }

    public FuelLine getFuelLine() {
        return fuelLine;
    }

    @Override
    public String toString() {
        StringBuilder tiresInfo = new StringBuilder("Tires: ");
        if (tires != null) {
            for (Tire tire : tires) {
                tiresInfo.append(tire).append(" ");
            }
        } else {
            tiresInfo.append("none");
        }
        return String.format("Vehicle [ %s, Engine: %s, FuelLine: %s ]",
                tiresInfo.toString().trim(),
                (engine != null ? engine : "none"),
                (fuelLine != null ? fuelLine : "none"));
    }
}
