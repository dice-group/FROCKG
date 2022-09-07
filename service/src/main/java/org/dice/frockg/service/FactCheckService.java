package org.dice.frockg.service;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dice.frockg.factcheck.FactCheckCorpus;
import org.dice.frockg.factcheck.FactCheckKG;
import org.dice.frockg.data.dto.*;
import org.dice.frockg.data.model.ServicesResponses;
import org.dice.frockg.data.repository.IFacadeResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class FactCheckService {

  private static final Logger logger = LogManager.getLogger(FactCheckService.class);

  @Autowired
  FactCheckCorpus factCheckCorpusService;

  @Autowired
  FactCheckKG factCheckKGService;

  @Autowired
  IFacadeResultRepository repository;

  @Value("${COPAAL.Server}")
  private String COPAALServerUrl;

  @Value("${FACTCHECK.Server}")
  private String FACTCHECKServerUrl;

  public FactCheckService(){}

//  public List<String> checkFact(String subject, String object, String predicate) {
//    logger.debug("start fact cheking ...");
//    List<Callable<String>> taskList = new ArrayList<Callable<String>>();
//    taskList.add(new FactCheckCorpus(subject, object, predicate, FACTCHECKServerUrl));
//    taskList.add(new FactCheckKG(subject, object, predicate, COPAALServerUrl));
//
//    ExecutorService executor = Executors.newFixedThreadPool(2);
//
//    List<String> results = new ArrayList<String>();
//
//    try {
//      for (Future<String> result : executor.invokeAll(taskList)) {
//        if (result.get() != null) {
//          results.add(result.get());
//        }
//      }
//    } catch (InterruptedException ie) {
//      logger.error(ie);
//    } catch (ExecutionException e) {
//      logger.error(e);
//    }
//
//    return results;
//  }

  public FactCheckingTicketDto requestFactChecking(String subject, String object, String predicate) {
    FactCheckingTicketDto response = new FactCheckingTicketDto();
    String taskId = UUID.randomUUID().toString();
    response.setTaskId(taskId);
    response.setStatus("not started");
    try {
      logger.debug("start fact cheking ...");
      ExecutorService executor = Executors.newFixedThreadPool(10);
      factCheckCorpusService.setValues(subject, object, predicate, FACTCHECKServerUrl, taskId);
      factCheckKGService.setValues(subject, object, predicate, COPAALServerUrl, taskId);

      executor.execute(() -> {
        factCheckCorpusService.call();
      });
      executor.execute(() -> {
        factCheckKGService.call();
      });
      response.setStatus("running");
    }catch (Exception ex){
      response.setStatus("faced error");
      response.setError(ex.getMessage());
      logger.error(ex.getStackTrace());
    }
    return response;
  }

  public ServicesResponses retrieveResult(String taskId){
    List<ServicesResponses> results = repository.findByTaskId(taskId);
    if(results.size()==0){
      return new ServicesResponses();
    }else{
      return results.get(0);
    }
  }

}
