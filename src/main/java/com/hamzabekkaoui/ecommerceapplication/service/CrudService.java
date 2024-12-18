package com.hamzabekkaoui.ecommerceapplication.service;

import com.hamzabekkaoui.ecommerceapplication.exception.ResourceAlreadyExist;
import com.hamzabekkaoui.ecommerceapplication.exception.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface CrudService<REQUEST , RESPONSE , ID> {

    List<RESPONSE> getAllAsList();

    Page<RESPONSE> getAllAsPage(int pageNumber, int pageSize);

    RESPONSE getByID(ID id) throws ResourceNotFoundException;

    RESPONSE create(REQUEST request) throws ResourceAlreadyExist;

    RESPONSE update(REQUEST request, ID id) throws ResourceNotFoundException;

    boolean delete(ID id)  throws ResourceNotFoundException;

}
