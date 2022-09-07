package org.dice.frockg.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class FactCheckServiceTest {
  @Autowired
  FactCheckService service;

  @Test
  void FactCheck_ArgumentsAreValid_ShouldNotThrowError() {
    String object = "http://dbpedia.org/resource/United_States";
    String subject = "http://dbpedia.org/resource/Bill_Gates";
    String predicate = "http://dbpedia.org/ontology/nationalityt";

    Assertions.assertDoesNotThrow(
        () -> {
          service.requestFactChecking(subject, object, predicate);
        });
  }
}
