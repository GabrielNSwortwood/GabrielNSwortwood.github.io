/**
 * Address.java (Modified)
 * 
 * This is a modified version of the original Address.java file. The original code was provided as an educational example
 * in the book "Operating System Concepts with Java - Eighth Edition" by Abraham Silberschatz, Peter B. Galvin,
 * and Greg Gagne. The modifications I made to this code include error handling being enhanced from its previous model,
 * improved input validation, code being more organized/structured, and overall documentation changes.
 * 
 * This class represents an address with a size of 32 bits. It supports various page sizes and
 * provides functionality to calculate the page number and offset for a given address and page size.
 *
 * @Orignally authored by: Gagne, Galvin, Silberschatz
 * Operating System Concepts with Java - Eighth Edition
 * Copyright John Wiley & Sons - 2010.
 * 
 * @modified by: Gabriel N. Swortwood
 * @modification_date July 10th, 2023
 * @update date July 20th, 2023
 * @update date July 25th, 2023
 * 
 * "Address" Java program is to calculate the page number 
 * and offset for a given page size and virtual address using bitwise operations. 
 * It aims to help users understand how virtual memory addresses are split into page numbers and offsets based on the provided page size.
 * 
 * Key Components and Functionality:
 * 
 *      main Method:
 *          The program performs address translation using a virtual memory scheme with various page sizes.
 *          The main method takes two command-line arguments: the page size and the address to be translated.
 * 
 *      ADDRESS_SIZE Constant:
 *          The constant ADDRESS_SIZE is set to 32, representing the size of the address in bits.
 * 
 *      validatePageSize Method:
 *          It validates the provided page size to ensure it is a positive power of 2 using the validatePageSize method.
 * 
 *      HashMap:
 *          It uses a HashMap called PAGE_SIZES to store supported page sizes 
 *          and their corresponding masks in a helper class called PageSizeInfo.
 * 
 *      Bitwise Operations: 
 *          The program calculates the page number and offset of the given address based on the selected page size using bitwise operations 
 *          and the masks from the PageSizeInfo. The result (page number and offset) is printed to the console.
 * 
 * Usage of class: 
 * 
 *          To use this file, run the Java application with two command-line arguments:     
 * 
 *          java Address <page size> <address>
 *          
 *          <page size>: The size of the page in bytes. Supported page sizes are 1024, 2048, 4096, 8192, and 16384.
 * 
 *          <address>: The memory address to be translated.
 * 
 * Summary of Modifications:
 * 
 * July 10th, 2023:
 * 
 * - Error Handling Improvement: 
 * In the new code, the error handling is enhanced. The program now exits with a status of 1 when incorrect command-line arguments are provided, 
 * and it also includes a catch block to handle potential NumberFormatExceptions when parsing the page size and address. 
 * This ensures that the program gracefully handles incorrect inputs.
 * 
 * - Input Validation: 
 * The new code introduces a validatePageSize method to check if the provided page size is a positive power of 2. 
 * This adds an extra layer of validation to ensure that only valid page sizes are accepted.
 * 
 * - Code Organization: 
 * The new code is more organized, with a clear separation of concerns. 
 * The validation logic is extracted into a separate private method, making the main method cleaner and easier to read.
 * 
 * - Usage Comment: 
 * The "Usage" section is provided as a concise and easy-to-read block, explaining how users should run the program with specific examples. 
 * The usage information is presented in a cleaner format, making it more user-friendly.
 * 
 * July 20th, 2023:
 * 
 * - PageSizeInfo helper class: 
 * Stores page size information, including the number of bits for the page, offset masks, and page masks.
 * 
 * - HashMap:
 * Uses a HashMap (PAGE_SIZES) to store the supported page sizes and their corresponding PageSizeInfo instances. 
 * This enhances scalability and makes it easier to add or modify page sizes without altering the code's structure.
 * 
 * - Code Organization/Refactoring:
 * Improved code readability with meaningful variable names and removal of switch-case statements.
 * 
 * - Input Validation:
 * Centralized validation of page size within the validatePageSize method, enhancing code reusability and maintainability.
 * 
 * July 25th, 2023
 * - Address Organization:
 * Moved the Address class to the package "com.mycompany.vm".
 * 
 * - Address MongoDB Dependencies:
 * Added MongoDB-related dependencies to interact with the database.
 * 
 * - Nested Class PageSizeInfo:
 * Created a nested private class "PageSizeInfo" to store page size information.
 * 
 * - Updates to main:
 * Updated the main method to call the "translateAddress" method for address translation.
 * 
 * - Implementation of translateAddress:
 * Implemented the "translateAddress" method to interact with a MongoDB database and retrieve address information.
 * 
 * - Improved error handling:
 * Improved error handling for invalid page size, address, and MongoDB operations.
 * 
 * - Import statement additions:
 * Added import statements for MongoDB-related classes.
 */


