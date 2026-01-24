import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Manager {
    private static Manager INSTANCE;
    public Manager() throws IOException {}
    public static Manager getINSTANCE() throws IOException {
        if(INSTANCE == null) INSTANCE = new Manager();
        return INSTANCE;
    }

    public List<Participant> Participants = FileUtility.loadParticipants("data/participants.txt",false);;
    public List<Workshop> Workshops = FileUtility.loadWorkshops("data/workshops.txt",false);;
    public List<Registration> Registrations = FileUtility.loadRegistrations("data/registrations.txt",false);;
    public Map<Workshop, ArrayList<Participant>> Info = new HashMap<>();
}
