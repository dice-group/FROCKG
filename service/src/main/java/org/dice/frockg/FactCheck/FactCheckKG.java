package org.dice.frockg.FactCheck;

import java.util.concurrent.Callable;
import org.springframework.web.client.RestTemplate;

public class FactCheckKG implements Callable<String> {
  private final String subject;
  private final String object;
  private final String predicate;
  private final String serverURL;

  public FactCheckKG(String subject, String object, String predicate, String serverURL) {
    this.subject = subject;
    this.object = object;
    this.predicate = predicate;
    this.serverURL = serverURL;
  }

  private String KGFactCheck(String subject, String object, String predicate) {

    String pathGeneratorType = handlePathGenerator(predicate);

    String url = serverURL + "/validate"
        + "?subject={subject}&property={predicate}&object={object}";

    RestTemplate restTemplate = new RestTemplate();

    String result = restTemplate.getForObject(url, String.class, subject, predicate, object, pathGeneratorType);

    return result;
  }

  private String handlePathGenerator(String predicate) {
    if (predicate.toLowerCase().contains("wikidata")) {
      return "wikidata";
    }
    return "default";
  }

  @Override
  public String call() throws Exception {
    try {
      String resultKG =
          KGFactCheck(subject, object, predicate);
      return resultKG;
    } catch (Exception ex) {
      return "{\"graphBaseFactCheckIsSucceed\": \"false\",\"graphBaseFactCheckErrorMessage\": \"Error\"}";
    }
  }
}
