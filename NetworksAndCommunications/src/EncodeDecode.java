import java.io.IOException;

public class EncodeDecode {
    public static void main(String[] args) throws IOException {
       /* String clientMessage = "Selamın Aleyküm";
        int key = 6;
        System.out.println("Client Message: " + clientMessage);
        char[] chars = clientMessage.toCharArray();
        char returnValue= 0;

        for (char c : chars) {
            c += key;
            System.out.print(c);
        }

        */
      /*  byte sendData[] = new byte[20];
        SecureRandom random = new SecureRandom();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Please enter your name ");
        String input = reader.readLine();

        sendData = input.getBytes();
        String[] message = input.split("");

        System.out.println("Your first letter of your text " + message[0]);


        for (String i : message) {
            System.out.println("Your massage ----> " + i);
        }

       */

        System.out.println(Encrypto("Selam"));

    }

    public static char Encrypto(String clientMessage) {
        int key = 6;
        String massage = " ";

        System.out.println("Client Message: " + clientMessage);
        char[] chars = clientMessage.toCharArray();
        char returnValue= 0;

        for (char c : chars) {
            c += key;
        }
            return returnValue;
    }

}
