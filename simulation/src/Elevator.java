public class Elevator {
    private Controller controller;
    private final int elevatorId;

    int currentFloor;
    boolean doorsOpen;

    public Elevator(int elevatorId, Controller controller) {
        this.controller = controller;
        this.elevatorId = elevatorId;
        this.currentFloor = 1;
        this.doorsOpen = false;
    }


}
