/**
* MakeBACKING_STORE.java (Modified)
* 
* This is a modified version of the original Address.java file. The original code was provided as an educational example
* in the book "Operating System Concepts with Java - Eighth Edition" by Abraham Silberschatz, Peter B. Galvin,
* and Greg Gagne. The modifications I made to this code include documentation, improved exception handling, updated import statements,
* and better formatting.
*
* MakeBACKING_STORE class creates a new file named "BACKING_STORE" and fills it with integer data.
* The file is accessed using RandomAccessFile with read-write access.
* It writes integers to the file in a loop, running 16384 times (256 * 256 / 4).
* If an IOException occurs during file creation or writing, an error message is displayed.
* The file is closed after writing in the 'finally' block to ensure proper resource handling.
*
* (Update functionality for: for the for loop to be a constant and not a magic number.)
* 
* Example: To use this class, simply run its main method. The program will create the "BACKING_STORE" file and fill it with data.
* The file will contain 16384 integers, which corresponds to 256 * 256 / 4.
* 
* @Orignally authored by: Gagne, Galvin, Silberschatz
* Operating System Concepts with Java - Eighth Edition
* Copyright John Wiley & Sons - 2010.
* 
* @modified by: Gabriel N. Swortwood
* @modification_date July 10th, 2023
* @update date July 22nd, 2023
* 
* Key Components and Functionality:
* 
*     BACKING_STORE:
*              Creation of a new file named "BACKING_STORE" using the File class.
*              Opening the file with read-write access using the RandomAccessFile class.
*              Writing a series of integers to the file to fill it with data.
*              The file will contain 16384 integers, as the loop runs 256 * 256 / 4 = 16384 times.
* 
* Usage of file:
* 
*       To use this file, simply run its main method. Upon execution, 
*       the program will create the "BACKING_STORE" file in the same directory as the program itself. 
*       The file will be filled with the sequence of integers, simulating memory contents. 
*       The generated file can be used in conjunction with an address translation algorithm or 
*       memory management simulation to simulate memory access patterns.
* 
* Summary of Modifications:
* 
* -Documentation:
* The new code features more comprehensive comments, providing a clear overview of the program's purpose, usage, and considerations.
* 
* -Updated import statement:
* The new code imports only the necessary classes from the java.io package, promoting better code organization and avoiding potential namespace conflicts.
* 
* -Format:
* The new code follows proper code formatting with consistent indentation and spacing, improving code readability.
* 
* -Exception Handling:
*  In the new code, the IOException is caught in a specific catch block, 
*  and an error message is displayed in case of an exception during file creation or writing, making the error handling more precise.
* 
* July 22nd, 2023
* 
* - Documentation:
* Updated documentation to reflect what things are doing in this class.
*/

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * MakeBACKING_STORE - Java program to create a binary file "backingstore.bin" and fill it with data.
 * The file will contain a sequence of integers from 0 to 16383 (256 * 256 / 4).
 */
public class MakeBACKING_STORE {
    
    /**
     * The main method is the entry point of the program.
     *
     * @param args Command-line arguments (not used in this program).
     * @throws IOException If there's an error during file creation or writing.
     */
    public static void main(String[] args) throws IOException {
        File fileName = new File("backingstore.bin"); // Use "backingstore.bin" as the filename
        RandomAccessFile disk = null;

        try {
            // Create a new file object with the name "backingstore.bin"
            fileName = new File("backingstore.bin");
            
            // Create a new RandomAccessFile with read-write access to the file
            disk = new RandomAccessFile(fileName, "rw");

            // Write integers to the file, filling it with data
            // The loop runs 16384 times (256 * 256 / 4 = 16384) to write 16384 integers sequentially.
            for (int i = 0; i < 256 * 256 / 4; i++) {
                disk.writeInt(i);
            }
            
            // File creation and writing are successful
            System.out.println("The file 'backingstore.bin' has been created and filled with data.");
        } catch (IOException e) {
            // If an exception occurs during file creation or writing, display an error message.
            System.err.println("Unable to create the file backingstore.bin");
            System.exit(1);
        } finally {
            // Close the file in the 'finally' block to ensure it's closed even if an exception occurs.
            if (disk != null) {
                disk.close();
            }
        }
    }
}
