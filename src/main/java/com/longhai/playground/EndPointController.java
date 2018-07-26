package com.longhai.playground;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.Arrays.asList;

@RestController

public class EndPointController {

    @RequestMapping("/")
    public String getMyEndPoint() {
        return "Get to work";
    }

    @GetMapping("/person")
    public Person getPerson() {
        Person person = new Person();
        person.firstName = "Dwayne";
        person.lastName = "Johnson";
        return person;
    }


    @GetMapping("/flights/flight")
    public Flight getFlight() {
        Person passenger = new Person("Some name", "Some other name");
        Flight flight = new Flight();
        flight.setDeparts(LocalDateTime.of(2017, 4, 21, 14, 34));
        flight.setTickets(asList(new Ticket(passenger, 200)));
        return flight;
    }

    @GetMapping("/flights")
    public List<Flight> getFlights() {
        Person passenger1 = new Person("Some name", "Some other name");
        Flight flight1 = new Flight();
        flight1.setDeparts(LocalDateTime.of(2017, 4, 21, 14, 34));
        flight1.setTickets(asList(new Ticket(passenger1, 200)));

        Person passenger2 = new Person("Some other name", null);
        Flight flight2 = new Flight();
        flight2.setDeparts(LocalDateTime.of(2017, 4, 21, 14, 34));
        flight2.setTickets(asList(new Ticket(passenger2, 500)));

        return asList(flight1, flight2);
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Person {
        private String firstName;
        private String lastName;

        public Person() {
        }

        public Person(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }
    }

    public static class Flight{
        @JsonFormat(pattern = "yyyy-MM-dd hh:mm")
        private LocalDateTime departs;
        private List<Ticket> tickets;

        public Flight() {
        }

        public Flight(LocalDateTime date, List<Ticket> tickets) {
            this.departs = date;
            this.tickets = tickets;
        }

        public LocalDateTime getDeparts() {
            return departs;
        }

        public void setDeparts(LocalDateTime departs) {
            this.departs = departs;
        }

        public List<Ticket> getTickets() {
            return tickets;
        }

        public void setTickets(List<Ticket> tickets) {
            this.tickets = tickets;
        }
    }

    public static class Ticket{
        private Person passenger;
        private int price;

        public Ticket(Person passenger, int price) {
            this.passenger = passenger;
            this.price = price;
        }

        public Person getPassenger() {
            return passenger;
        }

        public void setPassenger(Person passenger) {
            this.passenger = passenger;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }
    }

}
