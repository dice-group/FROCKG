package org.dice.FROCKG.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dice.FROCKG.Service.FactCheckService;
import org.dice.FROCKG.Service.InfrastructureMonitoringService;
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

  Logger logger = LogManager.getLogger(FactCheckController.class);

  @Autowired
  private FactCheckService service;

  @Autowired
  private InfrastructureMonitoringService monitorService;

  // To verify status of server
  @RequestMapping("/default")
  public String defaultpage() {
    logger.info("end point called.");
    return "OK!";
  }

  // To verify status of Copaal server
  @RequestMapping("/copaaltest")
  public String copaaltest() {
    if (monitorService.pingCopaal()) {
      return "OK!";
    }
    return "COPAAL service is disconnected!";
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
      logger.error("parameters are not valid" + "subject : " + subject + " object : " + object
          + " predicate : " + predicate + " isVirtual : " + isVirtualType + " pathLength "
          + pathLength);
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Parameter not valid", ex);
    } catch (Exception ex) {
      logger.error(ex);
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
