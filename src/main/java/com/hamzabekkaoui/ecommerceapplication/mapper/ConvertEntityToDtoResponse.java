package com.hamzabekkaoui.ecommerceapplication.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ConvertEntityToDtoResponse<ENTITY , RESPONSE> {

    private final ModelMapper modelMapper;


    public Page<RESPONSE> convert(Page<ENTITY> entityPage , Class<RESPONSE> responseClass){


        List<RESPONSE> responseList = entityPage.getContent().stream()
                .map(data -> modelMapper.map(data , responseClass))
                .collect(Collectors.toList());

        return new PageImpl<>(responseList , entityPage.getPageable() , entityPage.getTotalElements());
    }


}
