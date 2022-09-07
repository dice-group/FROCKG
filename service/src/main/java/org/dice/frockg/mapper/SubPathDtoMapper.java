package org.dice.frockg.mapper;

import org.dice.frockg.data.dto.SubPathDto;

import java.util.ArrayList;
import java.util.List;

public class SubPathDtoMapper {
    public List<SubPathDto> map(String input){
        List<SubPathDto> mappedList = new ArrayList<>();
        input = input.replace("<","");
        String[] parts=input.split(">/");
        for(String part:parts){
            SubPathDto sp = new SubPathDto();
            if(part.charAt(0)=='^'){
                sp.setInverse(true);
                part.replace("^","");
            }else{
                sp.setInverse(false);
            }
            sp.setProperty(part);
            mappedList.add(sp);
        }
        return mappedList;
    }
}
