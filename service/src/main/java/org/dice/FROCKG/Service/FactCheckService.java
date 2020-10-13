package org.dice.FROCKG.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dice.FROCKG.FactCheck.FactCheckCorpus;
import org.dice.FROCKG.FactCheck.FactCheckKG;
import org.dice.FROCKG.api.FactCheckController;
import org.dice.FROCKG.utilities.validator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class FactCheckService {

  Logger logger = LogManager.getLogger(FactCheckController.class);

  @Value("${COPAAL.Server}")
  private String COPAALServer;

  public String checkFact(String subject, String object, String predicate, boolean isVirtualType,
      int pathLength) {
    logger.debug("start fact cheking ...");
    if (!validateArgument(subject, object, predicate, isVirtualType, pathLength)) {
      throw new IllegalArgumentException();
    }
    logger.debug("Arguments are valid :) ");
    List<Callable<String>> taskList = new ArrayList<Callable<String>>();
    taskList.add(new FactCheckCorpus(subject, object, predicate));
    taskList
        .add(new FactCheckKG(subject, object, predicate, isVirtualType, pathLength, COPAALServer));

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

    return mergeResult(results);
  }

  private String mergeResult(List<String> results) {
    String RetVal = null;
    for (String s : results) {
      RetVal += s;
    }
    return RetVal;
  }

  private boolean validateArgument(String subject, String object, String predicate,
      boolean isVirtualType, int pathLength) {
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

}
