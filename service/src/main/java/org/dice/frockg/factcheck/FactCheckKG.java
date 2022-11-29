package org.dice.frockg.factcheck;

import java.util.List;
import java.util.concurrent.Callable;

import org.dice.frockg.data.model.ServicesResponses;
import org.dice.frockg.data.repository.IFacadeResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class FactCheckKG implements Callable<Void> {
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

  private void KGFactCheck(String subject, String object, String predicate) {
    ServicesResponses toSave = new ServicesResponses();

    try {
      String url = serverURL + "/validate"
              + "?subject={subject}&property={predicate}&object={object}&verbalize=true";

      RestTemplate restTemplate = new RestTemplate();
      String result = restTemplate.getForObject(url, String.class, subject, predicate, object);
      List<ServicesResponses> existData = repository.findByTaskId(taskId);
      if (existData.size() > 0) {
        toSave = existData.get(0);
      } else {
        toSave.setTaskId(taskId);
        toSave.setSubject(subject);
        toSave.setPredicate(predicate);
        toSave.setObject(object);
      }
      toSave.setCopaalResult(result);
      toSave.setCopaalFacedError(false);
      toSave.setCopaalResultIsReady(true);
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
      toSave.setCopaalFacedError(true);
      toSave.setCopaalResultIsReady(false);
      toSave.setCopaalError(ex.getMessage());
      repository.save(toSave);
    }
  }


  @Override
  public Void call()  {
    try {
          KGFactCheck(subject, object, predicate);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return null;
  }
}
