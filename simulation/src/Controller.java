import java.util.*;

public class Controller {
    private int numFloors;
    private Queue<Integer> queuedRequests;

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
            this.elevator = elevator;
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
        this.queuedRequests = new LinkedList<Integer>();
    }

    public int requestElevator(int floor) throws NoSuchFloorException {
        if (floor > this.numFloors || floor < 1) {
            throw new NoSuchFloorException("Floor doesn't exist");
        }

        // See if any elevators are currently waiting at the given floor
        ListIterator<ElevatorReference> iterator =  this.elevatorRefs.listIterator();
        while (iterator.hasNext()) {
            ElevatorReference ref = iterator.next();
            if (ref.currentFloor == floor && ref.direction == Direction.NotMoving) {
                ref.elevator.sendElevator(floor);
                return ref.getId();
            }
        }

        // See if there are any elevators moving that will pass this floor
        iterator = this.elevatorRefs.listIterator();
        while (iterator.hasNext()) {
            ElevatorReference ref = iterator.next();
            if (ref.getCurrentFloor() > floor && ref.direction == Direction.Down && ref.getTargetFloor() < floor) {
                ref.elevator.sendElevator(floor);
                return ref.getId();
            }
            if (ref.getCurrentFloor() < floor && ref.direction == Direction.Up && ref.getTargetFloor() > floor) {
                ref.elevator.sendElevator(floor);
                return ref.getId();
            }
        }

        // Find the closest free elevator to send
        iterator = this.elevatorRefs.listIterator();
        ElevatorReference closest = null;
        int closestDistance = Integer.MAX_VALUE;
        while (iterator.hasNext()) {
            ElevatorReference ref = iterator.next();
            if (ref.direction == Direction.NotMoving && Math.abs(ref.getCurrentFloor() - floor) < closestDistance) {
                closest = ref;
                closestDistance = Math.abs(ref.getCurrentFloor() - floor);
            }
        }
        if (closest != null) {
            closest.elevator.sendElevator(floor);
            return closest.getId();
        }

        // If all elevators are busy, queue the one that will end closest to this floor
        queuedRequests.add(floor);

        return -1;
    }

    public void updateElevatorStatus(int elevatorId, int currentFloor, boolean doorsOpen) {
        ElevatorReference ref = this.elevatorRefs.get(elevatorId);  // Making assumption they IDs are as created.  Should be more robust...
        ref.setCurrentFloor(currentFloor);
        ref.setDoorsOpen(doorsOpen);

        // See if there are requests queued
        if (this.queuedRequests.size() > 0) {
            if (doorsOpen == false && ref.direction == Direction.NotMoving) {
                int destinationFloor = this.queuedRequests.remove();
                ref.elevator.sendElevator(destinationFloor);
            }
        }
    }

    public int getElevatorId(Elevator elevator) {
        int newId = this.elevatorRefs.size();
        this.elevatorRefs.add(new ElevatorReference(newId, elevator));
        return newId;
    }

    public void transportOccupants(int elevatorId, int destinationFloor) throws NoSuchFloorException {
        if (destinationFloor > numFloors || destinationFloor < 1) {
            throw new NoSuchFloorException("Floor doesn't exist");
        }
        ElevatorReference ref = this.elevatorRefs.get(elevatorId);  // Making assumption they IDs are as created.  Should be more robust...
        ref.elevator.sendElevator(destinationFloor);
    }
}
