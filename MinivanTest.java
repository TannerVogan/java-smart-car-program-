package minivan;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 *  Write a one-sentence summary of your test class here.
 *  Summarize what your test objectives are.
 * 
 *  @author your name (your-pid)
 *  @version (place the date here, in this format: yyyy.mm.dd)
 */
public class MinivanTest
    // extends student.TestCase
{
    //~ Fields ......................................

    // Holds a minivan object to be used in your individual tests
    private Minivan minivan;


    //~ Constructor ..................................

    /**
     * Creates a new MinivanTest test object.
     */
    public MinivanTest()
    {
        // The constructor is usually empty in unit tests, since it runs
        // once for the whole class, not once for each test method.
        // Per-test initialization should be placed in setUp() instead.
    }


    //~ Methods .........................................

    /**
     * Sets up the test fixture.
     * Called before every test case method.
     * Here, we just create a minivan using the default constructor,
     * so a freshly created minivan is available for use in each test.
     */
    @Before
    public void setUp()
    {
        minivan = new Minivan();
    }


    /**
     * Check that the outside handle opens the door in
     * a newly constructed minivan object and that the log
     * message is the one we expect.
     */
    @Test
    public void testOuterHandleWithDefaultMinivan()
    {
    		minivan.pullOuterHandle();
        assertTrue(minivan.isOpen());
        assertEquals(Message.DOOR_NOW_OPEN, minivan.printLastMessage());
    }
    
    /**
     * check the function of child safe lock.
     */
    @Test
    public void testChildSafeLockChange()
    {
    		assertFalse(minivan.getChildLock());
    		
    		minivan.setChildLock(true);
    		assertTrue(minivan.getChildLock());
    		assertEquals(Message.CHILD_LOCK_NOW_ON,
    				minivan.printLastMessage());
    		
    		minivan.setChildLock(true);
    		assertTrue(minivan.getChildLock());
    		assertEquals(Message.CHILD_LOCK_ALREADY_ON,
    				minivan.printLastMessage());
    		
    		minivan.setChildLock(false);
    		assertFalse(minivan.getChildLock());
    		assertEquals(Message.CHILD_LOCK_NOW_OFF,
    				minivan.printLastMessage());
    		
    		minivan.setChildLock(false);
    		assertFalse(minivan.getChildLock());
    		assertEquals(Message.CHILD_LOCK_ALREADY_OFF,
    				minivan.printLastMessage());
    }
    
    /**
     * check the function of door lock button.
     */
    @Test
    public void testDoorLockChange()
    {
    		assertFalse(minivan.isLocked());
    		
    		minivan.pushLockButton();
    		assertTrue(minivan.isLocked());
    		assertEquals(Message.DOOR_NOW_LOCKED,
    				minivan.printLastMessage());
    		
    		minivan.pushLockButton();
    		assertTrue(minivan.isLocked());
    		assertEquals(Message.DOOR_ALREADY_LOCKED,
    				minivan.printLastMessage());
    		
    		minivan.pushUnlockButton();
    		assertFalse(minivan.isLocked());
    		assertEquals(Message.DOOR_NOW_UNLOCKED,
    				minivan.printLastMessage());
    		
    		minivan.pushUnlockButton();
    		assertFalse(minivan.isLocked());
    		assertEquals(Message.DOOR_ALREADY_UNLOCKED,
    				minivan.printLastMessage());
    }
    
    /**
     * check the function of gear change.
     */
    @Test
    public void testGearChange()
    {
    		assertEquals(Gear.PARK, minivan.getGear());
    		
    		minivan.setGear(Gear.PARK);
    		assertEquals(Gear.PARK, minivan.getGear());
		assertEquals(Message.alreadyGear(Gear.PARK),
					minivan.printLastMessage());
		
    		for (Gear newGear : Gear.values()) {
    			if (newGear == Gear.PARK) continue;
    			minivan.setGear(newGear);
    			assertEquals(newGear, minivan.getGear());
    			assertEquals(Message.nowGear(newGear),
    					minivan.printLastMessage());
    			
    			minivan.setGear(newGear);
    			assertEquals(newGear, minivan.getGear());
    			assertEquals(Message.alreadyGear(newGear),
    					minivan.printLastMessage());
    		}
    }
    
    /**
     * check the function of door close.
     */
    @Test
    public void testDoorClose()
    {
    		assertFalse(minivan.isOpen());
    		
    		minivan.pullInsideHandle();
    		assertTrue(minivan.isOpen());
    		
    		minivan.closeDoor();
    		assertFalse(minivan.isOpen());
    		assertEquals(Message.DOOR_NOW_CLOSED,
    				minivan.printLastMessage());
    		
    		minivan.closeDoor();
    		assertFalse(minivan.isOpen());
    		assertEquals(Message.DOOR_ALREADY_CLOSED,
    				minivan.printLastMessage());
    }
    
    /**
     * Test the function of pull outside.
     */
    @Test
    public void testPullOutside()
    {
    		assertFalse(minivan.isOpen());
    		
    		minivan.pullOuterHandle();
    		assertTrue(minivan.isOpen());
    		
    		// pull again
    		minivan.pullOuterHandle();
    		assertTrue(minivan.isOpen());
    		assertEquals(Message.DOOR_ALREADY_OPEN,
    			minivan.printLastMessage());
    		
    		minivan.closeDoor();
    		assertFalse(minivan.isOpen());
    		
    		// lock the door and pull again
    		minivan.pushLockButton();
    		minivan.pullOuterHandle();
    		assertFalse(minivan.isOpen());
    		assertEquals(Message.DOOR_LOCKED, 
    			minivan.printLastMessage());
    		minivan.pushUnlockButton();
    		
    		// change the gear and pull again
    		minivan.setGear(Gear.DRIVE);
    		minivan.pullOuterHandle();
    		assertFalse(minivan.isOpen());
    		assertEquals(Message.NOT_IN_PARK, 
    			minivan.printLastMessage());
    		minivan.setGear(Gear.PARK);
    		
    		// change the child safe lock and pull again
    		minivan.setChildLock(true);
    		minivan.pullOuterHandle();
    		assertTrue(minivan.isOpen());
    		assertEquals(Message.DOOR_NOW_OPEN, 
    			minivan.printLastMessage());
    		minivan.setChildLock(false);
    }
    
    /**
     * Test the function of open button.
     */
    @Test
    public void testOpenButton()
    {
    		assertFalse(minivan.isOpen());
    		
    		minivan.pushOpenButton();
    		assertTrue(minivan.isOpen());
    		
    		// open again
    		minivan.pushOpenButton();
    		assertTrue(minivan.isOpen());
    		assertEquals(Message.DOOR_ALREADY_OPEN,
    			minivan.printLastMessage());
    		
    		minivan.closeDoor();
    		assertFalse(minivan.isOpen());
    		
    		// lock the door and open again
    		minivan.pushLockButton();
    		minivan.pushOpenButton();
    		assertFalse(minivan.isOpen());
    		assertEquals(Message.DOOR_LOCKED, 
    			minivan.printLastMessage());
    		minivan.pushUnlockButton();
    		
    		// change the gear and open again
    		minivan.setGear(Gear.DRIVE);
    		minivan.pushOpenButton();
    		assertFalse(minivan.isOpen());
    		assertEquals(Message.NOT_IN_PARK, 
    			minivan.printLastMessage());
    		minivan.setGear(Gear.PARK);
    		
    		// change the child safe lock and open again
    		minivan.setChildLock(true);
    		minivan.pushOpenButton();
    		assertTrue(minivan.isOpen());
    		assertEquals(Message.DOOR_NOW_OPEN, 
    			minivan.printLastMessage());
    		minivan.setChildLock(false);
    }
    
    /**
     * Test the function of inside pull.
     */
    @Test
    public void testPullInside()
    {
    		assertFalse(minivan.isOpen());
    		
    		minivan.pullInsideHandle();
    		assertTrue(minivan.isOpen());
    		
    		// pull again
    		minivan.pullInsideHandle();
    		assertTrue(minivan.isOpen());
    		assertEquals(Message.DOOR_ALREADY_OPEN,
    			minivan.printLastMessage());
    		
    		minivan.closeDoor();
    		assertFalse(minivan.isOpen());
    		
    		// lock the door and pull again
    		minivan.pushLockButton();
    		minivan.pullInsideHandle();
    		assertFalse(minivan.isOpen());
    		assertEquals(Message.DOOR_LOCKED, 
    			minivan.printLastMessage());
    		minivan.pushUnlockButton();
    		
    		// change the gear and pull again
    		minivan.setGear(Gear.DRIVE);
    		minivan.pullInsideHandle();
    		assertFalse(minivan.isOpen());
    		assertEquals(Message.NOT_IN_PARK, 
    			minivan.printLastMessage());
    		minivan.setGear(Gear.PARK);
    		
    		// change the child safe lock and pull again
    		minivan.setChildLock(true);
    		minivan.pullInsideHandle();
    		assertFalse(minivan.isOpen());
    		assertEquals(Message.CHILD_LOCK_ON, 
    			minivan.printLastMessage());
    		minivan.setChildLock(false);
    }
    
    
}
