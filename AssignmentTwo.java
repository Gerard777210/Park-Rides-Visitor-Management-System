import java.io.IOException;
import java.time.LocalDate;

// Main class with demo methods
public class AssignmentTwo {
    public static void main(String[] args) {
        try {
            System.out.println("🎡=== Theme Park Management System Demo ===🎡\n");

            partThree();
            partFourA();
            partFourB();
            partFive();
            partSix();
            partSeven();

            System.out.println("\n🎉=== All Demos Completed Successfully! ===🎉");
        } catch (Exception e) {
            System.err.println("❌ Demo failed: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Part 3 Demo: Waiting Queue
    public static void partThree() {
        System.out.println("========== Part 3: Queue Management ==========");

        // Create ride facility
        Ride rollerCoaster = new Ride("Roller Coaster", "RC001", 4, null);

        // Create 5 visitors
        Visitor v1 = new Visitor("Zhang San", 25, "1001", "TK001", "2025-11-30");
        Visitor v2 = new Visitor("Li Si", 30, "1002", "TK002", "2025-11-30");
        Visitor v3 = new Visitor("Wang Wu", 22, "1003", "TK003", "2025-11-30");
        Visitor v4 = new Visitor("Zhao Liu", 28, "1004", "TK004", "2025-11-30");
        Visitor v5 = new Visitor("Sun Qi", 35, "1005", "TK005", "2025-11-30");

        // Add to queue with feedback
        rollerCoaster.addVisitorToQueue(v1);
        rollerCoaster.addVisitorToQueue(v2);
        rollerCoaster.addVisitorToQueue(v3);
        rollerCoaster.addVisitorToQueue(v4);
        rollerCoaster.addVisitorToQueue(v5);

        // Try to add duplicate
        rollerCoaster.addVisitorToQueue(v1);

        // Print queue
        rollerCoaster.printQueue();

        // Remove one visitor
        Visitor removed = rollerCoaster.removeVisitorFromQueue();
        System.out.println("\nRemoved visitor: " + removed.getName());

        // Print queue after removal
        rollerCoaster.printQueue();
        System.out.println("Current queue size: " + rollerCoaster.getQueueSize());
        System.out.println();
    }

    // Part 4A Demo: Ride History
    public static void partFourA() {
        System.out.println("========== Part 4A: Ride History Management ==========");

        Ride ferrisWheel = new Ride("Ferris Wheel", "FW001", 6, null);

        // Add history records
        Visitor v1 = new Visitor("Zhang San", 25, "1001", "TK001", "2025-11-30");
        Visitor v2 = new Visitor("Li Si", 30, "1002", "TK002", "2025-11-30");
        Visitor v3 = new Visitor("Wang Wu", 22, "1003", "TK003", "2025-11-30");

        ferrisWheel.addVisitorToHistory(v1);
        ferrisWheel.addVisitorToHistory(v2);
        ferrisWheel.addVisitorToHistory(v3);

        // Try to add duplicate
        ferrisWheel.addVisitorToHistory(v1);

        // Print history
        ferrisWheel.printRideHistory();

        // Check if visitor exists in history
        String checkId = "1002";
        System.out.println("\nCheck if visitor with ID " + checkId + " has ridden: " +
                ferrisWheel.checkVisitorFromHistory(checkId));

        checkId = "1006";
        System.out.println("Check if visitor with ID " + checkId + " has ridden: " +
                ferrisWheel.checkVisitorFromHistory(checkId));

        // Count total visitors
        System.out.println("Total number of visitors in history: " + ferrisWheel.numberOfVisitors());
        System.out.println();
    }

    // Part 4B Demo: Sort Ride History
    public static void partFourB() {
        System.out.println("========== Part 4B: Sorting Ride History ==========");

        Ride bumperCar = new Ride("Bumper Car", "BC001", 8, null);

        // Add unsorted visitors
        bumperCar.addVisitorToHistory(new Visitor("Zhang San", 25, "1001", "TK001", "2025-11-30"));
        bumperCar.addVisitorToHistory(new Visitor("Li Si", 30, "1002", "TK002", "2025-11-30"));
        bumperCar.addVisitorToHistory(new Visitor("Wang Wu", 22, "1003", "TK003", "2025-11-30"));
        bumperCar.addVisitorToHistory(new Visitor("Zhao Liu", 18, "1004", "TK004", "2025-11-30"));
        bumperCar.addVisitorToHistory(new Visitor("Alice", 35, "1007", "TK007", "2025-11-30"));

        // Before sorting
        System.out.println("Before sorting:");
        bumperCar.printRideHistory();

        // Sort by age
        bumperCar.sortRideHistoryByAge();

        // After sorting
        System.out.println("\nAfter sorting by age (ascending):");
        bumperCar.printRideHistory();

        // Sort by name
        bumperCar.sortRideHistoryByName();

        System.out.println("\nAfter sorting by name:");
        bumperCar.printRideHistory();
        System.out.println();
    }

    // Part 5 Demo: Run One Cycle
    public static void partFive() {
        System.out.println("========== Part 5: Run Ride Cycle ==========");

        // Create operator
        Employee operator = new Employee("Experienced Operator", 28, "OP001", "EMP001", "Senior Operator");
        operator.setAvailable(true);

        // Create ride facility (max 3 riders per cycle)
        Ride dragonCoaster = new Ride("Dragon Coaster", "DC001", 3, operator);

        // Add multiple visitors to queue
        String[] names = {"Alice", "Bob", "Charlie", "Diana", "Eve", "Frank", "Grace"};
        for (int i = 0; i < names.length; i++) {
            dragonCoaster.addVisitorToQueue(new Visitor(
                    names[i], 20 + i, "V" + (1000 + i),
                    "TKT" + (1000 + i), LocalDate.now().toString()
            ));
        }

        System.out.println("Initial queue status:");
        dragonCoaster.printQueue();
        dragonCoaster.displayStatus();

        // Run multiple cycles to demonstrate
        dragonCoaster.runOneCycle();
        dragonCoaster.runOneCycle();

        System.out.println("\nFinal status:");
        dragonCoaster.printQueue();
        dragonCoaster.printRideHistory();
        dragonCoaster.displayStatus();
        System.out.println();
    }

    // Part 6 Demo: Export to File
    public static void partSix() throws IOException {
        System.out.println("========== Part 6: Export to File ==========");

        Ride ferrisWheel = new Ride("Ferris Wheel", "FW001", 6, null);

        // Add test data
        ferrisWheel.addVisitorToHistory(new Visitor("Zhang San", 25, "1001", "TK001", "2025-11-30"));
        ferrisWheel.addVisitorToHistory(new Visitor("Li Si", 30, "1002", "TK002", "2025-11-30"));
        ferrisWheel.addVisitorToHistory(new Visitor("Wang Wu", 22, "1003", "TK003", "2025-11-30"));
        ferrisWheel.addVisitorToHistory(new Visitor("Test User", 40, "1008", "TK008", "2025-12-01"));

        // Export to file
        String exportPath = "ride_history_export.csv";
        ferrisWheel.exportRideHistory(exportPath);
        System.out.println();
    }

    // Part 7 Demo: Import from File
    public static void partSeven() throws IOException {
        System.out.println("========== Part 7: Import from File ==========");

        Ride ferrisWheel = new Ride("Ferris Wheel", "FW001", 6, null);
        String importPath = "ride_history_export.csv";

        // Before import
        System.out.println("Ride history before import:");
        ferrisWheel.printRideHistory();

        // Import from file
        ferrisWheel.importRideHistory(importPath);

        // Verify after import
        System.out.println("\nRide history after import:");
        ferrisWheel.printRideHistory();

        // Verify data integrity
        System.out.println("\nData verification:");
        System.out.println("Verify if visitor with ID 1002 exists: " + ferrisWheel.checkVisitorFromHistory("1002"));
        System.out.println("Verify if visitor with ID 9999 exists: " + ferrisWheel.checkVisitorFromHistory("9999"));
        System.out.println("Total number of visitors in history: " + ferrisWheel.numberOfVisitors());

        // Display final status
        ferrisWheel.displayStatus();
    }
}