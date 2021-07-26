package crc;

public class CRC
{
    public CRC()
    {}

    // TODO: Divide a binary number by a divisor and return the remainder
    public int[] divide(int old_data[], int divisor[])
    {
        // Remainder array
        int remainder[];

        // Create a new data array as the size of old data + divisor length
        int data[] = new int[old_data.length + divisor.length];
        System.arraycopy(old_data, 0, data, 0, old_data.length);

        // Remainder array stores the remainder
        remainder = new int[divisor.length];
        // Initially, remainder's bits will be set to the data bits
        System.arraycopy(data, 0, remainder, 0, divisor.length);

        // Loop runs for same number of times as number of bits of data
        // This loop will continuously xor the bits of the remainder and
        // divisor
        for(int i = 0 ; i < old_data.length ;i++ )
        {
            // Print first data bit
            //
            // ..
            System.out.println((i+1) + remainder[0]);
            System.out.println(remainder[0] == 1);
            // If remainder's first bit is 1
            if(remainder[0] ==1)
            {
                // We have to xor the remainder bits with divisor bits, add the result
                //  into remainder array and print the new remainder
                /** for( ; ; ) **/
                for(int x = 1 ; x< divisor.length ; x++){
                    remainder[x-1] = xor(remainder[x] , divisor[x]);
                    System.out.println(remainder[x-1]);
                }
                //
                // ..
            }
            // If remainder's first bit is 0
            else
            {
                // We have to xor the remainder bits with 0 and print the new remainder
                /** for( ; ; ) **/
                for(int z = 1 ; z < divisor.length ; z++){
                    remainder[z-1] = xor(remainder[z] , 0 );
                    System.out.println(remainder[z-1]);
                }
                //
                //
                // ..
            }
            // The last bit of the remainder will be taken from the data
            // This is the 'carry' taken from the dividend after every step
            // of division
            remainder[divisor.length-1] = data[i+divisor.length];
            System.out.println(remainder[divisor.length-1]);
            //
            // ..
        }

        return remainder;
    }

    // TODO: Accept the data,
    // Return false if there is an error with CRC code, true otherwise
    public boolean receive(int data[], int divisor[])
    {
        // This is the receiver method
        // It accepts the data and divisor (although the receiver already has
        // the divisor value stored, with no need for the sender to resend it)

        int remainder[] = divide(data, divisor);
        // Division is done
        // Check that if the remainder consists of all 0's
        /** for( ; ; ) **/
        for(int i = 0 ; i < remainder.length ; i++)
        {
            // If remainder is not all zeros; then there is an error
            if(remainder[i] != 0 )
            {
                System.out.println("There is an error in received data...");
                return false;
            }
        }

        //Otherwise there is no error in the received data
        System.out.println("Data was received without any error.");
        return true;
    }

    // This simple function returns the xor of two integer bits
    public int xor(int a, int b)
    {
        if(a == b) {
            return 0;
        }
        return 1;
    }

}
