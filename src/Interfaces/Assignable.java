package Interfaces;

/**
 * Assignable interface
 * ---------------------------------------------------------
 * Defines generic behavior for objects that can be assigned to another entity.
 * This interface uses Java Generics to allow flexibility in the type of assignee.
 *
 * @param <T> the type of object that can be assigned
 */
public interface Assignable<T> {

    /**
     * Assigns this object to a target object.
     *
     * @param object the target object to which this is assigned
     */
    void assignTo(T object);

    /**
     * Returns the current assignee of this object.
     *
     * @return the object to which this is assigned, or null if unassigned
     */
    T getAssignee();
    /**
     * Checks if this object is currently assigned.
     *
     * @return true if assigned, false otherwise
     */
    boolean isAssigned();

    /**
     * Unassigns this object from its current assignee.
     * After this method is called, isAssigned() should return false.
     */
    void unassign();
}
