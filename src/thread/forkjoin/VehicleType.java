package thread.forkjoin;

public enum VehicleType {
    CAR(4, 50, "Volkswagen Golf 1.9 Litters");

    private final int tireSize;
    private final int tirePsi = 10;
    private final int fuelCapacity;
    private final String engineModel;


    public int getTireSize() {
        return tireSize;
    }

    public int getFuelCapacity() {
        return fuelCapacity;
    }

    public String getEngineModel() {
        return engineModel;
    }

    public int getTirePsi() {
        return tirePsi;
    }

    VehicleType(int tireSize, int fuelCapacity, String engineModel) {
        this.tireSize = tireSize;
        this.fuelCapacity = fuelCapacity;
        this.engineModel = engineModel;
    }
}
