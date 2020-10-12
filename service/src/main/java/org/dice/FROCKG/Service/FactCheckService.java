package org.dice.FROCKG.Service;

import org.dice.FROCKG.utilities.validator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class FactCheckService {

  @Value("${COPAAL.Server}")
  private String COPAALServer;

  public String checkFact(
      String subject, String object, String predicate, boolean isVirtualType, int pathLength) {

    if (!validateArgument(subject, object, predicate, isVirtualType, pathLength)) {
      throw new IllegalArgumentException();
    }

    String resultKG = KGFactCheck(subject, object, predicate, isVirtualType, pathLength);
    String resultCorpus = CorpusFactCheck(subject, object, predicate);
    return mergeResult(resultKG, resultCorpus);
  }

  private String mergeResult(String resultKG, String resultCorpus) {
    return resultKG + resultCorpus;
  }

  private String CorpusFactCheck(String subject, String object, String predicate) {
    // TODO Implement
    return null;
  }

  private String KGFactCheck(
      String subject, String object, String predicate, boolean isVirtualType, int pathLength) {

    String pathGeneratorType = handlePathGenerator(predicate);

    String url =
        COPAALServer
            + "/validate"
            + "?subject={subject}&property={predicate}&object={object}&pathgeneratortype={pathGeneratorType}&virtualType={isVirtualType}&pathlength={pathLength}";
    System.out.print(url);
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
    System.out.print(result);
    return result;
  }

  private boolean validateArgument(
      String subject, String object, String predicate, boolean isVirtualType, int pathLength) {
    if (!validator.isUriValid(subject)) {
      return false;
    }
    if (!validator.isUriValid(object)) {
      return false;
    }
    if (!validator.isUriValid(predicate)) {
      return false;
    }
    if (pathLength != 2 && pathLength != 3) {
      return false;
    }
    return true;
  }

  private String handlePathGenerator(String predicate) {
    if (predicate.toLowerCase().contains("wikidata")) {
      return "wikidata";
    }
    return "default";
  }
}
