// Ride facility interface
public interface RideInterface {
    // Queue operations
    void addVisitorToQueue(Visitor visitor);
    Visitor removeVisitorFromQueue();
    void printQueue();

    // History operations
    void addVisitorToHistory(Visitor visitor);
    boolean checkVisitorFromHistory(String visitorId);
    int numberOfVisitors();
    void printRideHistory();

    // Ride operations
    void runOneCycle();

    // Additional utility methods
    boolean isOperational();
    int getQueueSize();
    double getUtilizationRate();
}