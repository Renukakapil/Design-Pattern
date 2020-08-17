package com.code.greendaogenerator;


import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Property;
import de.greenrobot.daogenerator.Schema;

public class DesignPattern {
    public static void main(String args[]) throws Exception {

        Schema schema = new Schema(5, "com.code.designpatternapp.data.impl.entities");


        Entity address = schema.addEntity("Address");
        address.addIdProperty();
        address.addStringProperty("name");
        address.addStringProperty("nickName");
        address.addStringProperty("line1");
        address.addStringProperty("line2");
        address.addStringProperty("state");
        address.addStringProperty("pinCode");
        address.addStringProperty("phone");
        address.addLongProperty("serverId");
        address.addStringProperty("email");
        address.addStringProperty("city");

        Property addressId=address.addLongProperty("addressId").getProperty();
        address.addToMany(address,addressId);


        new DaoGenerator().generateAll(schema, "./app/src/main/java");


    }
}