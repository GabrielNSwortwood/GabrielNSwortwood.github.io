/**
 * VM.java (Modified)
 *
 * This is a modified version of the original VM.java file. The original code was provided as an educational example
 * in the book "Operating System Concepts with Java - Eighth Edition" by Abraham Silberschatz, Peter B. Galvin,
 * and Greg Gagne. The modifications made to the original code include splitting the functionality into a separate
 * MemoryManager class, improving error handling, using Path and Files for file operations, and generating statistics
 * within the MemoryManager class.
 * 
 * @Orignally authored by: Gagne, Galvin, Silberschatz
 * Operating System Concepts with Java - Eighth Edition
 * Copyright John Wiley & Sons - 2010.
 *
 * @modified by: Gabriel N. Swortwood
 * @modification_date July 10th, 2023
 * @update date July 21st, 2023
 * @update date July 25th, 2023
 * 
 * The VM class represents a virtual memory simulation, allowing users to perform address translation for virtual memory addresses 
 * and generate statistics about the translation process. 
 * The class employs a MemoryManager object to manage memory, handle page faults,
 * and maintain a Translation Lookaside Buffer (TLB) for efficient address translation. 
 * Here are the key components and functionalities of the VM class:.
 *
 * Key Components and Functionality:
 *
 *   VM Class:
 *       Represents the main class of the virtual memory system.
 *       Initializes the backing store file and memory manager.
 *
 *   MemoryManager Class:
 *       Responsible for managing the memory operations and address translation.
 *       Contains methods to translate virtual addresses, retrieve values from physical memory, and maintain statistics.
 *
 *   Address Translation Process:
 *       The runTranslation method reads virtual memory addresses from an input file, 
 *       translates them into physical addresses using the MemoryManager, 
 *       and retrieves corresponding values from the backing store (emulating disk). 
 *       The translations are then printed to the console, including the virtual address, physical address, and value.
 *
 *   StatisticsGenration:
 *       The class calculates and prints statistics about the address translation process using the generateStatistics method. 
 * 
 *       The statistics include:
 * 
 *       Number of translated addresses.
 *       Number of page faults encountered during translation.
 *       Page fault rate (percentage of page faults relative to the total number of translations).
 *       Number of TLB hits (TLB cache hits during translation).
 *       TLB hit rate (percentage of TLB hits relative to the total number of translations).
 * 
 *    Output Desired Virtual Addresses: 
 *      The outputDesiredVirtualAddresses method allows users to specify the desired number of virtual addresses to translate and output. 
 *      It prints the translations for the selected addresses, providing virtual address, physical address, and value information.
 * 
 *    Input and Output: 
 *      The class accepts command-line arguments as input to specify the path of the input file containing virtual addresses 
 *      and the desired count of translations. After processing, the program prints the translated virtual addresses 
 *      and their associated information to the console.
 * 
 *    Backing Store: 
 *      The class uses a backing store file (BACKING_STORE) to emulate secondary storage (disk). 
 *      The file is read during the address translation process to fetch values corresponding to physical addresses.
 *
 * Usage of program:
 * 
 *      The program is run from the command line with the input file containing virtual addresses as an argument.
 *      Alternatively you can compile the program and run it using the following command-line format:
 *
 *      java VM <input file> <desired count>
 *
 *      Replace <input file> with the path to your input file and <desired count> with the number of virtual addresses you want to translate and output.
 *
 *      The program will perform the address translation, display the translated virtual addresses along with their physical addresses 
 *      and values, and generate statistics about the translation process, providing insights into the memory management efficiency.
 * 
 * Note:
 *     
 * In order for the program to operate you will need to set this line (that resides in main):
 * 
 * String inputFile = "D:/SNHU/IT365/Test/3/Module 5/inputfile.txt"; // Replace with the actual input file path
 * 
 * To where the it is located on a drive, this means where the input file is located.
 * (D:/SNHU/IT365/Test/3/Module 5/inputfile.txt) this is the code that will need to be altered to where the appropriate drive to point to.
 * If this is not done, the file will not operate and will produce no results or Not a number errors.
 * 
 * Summary of Modifications:
 * 
 * July 10th, 2023
 * 
 * - Separation of Concerns:
 * The functionality related to memory management, including page table, TLB, 
 * and physical memory handling, has been moved to a separate class named MemoryManager. This separation makes the code more organized 
 * and adheres to the principle of single responsibility.
 * 
 * - MemoryManager Class:
 * The MemoryManager class handles all the memory-related operations like page table lookups, TLB operations, handling page faults, 
 * and reading values from physical memory. This class helps encapsulate the memory management logic, promoting code reusability and maintainability.
 * 
 * - Input Handling:
 * The input file reading and virtual-to-physical address translation process are performed inside the runTranslation method, 
 * which is part of the VM class. 
 * However, the actual memory management operations are now handled by the MemoryManager.
 * 
 * - Statistics Generation: 
 * The statistics generation has also been moved to the MemoryManager class, 
 * and the generateStatistics method in the VM class now calls the corresponding method in the MemoryManager.
 * 
 * - Usage of Path and Files:
 * The new code uses Java's Path and Files classes to handle file operations like opening and reading the input file. 
 * This improves code readability and simplifies file handling.
 * 
 * - Error Handling:  
 * The new code provides better error handling by catching exceptions and printing informative error messages when necessary.
 * 
 * July 21st, 2023
 * 
 * - Additional Class Members: 
 * Includes an additional member variable named selectVirtualAddresses of type ArrayList<Integer>. 
 * This list is used to store selected virtual addresses that are later used in the outputDesiredVirtualAddresses 
 * and generateStatisticsForSelectAddresses methods.
 * 
 * - Desired Count Parameter: 
 * The runTranslation method now takes an additional parameter desiredCount, 
 * which specifies the number of virtual addresses to be translated from the input file. 
 * This allows the user to control the number of addresses that will be translated and displayed.
 * 
 * - Enhanced Input Handling: 
 * The main method takes user input for the desired count using Scanner, 
 * allowing the user to specify the number of virtual addresses to be translated during run-time.
 * 
 * - Methods to Output Desired Virtual Addresses: 
 * The new code introduces two methods: outputDesiredVirtualAddresses and generateStatisticsForSelectAddresses. 
 * These methods are used to output the desired number of virtual addresses along with their translations 
 * and generate statistics for the selected virtual addresses.
 * 
 * - Improved Statistic Generation: 
 * The generateStatistics method in the new code calculates the TLB hit rate (tlbHitRate) in addition to the page fault rate (pageFaultRate). 
 * The old code does not calculate the TLB hit rate.
 * 
 * - Improved Error Handling: 
 * Updated error handling to better match the new changes.
 * 
 * - Overall Structure and Clarity: 
 * Updated comments to better match against the new changes.
 * 
 * July 25th, 2023
 * 
 * - Additional Class Members:
 * Includes an additional member variable named selectVirtualAddresses of type ArrayList<Integer>. This list is used to store selected virtual addresses that are later used in the outputDesiredVirtualAddresses and generateStatisticsForSelectAddresses methods.
 *
 * - Desired Count Parameter:
 * The runTranslation method now takes an additional parameter desiredCount, which specifies the number of virtual addresses to be translated from the input file. This allows the user to control the number of addresses that will be translated and displayed.
 *
 * -Enhanced Input Handling:
 * The main method takes user input for the desired count using Scanner, allowing the user to specify the number of virtual addresses to be translated during run-time.
 * 
 * - Methods to Output Desired Virtual Addresses:
 * The new code introduces two methods: outputDesiredVirtualAddresses and generateStatisticsForSelectAddresses. These methods are used to output the desired number of virtual addresses along with their translations and generate statistics for the selected virtual addresses.
 *
 * - Improved Statistic Generation:
 * The generateStatistics method in the new code calculates the TLB hit rate (tlbHitRate) in addition to the page fault rate (pageFaultRate). The old code does not calculate the TLB hit rate.
 *
 * - Improved Error Handling:
 * Updated error handling to better match the new changes.
 *
 * - Overall Structure and Clarity:
 * Updated comments to better match against the new changes.
 */

