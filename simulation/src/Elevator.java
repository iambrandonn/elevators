public class Elevator {
    private Controller controller;
    private final int elevatorId;

    int currentFloor;
    boolean doorsOpen;

    public Elevator(Controller controller) {
        this.controller = controller;
        this.currentFloor = 1;
        this.doorsOpen = false;
        this.elevatorId = controller.getElevatorId();
    }
}
