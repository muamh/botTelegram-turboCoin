package Modules;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class NmapScanner {
    public static String scanPorts(String host) {
        StringBuilder result = new StringBuilder();

        try {
            String comando = "C:\\Program Files (x86)\\Nmap\\nmap.exe -p- " + host;
            System.out.println("entrato 1: \n" + comando);
            Process processo = Runtime.getRuntime().exec(comando);
            System.out.println("entrato 2 processo eseguito");

            BufferedReader reader = new BufferedReader(new InputStreamReader(processo.getInputStream()));
            System.out.println("entrato 3 buffered readato");
            String linea;
            System.out.println("entrato 4");
            while ((linea = reader.readLine()) != null) {
                System.out.println(linea);
                result.append(linea).append("\n");
            }
            System.out.println("uscito 5, fuori ciclo");

            int exitCode = processo.waitFor();
            result.append("Codice di uscita: ").append(exitCode);
            System.out.println("Codice di uscita: " + exitCode);

        } catch (IOException e) {
            System.out.println("Eccezione di IO");
            e.printStackTrace();
        } catch (InterruptedException e) {
            System.out.println("Eccezione di interruzione");
            e.printStackTrace();
        }

        System.out.println("Finito\n\n\n");
        return result.toString();
    }
}
