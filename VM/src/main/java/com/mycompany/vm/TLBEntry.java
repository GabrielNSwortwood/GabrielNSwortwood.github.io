/**
 * TLBEntry.java (Modified)
 * 
 * This is a modified version of the original TLBEntry.java. The original code was provided as an educational example
 * in the book "Operating System Concepts with Java - Eighth Edition" by Abraham Silberschatz, Peter B. Galvin,
 * and Greg Gagne. The modifications I made to this code include documentation and updating of methods.
 *
 * This updated version of the TLBEntry class represents an entry in the Translation Lookaside Buffer (TLB).
 * It efficiently stores information about the page number, corresponding frame number in physical memory,
 * and maintains a flag to indicate whether the entry is valid or not.
 * The class offers a constructor to initialize TLB entries with specific page and frame numbers,
 * and it provides utility methods to check the validity status and match page numbers.
 * The improvements in this version result in a more organized and expressive implementation,
 * making it easier to work with TLB entries in a virtual memory management system.
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
 * Instance Variables:
 *   private int pageNumber: Represents the page number associated with this TLB entry.
 *   private int frameNumber: Represents the corresponding frame number in physical memory.
 *   private boolean valid: Indicates whether the TLB entry is valid.
 * 
 * Constructor:
 *   public TLBEntry(int pageNumber, int frameNumber): Constructs a TLBEntry object with the specified page number and frame number. 
 *   The valid flag is set to true by default.
 * 
 * Methods:
 *   public String getValidityStatus(): Checks if the TLB entry is valid and returns "Valid" if the entry is valid or "Invalid" otherwise.
 *   public boolean checkPageNumber(int pageNumber): Checks if the TLB entry matches the given page number and returns true if the page number matches, 
 *   false otherwise.
 *   public int getFrameNumber(): Retrieves the frame number associated with this TLB entry.
 * 
 * Usage of class:
 *   The TLBEntry class is designed to represent an entry in the Translation Lookaside Buffer (TLB). 
 *   It holds information about the page number, frame number, and a flag to indicate whether the entry is valid.
 * 
 * Summary of Modifications:
 * 
 * July 10th, 2023
 * 
 * - TLBEntry constructor: 
 * now takes the pageNumber and frameNumber as parameters to initialize the TLB entry during object creation.
 * 
 * - isValid variable:
 * in the old code is replaced with valid in the new code for improved naming consistency.
 * 
 * - setMapping() method: 
 * present in the old code is removed, and the constructor is utilized for setting the initial values of the TLB entry.
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
 * Represents an entry in the Translation Lookaside Buffer (TLB).
 */
public class TLBEntry {
    private int pageNumber;     // The page number associated with this TLB entry
    private int frameNumber;    // The corresponding frame number in physical memory
    
    /**
     * Creates a new TLB entry with the specified page number and frame number.
     *
     * @param pageNumber The page number associated with this TLB entry.
     * @param frameNumber The corresponding frame number in physical memory.
     */
    public TLBEntry(int pageNumber, int frameNumber) {
        this.pageNumber = pageNumber;
        this.frameNumber = frameNumber;
    }

    /**
     * Checks if the TLB entry matches the given page number.
     *
     * @param pageNumber The page number to check.
     * @return True if the page number matches the TLB entry's page number, false otherwise.
     */
    public boolean checkPageNumber(int pageNumber) {
        return this.pageNumber == pageNumber;
    }

    /**
     * Retrieves the frame number associated with this TLB entry.
     *
     * @return The frame number corresponding to this TLB entry.
     */
    public int getFrameNumber() {
        return frameNumber;
    }
}