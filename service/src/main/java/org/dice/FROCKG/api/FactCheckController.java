package org.dice.FROCKG.api;

import org.dice.FROCKG.Service.FactCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/")
public class FactCheckController {

  @Autowired private FactCheckService service;

  // To verify status of server
  @RequestMapping("/default")
  public String defaultpage() {
    return "OK!";
  }

  /*@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Some parameters are invalid")*/
  @GetMapping("/checkFact")
  public String checkFact(
      @RequestParam(value = "subject", required = true) String subject,
      @RequestParam(value = "object", required = true) String object,
      @RequestParam(value = "predicate", required = true) String predicate,
      @RequestParam(value = "isVirtualType", defaultValue = "false") boolean isVirtualType,
      @RequestParam(value = "pathlength", required = false, defaultValue = "2")
          Integer pathLength) {

    return service.checkFact(subject, object, predicate, isVirtualType, pathLength);
  }

  @GetMapping("/checkKG")
  public String checkKG(
      @RequestParam(value = "sparql", required = true) String sparql,
      @RequestParam(value = "user", required = true) String user,
      @RequestParam(value = "password", required = true) String password) {

    throw new UnsupportedOperationException();
  }
}
