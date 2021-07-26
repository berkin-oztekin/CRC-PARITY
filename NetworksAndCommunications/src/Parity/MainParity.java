package Parity;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Scanner;

public class MainParity {

    public static void main(String[] args) throws UnknownHostException, IOException
    {
        // Bit size
        int size = 8;
        // Original data
        int[] data = new int[size];
        // Number of ones in original data
        int numberOfOnes = 0;
        // Original data + parity bit
        int[] dataWParity = new int[size + 1];

        // Create data array which consists of 0's and 1's

        /** createData(data, size); **/
        createData(data , size);

        System.out.println();
        // Calculate number of ones
        /** numberOfOnes = calculateParity(data, numberOfOnes); **/
        System.out.println("Number of '1' bits " + calculateParity(data , numberOfOnes));

        // Add parity bit to the data
        /** dataWParity = addParityBit(data, numberOfOnes); **/

        // Send the data to the server
        /** sendDataToServer(dataWParity, numberOfOnes); **/
    }

    // TODO: Create 8-bit binary data with integer array
    public static void createData(int[] data, int size)
    {


        // Accept the input from user
        //
        // ..

        Scanner input = new Scanner(System.in);

        // Enter the data bit by bit
        //
        //
        // ..
        for(int x = 0 ; x < size-1 ; x++){
            System.out.println("Please enter your data bit by bit");
            int bit =input.nextInt();
            data[x] = bit ;
        }
        // Print the data you have entered
        //
        //
        // ..
        for(int i = 0 ; i <data.length ; i++)
            System.out.print(data[i]);
    }

    // TODO: Calculate the number of ones in the original data
    private static int calculateParity(int[] data, int numOfOnes)
    {

        // Count the number of ones in integer array data[]
        // Print and return the number of ones
        //
        //
        // ..
        for(int i = 0 ; i < data.length ; i++){
            if(data[i] == 1){
                numOfOnes++ ;
            }
        }
        return numOfOnes;
    }

    // TODO: Add 0 or 1 based on the calculation of 1's in the original data ((FOR EVEN PARITY))
    private static int[] addParityBit(int[] data, int numOfOnes)
    {

        // Create a new integer array for the data parity bit added
        //
        // ..
        int [] array = new int[data.length+1];

        // Add all bits into new array and add parity bit

        if(numOfOnes % 2 == 0){
            System.out.println("The parity is even");
            array[data.length] = 0 ;

        }else {
            System.out.println("The parity is odd");
            array[data.length] = 1 ;
        }
        // (For even parity, the numbers of 1's must be even)
        //
        //
        //
        // ..

        return array;
    }

    // Send the parity bit added data to the server
    private static void sendDataToServer(int [] data, int numOfOnes) throws UnknownHostException, IOException
    {
        // Networking part
        String dataToBeSentToServer = "";
        for (int i = 0; i < data.length; i++) {
            dataToBeSentToServer += data[i];
        }

        Socket clientSocket = new Socket("localhost", 6788);

        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
        outToServer.writeUTF(dataToBeSentToServer + "\n");

        DataInputStream inFromServer = new DataInputStream(clientSocket.getInputStream());
        String responseFromServer = inFromServer.readUTF();
        System.out.println(responseFromServer);

        clientSocket.close();

    }

}
