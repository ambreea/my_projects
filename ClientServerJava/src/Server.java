import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

public class Server {
    public static void main(String[] args) {

        try (ServerSocket ss = new ServerSocket(80);
             Socket s = ss.accept();
             DataInputStream dis = new DataInputStream(s.getInputStream());
             DataOutputStream dos = new DataOutputStream(s.getOutputStream())) {

            while (true) {
                String input = dis.readUTF();

                if (input.equals(""))
                    break;

                int result = 0;
                String ecuatia = "";
                if (Arrays.stream(input.split(" ")).count() > 1) {
                    for (int i = 0; i < Arrays.stream(input.split(" ")).count(); i++) {
                        result = result + Integer.parseInt(input.split(" ")[i]);
                        if (i != Arrays.stream(input.split(" ")).count()-1)
                            ecuatia = ecuatia + input.split(" ")[i] + " + ";
                        else
                            {ecuatia = ecuatia + input.split(" ")[i];}

                    }

                    System.out.println("Ecuatia este: " + ecuatia);

                    System.out.println("Sending the result...");
                    dos.writeUTF(Integer.toString(result));
                } else {
                    dos.writeUTF("Nu se poate face adunarea cu mai putin de doua numere. Introduceti cel putin doua numere!");
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
