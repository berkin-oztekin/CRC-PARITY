import javax.management.OperationsException;
import java.lang.management.OperatingSystemMXBean;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Arrays;


public class MyServer {
    static double Balance = 1000 ;
    private static void deposit(double amount){
        Balance += amount ;
        System.out.println("Your new bank account " + Balance);
    }
    private static void withdraw(double amount){
        Balance -= amount ;
        System.out.println("Your new bank account " + Balance);
    }


    public static void main(String[] args) throws Exception {
        DatagramSocket serverSocket = new DatagramSocket(9876);


        byte [] sendData = new byte[1024];
        byte [] receiveData = new byte[1024];

        while(true) {
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            serverSocket.receive(receivePacket);
            String sentence = new String(receivePacket.getData());
            InetAddress IPadress = receivePacket.getAddress();
            int port = receivePacket.getPort();

            String capitalizedSentenced = sentence.toUpperCase();
            sendData = capitalizedSentenced.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPadress, port);
            serverSocket.send(sendPacket);

            String methodname = null;
            String money = " ";
            try {
                String[] splittedArray = capitalizedSentenced.split(" ");
                methodname = splittedArray[0];
                money = splittedArray[2];

            } catch (Exception e) {

            }

            if (methodname.equals("deposit")){
                deposit(Double.parseDouble(money));
                System.out.println("You deposited ---->" + money + "Now your bank account Balance ----->" + Balance);
            }else if ( methodname.equals("withdraw")){
                if((Double.parseDouble(money) < Balance)){
                    withdraw(Double.parseDouble(money));
                    System.out.println("You withdraw ---->" + money + "Now your bank account Balance ----->" + Balance);
                }else {
                    System.out.println("There is nı enough money -------> " + Balance);
                }
            }
        }
    }

    public char decrypt(String clientMessage) {
        int key = 6;
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
