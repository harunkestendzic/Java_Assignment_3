import java.util.List;

public class WorkShopThreads implements Runnable{
    private Workshop w;
    private List<Registration> allRegs;
    private Double result;

    public WorkShopThreads(Workshop w, List<Registration> allRegs) {
        this.w = w;
        this.allRegs = allRegs;
    }

    public Workshop getWorkshop() {
        return w;
    }

    public Double getResult() {
        return result;
    }

    @Override
    public void run(){
        result = allRegs.stream()
                .filter(r->r.getWorkshop().equals(w))
                .mapToDouble(Registration::getScore)
                .average()
                .orElse(0.0);
    }
}
