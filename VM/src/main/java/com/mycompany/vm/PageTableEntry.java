/**
 * PageTableEntry.java (Modified)
 * 
 * This is a modified version of the original PageTableEntry.java. The original code was provided as an educational example
 * in the book "Operating System Concepts with Java - Eighth Edition" by Abraham Silberschatz, Peter B. Galvin,
 * and Greg Gagne. The modifications I made to this code include documentation and updating of methods.
 *
 * A class representing an entry in the page table.
 * 
 * This class provides a convenient way to manage the entries in a page table. Each entry
 * consists of a frame number and a validity status. The frame number indicates the physical
 * memory location to which the corresponding virtual memory page is mapped. The validity status
 * determines whether the entry is currently valid or not.
 *
 * The class offers methods to initialize a new entry, check its validity status, retrieve the
 * assigned frame number, and set the mapping for the entry by assigning a frame number. By default,
 * when a new instance of PageTableEntry is created, it is initialized as invalid with no assigned frame number.
 * 
 * This code is based on the book "Operating System Concepts with Java - Eighth Edition"
 * by Abraham Silberschatz, Peter B. Galvin, and Greg Gagne.
 * 
 * @Orignally authored by: Gagne, Galvin, Silberschatz
 * Operating System Concepts with Java - Eighth Edition
 * Copyright John Wiley & Sons - 2010.
 * 
 * @modified by: Gabriel N. Swortwood
 * @modification_date July 10th, 2023
 * @update date July 21st, 2023
 * @update date July 23rd, 2023
 *
 * Key Components and Functionality:
 * 
 * This Java program defines a class called PageTableEntry, representing an entry in a page table. 
 * The class has two private instance variables, frameNumber (representing the frame number associated with the page table entry) 
 * and valid (a boolean indicating the validity of the entry). The class provides the following methods:
 * 
 *   Constructor PageTableEntry(): 
 *      Initializes a new instance of the class with the valid attribute set to false 
 *      and the frameNumber initialized to -1, indicating an invalid entry.
 *
 *   Method isValid(): 
 *      Checks if the page table entry is valid and returns true if it is, and false otherwise.
 *
 *   Method getFrameNumber(): 
 *      Retrieves the assigned frame number associated with the page table entry.
 *
 *   Method setMapping(int frameNumber): 
 *      Sets the mapping for the page table entry by assigning a frame number 
 *      and marks the entry as valid by setting the valid attribute to true.
 *
 * Usage of class:
 * 
 *      The PageTableEntry class can be utilized in an operating system context or other applications involving memory management. 
 *      It allows users to create instances of PageTableEntry, check their validity, retrieve the assigned frame number, 
 *      and set the mapping for the page table entry.
 *
 * Summary of Modifications:
 * 
 * July 10th. 2023
 * 
 * - Comment Block: 
 * The comment block at the beginning of the class, describing the purpose and attribution, has been removed in the new code.
 * 
 * - Method Name: 
 * The accessor method getValidBit() has been renamed to isValid() in the new code. 
 * This change improves the method's clarity and adheres to standard Java naming conventions.
 * 
 * - Validity check:
 * created a validity check, however, it only uses isValid as a check needs to be updated.
 * 
 * July 21st, 2023
 * 
 * - getValidityStatus:
 * Updated validity checking to check for valid/invalid rather than isValid.
 * 
 * July 23rd, 2023
 * 
 * - No further changes made.
 */

package com.mycompany.vm;

/**
 * Represents an entry in a page table.
 */
public class PageTableEntry {
    private int pageNumber;
    private int frameNumber;
    private boolean valid;

     /**
     * Constructs a PageTableEntry object with the specified page number and frame number.
     * The entry is initially considered valid.
     *
     * @param pageNumber The page number associated with this page table entry.
     * @param frameNumber The corresponding frame number in physical memory.
     */
    public PageTableEntry(int pageNumber, int frameNumber) {
        this.pageNumber = pageNumber;
        this.frameNumber = frameNumber;
        this.valid = true;
    }

    /**
     * Checks if the page table entry is valid.
     *
     * @return "Valid" if the entry is valid, "Invalid" otherwise.
     */
    public String getValidityStatus() {
        return valid ? "Valid" : "Invalid";
    }

    /**
     * Checks if the page table entry is valid.
     *
     * @return True if the entry is valid, false otherwise.
     */
    public boolean isValid() {
        return valid;
    }

    /**
     * Retrieves the frame number associated with the page table entry.
     *
     * @return the assigned frame number.
     */
    public int getFrameNumber() {
        return frameNumber;
    }

    /**
     * Sets the mapping for the page table entry by assigning a frame number.
     *
     * @param frameNumber the frame number to be assigned.
     */
    public void setMapping(int frameNumber) {
        this.frameNumber = frameNumber;
        valid = true;
    }
}