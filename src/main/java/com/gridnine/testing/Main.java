package com.gridnine.testing;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Flight> flightList = FlightBuilder.createFlights();

        System.out.println("Список всех перелетов:");
        for (int i = 0; i < flightList.size(); i++){
            System.out.println(flightList.get(i));
        }
        System.out.println();

        System.out.println("Список вылетов до текущего момента времени:");
        List<List<Segment>> pastDepartures = FlightBuilder.createFlights().stream()
                .map(flight -> flight.getSegments()
                        .stream()
                        .filter(segment -> segment.getDepartureDate().isBefore(LocalDateTime.now()))
                        .collect(Collectors.toList()))
                .collect(Collectors.toList());
        for (int i = 0; i < pastDepartures.size(); i++){
            System.out.println(pastDepartures.get(i));
        }
        System.out.println();

        System.out.println("Список перелетов, имеющие сегменты с датой прилёта раньше даты вылета:");
        List<List<Segment>> wrongDate = FlightBuilder.createFlights().stream()
                .map(flight -> flight.getSegments()
                        .stream()
                        .filter(segment -> segment.getDepartureDate().isAfter(segment.getArrivalDate()))
                        .collect(Collectors.toList()))
                .collect(Collectors.toList());
        for (int i = 0; i < wrongDate.size(); i++){
            System.out.println(wrongDate.get(i));
        }
        System.out.println();

        System.out.println("Список перелетов с пересадкой более 2-х часов:");
        Duration maxTransferTime = Duration.ofHours(2);
        Duration transferTime = Duration.ofHours(0);
        for (int i = 0; i < flightList.size(); i++){
            for (int j = 0; j < flightList.get(i).getSegments().size(); j++){
                if (j + 1 < flightList.get(i).getSegments().size()) {
                    LocalTime departureAfterTransfer = flightList.get(i).getSegments().get(j + 1).getDepartureDate().toLocalTime();
                    LocalTime arrivalBeforeTransfer = flightList.get(i).getSegments().get(j).getArrivalDate().toLocalTime();
                    transferTime = Duration.between(arrivalBeforeTransfer, departureAfterTransfer);
                }
            }
            if (transferTime.toHours() >= maxTransferTime.toHours())
            System.out.println(flightList.get(i));
        }
    }
}
