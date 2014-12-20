public class Simulation {
    public static void main(String[] args) {
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

        controller.requestElevator(6);
    }
}
