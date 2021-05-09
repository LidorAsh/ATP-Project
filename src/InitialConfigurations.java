import Server.Configurations;

public class InitialConfigurations {

    public static void main(String[] args) {
        Configurations conf = Configurations.getInstance();
        conf.setThreadPoolSize(3);
        conf.setMazeGeneratingAlgorithm("My");
        conf.setMazeSearchingAlgorithm("DFS");
    }
}
