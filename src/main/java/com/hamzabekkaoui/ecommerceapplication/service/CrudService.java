package com.hamzabekkaoui.ecommerceapplication.service;

import com.hamzabekkaoui.ecommerceapplication.exception.ResourceAlreadyExist;
import com.hamzabekkaoui.ecommerceapplication.exception.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface CrudService<REQUEST , RESPONSE , ID> {

    Page<RESPONSE> pageOfData(int pageNumber, int pageSize);

    RESPONSE getByID(ID id) throws ResourceNotFoundException;

    RESPONSE create(REQUEST request) throws ResourceAlreadyExist;

    RESPONSE update(REQUEST request, ID id) throws ResourceNotFoundException;

    boolean delete(ID id)  throws ResourceNotFoundException;

}
