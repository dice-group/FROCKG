package org.dice.FROCKG.FactCheck;

import java.util.concurrent.Callable;

public class FactCheckCorpus implements Callable<String> {
  private String subject;
  private String object;
  private String predicate;

  public FactCheckCorpus(String subject, String object, String predicate) {
    this.subject = subject;
    this.object = object;
    this.predicate = predicate;
  }

  private String CorpusFactCheck(String subject, String object, String predicate) {
    // TODO Implement
    return null;
  }

  @Override
  public String call() throws Exception {
    String resultCorpus = CorpusFactCheck(subject, object, predicate);
    return resultCorpus;
  }

}
