package com.trifacta.trifactaudfs;

import java.io.*;

public class DatarobotAPI {
    public static void main(String args[]) {
        DatarobotAPI e = new DatarobotAPI();
        String output = e.call("API_TOKEN", "DEPLOYMENT_ID", "DATAROBOT_KEY", "vijay@trifacta.com", "hdfs:///trifacta/uploads/1/dd62e978-f528-44fa-ad48-e85fa7b08f86/10k_diabetes_Predict.csv");
        System.out.println("Output: " + output);
    }
    public String stringTest() {
        StringBuilder sb = new StringBuilder("Hello ");
        return sb.append("world").toString();
    }
    public String call(String API_TOKEN, String DEPLOYMENT_ID, String DATAROBOT_KEY, String USERNAME, String INPUT_FILE) {
        StringBuilder stdout = new StringBuilder("");
        StringBuilder stderr = new StringBuilder("");
        try {
            String line;
            Process p = Runtime.getRuntime().exec("/udfs/trifacta_python /udfs/predict.py <API_TOKEN> <DEPLOYMENT_ID> <DATAROBOT_KEY> <USERNAME> <INPUT_FILE>"
                    .replace("<API_TOKEN>", API_TOKEN)
                    .replace("<DEPLOYMENT_ID>", DEPLOYMENT_ID)
                    .replace("<DATAROBOT_KEY>", DATAROBOT_KEY)
                    .replace("<USERNAME>", USERNAME)
                    .replace("<INPUT_FILE>", INPUT_FILE)
            );
            BufferedReader bri = new BufferedReader
                    (new InputStreamReader(p.getInputStream()));
            BufferedReader bre = new BufferedReader
                    (new InputStreamReader(p.getErrorStream()));
            while ((line = bri.readLine()) != null) {
                stdout.append(line);
                //System.out.println(line);
            }
            bri.close();
            while ((line = bre.readLine()) != null) {
                stderr.append(line);
                System.out.println(line);
            }
            bre.close();
            p.waitFor();
        }
        catch (Exception err) {
            err.printStackTrace();
        }
        return stdout.toString();
    }
}