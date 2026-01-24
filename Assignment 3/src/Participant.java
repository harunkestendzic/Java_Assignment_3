public class Participant extends Contact{
    public Participant(String id, String fullName, String email) {
        super(id, fullName, email);
    }

    public static class Builder{
        private String id;
        private String fullName;
        private String email;

        public Builder withId(String id){
            this.id = id;
            return this;
        }
        public Builder withFullName(String fullName){
            this.fullName = fullName;
            return this;
        }
        public Builder withEmail(String email){
            this.email = email;
            return this;
        }
        public Participant build(){
            return new Participant(id,fullName,email);
        }

    }

    @Override
    public String toString() {
        return "Participant{" +
                "id='" + id + '\'' +
                ", fullName='" + fullName + '\'' +
                '}';
    }
}
