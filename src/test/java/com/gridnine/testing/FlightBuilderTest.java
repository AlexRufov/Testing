package com.gridnine.testing;

import org.junit.After;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

/*
К сожалению, тесты я не писал. Это мои первые тесты.
 */

public class FlightBuilderTest {
    private FlightBuilder flightBuilder;
    private final List<Flight> flightList = FlightBuilder.createFlights();


    @Test
    public void createFlights() {
        assertNotNull(flightList);
        assertThat(flightList.size(), is(6));
    }

    @After
    public void setFlight(){
        System.out.println(flightList);
    }
}