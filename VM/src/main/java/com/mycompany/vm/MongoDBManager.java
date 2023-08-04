/**
 * MongoDBManage.java
 *
 * The class is based on the works of Gagne, Galvin, and Silberschatz from the book "Operating System Concepts with Java - Eighth Edition".
 * 
 * The MongoDBManager class is responsible for managing interactions with a MongoDB database in the context of a virtual memory system.
 * It handles CRUD (Create, Read, Update, Delete) operations for three different collections in the database: page_table, physical_memory, and tlb (Translation Lookaside Buffer).
 * 
 * Author: Gabriel N. Swortwood
 * Creation Date: July 28th, 2023
 * 
 * Key Components and Functionality:
 * 
 *  Setup:
 *    In it's current form it requires a database from MongoDB server to be constructed and named "Virtual_Memory_db",
 *    with a collections to be named "addresses". The collections should hold the inputfile (text that can be converted to json or csv)
 *    which is a series of virtual addresses for the system to read from. Which it works with the AddressInfo class to represent information
 *    to be stored and read from the database.
 * 
 *  Private fields:
 *   -`mongoClient`: A MongoClient instance used to connect to the MongoDB server running on the default localhost and port (27017).
 *    `database`: A MongoDatabase instance representing the "Virtual_Memory_db" database.
 *    `pageTableCollection`: A MongoCollection of type Document representing the "page_table" collection in the database.
 *    `physicalMemoryCollection`: A MongoCollection of type Document representing the "physical_memory" collection in the database.
 *    `tlbCollection`: A MongoCollection of type Document representing the "tlb" collection in the database.
 * 
 * Constructor:
 *  `MongoDBManager()`: Initializes the MongoDB-related variables by creating a MongoClient instance, obtaining the "Virtual_Memory_db" database,
 *    and getting references to the "page_table", "physical_memory", and "tlb" collections in the database.
 * 
 * MongoDB-related methods:
 *  `updatePageTableEntry(int pageNumber, int frameNumber)`: Updates the "page_table" collection with a new entry or updates an existing entry for the given `pageNumber`.
 *    The method takes the `pageNumber` and `frameNumber` as input, creates a new PageTableEntry object, and a corresponding MongoDB Document to update or insert into the collection.
 *    If the entry already exists, it updates the existing document; otherwise, it inserts a new document with the upsert(true) option.
 * 
 *  `updateTLBEntry(int pageNumber, int frameNumber)`: Updates the "tlb" collection with a new entry for the given `pageNumber` and `frameNumber`.
 *    The method takes the `pageNumber` and `frameNumber` as input, creates a new TLBEntry object, and a corresponding MongoDB Document to insert into the collection.
 *    If the "tlb" collection exceeds a certain size (specified by MemoryManager.TLB_SIZE), it removes the least recently used entry from the collection using natural ordering based on insertion time.
 * 
 *  `getPageTableCollection()`: Retrieves the MongoCollection<Document> instance representing the "page_table" collection, allowing external classes to access this collection directly.
 * 
 * Usage of class:
 *  This class is used in a virtual memory system to manage and store data in a MongoDB database.
 *  It is primarily used for updating page table entries and translation lookaside buffer entries.
 * 
 * Summary of Modifications:
 * - July 28th, 2023 
 * Initial implementation of MongoDBManager class.
 */

package com.mycompany.vm;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.UpdateOptions;
import org.bson.Document;

public class MongoDBManager {
    
    // MongoDB-related variables
    private MongoClient mongoClient;
    private MongoDatabase database;
    private MongoCollection<Document> pageTableCollection;
    private MongoCollection<Document> physicalMemoryCollection;
    private MongoCollection<Document> tlbCollection;

    /**
     * Constructor for initializing MongoDB-related variables.
     * It establishes a connection to the local MongoDB server and sets up the necessary collections.
     */
    public MongoDBManager() {
        mongoClient = MongoClients.create("mongodb://localhost:27017");
        database = mongoClient.getDatabase("Virtual_Memory_db");
        pageTableCollection = database.getCollection("page_table");
        physicalMemoryCollection = database.getCollection("physical_memory");
        tlbCollection = database.getCollection("tlb");
    }

    /**
     * Updates or inserts a new entry in the page table collection.
     *
     * @param pageNumber The page number to be updated or inserted.
     * @param frameNumber The frame number associated with the given page number.
     */
    public void updatePageTableEntry(int pageNumber, int frameNumber) {
        PageTableEntry entry = new PageTableEntry(pageNumber, frameNumber);
        Document document = new Document("pageNumber", pageNumber)
                .append("frameNumber", frameNumber);
        pageTableCollection.updateOne(new Document("pageNumber", pageNumber),
                new Document("$set", document),
                new UpdateOptions().upsert(true));
    }

    /**
     * Updates the TLB (Translation Lookaside Buffer) with a new entry for the provided page number
     * and frame number. If the TLB is full, it removes the least recently used entry to make space.
     *
     * @param pageNumber The page number to be updated in the TLB.
     * @param frameNumber The frame number associated with the given page number.
     */
    public void updateTLBEntry(int pageNumber, int frameNumber) {
        TLBEntry newEntry = new TLBEntry(pageNumber, frameNumber);
        Document document = new Document("pageNumber", pageNumber)
                .append("frameNumber", frameNumber);
        tlbCollection.insertOne(document);

        // Optional: If the TLB is full, use deleteOne() to remove the least recently used entry
        if (tlbCollection.countDocuments() > MemoryManager.TLB_SIZE) {
            Document oldestEntry = tlbCollection.find().sort(new Document("$natural", 1)).limit(1).first();
            tlbCollection.deleteOne(oldestEntry);
        }
    }

    /**
     * Retrieves the MongoDB collection representing the page table.
     *
     * @return The MongoDB collection representing the page table.
     */
    public MongoCollection<Document> getPageTableCollection() {
        return pageTableCollection;
    }
}
