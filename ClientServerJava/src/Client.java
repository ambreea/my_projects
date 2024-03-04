import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws UnknownHostException {

        Scanner in = new Scanner(System.in);

        InetAddress ip = InetAddress.getLocalHost();

        try (Socket s = new Socket(ip, 80);
             DataInputStream dis = new DataInputStream(s.getInputStream());
             DataOutputStream dos = new DataOutputStream(s.getOutputStream())) {

            while (true) {
                System.out.println("Introduceti numerele cu spatiu intre ele: ");
                String input = in.nextLine();

                if (input.equals(""))
                    break;

                dos.writeUTF(input);
                dos.flush();

                String answer = dis.readUTF();
                System.out.println("Answer= " + answer);
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
}
