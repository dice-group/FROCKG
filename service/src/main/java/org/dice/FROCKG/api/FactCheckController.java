package org.dice.FROCKG.api;

import org.dice.FROCKG.Service.FactCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/v1/")
public class FactCheckController {

  @Autowired
  private FactCheckService service;

  // To verify status of server
  @RequestMapping("/default")
  public String defaultpage() {
    return "OK!";
  }

  @GetMapping("/checkFact")
  public String checkFact(@RequestParam(value = "subject", required = true) String subject,
      @RequestParam(value = "object", required = true) String object,
      @RequestParam(value = "predicate", required = true) String predicate,
      @RequestParam(value = "isVirtualType", defaultValue = "false") boolean isVirtualType,
      @RequestParam(value = "pathlength", required = false,
          defaultValue = "2") Integer pathLength) {
    try {
      return service.checkFact(subject, object, predicate, isVirtualType, pathLength);
    } catch (IllegalArgumentException ex) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Parameter not valid", ex);
    } catch (Exception ex) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "error happened", ex);
    }
  }

  @GetMapping("/checkKG")
  public String checkKG(@RequestParam(value = "sparql", required = true) String sparql,
      @RequestParam(value = "user", required = true) String user,
      @RequestParam(value = "password", required = true) String password) {

    throw new UnsupportedOperationException();
  }
}