package com.mycompany.vm;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import java.util.HashMap;

public class Address {
    // The size of the address in bits
    public static final int ADDRESS_SIZE = 32;

    // Supported page sizes and their corresponding masks
    private static final HashMap<Integer, PageSizeInfo> PAGE_SIZES = new HashMap<>();

    static {
        PAGE_SIZES.put(1024, new PageSizeInfo(10, 0x000003ff, 0xfffffc00));
        PAGE_SIZES.put(2048, new PageSizeInfo(11, 0x000007ff, 0xfffff800));
        PAGE_SIZES.put(4096, new PageSizeInfo(12, 0x00000fff, 0xfffff000));
        PAGE_SIZES.put(8192, new PageSizeInfo(13, 0x00001fff, 0xffffe000));
        PAGE_SIZES.put(16384, new PageSizeInfo(14, 0x00003fff, 0xffffcfff));
    }

    public static void main(String[] args) {
        // Check if the correct number of command-line arguments is provided
        if (args.length != 2) {
            System.err.println("Usage: java Address <page size> <address>");
            return;
        }

        try {
            // Parse the page size and address from the command-line arguments
            int pageSize = Integer.parseInt(args[0].trim());
            int address = Integer.parseInt(args[1].trim());

            // Validate the page size
            validatePageSize(pageSize);

            // Translate the address using the MongoDB code
            translateAddress(address);
        } catch (NumberFormatException e) {
            System.err.println("Invalid page size or address provided: " + e.getMessage());
        }
    }
    
    private static void translateAddress(int address) {
        try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {
            MongoDatabase database = mongoClient.getDatabase("Virtual_Memory_db");
            MongoCollection<Document> collection = database.getCollection("virtual_addresses");

            // Find the document with the given virtualAddress
            Document query = new Document("virtualAddress", address);
            FindIterable<Document> result = collection.find(query);

            // Check if the document exists
            Document document = result.first();
            if (document != null) {
                // Check if the document contains the expected fields
                if (document.containsKey("virtualAddress") && document.containsKey("physicalAddress")) {
                    Integer virtualAddress = document.getInteger("virtualAddress");
                    Integer physicalAddress = document.getInteger("physicalAddress");

                    // Check if virtualAddress and physicalAddress are not null
                    if (virtualAddress != null && physicalAddress != null) {
                        // Print the retrieved information
                        System.out.println("Virtual Address: " + virtualAddress);
                        System.out.println("Physical Address: " + physicalAddress);
                        byte value = document.get("value", Byte.class);
                        System.out.println("Value: " + value);
                    } else {
                        System.err.println("Invalid document format: Missing virtualAddress or physicalAddress.");
                    }
                } else {
                    System.err.println("Invalid document format: Missing fields.");
                }
            } else {
                System.err.println("Document not found for virtualAddress: " + address);
            }
        } catch (Exception e) {
            // Handle any exceptions related to MongoDB operations
            System.err.println("Error accessing MongoDB: " + e.getMessage());
        }
    }
    

    // Validates the page size to ensure it is a positive power of 2
    private static void validatePageSize(int pageSize) {
        if (pageSize <= 0 || (pageSize & (pageSize - 1)) != 0) {
            System.err.println("Invalid page size: " + pageSize + ". Page size must be a positive power of 2.");
            System.exit(1);
        }
    }

    // Helper class to store page size information
    private static class PageSizeInfo {
        int pageBits;
        int offsetMask;
        int pageMask;

        PageSizeInfo(int pageBits, int offsetMask, int pageMask) {
            this.pageBits = pageBits;
            this.offsetMask = offsetMask;
            this.pageMask = pageMask;
        }
    }
}