package org.dice.FROCKG.FactCheck;

import java.util.concurrent.Callable;
import org.springframework.web.client.RestTemplate;

public class FactCheckCorpus implements Callable<String> {
  private final String subject;
  private final String object;
  private final String predicate;
  private final String serverURL;

  public FactCheckCorpus(String subject, String object, String predicate, String serverURL) {
    this.subject = subject;
    this.object = object;
    this.predicate = predicate;
    this.serverURL = serverURL;
  }

  private String CorpusFactCheck(String subject, String object, String predicate,
      String serverURL) {

    String url = serverURL + "/checkfact?subject={subject}&object={object}&predicate={predicate}";

    RestTemplate restTemplate = new RestTemplate();

    String result = restTemplate.getForObject(url, String.class, subject, object, predicate);

    return result;
  }

  @Override
  public String call() throws Exception {
    try {
      String resultCorpus = CorpusFactCheck(subject, object, predicate, serverURL);
      return resultCorpus;
    } catch (Exception ex) {
      return "{\"corpusFactCheckIsSucceed\": \"false\",\"corpusFactCheckErrorMessage\": \"Error\"}";
    }
  }

}
