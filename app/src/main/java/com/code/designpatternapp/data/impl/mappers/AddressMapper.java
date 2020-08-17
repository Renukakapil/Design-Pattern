package com.code.designpatternapp.data.impl.mappers;


import com.code.common.contracts.IModelMapper;
import com.code.designpatternapp.data.contracts.models.AddressModel;
import com.code.designpatternapp.data.impl.entities.Address;

public class AddressMapper implements IModelMapper<Address, AddressModel> {


    @Override
    public AddressModel Map(Address address) {
        AddressModel addressModel =new AddressModel();
        addressModel.setId(address.getId());
        addressModel.setName(address.getName());
        addressModel.setNickName(address.getNickName());
        addressModel.setLine1(address.getLine1());
        addressModel.setLine2(address.getLine2());
        addressModel.setState(address.getState());
        addressModel.setPinCode(address.getPinCode());
        addressModel.setPhone(address.getPhone());
        addressModel.setServerId(address.getServerId());
        addressModel.setEmail(address.getEmail());
        addressModel.setCity(address.getCity());
        return addressModel;
    }

}
