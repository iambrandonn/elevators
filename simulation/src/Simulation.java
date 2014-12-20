public class Simulation {
    public static void main(String[] args) {
        final int elevatorCount = 5;
        final int floorCount = 8;

        // Initialize the controller
        Controller controller = new Controller(floorCount);

        // Initialize the elevators
        Elevator[] elevators = new Elevator[elevatorCount];
        for (int i = 0; i < elevatorCount; i++) {
            elevators[i] = new Elevator(controller);
        }

        controller.requestElevator(6);

    }
}
