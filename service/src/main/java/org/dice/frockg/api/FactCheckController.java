package org.dice.frockg.api;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dice.frockg.service.FactCheckService;
import org.dice.frockg.data.dto.*;
import org.dice.frockg.mapper.*;
import org.dice.frockg.service.InfrastructureMonitoringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/service/")
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
  public ResponseEntity<String> defaultpage() {
    logger.info("end point called.");
    logger.info("...");
    return ResponseEntity.ok("OK!");
  }

  // To verify status of Copaal server
  @RequestMapping("/copaaltest")
  public ResponseEntity<String> copaaltest() {
    logger.info("copaal test end point called.");
    if (monitorService.pingCopaal()) {
      return ResponseEntity.ok("OK!");
    }
    return ResponseEntity.status(500).body("COPAAL service is disconnected!");
  }

  // To verify status of FactCheck server
  @RequestMapping("/factchecktest")
  public ResponseEntity<String> factchecktest() {
    logger.info("factcheck test end point called.");
    if (monitorService.pingFactCheck()) {
      return ResponseEntity.ok("OK!");
    }
    return ResponseEntity.status(500).body("FACTCHECK service is disconnected!");
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

  @RequestMapping("/error")
  public String handleError(HttpServletRequest request) throws Exception {
    Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

    if (status != null) {
      Integer statusCode = Integer.valueOf(status.toString());

      logger.error("status is "+statusCode);

      logger.error(httpServletRequestToString(request));

    }
    return "error";
  }

  String httpServletRequestToString(HttpServletRequest request) throws Exception {

    ServletInputStream mServletInputStream = request.getInputStream();
    byte[] httpInData = new byte[request.getContentLength()];
    int retVal = -1;
    StringBuilder stringBuilder = new StringBuilder();

    while ((retVal = mServletInputStream.read(httpInData)) != -1) {
      for (int i = 0; i < retVal; i++) {
        stringBuilder.append(Character.toString((char) httpInData[i]));
      }
    }

    return stringBuilder.toString();
  }
}
