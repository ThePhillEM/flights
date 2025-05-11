package com.example.flights.controller;

import com.example.flights.model.Departure;
import com.example.flights.service.FlightsService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Controller
public class FlightsController {

    private final FlightsService service;

    public FlightsController(FlightsService service) {
        this.service = service;
    }

    // Zobrazení prázdného formuláře
    @GetMapping("/")
    public String showForm() {
        return "form";  // vrátí resources/templates/form.html
    }

    // Zpracování odeslaného formuláře
    @PostMapping("/departures")
    public String showDepartures(
            @RequestParam String airport,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime from,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime until,
            Model model
    ) {
        // 1) výchozí interval poslední hodiny
        LocalDateTime now   = LocalDateTime.now();
        LocalDateTime start = (from   != null) ? from   : now.minusHours(1);
        LocalDateTime end   = (until != null) ? until : now;

        // 2) Tato část kódu vyobrazí uživateli errorMessage v případě, když zadá datum > než aktuální datum
        if (start.isAfter(now) || end.isAfter(now)) {
            model.addAttribute("errorMessage", "Datum nesmí být v budoucnosti.");
            presetForm(model, airport, from, until);
            return "form";
        }

        // 3) Tato částo kódu vyobrazí uživateli errorMessage v případě, když zadá intertval > 7 dní.
        long daysBetween = ChronoUnit.DAYS.between(start, end);
        if (daysBetween > 7) {
            model.addAttribute("errorMessage", "Maximální délka intervalu je 7 dní.");
            presetForm(model, airport, from, until);
            return "form";
        }

        // 4) Tato část kódu vyobrazí uživateli errorMessage v případě, když zadá datum starší než pět od současného data
        if (start.isBefore(now.minusYears(5))) {
            model.addAttribute("errorMessage", "Nelze hledat více než 5 let zpět.");
            presetForm(model, airport, from, until);
            return "form";
        }

        // 5) Převod na epoch-sekundy a volání služby
        long f = start.atZone(ZoneId.systemDefault()).toEpochSecond();
        long u = end  .atZone(ZoneId.systemDefault()).toEpochSecond();
        List<Departure> flights = service.getDepartures(airport, f, u);

        model.addAttribute("flights", flights);
        model.addAttribute("airport", airport);
        model.addAttribute("from",    f);
        model.addAttribute("until",   u);
        return "departures";
    }

    // Pomocná metoda pro předvyplnění polí formuláře při chybě
    private void presetForm(Model model,
                            String airport,
                            LocalDateTime from,
                            LocalDateTime until) {
        model.addAttribute("airport",   airport);
        model.addAttribute("fromValue", from);
        model.addAttribute("untilValue", until);
    }
}
