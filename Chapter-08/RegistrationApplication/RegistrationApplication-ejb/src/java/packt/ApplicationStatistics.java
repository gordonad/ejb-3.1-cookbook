package packt;

public class ApplicationStatistics {
    private static ApplicationStatistics instance;
    private static int count;
    private long totalTime;

    private ApplicationStatistics() {
        
    }
    
    public static ApplicationStatistics getInstance() {
        if(instance == null) {
            instance = new ApplicationStatistics();
        }
        return instance;
    }
    
    public int getCount() {
        return count;
    }

    public void increment() {
        count++;
    }

    public void increaseTotalTime(long time) {
        totalTime += time;
    }

    public long getTotalTime() {
        return this.totalTime;
    }

}
