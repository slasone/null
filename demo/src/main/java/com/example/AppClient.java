package com.example;
import java.io.*;
import java.net.*;
/**
 * Hello world!
 *
 */
public class Appclient {
    String nomeServer = "localhost";
    int portaServer = 6789;
    Socket mioSocket;
    BufferedReader tastiera;
    String stringaUtente;
    String stringaRicevutaDalServer;
    DataOutputStream outVersoServer;
    BufferedReader inDalServer;

    public Socket connetti(){
        try {

            tastiera = new BufferedReader(new InputStreamReader(System.in));
            mioSocket = new Socket(nomeServer, portaServer);
            outVersoServer = new DataOutputStream(mioSocket.getOutputStream());
            inDalServer = new BufferedReader(new InputStreamReader(mioSocket.getInputStream()));

        }catch(UnknownHostException e){

            System.out.println("Host sconusciuto");
                
            } catch (Exception e) {

                System.out.println(e.getMessage());
                System.out.println("Errore durante la connessione");
                System.exit(1);

            }
            return mioSocket;
    }


    public void comunica(){
        do {
            try {
                
                System.out.println("inserisci la stringa da trasmettere al server (se vuoi terminare il processo digita BYE):" +'\n');
                stringaUtente = tastiera.readLine();
                
                System.out.println("invio la stringa al server e attendo");
                outVersoServer.writeBytes(stringaUtente + '\n');
    
                stringaRicevutaDalServer = inDalServer.readLine();
                System.out.println("risposta dal server " + '\n' +stringaRicevutaDalServer);
    
                System.out.println(" CLIENT: termina elaborazione e chiude connessione" );
    
            } catch (Exception e) {
                
                System.out.println(e.getMessage());
                System.out.println("Errore durante la comunicazione col server !");
                System.exit(1);
                
            }
            
        } while (stringaRicevutaDalServer.equals("BYE"));
       
    }

    public static void main( String[] args ){
        AppClient client = new AppClient();
        client.connetti();
        client.comunica();
    }
}