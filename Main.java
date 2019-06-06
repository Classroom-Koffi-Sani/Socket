import java.io.*; 
import java.net.Socket;
public class Main {
    public static void main(String[] args) {
        Socket connexion = null;
        try {
            connexion = new Socket("www.google.fr",80);
            Writer output = new OutputStreamWriter(
            connexion.getOutputStream(), "8859_1");
            String  req = "GET /search?q=DEFITECH HTTP/1.1\r\n\r\n";

            output.write(req); 
            output.flush();
            connexion.shutdownOutput();
            // fermeture partielle
            BufferedReader input =
            new BufferedReader(new InputStreamReader(connexion.getInputStream(),"8859_1"),1024);
            // flux en lecture
            StringBuffer sb = new StringBuffer(); 
            int c;
            while ((c = input.read()) != -1) 
                sb.append((char) c);
            System.out.println(sb);
            //Browser browser = new Browser("fr.wikipedia.org", sb.toString());  
        } catch (IOException e) {
            System.out.println(e);
        }
        finally {
            try {
                if (connexion != null) connexion.close();
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }
}