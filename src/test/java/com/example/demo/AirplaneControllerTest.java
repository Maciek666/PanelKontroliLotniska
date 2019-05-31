package com.example.demo;

import com.example.demo.controllers.AirplaneController;
import com.example.demo.entities.Airplane;
import com.example.demo.entities.Airport;
import com.example.demo.repositories.AirplaneRepo;
import com.example.demo.repositories.AirportRepo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class AirplaneControllerTest {
    @Spy
    @InjectMocks
    private AirplaneController airplaneController;

    @Mock
    private AirplaneRepo airplaneRepo;

    @Test
    public void add(){
        Airplane airplane = new Airplane();
        airplane.setNumberOfSeats(12);
        airplane.setPlaneModel("testMockNumber");
        airplane.setPlaneModel("MockPlane");
        ResponseEntity<String> result = airplaneController.addAirPlane(airplane.getPlaneModel(),
                airplane.getNumberOfSeats(),airplane.getSerialNo(),null);

        verify(airplaneRepo, times(1)).save(airplane);
        Assert.assertEquals("saved", result.getBody());

    }


}
