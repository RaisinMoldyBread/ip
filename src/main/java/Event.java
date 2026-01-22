public class Event extends Task {

    protected String startBy;
    protected String endBy;

    public Event(String name, String start, String end) {
        super(name);
        this.startBy = start;
        this.endBy = end;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)",
                super.toString(), this.startBy, this.endBy);
    }
}
