public class Event extends Task {

    protected String startBy;
    protected String endBy;

    public Event(String name, boolean haveDone, String start, String end) {
        super(name, haveDone);
        this.startBy = start;
        this.endBy = end;
    }

    @Override
    public String toString() {
        return String.format("E | %s | %s - %s",
                super.toString(), this.startBy, this.endBy);
    }
}
