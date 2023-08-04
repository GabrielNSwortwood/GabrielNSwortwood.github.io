/**
 * AddressInfo.java
 *
 * The class is based on the works of Gagne, Galvin, and Silberschatz from the book "Operating System Concepts with Java - Eighth Edition".
 * 
 * The AddressInfo class based on the works of Gagne, Galvin, and Silberschatz. Added class fields, getters, setters, and methods 
 * to convert and save AddressInfo objects to MongoDB.
 * 
 * Author: Gabriel N. Swortwood
 * Creation Date: July 28th, 2023
 * 
 * Key Components and Functionality:
 * This class represents address information that can be stored in a MongoDB database. It provides methods to interact with the address information 
 * and a static method to generate and insert virtual addresses into the MongoDB collection.
 * 
 * Class Fields:
 *  ObjectId _id: Represents the MongoDB ObjectId used as a unique identifier for each document in the collection.
 *  int virtualAddress: Represents the virtual address.
 *  int physicalAddress: Represents the physical address.
 *  byte value: Represents the value associated with the address.
 * 
 * Getters and Setters:
 *  int getVirtualAddress(): Returns the virtual address.
 *  int getPhysicalAddress(): Returns the physical address.
 *  byte getValue(): Returns the value associated with the address.
 *  void setVirtualAddress(int virtualAddress): Sets the virtual address.
 *  void setPhysicalAddress(int physicalAddress): Sets the physical address.
 *  void setValue(byte value): Sets the value associated with the address.
 * 
 * toDocument() Method:
 *  Document toDocument(): Converts the AddressInfo object into a MongoDB Document. It creates a Document object and populates it with the values 
 *   of virtualAddress, physicalAddress, and value.
 * 
 * saveToMongoDB() Method:
 *  void saveToMongoDB(MongoCollection<Document> collection): Saves the AddressInfo object as a Document into the MongoDB collection specified as an argument.
 * 
 * generateAndInsertVirtualAddresses() Method:
 *  static void generateAndInsertVirtualAddresses(MongoCollection<Document> collection, int numAddresses): 
 *   Generates a specified number of virtual addresses and inserts them into the MongoDB collection. It creates AddressInfo objects, sets their properties, 
 *   and uses the saveToMongoDB() method to store them in the collection.
 * 
 * Usage of Class:
 *  This class can be used to represent and store address information in a MongoDB database. It provides methods to set and retrieve address properties, 
 *   convert objects to MongoDB Documents, and save AddressInfo objects to the MongoDB collection.
 * 
 * Summary of Modifications:
 * - July 28th, 2023
 * Created and implemented this class.
 * 
 */

package com.mycompany.vm;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.bson.Document;
import org.bson.types.ObjectId;

public class AddressInfo {
    private ObjectId _id;
    private int virtualAddress;
    private int physicalAddress;
    private byte value;
    
    public int getVirtualAddress() {
        return virtualAddress;
    }

    public int getPhysicalAddress() {
        return physicalAddress;
    }

    public byte getValue() {
        return value;
    }

    public void setVirtualAddress(int virtualAddress) {
        this.virtualAddress = virtualAddress;
    }

    public void setPhysicalAddress(int physicalAddress) {
        this.physicalAddress = physicalAddress;
    }

    public void setValue(byte value) {
        this.value = value;
    }
    
    public Document toDocument() {
        Document document = new Document();
        document.append("virtualAddress", virtualAddress)
                .append("physicalAddress", physicalAddress)
                .append("value", value);
        return document;
    }
    
    public ObjectId getId() {
        return _id;
    }

    public void setId(ObjectId id) {
        this._id = id;
    }

    public void saveToMongoDB(MongoCollection<Document> collection) {
        Document document = new Document()
                .append("virtualAddress", virtualAddress)
                .append("physicalAddress", physicalAddress)
                .append("value", value);

        collection.insertOne(document);
    }

    // Static method to generate and insert virtual addresses into the MongoDB collection
    public static void generateAndInsertVirtualAddresses(MongoCollection<Document> collection, int numAddresses) {
        for (int i = 0; i < numAddresses; i++) {
            AddressInfo addressInfo = new AddressInfo();
            addressInfo.setVirtualAddress(i);
            addressInfo.setPhysicalAddress(i); // You may set the physical address based on your simulation logic
            addressInfo.setValue((byte) 0); // Set the initial value of the address, if needed

            addressInfo.saveToMongoDB(collection);
        }
    }
    
    public static void main(String[] args) throws IOException {
        // Create a new MongoClient
        try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {
            // Get the database and collection
            MongoCollection<Document> collection = mongoClient.getDatabase("Virtual_Memory_db").getCollection("virtual_addresses");

            // Read the input file
            String inputFile = "D:\\SNHU\\IT365\\Test\\3\\Module 5\\inputfile.json";
            try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    // Parse the data from the line (assuming data is in JSON format)
                    Document document = Document.parse(line);

                    // Create an AddressInfo object and set the fields
                    AddressInfo addressInfo = new AddressInfo();
                    addressInfo.setVirtualAddress(document.getInteger("virtualAddress"));
                    addressInfo.setPhysicalAddress(document.getInteger("physicalAddress"));

                    // Convert the value to a byte
                    int intValue = document.getInteger("value");
                    byte byteValue = (byte) intValue;
                    addressInfo.setValue(byteValue);

                    // Save the AddressInfo object to the MongoDB collection
                    addressInfo.saveToMongoDB(collection);
                }
            } catch (NumberFormatException e) {
                System.err.println("Error parsing virtual address: " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Error accessing MongoDB: " + e.getMessage());
        }
    }
}