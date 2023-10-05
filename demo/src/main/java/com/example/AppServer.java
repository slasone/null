package com.example;
import java.io.*;
import java.net.*;
import java.util.*;

/**
 * Hello world!
 *
 */
public class AppServer 
{

    ServerSocket server = null;
    Socket client = null;
    String stringaRicevuta = null;
    String stringaModificata = null;
    BufferedReader inDalClient;
    DataOutputStream outVersoClient; 

    public Socket attendi(){
            try {

                System.out.println("Server partito in esecuzione ");
                server = new ServerSocket(6789);
                client = server.accept();

                inDalClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
                outVersoClient = new DataOutputStream(client.getOutputStream());

            } catch (Exception e) {

                System.out.println(e.getMessage());
                System.out.println("Errore durante l'instanza del server !");
                System.exit(1);
    
            }
            return client;
        }
    
        public void comunica(){
            for(;;){
                attendi();
            try {
                do{
                    System.out.println("benvenuto client, scrivi una frase e la trasformo in maiuscolo. Attendo ...");
                    stringaRicevuta = inDalClient.readLine();
                    System.out.println("ricevuta la stringa dal cliente : " + stringaRicevuta);
                   
                    stringaModificata = stringaRicevuta.toUpperCase();
                    System.out.println("invio la stringa modificata al client");
                    outVersoClient.writeBytes(stringaModificata + '\n');
        
                    System.out.println("server: fine elaborazione");
                    
                } while(!stringaRicevuta.equals("BYE"));

                if(stringaRicevuta.equals("BYE")){
                    client.close();
                }
            } catch (Exception e) {
                }
            }
        }
    
    public static void main (String args[]){
    
        AppServer server = new AppServer();
        server.attendi();
        server.comunica();
    }
    
}