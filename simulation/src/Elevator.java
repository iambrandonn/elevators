public class Elevator {
    private Controller controller;
    private final int elevatorId;

    int currentFloor;
    boolean doorsOpen;
    private boolean occupied; // Will ignore for now
    private Direction direction;

    public Elevator(Controller controller) {
        this.controller = controller;
        this.currentFloor = 1;
        this.doorsOpen = false;
        this.elevatorId = controller.getElevatorId(this);
    }

    public void sendElevator(int floor) {
        if (this.doorsOpen) {
            this.doorsOpen = false;
            this.updateStatus();
        }
        if (floor < this.currentFloor) {
            this.direction = Direction.Down;
        } else if (floor == this.currentFloor) {
            this.direction = Direction.NotMoving;
        } else {
            this.direction = Direction.Up;
        }

        while (this.currentFloor != floor) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ie) {
            }

            if (this.direction == Direction.Down) {
                this.currentFloor--;
            } else {
                this.currentFloor++;
            }

            this.updateStatus();
        }

        this.doorsOpen = true;
        this.updateStatus();
    }

    private void updateStatus() {
        this.controller.updateElevatorStatus(this.elevatorId, this.currentFloor, this.doorsOpen);
    }
}
