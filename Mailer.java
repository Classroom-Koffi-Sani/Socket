import java.net.*; 
import java.io.*;

/**
 * 
 */
public class Mailer {
    public static void main(String[] args) {
        System.setProperty("mail.host", "smtp.orange.fr");
        try {
            URL urlMail = new URL("mailto:cf@lipn.univ-paris13.fr");
            URLConnection connection = urlMail.openConnection();
            PrintStream p = new PrintStream(connection.getOutputStream());
            p.println("Subject: test\r\n\r\n corps du mail");
            // un message est constitué d’un en-tête
            // séparé d’une ligne vide avant le corps du message
            p.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}