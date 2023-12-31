/**
 * MakeBACKING_STORE.java
 *
 * This program makes the file for the address translation assignment
 *
 * The name of the constructed file is BACKING_STORE.
 *
 * @author Gagne, Galvin, Silberschatz
 * Operating System Concepts with Java - Eighth Edition
 * Copyright John Wiley & Sons - 2010. 
 */

import java.io.*;

public class MakeBACKING_STORE
{
	public static void main(String[] args) throws java.io.IOException {
		/* Creates the necessary references for File class and RandomAccessFile class,
                also opens file and writes data. */
     	File fileName;
     	RandomAccessFile disk = null;

		try {
              	fileName = new File("BACKING_STORE");
              	disk = new RandomAccessFile(fileName, "rw");
		
			for (int i = 0; i < 256*256/4; i++) {
				disk.writeInt(i);
			}
          }
          catch (IOException e) {
          	System.err.println ("Unable to create the file BACKING_STORE");
              	System.exit(1);
          }
		finally {
			disk.close();
		}
	}
}
