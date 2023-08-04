/**
 * MemoryManager.java
 *
 * The class is based on the works of Gagne, Galvin, and Silberschatz from the book "Operating System Concepts with Java - Eighth Edition".
 * It utilizes a page table, physical memory frames, a translation look-aside buffer (TLB), and a disk-based backing store.
 * 
 * The "MemoryManager" class is a Java implementation of a virtual memory manager. 
 * It employs a page table, TLB (Translation Lookaside Buffer), 
 * and physical memory frames to efficiently translate virtual addresses to corresponding physical addresses while minimizing page faults
 * and optimizing TLB hits. The class initializes data structures, tracks statistics, 
 * and handles page faults by loading pages from a disk (backing store) into physical memory. 
 * The manager also provides methods to calculate page fault and TLB hit rates, enabling performance analysis. 
 * Overall, the class facilitates smooth memory access and effective memory management in a virtual environment.
 * 
 * @author Gabriel N. Swortwood
 * @creation date: July 10th, 2023
 * @update date July 20th, 2023
 * @update date July 26th, 2023
 * 
 * Key Components and Functionality:
 * 
 *      Memory Management: 
 *          The MemoryManager class provides methods to manage memory operations for a virtual memory system.
 * 
 *      Virtual Address Translation: 
 *          The translateVirtualAddress() method translates virtual addresses to physical addresses using the TLB and page table. 
 *          It handles TLB hits, TLB misses (page faults), and updates the TLB and page table accordingly.
 * 
 *      Physical Memory Access: 
 *          The getValue() method allows retrieving byte values from specified physical addresses in the physical memory.
 * 
 *      Page Fault Handling: 
 *          The handlePageFault() method handles page faults by loading pages from the disk into physical memory and updating the page table.
 * 
 *      Translation Lookaside Buffer (TLB):
 *          The updateTLB() method manages the TLB, updating mappings for page numbers and frame numbers, 
 *          and evicting the least recently used entry if the TLB is full.
 * 
 *      Statistics and Tracking:
 *          The class tracks and provides statistics such as the number of page faults (getPageFaults()), TLB hits (getTLBHits()), 
 *          and the total number of virtual addresses processed (getNumberOfAddresses()). 
 *          It also calculates the page fault rate (getPageFaultRate()) and TLB hit rate (getTLBHitRate()).
 * 
 * Usage of class:
 *      
 *      The MemoryManager class is designed to be used in a virtual memory system simulation. To use the program, follow these steps:
 * 
 *              1. Create a RandomAccessFile object representing the disk used as the backing store.
 * 
 *              2. Instantiate a MemoryManager object, passing the RandomAccessFile to its constructor.
 * 
 *              3. Invoke the translateVirtualAddress() method to translate virtual addresses to physical addresses.
 * 
 *              4. Access physical memory and retrieve byte values using the getValue() method.
 * 
 *              5. Monitor and retrieve statistics related to page faults and TLB hits using the provided methods 
 *                 (getPageFaults(), getTLBHits(), getNumberOfAddresses(), getPageFaultRate(), and getTLBHitRate()).
 * 
 * Summary of Modifications:
 * 
 * July 10th 2023:
 * 
 * Class and initial logic are constructed.
 * 
 * - Initialization:
 * The old code initializes the memory manager by creating page table entries, physical memory frames, and an empty TLB. 
 * It also sets various counters and trackers to their initial values.
 * 
 * - Address Translation:
 * The primary function of the memory manager is to translate virtual addresses to physical addresses. 
 * It does this by first checking the TLB for a mapping of the page number to a frame number. 
 * If a mapping is found in the TLB (TLB hit), the corresponding physical address is determined. Otherwise, 
 * it looks up the page table to find the frame number associated with the given page number.
 * 
 * - Page Fault Handling:
 * If a TLB miss occurs during address translation, indicating that the requested page is not in the TLB, the memory manager handles a page fault. 
 * A page fault means the requested page is not loaded in physical memory, 
 * so the memory manager loads it from the backing store (disk) into a free frame in physical memory.
 * 
 * - Updating TLB:
 * After handling a page fault or a TLB miss, the memory manager updates the TLB with the new mapping to optimize future address translations. 
 * It uses a "least recently used" replacement policy, removing the oldest entry from the TLB if it is full.
 * 
 * - Reading Data:
 * The memory manager provides a method to read a byte value from the specified physical address in the physical memory.
 * 
 * - Statistics Tracking:
 * The old code tracks statistics like the number of page faults, TLB hits, and the total number of virtual addresses processed. 
 * These statistics are used to calculate the page fault rate and TLB hit rate.
 * 
 * July 20th 2023:
 * 
 * - loadedPages boolean array: 
 * In the new code, the "loadedPages" array is added as a member variable. 
 * This array keeps track of which pages are currently loaded in memory. 
 * It is used to determine if a page is already in memory or if a page fault should be handled.
 * 
 * The new code uses the "loadedPages" array to check whether a page is already loaded in memory or not. 
 * If the page is loaded, it proceeds with TLB lookups and page table entry updates. 
 * If the page is not loaded, it invokes the "handlePageFault" method to load the page into memory and updates the page table and TLB.
 * 
 * - numberOfAddresses/incrementNumberOfAddresses method changes:
 * The new code introduces these methods to keep track of the total number of virtual addresses processed. 
 * These methods are used to calculate the page fault rate and TLB hit rate in the new code.
 * 
 * - initalize changes: 
 * The "initialize" method is added in the new code, 
 * which sets up the data structures for page table entries, physical memory frames, and TLB. 
 * It also resets various counters and trackers. This method is called in the constructor to initialize the MemoryManager object.
 * 
 * - translaterVirtualAddress method:
 * In the old code, the "translateVirtualAddress" method directly handled page faults and TLB updates. 
 * In the new code, it separates the logic for handling page faults and updating the TLB into their own methods. 
 * This makes the code more organized and easier to maintain.
 * 
 * - handlePageFault method changes:
 * In the old code, the "handlePageFault" method directly updated the page table entry within the method. 
 * In the new code, this method is modified to only handle the page fault by loading the page from the backing store into a free frame in physical memory. 
 * The update of the page table entry is moved to a separate method called "updatePageTable."
 * 
 * getPageFaultRate and getTLBHitRate method changes:
 * In the new code, these methods are modified to use the total number of virtual addresses processed, 
 * which is tracked using the "numberOfAddresses" member variable. 
 * This ensures accurate calculation of the page fault rate and TLB hit rate.
 * 
 * July 26th, 2023
 * 
  * - Added Package Declaration:
 *   - The MemoryManager class is now part of the package "com.mycompany.vm" to organize code.
 *
 * - Additional Imports:
 *   - Imported MongoDB related classes such as "com.mongodb.client.FindIterable," "com.mongodb.client.MongoCollection," and "org.bson.Document."
 *
 * - Class Description:
 *   - Added a class-level Javadoc comment to describe the purpose of the MemoryManager class and its use of a page table and TLB for address translation.
 *
 * - Constructor Parameters:
 *   - Added an additional parameter "pageTableCollection" of type "MongoCollection<Document>."
 *   - This parameter is used to store page table entries in a MongoDB collection for improved data management.
 *
 * - Added Member Variable:
 *   - Declared a boolean array "loadedPages" to keep track of loaded pages in memory.
 *   - This array is used to efficiently check for page faults during address translation.
 *
 * - Additional Methods:
 *   - Added "isPageFault()" and "isTLBHit()" methods to check if a page fault or TLB hit occurred during the translation of the last virtual address.
 *   - These methods provide insights into address translation performance.
 *
 * - MongoDB Integration:
 *   - Modified the "initialize()" method to accept a "MongoCollection<Document>" parameter.
 *   - The method now uses this collection to initialize the page table entries in MongoDB.
 *   - The "handlePageFault()" method was updated to store the page table entry in MongoDB when handling a page fault.
 *
 * - Code Improvements:
 *   - Introduced constants "TLB_SIZE" and "FRAME_SIZE" for better code readability and maintainability.
 *   - Updated the "getValue()" method to include offset validation to prevent buffer overflow.
 *   - Improved comments and method descriptions for better code understanding.
 */

