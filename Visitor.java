import java.time.LocalDate;

// Visitor class (inherits Person)
public class Visitor extends Person {
    // Instance variables
    private String visitorTicketId;
    private LocalDate visitDate;
    private boolean hasRidden = false;

    // Default constructor
    public Visitor() {}

    // Parameterized constructor
    public Visitor(String name, int age, String id, String visitorTicketId, LocalDate visitDate) {
        super(name, age, id);
        this.visitorTicketId = visitorTicketId;
        this.visitDate = visitDate;
    }

    // String date constructor for convenience
    public Visitor(String name, int age, String id, String visitorTicketId, String visitDate) {
        super(name, age, id);
        this.visitorTicketId = visitorTicketId;
        this.visitDate = LocalDate.parse(visitDate);
    }

    // Getter & Setter
    public String getVisitorTicketId() { return visitorTicketId; }
    public void setVisitorTicketId(String visitorTicketId) { this.visitorTicketId = visitorTicketId; }

    public LocalDate getVisitDate() { return visitDate; }
    public void setVisitDate(LocalDate visitDate) { this.visitDate = visitDate; }

    public boolean hasRidden() { return hasRidden; }
    public void setHasRidden(boolean hasRidden) { this.hasRidden = hasRidden; }

    @Override
    public String toString() {
        return String.format("Visitor{name='%s', age=%d, id='%s', ticketId='%s', visitDate=%s, hasRidden=%s}",
                getName(), getAge(), getId(), visitorTicketId, visitDate, hasRidden);
    }

    // For CSV serialization
    public String toCSV() {
        return String.join(",",
                getName(),
                String.valueOf(getAge()),
                getId(),
                visitorTicketId,
                visitDate.toString()
        );
    }

    // Deserialize from CSV
    public static Visitor fromCSV(String csvLine) {
        try {
            String[] parts = csvLine.split(",");
            if (parts.length != 5) {
                System.err.println("Invalid CSV format: " + csvLine);
                return null;
            }
            return new Visitor(
                    parts[0].trim(),
                    Integer.parseInt(parts[1].trim()),
                    parts[2].trim(),
                    parts[3].trim(),
                    LocalDate.parse(parts[4].trim())
            );
        } catch (Exception e) {
            System.err.println("Error parsing CSV line: " + csvLine + " - " + e.getMessage());
            return null;
        }
    }
}