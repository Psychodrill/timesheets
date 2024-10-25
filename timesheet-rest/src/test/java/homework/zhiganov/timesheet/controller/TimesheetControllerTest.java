package homework.zhiganov.timesheet.controller;

import static org.junit.jupiter.api.Assertions.*;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestClient;

import homework.zhiganov.timesheet.model.Timesheet;
import homework.zhiganov.timesheet.repository.TimesheetRepository;
import homework.zhiganov.timesheet.service.TimesheetService;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TimesheetControllerTest {

     @Autowired
     TimesheetRepository tsRepository;
    // @Autowired
    // TimesheetService tsService;
    // @Autowired
    // TimesheetController timesheetController;
    @LocalServerPort
    private int port;
    private RestClient restClient;

    @BeforeEach
    void beforeEach(){
        restClient = RestClient.create("http://localhost:" + port);
        tsRepository.deleteAll();
    }

    @Test
    void getTimesheetTest(){
        //Arrange
        Timesheet expected = new Timesheet();
        expected.setMinutes(666);
        expected.setCreatedAt(LocalDate.now());
        expected.setProjectId(666L);
        expected.setEmployeeId(666L);
        expected = tsRepository.save(expected);
        //Act
        ResponseEntity<Timesheet> actual = restClient.get()
            .uri("/timesheets/" + expected.getId())
            .retrieve()
            .toEntity(Timesheet.class);

        //Assert
        assertEquals(HttpStatus.OK, actual.getStatusCode());

        Timesheet responseBody = actual.getBody(); 
        assertNotNull(responseBody);
        assertEquals(expected.getId(), responseBody.getId());
        assertEquals(expected.getMinutes(), responseBody.getMinutes());
        assertEquals(expected.getCreatedAt(), responseBody.getCreatedAt());
        assertEquals(expected.getEmployeeId(), responseBody.getEmployeeId());
        assertEquals(expected.getProjectId(), responseBody.getProjectId());

    }
    @Test
    void createTest(){
        //Arrange
        Timesheet toCreate = new Timesheet();
        toCreate.setMinutes(666);
        toCreate.setCreatedAt(LocalDate.now());
        toCreate.setProjectId(666L);
        toCreate.setEmployeeId(666L);
        toCreate = tsRepository.save(toCreate);

        //Act
        ResponseEntity<Timesheet> response = restClient.post()
            .uri("/timesheets")
            .body(toCreate)
            .retrieve()
            .toEntity(Timesheet.class);

        //Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());

        Timesheet responseBody = response.getBody(); 
        assertNotNull(responseBody);
        assertNotNull(responseBody.getId());
        //assertEquals(expected.getId(), responseBody.getId());
        assertEquals(toCreate.getMinutes(), responseBody.getMinutes());
        assertEquals(toCreate.getCreatedAt(), responseBody.getCreatedAt());
        assertEquals(toCreate.getEmployeeId(), responseBody.getEmployeeId());
        assertEquals(toCreate.getProjectId(), responseBody.getProjectId());

        assertTrue(tsRepository.existsById(responseBody.getId()));

    }
    @Test
    void deleteTest(){
        //Arrange
        Timesheet toDelete = new Timesheet();
        toDelete.setMinutes(666);
        toDelete.setCreatedAt(LocalDate.now());
        toDelete.setProjectId(666L);
        toDelete.setEmployeeId(666L);
        toDelete = tsRepository.save(toDelete);

        //Act
        ResponseEntity<Void> response = restClient.delete()
            .uri("/timesheets/" + toDelete.getId())
            .retrieve().toBodilessEntity();


        //Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());

        assertFalse(tsRepository.existsById(toDelete.getId()));
        

    }

    @Test
    void getAllTimesheetsTest(){
        //Arrange
        Timesheet expected = new Timesheet();
        expected.setMinutes(666);
        expected.setCreatedAt(LocalDate.now());
        expected.setProjectId(666L);
        expected.setEmployeeId(666L);
        expected = tsRepository.save(expected);
        Timesheet expected1 = new Timesheet();
        expected1.setMinutes(777);
        expected1.setCreatedAt(LocalDate.now());
        expected1.setProjectId(777L);
        expected1.setEmployeeId(777L);
        expected1 = tsRepository.save(expected1);
        //Act
        ResponseEntity<List<Timesheet>> actual = restClient.get()
            .uri("/timesheets")
            .retrieve()
            .toEntity( new ParameterizedTypeReference<List<Timesheet>>(){});

        //Assert
        assertEquals(HttpStatus.OK, actual.getStatusCode());

        List<Timesheet> responseBody = actual.getBody(); 
        assertNotNull(responseBody);
        assertEquals(expected.getId(), responseBody.get(0).getId());
        assertEquals(expected.getMinutes(), responseBody.get(0).getMinutes());
        assertEquals(expected.getCreatedAt(), responseBody.get(0).getCreatedAt());
        assertEquals(expected.getEmployeeId(), responseBody.get(0).getEmployeeId());
        assertEquals(expected.getProjectId(), responseBody.get(0).getProjectId());

        assertEquals(expected1.getId(), responseBody.get(1).getId());
        assertEquals(expected1.getMinutes(), responseBody.get(1).getMinutes());
        assertEquals(expected1.getCreatedAt(), responseBody.get(1).getCreatedAt());
        assertEquals(expected1.getEmployeeId(), responseBody.get(1).getEmployeeId());
        assertEquals(expected1.getProjectId(), responseBody.get(1).getProjectId());

    }




}
