import java.net.*; 
import java.io.*;

public class Main {
    private ServerSocket serverSocket;
    private Socket socket;
    
    public Main(int port) {
        try { 
            serverSocket = new ServerSocket(port, 1);
        }
            // creation du serveur
        catch (IOException e) {
            //e.printStackTrace();
            System.out.println(e.getMessage());
        }
        // erreur de création
    }
    public static void main(String[] args) throws IOException {
        int port;
        try {
            port = Integer.parseInt(args[0]);
        }
        catch (Exception e) {
            port = 0;
        } // donc valeur au hasard
        Main ct = new Main(port);
        System.out.println("Serveur mis à l'écoute sur le port " + port);
        ct.clientMgr();
    }

    public void clientMgr() throws IOException {
        while (true) {
            Socket socket = serverSocket.accept();

            try{
                
                new Thread(new ThreadForClient(socket)).start();
            }
            catch(Exception e){
                System.out.println(":( client");
            }
        }
    }
    
    public class ThreadForClient implements Runnable{
    
        Socket socket;
        ThreadForClient(Socket socket){
            this.socket = socket;
        }
        
        @Override
        public void run(){
        
        // écoute
            try { 
                
                Thread inputThread = new InputThread(socket.getInputStream());
                inputThread.start();
                   
                   
                // thread pour lecture
                Thread outputThread = new OutputThread(socket.getOutputStream());
                outputThread.start();
                // thread pour écriture
                try { 
                    inputThread.join();
                    outputThread.join();
                } //attente de fin R/W
                catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
                // interruption de thread
                
            }catch (IOException e) {
                System.out.println(e);
                System.out.println(e.getMessage());
            }
            finally {
                try { 
                    if (socket != null) 
                        socket.close();
                }catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }


            
 }

