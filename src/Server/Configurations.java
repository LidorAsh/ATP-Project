package Server;

import java.io.*;
import java.util.Properties;

public class Configurations {

    private static Configurations instance = null;
    private Properties p;
    private OutputStream os;
    private InputStream is;
    private String filePath = "resources//config.properties";


    private Configurations() {
//        try {
            this.p = new Properties();
//            os = new FileOutputStream("resources//config.properties");
//            is = new FileInputStream("resources//config.properties");
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
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
        return Integer.parseInt(ret);
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
