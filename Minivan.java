package minivan;

import java.util.ArrayList;

/**
 * Write a one-sentence summary of your class here. 
 * Follow it with additional
 * details about its purpose, what abstraction it represents, 
 * and how to use it.
 * 
 * @author your name (your-pid)
 * @version (place the date here, in this format: yyyy.mm.dd)
 */
public class Minivan {

	// ~ Fields ................................................
	private boolean locked;
	private boolean closed;
	private boolean safeOn;
	private Gear gear;
	private ArrayList<String> log;

	// ~ Constructor .............................

	/**
	 * Creates a new minivan object with the doors closed,
	 * gear shift in park,
	 * child lock switch off, and master unlock switch on (unlocked).
	 */
	public Minivan() {
		locked = false;
		closed = true;
		safeOn = false;
		gear = Gear.PARK;
		log = new ArrayList<>();
	}

	// ~Public Methods ...........................

	/**
	 * 
	 * @return is the door open?
	 */
	public boolean isOpen() {
		return !closed;
	}

	/**
	 * 
	 * @return is the door locked?
	 */
	public boolean isLocked() {
		return locked;
	}

	/**
	 * 
	 * @return is the safe child lock on?
	 */
	public boolean getChildLock() {
		return safeOn;
	}

	/**
	 * 
	 * @return the current gear
	 */
	public Gear getGear() {
		return gear;
	}

	/**
	 * this function check if expected == actual.
	 * If true, add the trueLog and return !actual.
	 * Otherwise, add the falseLog and return actual.
	 * @param actual
	 * @param expected
	 * @param trueLog
	 * @param falseLog
	 * @return expected if actual == expected, otherwise, !expected.
	 */
	private boolean test(boolean actual, boolean expected, 
			String trueLog, String falseLog) {
		if (expected == actual) {
			log.add(trueLog);
			return !actual;
		} else {
			log.add(falseLog);
			return actual;
		}
	}

	/**
	 * Simulate pushing lock button to lock the door.
	 */
	public void pushLockButton() {
		locked = test(locked, false, Message.DOOR_NOW_LOCKED,
				Message.DOOR_ALREADY_LOCKED);
	}

	/**
	 * Simulate pushing unlock button to unlock the door.
	 */
	public void pushUnlockButton() {
		locked = test(locked, true, Message.DOOR_NOW_UNLOCKED,
				Message.DOOR_ALREADY_UNLOCKED);
	}

	/**
	 * Update the status of the child safe lock.
	 * 
	 * @param value
	 *            the new state.
	 */
	public void setChildLock(boolean value) {
		if (value) { // turn on
			safeOn = test(safeOn, false, Message.CHILD_LOCK_NOW_ON,
					Message.CHILD_LOCK_ALREADY_ON);
		} else { // turn off
			safeOn = test(safeOn, true, Message.CHILD_LOCK_NOW_OFF,
					Message.CHILD_LOCK_ALREADY_OFF);
		}
	}
	
	/**
	 * 
	 * @return whether or not the door is closed and unlocked,
	 * and in PARK gear.
	 */
	private boolean checkDoorCloseAndUnlock() {
		if (!closed) {
			log.add(Message.DOOR_ALREADY_OPEN);
			return false;
		}
		if (locked) {
			log.add(Message.DOOR_LOCKED);
			return false;
		}
		if (gear != Gear.PARK) {
			log.add(Message.NOT_IN_PARK);
			return false;
		}
		return true;
	}

	/**
	 * Simulate pull the inside handle, try to open the door. 
	 * The door only
	 * opens if its closed/unlocked and the child safe lock is off.
	 */
	public void pullInsideHandle() {
		if (checkDoorCloseAndUnlock()) {
			if (this.safeOn) {
				log.add(Message.CHILD_LOCK_ON);
			} else {
				closed = false;
				log.add(Message.DOOR_NOW_OPEN);
			}
		}
	}

	/**
	 * Simulate pull the outside handle, try to open the door. 
	 * The door only
	 * opens if its closed/unlocked.
	 */
	public void pullOuterHandle() {
		if (checkDoorCloseAndUnlock()) {
			closed = false;
			log.add(Message.DOOR_NOW_OPEN);
		}
	}

	/**
	 * Simulate press the open button, try to open the door. 
	 * The door only opens
	 * if its closed/unlocked.
	 */
	public void pushOpenButton() {
		if (checkDoorCloseAndUnlock()) {
			closed = false;
			log.add(Message.DOOR_NOW_OPEN);
		}
	}

	/**
	 * Close the door if it is currently open.
	 */
	public void closeDoor() {
		closed = test(closed, false, Message.DOOR_NOW_CLOSED,
				Message.DOOR_ALREADY_CLOSED);
	}

	/**
	 * Change the gear.
	 * 
	 * @param newGear
	 *            the new gear
	 */
	public void setGear(Gear newGear) {
		if (gear == newGear) {
			log.add(Message.alreadyGear(gear));
		} else {
			gear = newGear;
			log.add(Message.nowGear(gear));
		}
	}

	/**
	 * Print log messages, one per line.
	 */
	public void printLog() {
		for (String value : log) {
			System.out.println(value);
		}
	}

	/**
	 * 
	 * @return the last log message.
	 */
	public String printLastMessage() {
		if (log.size() > 0) {
			return log.get(log.size() - 1);
		} else {
			return null;
		}
	}

	/**
	 * @return the string value of this minivan.
	 */
	public String toString() {
		StringBuilder buff = new StringBuilder();
		buff.append("The van is in ").append(gear).append(".\n");
		if (safeOn) {
			buff.append("The child lock is on.\n");
		} else {
			buff.append("The child lock is off.\n");
		}
		if (closed) {
			buff.append("The door is closed and ");
		} else {
			buff.append("The door is open and ");
		}
		if (locked) {
			buff.append("locked.");
		} else {
			buff.append("unlocked.");
		}
		return buff.toString();
	}
}
