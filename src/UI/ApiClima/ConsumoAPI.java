/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UI.ApiClima;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

/**
 *
 * @author sergi
 */
public class ConsumoAPI {
     public static String getInfo()
        {
            try
            {
                URL url = new URL("https://api.weatherapi.com/v1/current.json?key=55e0aba5d2ff44b0ade142639231306&q=Mar%20del%20Plata");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.connect();
                int responseCode = connection.getResponseCode();
                if (responseCode != 200)
                {
                    throw new RuntimeException("Codigo de error: "+responseCode);
                }
                else
                {
                    StringBuilder stringBuilder = new StringBuilder();
                    Scanner scanner = new Scanner(url.openStream());
                    while (scanner.hasNext())
                    {
                        stringBuilder.append(scanner.nextLine());
                    }
                    scanner.close();
                    return stringBuilder.toString();
                }
            }
            catch (IOException exception)
            {
                System.out.println(exception.getMessage());
            }
            return "";
        }
    
}
