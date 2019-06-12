import java.io.*;

class OutputThread extends Thread {
    OutputStreamWriter out;
    
    public OutputThread(OutputStream out) {
        this.out = new OutputStreamWriter(out);
    }
    
    public void run() {
        String ligne;
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        try {
            out.write("Bonjour client");
            System.out.println("Lecture depuis OutputThread");
            while (true) {
                ligne ="Reponse"; //in.readLine();
                System.out.println(" Vous avez saisi : " + ligne);
                if (ligne.equals(".")) 
                    break;
                out.write(ligne + "\r\n");
                out.flush();
            } 
        }
        catch (IOException e) {}
        try { 
            out.close(); 
        } catch (IOException e) {}
    }
}