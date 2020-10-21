package org.dice.FROCKG.customvalidator;

import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import org.dice.FROCKG.data.dto.FactCheckRequestDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class FactCheckDtoValidatorTest {

  private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

  @Test
  public void FactCheckDtoValidator_WhenCallcheckFactWithValidParameters_ShouldNotReturnError() {
    FactCheckRequestDto r = new FactCheckRequestDto();
    r.setObject("http://dbpedia.org/resource/a");
    r.setSubject("http://dbpedia.org/resource/b");
    r.setPredicate("http://dbpedia.org/resource/c");
    r.setIsvirtualtype(false);
    r.setPathlength(2);
    Set<ConstraintViolation<FactCheckRequestDto>> violations = validator.validate(r);
    Assertions.assertEquals(0, violations.size());
  }

  @Test
  public void FactCheckDtoValidator_WhenCallcheckFactWithNotValidSubject_ShouldReturnError() {
    FactCheckRequestDto r = new FactCheckRequestDto();
    r.setObject("a");
    r.setSubject("http://dbpedia.org/resource/b");
    r.setPredicate("http://dbpedia.org/resource/c");
    r.setIsvirtualtype(false);
    r.setPathlength(2);
    Set<ConstraintViolation<FactCheckRequestDto>> violations = validator.validate(r);
    Assertions.assertEquals(1, violations.size());
  }

  @Test
  public void FactCheckDtoValidator_WhenCallcheckFactWithNotValidObject_ShouldReturnError() {
    FactCheckRequestDto r = new FactCheckRequestDto();
    r.setObject("http://dbpedia.org/resource/a");
    r.setSubject("b");
    r.setPredicate("http://dbpedia.org/resource/c");
    r.setIsvirtualtype(false);
    r.setPathlength(2);
    Set<ConstraintViolation<FactCheckRequestDto>> violations = validator.validate(r);
    Assertions.assertEquals(1, violations.size());
  }

  @Test
  public void FactCheckDtoValidator_WhenCallcheckFactWithNotValidPredicate_ShouldReturnError() {
    FactCheckRequestDto r = new FactCheckRequestDto();
    r.setObject("http://dbpedia.org/resource/a");
    r.setSubject("http://dbpedia.org/resource/b");
    r.setPredicate("c");
    r.setIsvirtualtype(false);
    r.setPathlength(2);
    Set<ConstraintViolation<FactCheckRequestDto>> violations = validator.validate(r);
    Assertions.assertEquals(1, violations.size());
  }

  @Test
  public void FactCheckDtoValidator_WhenCallcheckFactWithNotValidPathLenght_ShouldReturnError() {
    FactCheckRequestDto r = new FactCheckRequestDto();
    r.setObject("http://dbpedia.org/resource/a");
    r.setSubject("http://dbpedia.org/resource/b");
    r.setPredicate("http://dbpedia.org/resource/c");
    r.setIsvirtualtype(false);
    r.setPathlength(4);
    Set<ConstraintViolation<FactCheckRequestDto>> violations = validator.validate(r);
    Assertions.assertEquals(1, violations.size());
  }

  @Test
  public void FactCheckDtoValidator_WhenCallcheckFactWithNullPathLenght_ShouldNotReturnError() {
    FactCheckRequestDto r = new FactCheckRequestDto();
    r.setObject("http://dbpedia.org/resource/a");
    r.setSubject("http://dbpedia.org/resource/b");
    r.setPredicate("http://dbpedia.org/resource/c");
    r.setIsvirtualtype(false);
    Set<ConstraintViolation<FactCheckRequestDto>> violations = validator.validate(r);
    Assertions.assertEquals(0, violations.size());
  }
}
