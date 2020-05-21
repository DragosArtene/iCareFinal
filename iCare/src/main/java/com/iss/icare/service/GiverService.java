package com.iss.icare.service;

import com.iss.icare.model.*;
import com.iss.icare.repository.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class GiverService {

    private final GiverRepository giverRepository;

    public GiverService(GiverRepository giverRepository) {
        this.giverRepository = giverRepository;
    }

    public List<Giver> findAllGivers() {
        return giverRepository.findAllGivers();
    }

    public Giver findGiverById(int giverId) {
        return giverRepository.findGiverById(giverId);
    }

    public Giver findGiverByUsername(String username) {
        return giverRepository.findGiverByUsername(username);
    }

    public int insertGiver(Giver giver) {
        return giverRepository.insertGiver(giver);
    }

    public boolean exists(int id) {
        return giverRepository.exists(id);
    }

    public boolean existsUsername(String username) {
        return giverRepository.existsUsername(username);
    }

    public void delete(int giver_id) {
        giverRepository.delete(giver_id);
    }

    public void update(Giver giver) {
        giverRepository.update(giver);
    }

    public void updatePassword(int id,String password) {
        giverRepository.updatePassword(id,password);
    }

}
