package com.code.designpatternapp.data.contracts.models;


import com.code.common.contracts.ISynchronizedModel;
import com.code.common.contracts.ModelState;

import java.util.Date;


public class AddressModel implements ISynchronizedModel {

    Long id;
    Long serverId;
    String name;
    String nickName;
    String line1;
    String line2;
    String state;
    String pinCode;
    String phone;
    String email;
    String city;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getNickName(){
        return nickName;
    }
    public void setNickName(String nickName){
        this.nickName=nickName;
    }

    public String getLine1() {
        return line1;
    }

    public void setLine1(String line1) {
        this.line1 = line1;
    }

    public String getLine2() {
        return line2 ;
    }

    public void setLine2(String line2) {
        this.line2 = line2;
    }

    public String getState() {
        return state;
    }


    public void setState(String state) {
        this.state = state;
    }
    public String getPinCode() {
        return pinCode;
    }
    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public void setId(Long id) {
        this.id=id;
    }

    @Override
    public Date getTimeStamp() {
        return null;
    }

    @Override
    public void setTimeStamp(Date timeStamp) {

    }

    @Override
    public ModelState getModelStatus() {
        return null;
    }

    @Override
    public void setStatus(Integer status) {

    }

    @Override
    public Long getServerId() {
        return serverId;
    }

    @Override
    public void setServerId(Long id) {
        serverId=id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
