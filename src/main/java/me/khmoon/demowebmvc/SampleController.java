package me.khmoon.demowebmvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class SampleController {

  @GetMapping("/events/form")
  public String eventsForm(Model model) {
    Event newEvent = new Event();
    newEvent.setLimit(50);
    model.addAttribute("event", newEvent);
    return "/events/form";
  }

  @PostMapping("/events")
  public String getEvent(@Validated @ModelAttribute Event event, BindingResult bindingResult, Model model) {
    if (bindingResult.hasErrors()) {
      return "events/form";
    }
    List<Event> eventList = new ArrayList<>();
    eventList.add(event);
    model.addAttribute("eventList", eventList);
    return "events/list";

  }
}
