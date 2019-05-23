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
            while (true) {
                ligne = in.readLine();
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