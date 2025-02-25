package thread.forkjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ForkJoin {

    static class VehicleAssemblerRecursiveTask extends RecursiveTask<Vehicle> {
        private BuildType buildType;

        private enum BuildType {
            TIRE, ENGINE, FUEL_LINE
        }
        private final VehicleType vehicleType;

        public VehicleAssemblerRecursiveTask(VehicleType vehicleType) {
            this.vehicleType = vehicleType;
        }

        public VehicleAssemblerRecursiveTask(VehicleType vehicleType, BuildType buildType) {
            this.vehicleType = vehicleType;
            this.buildType = buildType;
        }

        @Override
        protected Vehicle compute() {
            //build if it's small enough
            if (buildType != null) {
                switch (buildType) {
                    case TIRE -> {
                        return buildTire(vehicleType);
                    }
                    case ENGINE -> {
                        return buildEngine(vehicleType);
                    }
                    case FUEL_LINE -> {
                        return buildFuelLine(vehicleType);
                    }
                }
            }


            //if not then call for parallelization
            VehicleAssemblerRecursiveTask tireTask = new VehicleAssemblerRecursiveTask(vehicleType, BuildType.TIRE);
            VehicleAssemblerRecursiveTask engineTask = new VehicleAssemblerRecursiveTask(vehicleType, BuildType.ENGINE);
            VehicleAssemblerRecursiveTask fuelLineTask = new VehicleAssemblerRecursiveTask(vehicleType, BuildType.FUEL_LINE);

            tireTask.fork();
            engineTask.fork();
            fuelLineTask.fork();

            Vehicle assembledTires = tireTask.join();
            Vehicle assembledEngine = engineTask.join();
            Vehicle assembledFuelLine = fuelLineTask.join();

            return Vehicle.from(assembledTires.getTires(), assembledEngine.getEngine(), assembledFuelLine.getFuelLine());
        }

        private Vehicle buildFuelLine(VehicleType vehicleType) {
            System.out.printf("Building fuel line on thread: %s\n", Thread.currentThread().getName());
            return Vehicle.fuelLine(vehicleType);
        }

        private Vehicle buildEngine(VehicleType vehicleType) {
            System.out.printf("Building engine on thread: %s\n", Thread.currentThread().getName());
            return Vehicle.engine(vehicleType.getEngineModel());
        }

        private Vehicle buildTire(VehicleType vehicleType) {
            System.out.printf("Building tires on thread: %s\n", Thread.currentThread().getName());
            int tireSize = vehicleType.getTireSize();
            Tire[] tires = new Tire[tireSize];
            for (int i=0; i<tireSize; i++) {
                tires[i] = new Tire(vehicleType.getTirePsi());
            }
            return Vehicle.tires(tires);
        }
    }

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
        VehicleAssemblerRecursiveTask task = new VehicleAssemblerRecursiveTask(VehicleType.CAR);
        Vehicle vehicle = forkJoinPool.invoke(task);
        System.out.println(vehicle);
    }
}
