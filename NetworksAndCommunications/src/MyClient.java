import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class MyClient {
    public static void main(String [] args) throws Exception{
        {
            String userInput = "deposit 400" ;

                        BufferedReader inFromUser =
                        new BufferedReader(new InputStreamReader(System.in));
                DatagramSocket clientSocket = new DatagramSocket();
                InetAddress IPAddress = InetAddress.getByName("localhost");

                byte[] sendData = new byte[1024];
                byte[] receiveData = new byte[1024];
                userInput = inFromUser.readLine();
                sendData = userInput.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
                clientSocket.send(sendPacket);

                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                clientSocket.receive(receivePacket);
                String modifiedSentence = new String(receivePacket.getData());
                System.out.println("FROM SERVER:" + modifiedSentence);
                clientSocket.close();


            }
        }
    public char decrypt(String clientMessage) {
        int key = -6;
        System.out.println("Client Message: " + clientMessage);
        char[] chars = clientMessage.toCharArray();
        char word = 0;
        for (char c : chars) {
            c += key;
            word = c ;
            System.out.println(word);
        }
        return word ;

    }
    }

