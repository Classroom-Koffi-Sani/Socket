import java.io.*;
import java.net.*;



class InputThread extends Thread {
    InputStreamReader in;
    
    public InputThread(InputStream in) {
        this.in = new InputStreamReader(in);
    }
    
    public void run() {
        try {
            int i;
            while ((i = in.read()) != -1) { 
                System.out.write(i); 
            }
        }
        catch (SocketException e) {} // cas socket fermé
        catch (IOException e) {}
        try { 
            in.close(); 
        }catch (IOException e) {}
    }
}