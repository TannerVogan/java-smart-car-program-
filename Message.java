package minivan;

public class Message {
    
    public static final String DOOR_NOW_OPEN = "The door is now open.";
    public static final String DOOR_NOW_CLOSED = "The door is now closed";
    public static final String DOOR_NOW_LOCKED = "The door is now locked";
    public static final String DOOR_NOW_UNLOCKED = "The door is now unlocked.";
    public static final String CHILD_LOCK_NOW_ON = "The child lock is now on.";
    public static final String CHILD_LOCK_NOW_OFF = "The child lock is now off.";

    public static final String DOOR_ALREADY_OPEN = "The door is already open.";
    public static final String DOOR_ALREADY_CLOSED = "The door is already closed";
    public static final String DOOR_ALREADY_LOCKED = "The door is already locked";
    public static final String DOOR_ALREADY_UNLOCKED = "The door is already unlocked.";
    public static final String CHILD_LOCK_ALREADY_ON = "The child lock is already on.";
    public static final String CHILD_LOCK_ALREADY_OFF = "The child lock is already off.";
    
    public static final String DOOR_LOCKED = "The door is locked.";
    public static final String NOT_IN_PARK = "The van is not in park.";
    public static final String CHILD_LOCK_ON = "The child lock is on.";    
    
    public static String nowGear(Gear gear) { 
        return "The van is now in " + gear + ".";
    }
    
    public static String alreadyGear(Gear gear) {
        return "The van is already in " + gear + ".";        
    }
}
