import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

// Ride class implements RideInterface
public class Ride implements RideInterface {
    // Instance variables
    private String rideName;
    private String rideId;
    private Queue<Visitor> waitQueue; // Waiting queue
    private LinkedList<Visitor> rideHistory; // Ride history
    private Employee operator; // Operator
    private int maxRider; // Max riders per cycle
    private int numOfCycles; // Number of cycles run
    private boolean isUnderMaintenance = false;

    // Constants
    private static final int MAX_QUEUE_SIZE = 1000;

    // Default constructor
    public Ride() {
        this.waitQueue = new LinkedList<>();
        this.rideHistory = new LinkedList<>();
        this.maxRider = 1;
        this.numOfCycles = 0;
    }

    // Parameterized constructor
    public Ride(String rideName, String rideId, int maxRider, Employee operator) {
        if (rideName == null || rideName.trim().isEmpty()) {
            throw new IllegalArgumentException("Ride name cannot be empty");
        }
        if (rideId == null || rideId.trim().isEmpty()) {
            throw new IllegalArgumentException("Ride ID cannot be empty");
        }
        if (maxRider <= 0) {
            throw new IllegalArgumentException("Max rider must be positive");
        }

        this.rideName = rideName;
        this.rideId = rideId;
        this.maxRider = maxRider;
        this.operator = operator;
        this.waitQueue = new LinkedList<>();
        this.rideHistory = new LinkedList<>();
        this.numOfCycles = 0;
    }

    // Getter & Setter
    public String getRideName() { return rideName; }
    public void setRideName(String rideName) {
        if (rideName == null || rideName.trim().isEmpty()) {
            throw new IllegalArgumentException("Ride name cannot be empty");
        }
        this.rideName = rideName;
    }

    public String getRideId() { return rideId; }
    public void setRideId(String rideId) {
        if (rideId == null || rideId.trim().isEmpty()) {
            throw new IllegalArgumentException("Ride ID cannot be empty");
        }
        this.rideId = rideId;
    }

    public Employee getOperator() { return operator; }
    public void setOperator(Employee operator) {
        this.operator = operator;
    }

    public int getMaxRider() { return maxRider; }
    public void setMaxRider(int maxRider) {
        if (maxRider <= 0) {
            throw new IllegalArgumentException("Max rider must be positive");
        }
        this.maxRider = maxRider;
    }

    public int getNumOfCycles() { return numOfCycles; }
    public void setNumOfCycles(int numOfCycles) {
        if (numOfCycles < 0) {
            throw new IllegalArgumentException("Number of cycles cannot be negative");
        }
        this.numOfCycles = numOfCycles;
    }

    public boolean isUnderMaintenance() { return isUnderMaintenance; }
    public void setUnderMaintenance(boolean underMaintenance) {
        isUnderMaintenance = underMaintenance;
    }

    // ===================== Part 3: Waiting Queue Implementation =====================
    @Override
    public void addVisitorToQueue(Visitor visitor) {
        if (visitor == null) {
            System.out.println("❌ Error: Cannot add null visitor to queue");
            return;
        }
        if (waitQueue.size() >= MAX_QUEUE_SIZE) {
            System.out.println("❌ Error: Queue is full, cannot add more visitors");
            return;
        }
        if (waitQueue.contains(visitor)) {
            System.out.println("⚠️ Warning: Visitor " + visitor.getName() + " is already in queue");
            return;
        }

        waitQueue.offer(visitor);
        System.out.println("✅ Visitor " + visitor.getName() + " added to queue");
    }

    @Override
    public Visitor removeVisitorFromQueue() {
        Visitor visitor = waitQueue.poll();
        if (visitor != null) {
            System.out.println("✅ Removed " + visitor.getName() + " from queue");
        } else {
            System.out.println("ℹ️ Queue is empty - no visitor to remove");
        }
        return visitor;
    }

