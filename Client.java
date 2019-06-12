import java.io.*; 
import java.net.Socket;
public class Client {
    public static void main(String[] args) {
        Socket connexion = null;
        try {
            connexion = new Socket("127.0.0.1",5000);
            Writer output = new OutputStreamWriter(
                connexion.getOutputStream(), "8859_1");
            System.out.println("Création de la connexion. Envoi de message et attente de réponse");
            output.write("GET / HTTP 1.0\r\n\r\n"); 
            output.write("Hello World !!! \r\n\r\n"); 
            output.flush();
            connexion.shutdownOutput();
            // fermeture partielle
            BufferedReader input =
                new BufferedReader(new InputStreamReader(connexion.getInputStream(),"8859_1"),1024);
                System.out.println("Obtention de l'input reader: " + connexion.getInputStream().available());
            // flux en lecture
            StringBuffer sb = new StringBuffer(); 
            int c;
            System.out.println(input.readLine() + " : " + input.ready());
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