import java.util.ArrayList;

public class Controller {
    private int numFloors;

    private class ElevatorReference {
        private int currentFloor;
        private boolean doorsOpen;

        public ElevatorReference() {

        }

        public boolean isDoorsOpen() {
            return doorsOpen;
        }

        public int getCurrentFloor() {
            return currentFloor;
        }

        public void setDoorsOpen(boolean doorsOpen) {
            this.doorsOpen = doorsOpen;
        }

        public void setCurrentFloor(int currentFloor) {
            this.currentFloor = currentFloor;
        }
    }

    private ArrayList<ElevatorReference> elevatorRefs;

    public Controller(int numFloors) {
        this.numFloors = numFloors;

        this.elevatorRefs = new ArrayList<ElevatorReference>();
    }

    public void requestElevator(int floor) {

    }

    public void updateElevatorStatus(int elevatorId, int currentFloor, boolean doorsOpen) {
        ElevatorReference ref = this.elevatorRefs.get(elevatorId);  // Making assumption they IDs are as created.  Should be more robust...
        ref.setCurrentFloor(currentFloor);
        ref.setDoorsOpen(doorsOpen);
    }

    public int getElevatorId() {
        int newId = this.elevatorRefs.size();
        this.elevatorRefs.add(new ElevatorReference());
        return newId;
    }
}
