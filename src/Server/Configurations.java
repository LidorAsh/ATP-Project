package Server;

import java.io.*;
import java.util.Properties;

public class Configurations {

    private static Configurations instance = null;
    private final Properties p;
    private OutputStream os;
    private InputStream is;
    private final String filePath = "resources//config.properties";

    private Configurations() {
            this.p = new Properties();
    }

    public static Configurations getInstance() {
        if(instance == null)
            instance = new Configurations();
        return instance;
    }

    public void setThreadPoolSize(int n) {
        try {
            os = new FileOutputStream(filePath);
            p.setProperty("ThreadPoolSize", String.valueOf(n));
            p.store(os, null);
            os.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setMazeGeneratingAlgorithm(String s) {
        try {
            os = new FileOutputStream(filePath);
            p.setProperty("MazeGeneratingAlgorithm", s);
            p.store(os, null);
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setMazeSearchingAlgorithm(String s) {
        try {
            os = new FileOutputStream(filePath);
            p.setProperty("MazeSearchingAlgorithm", s);
            p.store(os, null);
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getThreadPoolSize() {
        String ret = null;
        try {
            is = new FileInputStream(filePath);
            p.load(is);
            ret = p.getProperty("ThreadPoolSize");
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (ret != null) {
            return Integer.parseInt(ret);
        }
        return 0;
    }

    public String getMazeGeneratingAlgorithm() {
        String ret = null;
        try {
            is = new FileInputStream(filePath);
            p.load(is);
            ret = p.getProperty("MazeGeneratingAlgorithm");
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ret;
    }

    public String getMazeSearchingAlgorithm() {
        String ret = null;
        try {
            is = new FileInputStream(filePath);
            p.load(is);
            ret = p.getProperty("MazeSearchingAlgorithm");
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ret;
    }
}