    @Override
    public void printQueue() {
        System.out.println("=== Waiting Queue for " + rideName + " (" + waitQueue.size() + " people) ===");
        if (waitQueue.isEmpty()) {
            System.out.println("Queue is empty");
            return;
        }
        int i = 1;
        for (Visitor v : waitQueue) {
            System.out.println(i + ". " + v.getName() + " (ID: " + v.getId() + ", Age: " + v.getAge() + ")");
            i++;
        }
    }

    @Override
    public int getQueueSize() {
        return waitQueue.size();
    }

    // ===================== Part 4A: Ride History =====================
    @Override
    public void addVisitorToHistory(Visitor visitor) {
        if (visitor == null) {
            System.out.println("❌ Error: Cannot add null visitor to history");
            return;
        }
        if (rideHistory.contains(visitor)) {
            System.out.println("⚠️ Warning: Visitor " + visitor.getName() + " is already in ride history");
            return;
        }

        visitor.setHasRidden(true);
        rideHistory.add(visitor);
        System.out.println("✅ Visitor " + visitor.getName() + " added to ride history");
    }

    @Override
    public boolean checkVisitorFromHistory(String visitorId) {
        if (visitorId == null || visitorId.trim().isEmpty()) {
            System.out.println("❌ Error: Invalid visitor ID");
            return false;
        }

        boolean found = rideHistory.stream()
                .anyMatch(visitor -> visitor.getId().equals(visitorId));

        System.out.println("ℹ️ Visitor " + visitorId + " in history: " + found);
        return found;
    }

    @Override
    public int numberOfVisitors() {
        return rideHistory.size();
    }

    @Override
    public void printRideHistory() {
        System.out.println("=== Ride History for " + rideName + " (" + rideHistory.size() + " visitors) ===");
        if (rideHistory.isEmpty()) {
            System.out.println("No ride records yet");
            return;
        }
        Iterator<Visitor> iterator = rideHistory.iterator();
        int i = 1;
        while (iterator.hasNext()) {
            Visitor v = iterator.next();
            System.out.println(i + ". " + v.getName() + " (ID: " + v.getId() +
                    ", Age: " + v.getAge() + ", Date: " + v.getVisitDate() + ")");
            i++;
        }
    }

    // ===================== Part 4B: Sort Ride History =====================
    // Comparator for sorting by age (ascending)
    static class VisitorAgeComparator implements Comparator<Visitor> {
        @Override
        public int compare(Visitor v1, Visitor v2) {
            return Integer.compare(v1.getAge(), v2.getAge());
        }
    }

    // Comparator for sorting by name
    static class VisitorNameComparator implements Comparator<Visitor> {
        @Override
        public int compare(Visitor v1, Visitor v2) {
            return v1.getName().compareToIgnoreCase(v2.getName());
        }
    }

    // Sort method by age
    public void sortRideHistoryByAge() {
        Collections.sort(rideHistory, new VisitorAgeComparator());
        System.out.println("✅ Ride history sorted by age");
    }

    // Sort method by name
    public void sortRideHistoryByName() {
        Collections.sort(rideHistory, new VisitorNameComparator());
        System.out.println("✅ Ride history sorted by name");
    }

    // Generic sort method
    public void sortRideHistory(Comparator<Visitor> comparator) {
        Collections.sort(rideHistory, comparator);
        System.out.println("✅ Ride history sorted");
    }

