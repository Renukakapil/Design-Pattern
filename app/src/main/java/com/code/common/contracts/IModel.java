package com.code.common.contracts;


import java.io.Serializable;
import java.util.Date;

public interface IModel extends Serializable {

    Long getId();

    void setId(Long id);

    Date getTimeStamp();

    void setTimeStamp(Date timeStamp);

    ModelState getModelStatus();

    void setStatus(Integer status);

    Long getServerId();

    void setServerId(Long id);
}
