package org.dice.frockg.api;

import static org.assertj.core.api.Assertions.assertThat;
import org.dice.frockg.data.dto.FactCheckResultDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class FactCheckControllerTest {

  @LocalServerPort
  private int port;

  @Autowired
  private TestRestTemplate restTemplate;

  @Test
  public void FactCheckController_WhenCallDefaultEndPoint_ShouldReturnDefaultMessage()
      throws Exception {
    assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/api/v1/default",
        String.class)).contains("OK!");
  }

  @Test
  public void FactCheckController_WhenCallCopaaltestEndPoint_ShouldReturnDefaultMessage()
      throws Exception {
    assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/api/v1/copaaltest",
        String.class)).contains("OK!");
  }

  @Test
  public void FactCheckController_WhenCallcheckFactWithNotValidParameters_ShouldReturnErrorMessage()
      throws Exception {
    String url = "http://localhost:" + port
        + "/api/v1/checkFact?subject=a&object=b&predicate=c&isVirtualType=False&pathlength=5";
    ResponseEntity<FactCheckResultDto> resp =
        restTemplate.getForEntity(url, FactCheckResultDto.class);
    Assertions.assertEquals(resp.getStatusCode(), HttpStatus.BAD_REQUEST);
  }

  @Test
  public void FactCheckController_WhenCallcheckFactWithValidParameters_ShouldReturnResult()
      throws Exception {
    String url = "http://localhost:" + port
        + "/api/v1/checkFact?subject=http://dbpedia.org/resource/Bill_Gates&object=http://dbpedia.org/resource/United_States&predicate=http://dbpedia.org/ontology/nationality&isVirtualType=False&pathlength=2";

    ResponseEntity<FactCheckResultDto> resp =
        restTemplate.getForEntity(url, FactCheckResultDto.class);

    Assertions.assertEquals(resp.getStatusCode(), HttpStatus.OK);
  }

}