    // ===================== Part 5: Run One Ride Cycle =====================
    @Override
    public void runOneCycle() {
        System.out.println("\n🎢 === Starting Cycle " + (numOfCycles + 1) + " for " + rideName + " ===");

        // Enhanced validation
        if (isUnderMaintenance) {
            System.out.println("❌ Error: Ride is under maintenance!");
            return;
        }

        if (operator == null) {
            System.out.println("❌ Error: No operator assigned to ride!");
            return;
        }

        if (!operator.isAvailable()) {
            System.out.println("❌ Error: Operator " + operator.getName() + " is not available!");
            return;
        }

        if (waitQueue.isEmpty()) {
            System.out.println("⚠️ Warning: No visitors in queue");
            return;
        }

        int ridersThisCycle = Math.min(maxRider, waitQueue.size());
        List<Visitor> riders = new ArrayList<>();

        System.out.println("👨‍💼 Operator: " + operator.getName() + " (" + operator.getPosition() + ")");
        System.out.println("🎫 Riders for this cycle: " + ridersThisCycle + " (Max limit: " + maxRider + ")");

        // Batch process riders
        for (int i = 0; i < ridersThisCycle; i++) {
            Visitor rider = removeVisitorFromQueue();
            if (rider != null) {
                riders.add(rider);
                addVisitorToHistory(rider);
            }
        }

        numOfCycles++;
        System.out.println("✅ Cycle " + numOfCycles + " completed successfully!");
        riders.forEach(rider ->
                System.out.println("   🎉 " + rider.getName() + " enjoyed the ride!"));
    }

    @Override
    public boolean isOperational() {
        return operator != null && operator.isAvailable() && !isUnderMaintenance;
    }

    @Override
    public double getUtilizationRate() {
        if (numOfCycles == 0) return 0.0;
        int totalCapacity = numOfCycles * maxRider;
        int actualRiders = rideHistory.size();
        return (double) actualRiders / totalCapacity * 100;
    }

    // ===================== Part 6: Write to File =====================
    public void exportRideHistory(String filePath) {
        try (FileWriter writer = new FileWriter(filePath);
             BufferedWriter bw = new BufferedWriter(writer)) {

            // Write header
            bw.write("Name,Age,ID,TicketID,VisitDate\n");

            // Write visitor data
            for (Visitor v : rideHistory) {
                bw.write(v.toCSV() + "\n");
            }

            System.out.println("✅ Ride history exported successfully to: " + filePath);
            System.out.println("   📊 Total records: " + rideHistory.size());

        } catch (IOException e) {
            System.err.println("❌ Error exporting ride history: " + e.getMessage());
            throw new RuntimeException("Export failed: " + e.getMessage(), e);
        }
    }

    // ===================== Part 7: Read from File =====================
    public void importRideHistory(String filePath) {
        int importedCount = 0;
        try (FileReader reader = new FileReader(filePath);
             BufferedReader br = new BufferedReader(reader)) {

            // Skip header
            String header = br.readLine();
            if (header == null || !header.startsWith("Name,Age,ID,TicketID,VisitDate")) {
                System.out.println("⚠️ Warning: File header may be incorrect");
            }

            // Read each line of data
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue; // Skip empty lines

                Visitor v = Visitor.fromCSV(line);
                if (v != null) {
                    rideHistory.add(v);
                    importedCount++;
                }
            }

            System.out.println("✅ Ride history imported successfully from: " + filePath);
            System.out.println("   📥 Imported records: " + importedCount);

        } catch (FileNotFoundException e) {
            System.err.println("❌ Error: File not found: " + filePath);
        } catch (IOException e) {
            System.err.println("❌ Error reading file: " + e.getMessage());
            throw new RuntimeException("Import failed: " + e.getMessage(), e);
        }
    }

    // Utility method to clear all data
    public void clearAllData() {
        waitQueue.clear();
        rideHistory.clear();
        numOfCycles = 0;
        System.out.println("✅ All ride data cleared");
    }

    // Display ride status
    public void displayStatus() {
        System.out.println("\n=== " + rideName + " Status ===");
        System.out.println("Ride ID: " + rideId);
        System.out.println("Operator: " + (operator != null ? operator.getName() : "None"));
        System.out.println("Max riders per cycle: " + maxRider);
        System.out.println("Cycles run: " + numOfCycles);
        System.out.println("Visitors in queue: " + waitQueue.size());
        System.out.println("Visitors in history: " + rideHistory.size());
        System.out.println("Utilization rate: " + String.format("%.1f%%", getUtilizationRate()));
        System.out.println("Operational: " + (isOperational() ? "✅ Yes" : "❌ No"));
    }
}