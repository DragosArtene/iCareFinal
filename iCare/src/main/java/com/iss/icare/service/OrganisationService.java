package com.iss.icare.service;

import com.iss.icare.model.*;
import com.iss.icare.repository.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class OrganisationService {

    private final OrganisationRepository ongRepository;

    public OrganisationService(OrganisationRepository ongRepository) {
        this.ongRepository = ongRepository;
    }

    public List<Organisation> findAllOrganisations() {
        return ongRepository.findAllOngs();
    }

    public Organisation findOrganisationById(int ongId) {
        return ongRepository.findOrganisationById(ongId);
    }

    public Organisation findOrganisationByUsername(String username) {
        return ongRepository.findOrganisationByUsername(username);
    }

    public int insertOrganisation(Organisation ong) {
        return ongRepository.insertOrganisation(ong);
    }

    public boolean exists(int id) {
        return ongRepository.exists(id);
    }

    public boolean existsUsername(String username) {
        return ongRepository.existsUsername(username);
    }

    public void delete(int ong_id) {
        ongRepository.delete(ong_id);
    }

    public void update(Organisation ong) {
        ongRepository.update(ong);
    }

    public void updatePassword(int id,String password) { ongRepository.updatePassword(id,password);}

}