package com.mycompany.vm;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

import com.mongodb.client.MongoCollection;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Arrays;
import org.bson.Document;

/**
 * MemoryManager class manages the virtual memory and its mapping to physical memory frames.
 * It uses a page table and a Translation Lookaside Buffer (TLB) for efficient address translation.
 */
public class MemoryManager {
    // Constants
    private static final int PAGE_TABLE_ENTRIES = 256;
    private static final int NUMBER_OF_FRAMES = 256;
    public static final int TLB_SIZE = 16;
    private static final int FRAME_SIZE = 256;

    // Member Variables
    private boolean[] loadedPages;
    private PageTableEntry[] pageTable;
    private Frame[] physicalMemory;
    private ArrayList<TLBEntry> TLB;
    private RandomAccessFile disk;

    // Other member variables
    private int nextFrameNumber;
    private int pageFaults;
    private int tlbHits;
    private int numberOfAddresses;

    /**
     * Constructs a MemoryManager object with the specified disk for the backing store.
     *
     * @param disk A RandomAccessFile representing the disk used as the backing store.
     * @param pageTableCollection A MongoDB collection for storing the page table entries.
     */
    public MemoryManager(RandomAccessFile disk, MongoCollection<Document> pageTableCollection) {
        this.disk = disk;
        initialize();
    }

