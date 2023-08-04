/**
 * Frame.java (Modified)
 * 
 * This is a modified version of the original Address.java file. The original code was provided as an educational example
 * in the book "Operating System Concepts with Java - Eighth Edition" by Abraham Silberschatz, Peter B. Galvin,
 * and Greg Gagne. The modifications I made to this code include documentation clarity and improving setFrame.
 *
 * This class, named "Frame," represents a data structure for managing frames of bytes.
 * It provides methods for setting and reading bytes within the frame.
 * The class has a constant FRAME_SIZE representing the size of a frame in bytes.
 * It has a private frameValue array that holds the actual data of the frame.
 * 
 * (Update file to better represent what the file is doing.)
 *
 * Originally authored by: Gagne, Galvin, Silberschatz
 * Operating System Concepts with Java - Eighth Edition
 * Copyright John Wiley & Sons - 2010.
 * 
 * @modified by: Gabriel N. Swortwood
 * @modification date: July 10th, 2023
 * @update date: July 26th, 2023
 * 
 * Key Components and Functionality:
 * 
 *      Frame:
 *          The Frame class represents a physical page frame within a simulated operating system.
 *          It contains a constant FRAME_SIZE set to 256 bytes, representing the fixed size of the frame.
 *          The class has a private byte[] frameValue to store the data within the frame.
 *          The constructor initializes the frameValue array with an empty byte array of size FRAME_SIZE.
 * 
 *      setFrame:
 *          The setFrame method allows users to set the contents of the frame by copying the provided byte array into frameValue. 
 *          If the provided array is smaller than FRAME_SIZE, it is copied up to its size, and the remaining bytes in frameValue are left unchanged.
 * 
 *      readWord:
 *          The readWord method reads a single byte (word) from the frame at the specified offset.
 * 
 * Usage of class:
 * 
 *      The Frame class is intended to be used within a simulated operating system or memory management system. 
 *      It provides an abstraction for managing page frames with fixed sizes 
 *      and allows users to set and read data within the frame using the setFrame and readWord methods.
 * 
 * Summary of Modifications:
 * 
 * July 10th, 2023
 * 
 * - Improved Documentation: 
 * Added descriptive comments to the class, explaining its purpose and functionality.
 * 
 * - Improved Clarity setFrame:
 * the clarity of comments, including fixing a grammatical error in the setFrame method comment.
 * 
 * July 26th, 2023
 * 
 * - Frame Organization: 
 * Moved the Frame class to the "com.mycompany.vm" package for better organization.
 * 
 * - FRAME_SIZE:
 * Changed the access modifier of the FRAME_SIZE constant to private static final, making it accessible only within the class.
 * 
 * - frameValue to frameData:
 * Renamed the variable holding the frame data from 'frameValue' to 'frameData' for improved clarity.
 * 
 * - getFrameValue Removal:
 * Removed the getFrameValue() method to prevent direct access to the frame data from outside the class.
 * 
 * setFrame update:
 * Updated the setFrame() method to copy the provided byte array up to its size into the frame data array, 
 * and no longer throws an exception for exceeding the FRAME_SIZE.
 * 
 * Creation of getFrameSize:
 * Introduced the getFrameSize() method to retrieve the size of a frame in bytes.
 * 
 * Replaced readWord with readByte:
 * Removed the readWord() method, leaving only the readByte() method to read a single byte from the frame at the specified offset.
 * 
 * Removed main:
 * Removed the main() method used for demonstration purposes.
 */

package com.mycompany.vm;

/**
 * Frame class represents a frame of data in a virtual machine.
 * Each frame has a fixed size in bytes and stores data in a byte array.
 */
public class Frame {

    /**
     * The size of a frame in bytes.
     */
    private static final int FRAME_SIZE = 256; // Replace 256 with the actual frame size in bytes
    private byte[] frameData;

    /**
     * Constructs a new Frame object with the specified frame size.
     * The frame data array is initialized with zeroes.
     */
    public Frame() {
        frameData = new byte[FRAME_SIZE];
    }

    /**
     * Gets the size of a frame in bytes.
     *
     * @return The size of a frame in bytes.
     */
    public static int getFrameSize() {
        return FRAME_SIZE;
    }

    /**
     * Sets the contents of the frame by copying the provided byte array into the data array.
     * If the provided byte array is smaller than FRAME_SIZE, it is copied up to its size,
     * and the remaining bytes in the data array are left unchanged.
     *
     * @param bytes the byte array to set as the contents of the frame
     */
    public void setFrame(byte[] bytes) {
        System.arraycopy(bytes, 0, frameData, 0, Math.min(bytes.length, FRAME_SIZE));
    }

    /**
     * Reads a single byte from the frame at the specified offset.
     *
     * @param offset the offset within the frame from which to read the byte
     * @return the byte value at the specified offset in the frame
     * @throws IndexOutOfBoundsException if the offset is outside the valid range of the frame
     */
    public byte readByte(int offset) {
        return frameData[offset];
    }
}