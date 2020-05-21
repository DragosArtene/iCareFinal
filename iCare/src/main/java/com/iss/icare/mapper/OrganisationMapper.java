package com.iss.icare.mapper;
import com.iss.icare.dto.*;
import com.iss.icare.model.*;

public class OrganisationMapper {
    public OrganisationDto convertToDto(Organisation ong) {
        OrganisationDto ongDto = new OrganisationDto();

        ongDto.setOngId(ong.getOngId());
        ongDto.setOngFirstName(ong.getOngFirstName());
        ongDto.setOngLastName(ong.getOngLastName());
        ongDto.setEmail(ong.getEmail());
        ongDto.setPassword(ong.getPassword());
        ongDto.setOngDescription(ong.getOngDescription());
        ongDto.setUsername(ong.getUsername());
        ongDto.setPhoneNumber(ong.getPhoneNumber());

        return ongDto;
    }

    public Organisation convertToEntity(OrganisationDto ongDto) {
        Organisation ong = new Organisation();

        ong.setOngId(ongDto.getOngId());
        ong.setOngFirstName(ongDto.getOngFirstName());
        ong.setOngLastName(ongDto.getOngLastName());
        ong.setEmail(ongDto.getEmail());
        ong.setPassword(ongDto.getPassword());
        ong.setOngDescription(ongDto.getOngDescription());
        ong.setUsername(ongDto.getUsername());
        ong.setPhoneNumber(ongDto.getPhoneNumber());

        return ong;
    }
}
