public class Simulation {
    public static void main(String[] args) {
        final int elevatorCount = 5;
        final int floorCount = 8;

        // Initialize the controller
        Controller controller = new Controller(elevatorCount, floorCount);

        // Initialize the elevators
        Elevator[] elevators = new Elevator[elevatorCount];
        for (int id = 0; id < elevatorCount; id++) {
            elevators[id] = new Elevator(id, controller);
        }

        controller.requestElevator(6);

    }
}
