public class Registration {
    private Participant participant;
    private Workshop workshop;
    private Integer score;

    @Override
    public String toString() {
        return "Registration{" +
                "participant=" + participant +
                ", workshop=" + workshop +
                ", score=" + score +
                '}';
    }

    public Participant getParticipant() {
        return participant;
    }

    public Workshop getWorkshop() {
        return workshop;
    }

    public Integer getScore() {
        return score;
    }

    public Registration(Participant participant, Workshop workshop, Integer score) {
        this.participant = participant;
        this.workshop = workshop;
        this.score = score;
    }
}
