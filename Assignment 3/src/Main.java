//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
void main() throws IOException, InterruptedException {
    Manager manager = Manager.getINSTANCE();
    //6
    Map<Participant, List<Registration>> registrationsByParticipant = manager.Registrations
            .stream().collect(Collectors.groupingBy(Registration::getParticipant));
    Map<Workshop,List<Registration>> registrationsByWorkshop = manager.Registrations
            .stream().collect(Collectors.groupingBy(Registration::getWorkshop));

    //7
    Map<Workshop,Double> workshopsSummary = manager.Registrations
            .stream().collect(Collectors.groupingBy(Registration::getWorkshop,
                    Collectors.averagingDouble(Registration::getScore)));
    if(FileUtility.saveRep1("workshops_summary.txt",workshopsSummary))
        System.out.println("workshops_summary.txt has been successfully saved");
    else
        System.out.println("workshops_summary.txt has not been saved");

    if(FileUtility.saveRep2(registrationsByParticipant))
        System.out.println("Participant reports have been successfully saved");
    else
        System.out.println("Participant reports have not been saved");

    //8
    try {
    generateRep1();
    }catch (Exception e){
        System.out.println(e);
    }

}

public void generateRep1() throws IOException, InterruptedException {
    Manager manager = Manager.getINSTANCE();

    ArrayList<WorkShopThreads> workers = new ArrayList<>();
    ArrayList<Thread> threads = new ArrayList<>();

    for(Workshop w: manager.Workshops){
        WorkShopThreads worker = new WorkShopThreads(w,manager.Registrations);
        Thread t = new Thread(worker);

        workers.add(worker);
        threads.add(t);

        t.start();
    }

    for(Thread t : threads) t.join();

    Map<Workshop,Double> summaryMap = new HashMap<>();
    for (WorkShopThreads worker : workers){
        summaryMap.put(worker.getWorkshop(),worker.getResult());
    }

    FileUtility.saveRep1("workshop_report_threads.txt",summaryMap);
}
