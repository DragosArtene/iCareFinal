package com.iss.icare.controller;

import com.iss.icare.dto.*;
import com.iss.icare.exception.*;
import com.iss.icare.mapper.*;
import com.iss.icare.model.*;
import com.iss.icare.service.*;
import io.swagger.annotations.*;
import org.apache.logging.log4j.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.*;

@Api(value = "ong controller")
@RestController
@RequestMapping("/ongs")
public class OrganisationController {

    private static final Logger LOGGER = LogManager.getLogger(OrganisationController.class);
    private final OrganisationService ongService;
    private final OrganisationMapper ongMapper;

    public OrganisationController(OrganisationService ongService) {
        this.ongService = ongService;
        this.ongMapper = new OrganisationMapper();
    }

    @ApiOperation(value = "Find All ongs")
    @GetMapping
    public ResponseEntity<List<OrganisationDto>> findAllOrganisations() {
        LOGGER.info("Getting all ongs");
        List<Organisation> list = ongService.findAllOrganisations();

        List<OrganisationDto> result = list.stream().map(ongMapper::convertToDto).collect(Collectors.toList());

        LOGGER.info("Returning {} ongs", result.size());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ApiOperation(value = "Find ong by id")
    @GetMapping("/{ong_id}")
    public ResponseEntity<OrganisationDto> findOrganisationById(@PathVariable("ong_id") int ongId) {
        LOGGER.info("Finding ong with id:{}", ongId);

        if (!ongService.exists(ongId)) {
            LOGGER.info("Ong with id: {} doesn't exists", ongId);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Organisation ong = ongService.findOrganisationById(ongId);

        LOGGER.info("Ong {} found successfully", ongId);
        return new ResponseEntity<>(ongMapper.convertToDto(ong), HttpStatus.OK);
    }

    @ApiOperation(value = "Find ong by username")
    @GetMapping("/username/{username}")
    public ResponseEntity<OrganisationDto> findOrganisationByUsername(@PathVariable("username") String username) {
        LOGGER.info("Finding ong with username:{}", username);

        if (ongService.existsUsername(username)) {
            LOGGER.info("Ong with username: {} already exists", username);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Organisation ong = ongService.findOrganisationByUsername(username);

        LOGGER.info("Ong {} found successfully", username);
        return new ResponseEntity<>(ongMapper.convertToDto(ong), HttpStatus.OK);
    }

    @ApiOperation(value = "Insert an ong")
    @PostMapping
    public ResponseEntity<Void> insertOrganisation(@RequestBody OrganisationDto ongDto) {

        LOGGER.info("Adding a new ong");
        ongService.insertOrganisation(ongMapper.convertToEntity(ongDto));

        LOGGER.info("Ong {} added successfully!", ongDto.getOngId());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @ApiOperation(value = "Update an ong")
    @PutMapping(value = "/{ong_id}")
    public ResponseEntity<Void> update(@PathVariable("ong_id") int ong_id, @RequestBody OrganisationDto ong) {
        LOGGER.info("Updating  ong with id:{}", ong_id);

        if (!ongService.exists(ong_id)) {
            LOGGER.info("Ong with id:{} doesn't exists", ong_id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        ong.setOngId(ong_id);
        ongService.update(ongMapper.convertToEntity(ong));
        LOGGER.info("Ong with id:{} updated successfully", ong_id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "Update ong's password")
    @PutMapping(value = "/{ong_id}/password")
    public ResponseEntity<Void> updatePassword(@PathVariable("ong_id") int ong_id, @RequestBody String password) {
        LOGGER.info("Updating  ong with id:{}", ong_id);

        if (!ongService.exists(ong_id)) {
            LOGGER.info("Ong with id:{} doesn't exists", ong_id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Organisation ong = ongService.findOrganisationById(ong_id);
        ong.setOngId(ong_id);
        ongService.updatePassword(ong_id,password);
        LOGGER.info("Ong's password with id:{} updated successfully", ong_id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "Delete an ong")
    @DeleteMapping(value = "/{ong_id}")
    public ResponseEntity<Void> delete(@PathVariable("ong_id") int id) throws ConvertBlobException {
        LOGGER.info("Deleting ong with id={}", id);

        ongService.findOrganisationById(id);
        if (!ongService.exists(id)) {
            LOGGER.info("Ong with id:{} doesn't exists", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        ongService.delete(id);

        LOGGER.info("Ong {} deleted successfully!", id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
