package me.khmoon.demowebmvc;

import org.apache.tika.Tika;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;

@Controller
public class FileController {
  @Autowired
  ResourceLoader resourceLoader;
  @PostMapping("/file")
  public String uploadFile(@RequestParam MultipartFile file,
                           RedirectAttributes attributes) {
    String message = file.getOriginalFilename() + " is uploaded.";
    System.out.println(message);
    attributes.addFlashAttribute("message", message);
    return "redirect:/events/list";
  }

  @GetMapping("/file/{filename}")
  @ResponseBody
  public ResponseEntity<Resource> downloadFile(@PathVariable String filename) throws IOException {
    Resource resource = resourceLoader.getResource("classpath:" + filename);
    File file = resource.getFile();
    Tika tika = new Tika();
    String type = tika.detect(file);
    return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachement; filename=\"" + resource.getFilename() + "\"")
            .header(HttpHeaders.CONTENT_TYPE, type)
            .header(HttpHeaders.CONTENT_LENGTH, String.valueOf(file.length()))
            .body(resource);
  }

}
