package me.khmoon.demowebmvc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest
public class SampleControllerTest {

  @Autowired
  MockMvc mockMvc;

  @Test
  public void postEvent() throws Exception {
    mockMvc.perform(post("/events")
    .param("name","keesun")
    .param("limit","-10")
    )

            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(model().hasErrors())
    ;
  }
}