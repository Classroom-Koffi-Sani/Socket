import java.io.*; 
import java.net.Socket;
public class Client {
    public static void main(String[] args) {
        Socket connexion = null;
        try {
            connexion = new Socket("127.0.0.1",5000);
            Writer output = new OutputStreamWriter(
            connexion.getOutputStream(), "8859_1");
            output.write("GET / HTTP 1.0\r\n\r\n"); 
            output.write("Hello World !!! \r\n\r\n"); 
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