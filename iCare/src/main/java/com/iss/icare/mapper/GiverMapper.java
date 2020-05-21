package com.iss.icare.mapper;
import com.iss.icare.dto.*;
import com.iss.icare.model.*;

public class GiverMapper {
    public GiverDto convertToDto(Giver giver){
        GiverDto giverDto = new GiverDto();

        giverDto.setGiverId(giver.getGiverId());
        giverDto.setGiverFirstName(giver.getGiverFirstName());
        giverDto.setGiverLastName(giver.getGiverLastName());
        giverDto.setEmail(giver.getEmail());
        giverDto.setPassword(giver.getPassword());
        giverDto.setGiverDescription(giver.getGiverDescription());
        giverDto.setUsername(giver.getUsername());
        giverDto.setPhoneNumber(giver.getPhoneNumber());

        return giverDto;
    }

    public Giver convertToEntity(GiverDto giverDto){
        Giver giver = new Giver();

        giver.setGiverId(giverDto.getGiverId());
        giver.setGiverFirstName(giverDto.getGiverFirstName());
        giver.setGiverLastName(giverDto.getGiverLastName());
        giver.setEmail(giverDto.getEmail());
        giver.setPassword(giverDto.getPassword());
        giver.setGiverDescription(giverDto.getGiverDescription());
        giver.setUsername(giverDto.getUsername());
        giver.setPhoneNumber(giverDto.getPhoneNumber());

        return giver;
    }

}
