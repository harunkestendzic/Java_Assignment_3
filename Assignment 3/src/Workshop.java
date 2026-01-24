public class Workshop {
    private String id;
    private String title;
    private Integer maxSeats;

    @Override
    public String toString() {
        return "Workshop{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", maxSeats=" + maxSeats +
                '}';
    }

    public Workshop(String id, String title, Integer maxSeats) {
        this.id = id;
        this.title = title;
        this.maxSeats = maxSeats;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Integer getMaxSeats() {
        return maxSeats;
    }
}
