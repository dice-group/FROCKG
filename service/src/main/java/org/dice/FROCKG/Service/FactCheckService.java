package org.dice.FROCKG.Service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class FactCheckService {
  @Value("${COPAAL.Server}")
  private String COPAALServer;

  public String checkThisFact(
      String subject, String object, String predicate, boolean isVirtualType) {

    String pathGeneratorType = handlePathGenerator(predicate);

    String url =
        COPAALServer
            + "?subject={subject}&property={predicate}&object={object}&pathgeneratortype={pathGeneratorType}&virtualType={isVirtualType}";

    RestTemplate restTemplate = new RestTemplate();

    String result =
        restTemplate.getForObject(
            url, String.class, subject, predicate, object, pathGeneratorType, isVirtualType);

    return result;
  }

  private String handlePathGenerator(String predicate) {
    if (predicate.toLowerCase().contains("wikidata")) {
      return "wikidata";
    }
    return "default";
  }
}
