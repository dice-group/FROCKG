package org.dice.frockg.api;

import javax.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dice.frockg.service.FactCheckService;
import org.dice.frockg.data.dto.*;
import org.dice.frockg.mapper.*;
import org.dice.frockg.service.InfrastructureMonitoringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/")
public class FactCheckController {

  Logger logger = LogManager.getLogger(FactCheckController.class);

  @Autowired
  private FactCheckService service;

  @Autowired
  FacadeResultMapper mapper;

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

  // To verify status of FactCheck server
  @RequestMapping("/factchecktest")
  public String factchecktest() {
    if (monitorService.pingFactCheck()) {
      return "OK!";
    }
    return "FACTCHECK service is disconnected!";
  }

  @GetMapping("/requestFactChecking")
  public ResponseEntity<Object> requestFactChecking(@Valid FactCheckRequestDto input) {
    FactCheckingTicketDto ticket = service.requestFactChecking(input.getSubject(), input.getObject(), input.getPredicate());
    return ResponseEntity.ok(ticket);
  }

  @GetMapping("/retrieveResult")
  public ResponseEntity<Object> retrieveResult(@Valid String taskId) {
    FacadeResultDto result = mapper.map(service.retrieveResult(taskId));
    //result.generateExplanation();
    return ResponseEntity.ok(result);
  }


  @GetMapping("/checkKG")
  public String checkKG(@RequestParam(value = "sparql", required = true) String sparql,
      @RequestParam(value = "user", required = true) String user,
      @RequestParam(value = "password", required = true) String password) {

    throw new UnsupportedOperationException();
  }
}
