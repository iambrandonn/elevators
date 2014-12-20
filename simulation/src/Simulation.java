public class Simulation {
    public static void main(String[] args) {
        final int elevatorCount = 5;
        final int floorCount = 8;

        Controller controller = new Controller(elevatorCount, floorCount);

        controller.requestElevator(6);

    }
}
