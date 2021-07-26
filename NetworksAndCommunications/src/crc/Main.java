package crc;

import java.util.Scanner;
import java.io.*;
import java.net.*;

public class Main {

    public static int[] fourBitDivisor = {1, 0, 1, 1};

    public static void main(String[] args) throws UnknownHostException, IOException {

        CRC checker = new CRC();

        int[] datawCRC;
        Scanner scan = new Scanner(System.in);
        int n;

        // Take the input from the user
        System.out.println("Enter the size of the data:");
        n = scan.nextInt();
        int data[] = new int[n];
        System.out.println("Enter the data, bit by bit:");
        for(int i=0 ; i < n ; i++) {
            System.out.println("Enter bit number 2 to the power " + (n-i) + ":");
            data[i] = scan.nextInt();
        }

        // Print the data you have entered
        System.out.print("The data is: ");
        for (int i = 0; i < data.length; i++) {
            System.out.print(data[i]);
        }
        System.out.println();

        // Print the predefined divisor (1011)
        System.out.print("The divisor is: ");
        for (int i = 0; i < fourBitDivisor.length; i++) {
            System.out.print(fourBitDivisor[i]);
        }
        System.out.println();

        // Divide the inputted data by the inputted divisor
        // Store the remainder that is returned by the method
        int remainder[] = checker.divide(data, fourBitDivisor);
        System.out.print("CRC bits to be added to the data are: ");
        for(int i=0 ; i < remainder.length-1 ; i++) {
            System.out.print(remainder[i]);
        }

        // Put bits into data array with CRC and print the data
        System.out.print("\nThe CRC code generated is:");
        datawCRC = new int[data.length + (fourBitDivisor.length - 1)];
        for(int i=0 ; i < data.length ; i++) {
            datawCRC[i] = data[i];
        }
        for(int i=0 ; i < remainder.length-1 ; i++) {
            datawCRC[data.length + i] = remainder[i];
        }
        for (int i = 0; i < datawCRC.length; i++) {
            System.out.print(datawCRC[i]);
        }
        System.out.println();

        // Send the data with CRC to the server
        sendDataToServer(datawCRC);

    }

    // Send the CRC bits added data to the server
    private static void sendDataToServer(int [] data) throws UnknownHostException, IOException
    {
        String dataToBeSentToServer = "";
        for (int i = 0; i < data.length; i++) {
            dataToBeSentToServer += data[i];
        }
        // Networking part
        Socket clientSocket = new Socket("localhost", 6789);

        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
        outToServer.writeUTF(dataToBeSentToServer + "\n");

        DataInputStream inFromServer = new DataInputStream(clientSocket.getInputStream());
        String responseFromServer = inFromServer.readUTF();
        System.out.println(responseFromServer);

        clientSocket.close();

    }

}