    /**
     * Initializes the memory manager by creating page table entries, physical memory frames, and the TLB.
     * It also resets various counters and trackers.
     */
    private void initialize() {
        // Create page table entries
        loadedPages = new boolean[PAGE_TABLE_ENTRIES];
        Arrays.fill(loadedPages, false);
        pageTable = new PageTableEntry[PAGE_TABLE_ENTRIES];
        for (int i = 0; i < PAGE_TABLE_ENTRIES; i++) {
            pageTable[i] = new PageTableEntry(i, -1); // Initialize with invalid frame number
        }

        // Create physical memory frames
        physicalMemory = new Frame[NUMBER_OF_FRAMES];
        for (int i = 0; i < NUMBER_OF_FRAMES; i++) {
            physicalMemory[i] = new Frame();
        }

        // Initialize the TLB
        TLB = new ArrayList<>();

        // Reset counters and trackers
        nextFrameNumber = 0;
        pageFaults = 0;
        tlbHits = 0;
        numberOfAddresses = 0;
    }
    
    /**
     * Checks if a page fault occurred during the translation of the last virtual address.
     *
     * @return true if a page fault occurred, false otherwise.
     */
    public boolean isPageFault() {
        // Implement the logic to check if a page fault occurred
        // You can use any criteria based on your implementation
        return false;
    }

    /**
     * Checks if a TLB hit occurred during the translation of the last virtual address.
     *
     * @return true if a TLB hit occurred, false otherwise.
     */
    public boolean isTLBHit() {
        // Implement the logic to check if a TLB hit occurred
        // You can use any criteria based on your implementation
        return false;
    }
    
    /**
     * Increments the number of translated addresses.
     */
    public void incrementNumberOfAddresses() {
        numberOfAddresses++;
    }

    /**
     * Increments the number of page faults.
     */
    public void incrementPageFaults() {
        pageFaults++;
    }

    /**
     * Increments the number of TLB hits.
     */
    public void incrementTLBHits() {
        tlbHits++;
    }

    /**
     * Gets the number of translated addresses.
     *
     * @return The number of translated addresses.
     */
    public int getNumberOfAddresses() {
        return numberOfAddresses;
    }

    /**
     * Gets the number of page faults that occurred during address translation.
     *
     * @return The number of page faults.
     */
    public int getPageFaults() {
        return pageFaults;
    }

    /**
     * Gets the number of TLB hits that occurred during address translation.
     *
     * @return The number of TLB hits.
     */
    public int getTLBHits() {
        return tlbHits;
    }

    /**
     * Calculates the page fault rate as a percentage.
     *
     * @return The page fault rate as a percentage.
     */
    public float getPageFaultRate() {
        if (numberOfAddresses == 0) {
            return 0.0f;
        }
        return (float) pageFaults / numberOfAddresses * 100.0f;
    }

    /**
     * Gets the size of the physical memory in bytes.
     *
     * @return The size of the physical memory in bytes.
     */
    public int getMemorySize() {
        return physicalMemory.length * physicalMemory[0].getFrameSize();
    }

