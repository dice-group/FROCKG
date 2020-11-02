package org.dice.FROCKG.api;

import javax.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dice.FROCKG.Service.FactCheckService;
import org.dice.FROCKG.Service.InfrastructureMonitoringService;
import org.dice.FROCKG.data.dto.FactCheckRequestDto;
import org.dice.FROCKG.data.dto.FactCheckResultDto;
import org.dice.FROCKG.mapper.FactCheckResultMapper;
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

  @Autowired private FactCheckService service;

  @Autowired FactCheckResultMapper factCheckMapper;

  @Autowired private InfrastructureMonitoringService monitorService;

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
  public ResponseEntity<FactCheckResultDto> checkFact(@Valid FactCheckRequestDto input) {

    final FactCheckResultDto rr =
        factCheckMapper.ToDto(
            service.checkFact(
                input.getSubject(),
                input.getObject(),
                input.getPredicate(),
                input.isVirtualtype(),
                input.getPathlength(),
                input.isVerbalize()));
    return ResponseEntity.ok(rr);
  }

  @GetMapping("/checkKG")
  public String checkKG(
      @RequestParam(value = "sparql", required = true) String sparql,
      @RequestParam(value = "user", required = true) String user,
      @RequestParam(value = "password", required = true) String password) {

    throw new UnsupportedOperationException();
  }
}
