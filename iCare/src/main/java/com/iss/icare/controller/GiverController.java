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

@Api(value = "giver controller")
@RestController
@RequestMapping("/givers")
public class GiverController {

    private static final Logger LOGGER = LogManager.getLogger(GiverController.class);
    private final GiverService giverService;
    private final GiverMapper giverMapper;

    public GiverController(GiverService giverService) {
        this.giverService = giverService;
        this.giverMapper = new GiverMapper();
    }

    @ApiOperation(value = "Find All Givers")
    @GetMapping
    public ResponseEntity<List<GiverDto>> findAllGivers() {
        LOGGER.info("Getting all givers");
        List<Giver> list = giverService.findAllGivers();

        List<GiverDto> result = list.stream().map(giverMapper::convertToDto).collect(Collectors.toList());

        LOGGER.info("Returning {} givers", result.size());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ApiOperation(value = "Find giver by id")
    @GetMapping("/{giver_id}")
    public ResponseEntity<GiverDto> findGiverById(@PathVariable("giver_id") int giverId) {
        LOGGER.info("Finding giver with id:{}", giverId);

        if (!giverService.exists(giverId)) {
            LOGGER.info("Giver with id: {} doesn't exists", giverId);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Giver giver = giverService.findGiverById(giverId);

        LOGGER.info("Giver {} found successfully", giverId);
        return new ResponseEntity<>(giverMapper.convertToDto(giver), HttpStatus.OK);
    }

    @ApiOperation(value = "Find giver by username")
    @GetMapping("/username/{username}")
    public ResponseEntity<GiverDto> findGiverByUsername(@PathVariable("username") String username) {
        LOGGER.info("Finding giver with username:{}", username);

        if (giverService.existsUsername(username)) {
            LOGGER.info("Giver with username: {} already exists", username);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Giver giver = giverService.findGiverByUsername(username);

        LOGGER.info("Giver {} found successfully", username);
        return new ResponseEntity<>(giverMapper.convertToDto(giver), HttpStatus.OK);
    }

    @ApiOperation(value = "Insert a giver")
    @PostMapping
    public ResponseEntity<Void> insertGiver(@RequestBody GiverDto giverDto) {

        LOGGER.info("Adding a new giver");
        giverService.insertGiver(giverMapper.convertToEntity(giverDto));

        LOGGER.info("Giver {} added successfully!", giverDto.getGiverId());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @ApiOperation(value = "Update a giver")
    @PutMapping(value = "/{giver_id}")
    public ResponseEntity<Void> update(@PathVariable("giver_id") int giver_id, @RequestBody GiverDto giver) {
        LOGGER.info("Updating  giver with id:{}", giver_id);

        if (!giverService.exists(giver_id)) {
            LOGGER.info("Giver with id:{} doesn't exists", giver_id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        giver.setGiverId(giver_id);
        giverService.update(giverMapper.convertToEntity(giver));
        LOGGER.info("Giver with id:{} updated successfully", giver_id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "Update giver's password")
    @PutMapping(value = "/{giver_id}/password")
    public ResponseEntity<Void> update(@PathVariable("giver_id") int giver_id, @RequestBody String password) {
        LOGGER.info("Updating giver with id:{}", giver_id);

        if (!giverService.exists(giver_id)) {
            LOGGER.info("Giver with id:{} doesn't exists", giver_id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Giver giver = giverService.findGiverById(giver_id);
        giver.setGiverId(giver_id);
        giverService.updatePassword(giver_id,password);
        LOGGER.info("Giver's password with id:{} updated successfully", giver_id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "Delete a giver")
    @DeleteMapping(value = "/{giver_id}")
    public ResponseEntity<Void> delete(@PathVariable("giver_id") int id) throws ConvertBlobException {
        LOGGER.info("Deleting giver with id={}", id);

        giverService.findGiverById(id);
        if (!giverService.exists(id)) {
            LOGGER.info("Giver with id:{} doesn't exists", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        giverService.delete(id);

        LOGGER.info("Giver {} deleted successfully!", id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
