package org.dice.frockg.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dice.frockg.FactCheck.FactCheckCorpus;
import org.dice.frockg.FactCheck.FactCheckKG;
import org.dice.frockg.api.FactCheckController;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class FactCheckService {

  private static final Logger logger = LogManager.getLogger(FactCheckController.class);

  @Value("${COPAAL.Server}")
  private String COPAALServerUrl;

  @Value("${FACTCHECK.Server}")
  private String FACTCHECKServerUrl;

  public List<String> checkFact(String subject, String object, String predicate) {
    logger.debug("start fact cheking ...");
    List<Callable<String>> taskList = new ArrayList<Callable<String>>();
    taskList.add(new FactCheckCorpus(subject, object, predicate, FACTCHECKServerUrl));
    taskList.add(new FactCheckKG(subject, object, predicate, COPAALServerUrl));

    ExecutorService executor = Executors.newFixedThreadPool(2);

    List<String> results = new ArrayList<String>();

    try {
      for (Future<String> result : executor.invokeAll(taskList)) {
        if (result.get() != null) {
          results.add(result.get());
        }
      }
    } catch (InterruptedException ie) {
      logger.error(ie);
    } catch (ExecutionException e) {
      logger.error(e);
    }

    return results;
  }
}
