package org.dice.FROCKG.Service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class FactCheckServiceTest {
  @Autowired FactCheckService service ;

  @Test
  void factCheck_ObjectNotURI_ShouldThrowError() {   
    String subject = "http://example.com/test";
    String predicate = "http://example.com/test";
    boolean isVirtualType = false;
    int pathLength = 2;

    Assertions.assertThrows(
        IllegalArgumentException.class,
        () -> {
          service.checkFact(subject, "", predicate, isVirtualType, pathLength);
        });

    Assertions.assertThrows(
        IllegalArgumentException.class,
        () -> {
          service.checkFact(subject, "test", predicate, isVirtualType, pathLength);
        });

    Assertions.assertThrows(
        IllegalArgumentException.class,
        () -> {
          service.checkFact(subject, "wikidata.com/test", predicate, isVirtualType, pathLength);
        });

    // is it a valid URI ?
    Assertions.assertThrows(
        IllegalArgumentException.class,
        () -> {
          service.checkFact(
              subject,
              "foo://example.com:8042/over/there?name=ferret#nose",
              predicate,
              isVirtualType,
              pathLength);
        });
  }

  @Test
  void factCheck_SubjectNotURI_ShouldThrowError() { 
    String object = "http://example.com/test";
    String predicate = "http://example.com/test";
    boolean isVirtualType = false;
    int pathLength = 2;

    Assertions.assertThrows(
        IllegalArgumentException.class,
        () -> {
          service.checkFact("", object, predicate, isVirtualType, pathLength);
        });

    Assertions.assertThrows(
        IllegalArgumentException.class,
        () -> {
          service.checkFact("test", object, predicate, isVirtualType, pathLength);
        });

    Assertions.assertThrows(
        IllegalArgumentException.class,
        () -> {
          service.checkFact("wikidata.com/test", object, predicate, isVirtualType, pathLength);
        });

    // is it a valid URI ?
    Assertions.assertThrows(
        IllegalArgumentException.class,
        () -> {
          service.checkFact(
              "foo://example.com:8042/over/there?name=ferret#nose",
              object,
              predicate,
              isVirtualType,
              pathLength);
        });
  }

  @Test
  void factCheck_PredicateNotURI_ShouldThrowError() {
    String object = "http://example.com/test";
    String subject = "http://example.com/test";
    boolean isVirtualType = false;
    int pathLength = 2;

    Assertions.assertThrows(
        IllegalArgumentException.class,
        () -> {
          service.checkFact(subject, object, "", isVirtualType, pathLength);
        });

    Assertions.assertThrows(
        IllegalArgumentException.class,
        () -> {
          service.checkFact(subject, object, "test", isVirtualType, pathLength);
        });

    Assertions.assertThrows(
        IllegalArgumentException.class,
        () -> {
          service.checkFact(subject, object, "wikidata.com/test", isVirtualType, pathLength);
        });

    // is it a valid URI ?
    Assertions.assertThrows(
        IllegalArgumentException.class,
        () -> {
          service.checkFact(
              subject,
              object,
              "foo://example.com:8042/over/there?name=ferret#nose",
              isVirtualType,
              pathLength);
        });
  }

  @Test
  void factCheck_PathLengthNotValid_ShouldThrowError() {
    String object = "http://example.com/test";
    String subject = "http://example.com/test";
    String predicate = "http://example.com/test";

    boolean isVirtualType = false;

    Assertions.assertThrows(
        IllegalArgumentException.class,
        () -> {
          service.checkFact(subject, object, predicate, isVirtualType, 5);
        });
  }

  @Test
  void factCheck_ArgumentsAreValid_ShouldNotThrowError() {
    String object = "http://dbpedia.org/resource/United_States";
    String subject = "http://dbpedia.org/resource/Bill_Gates";
    String predicate = "http://dbpedia.org/ontology/nationalityt";
    boolean isVirtualType = false;
    int pathLength = 2;

    Assertions.assertDoesNotThrow(
        () -> {
          service.checkFact(subject, object, predicate, isVirtualType, pathLength);
        });
  }
}
