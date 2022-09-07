package org.dice.frockg.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class InfrastructureMonitoringService {

  private static final Logger logger = LogManager.getLogger(InfrastructureMonitoringService.class);

  @Value("${COPAAL.Server}")
  String COPAALServerUrl;

  @Value("${FACTCHECK.Server}")
  String FACTCHECKServerUrl;

  public boolean pingCopaal() {
    try {
      String url = COPAALServerUrl + "/test";

      RestTemplate restTemplate = new RestTemplate();

      String result = restTemplate.getForObject(url, String.class);

      if (result.toLowerCase().equals("ok!")) {
        logger.info("COPAAL is up");
        return true;
      }
    } catch (Exception ex) {
      logger.error(ex);
    }
    logger.info("COPAAL is down");
    return false;
  }

  public boolean pingFactCheck() {
    try {
      String url = FACTCHECKServerUrl + "/default";

      RestTemplate restTemplate = new RestTemplate();

      String result = restTemplate.getForObject(url, String.class);

      if (result.toLowerCase().equals("ok!")) {
        logger.info("FACTCHECK is up");
        return true;
      }
    } catch (Exception ex) {
      logger.error(ex);
    }
    logger.info("FACTCHECK is down");
    return false;
  }
}