package com.mycompany.vm;

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * VM (Virtual Memory) Simulation Program.
 * 
 * This program simulates the address translation process of a virtual memory system using data stored in MongoDB.
 * It reads virtual addresses from a MongoDB collection, translates them into physical addresses, retrieves the corresponding
 * value from a backing store file (emulating disk), and generates statistics about the translation process.
 */
public class VM {
    // Constants
    private static final String BACKING_STORE_FILENAME = "BACKING_STORE";
    private static final String USAGE_MESSAGE = "Usage: java VM <input file> <desired count>";

    // File and Database Related Members
    private Path backingStorePath;
    private RandomAccessFile disk = null;
    private BufferedReader reader = null;
    private MongoClient mongoClient;
    private MongoDatabase database;
    private MongoCollection<Document> collection;

    // Address Translation Related Members
    private int virtualAddress;
    private int physicalAddress;
    private byte value;
    private MemoryManager memoryManager;

      // Select Virtual Addresses
    private ArrayList<Integer> selectVirtualAddresses;

     /**
     * Constructs a new VM object.
     * 
     * @param disk The RandomAccessFile representing the backing store file.
     * @param pageTableCollection The MongoDB collection containing virtual-to-physical address mappings.
     */
    public VM(RandomAccessFile disk, MongoCollection<Document> pageTableCollection) {
        // Initialize the VM object by setting up the MongoDB connection.
        mongoClient = MongoClients.create("mongodb://localhost:27017"); // Replace with your MongoDB connection URI
        database = mongoClient.getDatabase("Virtual_Memory_db");
        collection = database.getCollection("addresses");

        // Initialize the list of select virtual addresses
        selectVirtualAddresses = new ArrayList<>();

        // Instantiate the memoryManager
        memoryManager = new MemoryManager(disk, pageTableCollection); // Update this line
    }

