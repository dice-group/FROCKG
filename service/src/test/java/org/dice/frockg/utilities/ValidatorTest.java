package org.dice.frockg.utilities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ValidatorTest {
  @Test
  public void IsUriValid_ShouldResponceCorrec() {
    evaluation(false, "");
    evaluation(false, "1");
    evaluation(false, "a");
    evaluation(false, "http");
    evaluation(false, "://a");
    // evaluation(false, "http://a");
    // evaluation(false, "a.b");
    evaluation(true, "http://a.b");
    evaluation(true, "http://a.b/c");
    evaluation(true, "http://a.b:90/a");
    evaluation(true, "http://a.b////c");
    evaluation(true, "https://a.b/f");
    evaluation(false, "https://");
  }

  public void evaluation(boolean exbected, String uri) {
    boolean acctual = Validator.IsUriValid(uri);
    // System.out.println(uri + " is : " + acctual);
    Assertions.assertEquals(exbected, acctual);
  }
}
