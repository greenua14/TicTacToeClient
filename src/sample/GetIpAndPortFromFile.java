package sample;

import java.io.*;
import java.net.InetAddress;
import java.util.Properties;

public class GetIpAndPortFromFile {
    private static final String PROPERTIES_FILE = "serverConfig.properties";
    public int PORT;
    public InetAddress IP;

    public GetIpAndPortFromFile(){
        FileInputStream fileProp = null;
        try {
            Properties properties = new Properties();
            fileProp = new FileInputStream("config");
            properties.load(fileProp);

            PORT = Integer.parseInt(properties.getProperty("PORT"));
            IP = InetAddress.getByName(properties.getProperty("IP"));
        } catch (FileNotFoundException e) {
            // System.err.println("Properties config file not found");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Error while reading file");
        } finally {
            try {
                fileProp.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