    /**
     * Runs the address translation process using data from MongoDB.
     *
     * @param desiredCount The number of virtual addresses to translate and output.
     */
    public void runTranslation(int desiredCount) {
        try {
            // Fetch virtual addresses from MongoDB and translate them to physical addresses.
            for (Document document : collection.find().limit(desiredCount)) {
                Integer virtualAddressObj = document.getInteger("virtualAddress");
                if (virtualAddressObj == null) {
                    System.err.println("Virtual address is missing in the document.");
                    continue; // Skip this document and proceed to the next one
                }

                virtualAddress = virtualAddressObj.intValue();
                physicalAddress = memoryManager.translateVirtualAddress(virtualAddress);
                value = memoryManager.getValue(physicalAddress);
                System.out.println("Virtual address: " + virtualAddress +
                        " Physical address: " + physicalAddress +
                        " Value: " + value);
                memoryManager.incrementNumberOfAddresses(); // Increment the number of translated addresses

                // Check if a page fault occurred and increment the page fault count
                if (memoryManager.isPageFault()) {
                    memoryManager.incrementPageFaults();
                }

                // Check if a TLB hit occurred and increment the TLB hit count
                if (memoryManager.isTLBHit()) {
                    memoryManager.incrementTLBHits();
                }
            }

        } catch (Exception e) {
            System.err.println("Error accessing MongoDB: " + e.getMessage());
        } finally {
            // Close the MongoDB connection.
            if (mongoClient != null)
                mongoClient.close();
        }
    }
    
    /**
     * Outputs the desired number of virtual addresses along with their translations.
     *
     * @param desiredCount The number of virtual addresses to output.
     */
    public void outputDesiredVirtualAddresses(int desiredCount) {
        System.out.println("Desired number of Virtual Addresses (" + desiredCount + "):");
        int count = 0;
        for (Integer virtualAddress : selectVirtualAddresses) {
            if (count >= desiredCount) {
                break;
            }

            try {
                int physicalAddress = memoryManager.translateVirtualAddress(virtualAddress);
                byte value = memoryManager.getValue(physicalAddress);
                System.out.println("Virtual address: " + virtualAddress +
                        " Physical address: " + physicalAddress +
                        " Value: " + value);
                count++;
            } catch (IOException e) {
                System.err.println("Error occurred while translating the address: " + e.getMessage());
            }
        }
    }

    /**
     * Generates and prints statistics for the select virtual addresses.
     */
    public void generateStatisticsForSelectAddresses() {
        System.out.println("Statistics for Select Virtual Addresses:");
        for (Integer virtualAddress : selectVirtualAddresses) {
            try {
                int physicalAddress = memoryManager.translateVirtualAddress(virtualAddress);
                byte value = memoryManager.getValue(physicalAddress);
                System.out.println("Virtual address: " + virtualAddress +
                        " Physical address: " + physicalAddress +
                        " Value: " + value);
            } catch (IOException e) {
                System.err.println("Error occurred while translating the address: " + e.getMessage());
            }
        }
    }

    /**
     * Generates and prints statistics about the address translation process.
     */
    public void generateStatistics() {
        // Get the statistics from the memory manager.
        int numberOfAddresses = memoryManager.getNumberOfAddresses();
        int pageFaults = memoryManager.getPageFaults();
        int tlbHits = memoryManager.getTLBHits();
        float pageFaultRate = memoryManager.getPageFaultRate();
        float tlbHitRate = (float) tlbHits / numberOfAddresses * 100;

        // Print the overall statistics.
        System.out.println("Overall Statistics:");
        System.out.println("Number of Translated Addresses = " + numberOfAddresses);
        System.out.println("Page Faults = " + pageFaults);
        System.out.printf("Page Fault Rate = %.2f\n", pageFaultRate);
        System.out.println("TLB Hits = " + tlbHits);
        System.out.printf("TLB Hit Rate = %.2f\n", tlbHitRate);
    }

    
    /**
     * The entry point of the program.
     *
     * @param args The command-line arguments. Expects two arguments: input file and desired count.
     * @throws IOException If an I/O error occurs while reading the input file or closing resources.
     */
    public static void main(String[] args) throws IOException {
        int desiredCount;

        // Create a new Scanner object to read user input from the console
        Scanner scanner = new Scanner(System.in);

        // Prompt the user to enter the desired number of addresses
        System.out.print("Enter the number of addresses you want to translate: ");
        desiredCount = scanner.nextInt();

        // Create a new VM object and run the address translation process.
        RandomAccessFile disk = new RandomAccessFile(BACKING_STORE_FILENAME, "r");
        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017"); // Replace with your MongoDB connection URI
        MongoDatabase database = mongoClient.getDatabase("Virtual_Memory_db");
        MongoCollection<Document> pageTableCollection = database.getCollection("pageTable");
        VM vm = new VM(disk, pageTableCollection);
        vm.runTranslation(desiredCount);

        // Generate and print overall statistics.
        vm.generateStatistics();

        // Close the Scanner to release system resources after reading user input
        scanner.close();
    }
}
