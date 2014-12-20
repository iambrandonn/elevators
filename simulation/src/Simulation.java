public class Simulation {
    public static void main(String[] args) throws NoSuchFloorException {
        // Maybe set these from command line args...
        final int elevatorCount = 5;
        final int floorCount = 8;

        // Create the controller
        Controller controller = new Controller(floorCount);

        // Create the elevators
        Elevator[] elevators = new Elevator[elevatorCount];
        for (int i = 0; i < elevatorCount; i++) {
            elevators[i] = new Elevator(controller);
        }

        // Start the simulation
        int firstElevatorId = controller.requestElevator(6);
        controller.transportOccupants(firstElevatorId, 3);

        int secondElevatorId = controller.requestElevator(8);
        controller.transportOccupants(secondElevatorId, 1);

        int thirdElevatorId = controller.requestElevator(1);
        controller.transportOccupants(thirdElevatorId, 7);
    }
}
