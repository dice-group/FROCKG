package org.dice.FROCKG.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dice.FROCKG.Service.FactCheckService;
import org.dice.FROCKG.Service.InfrastructureMonitoringService;
import org.dice.FROCKG.data.dto.FactCheckResultDto;
import org.dice.FROCKG.mapper.FactCheckResultMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
  FactCheckResultMapper factCheckMapper;

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
  public ResponseEntity<FactCheckResultDto> checkFact(
      @RequestParam(value = "subject", required = true) String subject,
      @RequestParam(value = "object", required = true) String object,
      @RequestParam(value = "predicate", required = true) String predicate,
      @RequestParam(value = "isVirtualType", defaultValue = "false") boolean isVirtualType,
      @RequestParam(value = "pathlength", required = false,
          defaultValue = "2") Integer pathLength) {
    try {
      final FactCheckResultDto result = factCheckMapper
          .ToDto(service.checkFact(subject, object, predicate, isVirtualType, pathLength));
      return ResponseEntity.ok(result);
    } catch (IllegalArgumentException ex) {
      logger.error("parameters are not valid" + "subject : " + subject + " object : " + object
          + " predicate : " + predicate + " isVirtual : " + isVirtualType + " pathLength "
          + pathLength);
      return ResponseEntity.badRequest().build();
    } catch (Exception ex) {
      logger.error(ex);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

  @GetMapping("/checkKG")
  public String checkKG(@RequestParam(value = "sparql", required = true) String sparql,
      @RequestParam(value = "user", required = true) String user,
      @RequestParam(value = "password", required = true) String password) {

    throw new UnsupportedOperationException();
  }
}
