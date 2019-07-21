package me.khmoon.demowebmvc;

import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/events")
public class EventApi {

  @PostMapping
  public Event createEvent(HttpEntity<Event> request) {
    MediaType contentType=request.getHeaders().getContentType();
    System.out.println(contentType);
    return request.getBody();
  }
}
