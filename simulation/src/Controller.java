import java.util.ArrayList;
import java.util.ListIterator;

public class Controller {
    private int numFloors;

    // Keeps track of the status of elevators
    private class ElevatorReference {
        private int currentFloor;
        private boolean doorsOpen;
        private Direction direction;
        private final int id;
        private Elevator elevator;

        public int getTargetFloor() {
            return targetFloor;
        }

        public void setTargetFloor(int targetFloor) {
            this.targetFloor = targetFloor;
        }

        private int targetFloor;

        public ElevatorReference(int id, Elevator elevator) {
            this.id = id;
            this.direction = Direction.NotMoving;
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

        public int getId() {
            return this.id;
        }
    }

    private ArrayList<ElevatorReference> elevatorRefs;

    public Controller(int numFloors) {
        this.numFloors = numFloors;

        this.elevatorRefs = new ArrayList<ElevatorReference>();
    }

    public int requestElevator(int floor) {
        // See if any elevators are currently waiting at the given floor
        ListIterator<ElevatorReference> iterator =  this.elevatorRefs.listIterator();
        while (iterator.hasNext()) {
            ElevatorReference ref = iterator.next();
            if (ref.currentFloor == floor && ref.direction == Direction.NotMoving) {
                // send elevator

                return ref.getId();
            }
        }

        // See if there are any elevators moving that will pass this floor
        iterator = this.elevatorRefs.listIterator();
        while (iterator.hasNext()) {
            ElevatorReference ref = iterator.next();
            if (ref.getCurrentFloor() > floor && ref.direction == Direction.Down && ref.getTargetFloor() < floor) {
                return ref.getId();
            }
            if (ref.getCurrentFloor() < floor && ref.direction == Direction.Up && ref.getTargetFloor() > floor) {
                return ref.getId();
            }
        }

        // Find the closest free elevator to send


        // If all elevators are busy, queue the one that will end closest to this floor


        return -1;
    }

    public void updateElevatorStatus(int elevatorId, int currentFloor, boolean doorsOpen) {
        ElevatorReference ref = this.elevatorRefs.get(elevatorId);  // Making assumption they IDs are as created.  Should be more robust...
        ref.setCurrentFloor(currentFloor);
        ref.setDoorsOpen(doorsOpen);
    }

    public int getElevatorId(Elevator elevator) {
        int newId = this.elevatorRefs.size();
        this.elevatorRefs.add(new ElevatorReference(newId, elevator));
        return newId;
    }
}
