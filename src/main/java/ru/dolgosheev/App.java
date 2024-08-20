package ru.dolgosheev;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class App {
    public static void main(String[] args) throws IOException, ParseException {

        String fileName = "tickets.json";
        ObjectMapper mapper = new ObjectMapper();

        //Парсинг файла и последующая сортировка билетов по городам отправления (Владивосток) - назначения (Тель-Авив)
        TicketsList ticketsList = mapper.readValue(new File(fileName), new TypeReference<>() {
        });
        List<Ticket> ticketsFromVVOToTLV = ticketsList.getTickets()
                .stream()
                .filter(c -> c.getOrigin().equals("VVO"))
                .filter(c -> c.getDestination().equals("TLV"))
                .toList();

        Set<String> carrierSet = convert(ticketsFromVVOToTLV);
        for (String carrier : carrierSet) {
            minTime(ticketsFromVVOToTLV, carrier);
        }

        int[] ticketsPriceArray = ticketsFromVVOToTLV
                .stream()
                .mapToInt(Ticket::getPrice)
                .toArray();

        int diffBetweenAverageAndMedian = (int) (average(ticketsPriceArray) - median(ticketsPriceArray));

        System.out.println("Разница между средней ценой и медианой для полета между городами Владивосток и Тель-Авив: "
                + (diffBetweenAverageAndMedian));
    }

    //метод для расчета минимального времени полета
    public static void minTime(List<Ticket> ticketsList, String carrier) throws ParseException {
        SimpleDateFormat dateTimeFormatter = new SimpleDateFormat("HH:mm");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yy");
        long minTime = 1000000000;

        List<Ticket> filtredTicketsList = ticketsList.stream()
                .filter(t -> t.getCarrier().equals(carrier))
                .toList();

        for (Ticket ticket : filtredTicketsList) {
            Date departureTime = dateTimeFormatter.parse(ticket.getDepartureTime());
            Date arrivalTime = dateTimeFormatter.parse(ticket.getArrivalTime());
            Date departureDate = dateFormat.parse(ticket.getDepartureDate());
            Date arrivalDate = dateFormat.parse(ticket.getArrivalDate());

            long mSecInDay = 86400000;
            long diff = arrivalTime.getTime() - departureTime.getTime();
            if (arrivalDate.after(departureDate)) {
                diff += mSecInDay;
            } else if (departureDate.after(arrivalDate)) {
                System.out.println("\nНеверно указана дата отправления / прилета билета:\n" + ticket.toString() + "\n");
                continue;
            } else if (departureDate.equals(arrivalDate)) {
                if (departureTime.after(arrivalDate)) {
                    System.out.println("\nНеверно указано время отправления / прилета билета:\n" + ticket.toString() + "\n");
                    continue;
                }
            }
            if (diff < minTime) {
                minTime = diff;
            }
        }
        long minTimeHours = (minTime / (60 * 60 * 1000));

        System.out.println("Минимальное время полета перевозчика \""
                + carrier + "\": "
                + minTimeHours + ":" + minTimeMinutesFormated(minTime));
    }

    //метод форматирования минут
    public static String minTimeMinutesFormated(long minTime) {
        StringBuilder minTimeMinutesToString = new StringBuilder();
        long minTimeMinutes = (minTime / (60 * 1000) % 60);
        if (String.valueOf(minTimeMinutes).length() == 1) {
            minTimeMinutesToString.append("0").append(minTimeMinutes);
        } else {
            minTimeMinutesToString.append(minTimeMinutes);
        }
        return minTimeMinutesToString.toString();
    }

    //метод для создания списка перевозчиков
    public static Set<String> convert(List<Ticket> tickets) {
        Set<String> carrierSet = new HashSet<>();
        for (Ticket ticket : tickets) {
            carrierSet.add(ticket.getCarrier());
        }
        return carrierSet;
    }

    //метод для нахождения среднего арифметического
    public static float average(int[] array) {
        int valueArithmeticMean = 0;
        for (int j : array) {
            valueArithmeticMean += j;
        }
        return valueArithmeticMean / array.length;
    }

    //метод для нахождения медианы чисел
    public static float median(int[] array) {
        Arrays.sort(array);
        if (array.length % 2 == 0) {
            return ((array[array.length / 2]
                    + array[array.length / 2 - 1]) / 2f);
        }
        return array[array.length / 2];
    }
}
