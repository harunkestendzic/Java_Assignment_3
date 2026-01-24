import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class FileUtility {
    static Map<String,Participant> participantMap = new HashMap<>();
    static Map<String,Workshop> workshopMap = new HashMap<>();
    public static ArrayList<Participant> loadParticipants(String path,boolean hasHeader)  throws IOException{
        ArrayList<Participant> participants = new ArrayList<>();

        try{
            File f = new File(path);
            Scanner sc = new Scanner(f);
            if(hasHeader && sc.hasNextLine()) sc.nextLine();
            while (sc.hasNextLine()){
                String line = sc.nextLine();
                String[] parts = line.split(",");
                if(parts.length != 3){
                    System.out.println("ERROR, INVALID INPUT: "+ line);
                    continue;
                }
                String id = parts[0].trim();
                String fullName = parts[1].trim();
                String email = parts[2].trim();

                Participant p = new Participant.Builder()
                        .withId(id)
                        .withFullName(fullName)
                        .withEmail(email)
                        .build();

                participants.add(p);
                participantMap.put(id,p);
            }
        }catch (Exception e){
            System.out.println(e);
        }

        return participants;
    }

    public static ArrayList<Workshop> loadWorkshops(String path,boolean hasHeader) throws IOException {
        ArrayList<Workshop> workshops = new ArrayList<>();

        try{
            File f = new File(path);
            Scanner sc = new Scanner(f);
            if(hasHeader && sc.hasNextLine()) sc.nextLine();
            while (sc.hasNextLine()){
                String line = sc.nextLine();
                String[] parts = line.split(",");
                if(parts.length != 3){
                    System.out.println("ERROR, INVALID INPUT: "+ line);
                    continue;
                }
                String id = parts[0].trim();
                String title = parts[1].trim();
                Integer maxSeats = Integer.parseInt(parts[2].trim());

                Workshop w = new Workshop(id,title,maxSeats);
                workshops.add(w);
                workshopMap.put(id,w);
            }
        }catch (Exception e){
            System.out.println(e);
        }

        return workshops;
    }
    public static ArrayList<Registration> loadRegistrations(String path,boolean hasHeader) throws IOException {
        ArrayList<Registration> registrations = new ArrayList<>();

        try{
            File f = new File(path);
            Scanner sc = new Scanner(f);
            if(hasHeader && sc.hasNextLine()) sc.nextLine();
            while (sc.hasNextLine()){
                String line = sc.nextLine();
                String[] parts = line.split(",");
                if(parts.length != 3){
                    System.out.println("ERROR, INVALID INPUT: "+ line);
                    continue;
                }
                String pId = parts[0].trim();
                Participant p = participantMap.get(pId);
                String wId = parts[1].trim();
                Workshop w = workshopMap.get(wId);
                Integer score = Integer.parseInt(parts[2].trim());

                Registration r = new Registration(p,w,score);
                registrations.add(r);
            }
        }catch (Exception e){
            System.out.println(e);
        }

        return registrations;
    }

    public static boolean saveRep1(String fileName, Map<Workshop,Double> mp)  {
        File dir = new File("output");
        if(!dir.exists()) dir.mkdir();

        try{
            FileWriter fw = new FileWriter(new File(dir,fileName));
            for(Map.Entry<Workshop,Double> entry: mp.entrySet()){
                fw.write(entry.getKey().getId() + " - " + entry.getKey().getTitle()
                        + " - " + String.format("%.2f",entry.getValue()) + "\n");
            }
            fw.close();
            return true;
        }catch (IOException e){
            return false;
        }

    }
    public static boolean saveRep2(Map<Participant, List<Registration>> mp)  {
        File dir = new File("output/reports");
        if(!dir.exists()) dir.mkdir();

        try{
            for(Map.Entry<Participant,List<Registration>> entry: mp.entrySet()){
                FileWriter fw = new FileWriter(new File(dir,entry.getKey().id));

                fw.write(entry.getKey().getId() + " - " + entry.getKey().getFullName()
                        + " - " + entry.getKey().getEmail() +"\n" + "\n");

                Double sum = 0.0;
                for(Registration r : entry.getValue()){
                    fw.write(r.getWorkshop().getId() + " - " + r.getWorkshop().getTitle() +" - " +
                            r.getScore()+"\n");
                    sum+=r.getScore();
                }
                Double avgS = sum / entry.getValue().size();

                fw.write("\n" + "Average score: " + String.format("%.2f",avgS));

                fw.close();
            }
            return true;
        }catch (IOException e){
            return false;
        }

    }
}
