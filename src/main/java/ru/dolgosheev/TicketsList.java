package ru.dolgosheev;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class TicketsList {

    @JsonProperty("tickets")
    private List<Ticket> tickets;

    public List<Ticket> getTickets() {
        return tickets;
    }
}