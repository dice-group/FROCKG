package org.dice.FROCKG.FactCheck;

import java.util.concurrent.Callable;
import org.springframework.web.client.RestTemplate;

public class FactCheckKG implements Callable<String> {
  private String subject;
  private String object;
  private String predicate;
  private boolean isVirtualType;
  private int pathLength;
  private String serverURL;
  private boolean verbalize;

  public FactCheckKG(
      String subject,
      String object,
      String predicate,
      boolean isVirtualType,
      int pathLength,
      String serverURL,
      boolean verbalize) {
    this.subject = subject;
    this.object = object;
    this.predicate = predicate;
    this.isVirtualType = isVirtualType;
    this.pathLength = pathLength;
    this.serverURL = serverURL;
    this.verbalize = verbalize;
  }

  private String KGFactCheck(
      String subject,
      String object,
      String predicate,
      boolean isVirtualType,
      int pathLength,
      boolean verbalize) {

    String pathGeneratorType = handlePathGenerator(predicate);

    String url =
        serverURL
            + "/validate"
            + "?subject={subject}&property={predicate}&object={object}&pathgeneratortype={pathGeneratorType}&virtualType={isVirtualType}&pathlength={pathLength}&verbalize={verbalize}";

    RestTemplate restTemplate = new RestTemplate();

    String result =
        restTemplate.getForObject(
            url,
            String.class,
            subject,
            predicate,
            object,
            pathGeneratorType,
            isVirtualType,
            pathLength);

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
    String resultKG = KGFactCheck(subject, object, predicate, isVirtualType, pathLength, verbalize);
    return resultKG;
  }
}
