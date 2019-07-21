package me.khmoon.demowebmvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@SessionAttributes("event")
public class EventController {

  @InitBinder
  public void initEventBinder(WebDataBinder webDataBinder) {
    webDataBinder.setDisallowedFields("id");
  }

  @ModelAttribute
  public void subjects(Model model) {
    model.addAttribute("subjects", List.of("study", "seminar", "hobby", "social"));
  }


  @GetMapping("/events/form/name")
  public String eventsFormName(Model model) {
    Event newEvent = new Event();
    newEvent.setLimit(50);
    model.addAttribute("event", newEvent);
    return "/events/form-name";
  }

  @PostMapping("/events/form/name")
  public String eventFormNameSubmit(@Validated @ModelAttribute Event event, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return "events/form-name";
    }
    return "redirect:/events/form/limit";
  }

  @GetMapping("/events/form/limit")
  public String eventFormLimit(@ModelAttribute Event event, Model model) {
    model.addAttribute("event", event);
    return "/events/form-limit";
  }

  @PostMapping("/events/form/limit")
  public String eventFormLimitSubmit(@Validated @ModelAttribute Event event, BindingResult bindingResult, SessionStatus sessionStatus, RedirectAttributes attributes) {
    if (bindingResult.hasErrors()) {
      return "events/form-limit";
    }
    sessionStatus.setComplete();
    attributes.addFlashAttribute("newEvent", event);
    return "redirect:/events/list";
  }

  @GetMapping("/events/list")
  public String getEvents(Model model, @SessionAttribute LocalDateTime visitTime) {
    System.out.println(visitTime);
    Event spring = new Event();
    spring.setName("spring");
    spring.setLimit(10);

    Event newEvent = (Event) model.asMap().get("newEvent");

    List<Event> eventList = new ArrayList<>();
    eventList.add(spring);
    eventList.add(newEvent);
    model.addAttribute("eventList", eventList);

    return "/events/list";
  }
}
