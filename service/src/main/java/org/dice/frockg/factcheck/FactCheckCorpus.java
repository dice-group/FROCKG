package org.dice.frockg.factcheck;

import java.util.List;
import java.util.concurrent.Callable;

import org.dice.frockg.data.model.ServicesResponses;
import org.dice.frockg.data.repository.IFacadeResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class FactCheckCorpus implements Callable<Void> {
  private String subject;
  private String object;
  private String predicate;
  private String serverURL;
  private String taskId;

  @Autowired
  IFacadeResultRepository repository;



  public void setValues(String subject, String object, String predicate, String serverURL, String taskId) {
    this.subject = subject;
    this.object = object;
    this.predicate = predicate;
    this.serverURL = serverURL;
    this.taskId = taskId;
  }

  private void CorpusFactCheck(String subject, String object, String predicate, String serverURL) {
    ServicesResponses toSave = new ServicesResponses();

    try {
      String url = serverURL + "/checkfact?subject={subject}&object={object}&predicate={predicate}";
      RestTemplate restTemplate = new RestTemplate();
      String result = restTemplate.getForObject(url, String.class, subject, object, predicate);

      List<ServicesResponses> existData = repository.findByTaskId(taskId);
      if (existData.size() > 0) {
        toSave = existData.get(0);
      } else {
        toSave.setTaskId(taskId);
        toSave.setSubject(subject);
        toSave.setPredicate(predicate);
        toSave.setObject(object);
      }
      toSave.setFactcheckResult(result);
      toSave.setFactcheckFacedError(false);
      toSave.setFactcheckResultIsReady(true);

      repository.save(toSave);
    }catch (Exception ex){
      List<ServicesResponses> existData = repository.findByTaskId(taskId);
      if (existData.size() > 0) {
        toSave = existData.get(0);
      } else {
        toSave.setTaskId(taskId);
        toSave.setSubject(subject);
        toSave.setPredicate(predicate);
        toSave.setObject(object);
      }
      toSave.setFactcheckFacedError(true);
      toSave.setFactcheckError(ex.getMessage());
      toSave.setFactcheckResultIsReady(false);
      repository.save(toSave);
    }
  }

  @Override
  public Void call() {
    try {
       CorpusFactCheck(subject, object, predicate, serverURL);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return null;
  }

}