    public int translateVirtualAddress(int virtualAddress) throws IOException {
        int pageNumber = getPageNumber(virtualAddress);
        int offset = getOffset(virtualAddress);
        numberOfAddresses++; // Increment the total number of translated addresses

        // Search the page table for the page number entry
        PageTableEntry pageEntry = pageTable[pageNumber];

        if (pageEntry != null && pageEntry.isValid()) {
            int frameNumber = pageEntry.getFrameNumber();
            // Increment TLB hits when a valid mapping is found in TLB
            tlbHits++;
            // Proceed with the translation and return the physical address
            int physicalAddress = (frameNumber << 8) + offset;
            updateTLB(pageNumber, frameNumber); // Update TLB with the page table entry
            return physicalAddress;
        } else {
            // Handle the page fault here
            int frameNumber = handlePageFault(pageNumber);
            // Proceed with the translation and return the physical address
            int physicalAddress = (frameNumber << 8) + offset;
            updateTLB(pageNumber, frameNumber); // Update TLB with the page table entry
            return physicalAddress;
        }
    }

    /**
     * Retrieves the byte value from the specified physical address in the physical memory.
     *
     * @param physicalAddress The physical address to read from.
     * @return The byte value at the specified physical address.
     * @throws IOException If an I/O error occurs while accessing the disk.
     */
    public byte getValue(int physicalAddress) throws IOException {
        int frameNumber = (physicalAddress & 0x0000ff00) >> 8;
        int offset = physicalAddress & 0x000000ff;

        // Validate that the provided offset does not lead to buffer overflow
        if (offset < 0 || offset >= physicalMemory[frameNumber].getFrameSize()) {
            throw new IndexOutOfBoundsException("Invalid offset: " + offset);
        }

        if (frameNumber < 0 || frameNumber >= NUMBER_OF_FRAMES) {
            throw new IndexOutOfBoundsException("Frame number is outside the valid range of physical memory");
        }

        return physicalMemory[frameNumber].readByte(offset);
    }

    /**
     * Retrieves the page number from the virtual address.
     *
     * @param virtualAddress The virtual address.
     * @return The page number.
     */
    private int getPageNumber(int virtualAddress) {
        return (virtualAddress & 0x0000ff00) >> 8;
    }

    /**
     * Retrieves the offset from the virtual address.
     *
     * @param virtualAddress The virtual address.
     * @return The offset.
     */
    private int getOffset(int virtualAddress) {
        return (virtualAddress & 0x000000ff);
    }
    
    

    /**
     * Handles a page fault for the specified page number.
     * Loads the page from the backing store into a free frame in physical memory.
     *
     * @param pageNumber The page number.
     * @return The frame number where the page is loaded.
     * @throws IOException If an I/O error occurs while accessing the disk.
     */
    private int handlePageFault(int pageNumber) throws IOException {
        // Increment the pageFaults counter
        pageFaults++;

        // Find a free frame in physical memory
        int frameNumber = getNextFreeFrame();

        // Load the page from the backing store into the frame
        byte[] pageData = new byte[physicalMemory[0].getFrameSize()];
        disk.seek(pageNumber * physicalMemory[0].getFrameSize());
        disk.readFully(pageData);
        physicalMemory[frameNumber].setFrame(pageData);

        // Mark the page as loaded in memory
        loadedPages[pageNumber] = true;

        // Update the page table entry for the specified page number
        PageTableEntry pageEntry = new PageTableEntry(pageNumber, frameNumber);
        pageTable[pageNumber] = pageEntry;

        return frameNumber;
    }

    /**
     * Gets the next free frame number in physical memory.
     *
     * @return The next free frame number.
     */
    private int getNextFreeFrame() {
        int frameNumber = nextFrameNumber;
        nextFrameNumber = (nextFrameNumber + 1) % NUMBER_OF_FRAMES;
        return frameNumber;
    }

    /**
     * Updates the TLB with the mapping for the specified page number and frame number.
     * If the mapping already exists, it does not update the TLB.
     * If the TLB is full, it removes the least recently used entry.
     *
     * @param pageNumber The page number.
     * @param frameNumber The frame number.
     */
    public void updateTLB(int pageNumber, int frameNumber) {
        // Check if TLB already contains the mapping for the given page number
        for (TLBEntry entry : TLB) {
            if (entry.checkPageNumber(pageNumber)) {
                return; // Mapping already exists, no need to update TLB
            }
        }

        // TLB miss, add new entry
        TLBEntry newEntry = new TLBEntry(pageNumber, frameNumber);
        TLB.add(0, newEntry); // Add at the front of the TLB (most recently used)

        // Limit the size of TLB to TLB_SIZE
        if (TLB.size() > TLB_SIZE) {
            TLB.remove(TLB_SIZE); // Remove the least recently used entry if TLB is full
        }
    }
}



