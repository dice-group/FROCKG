package org.dice.frockg.mapper;

import org.dice.frockg.data.dto.FactCheckResultDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@SpringBootTest
public class FactCheckResultMapperTest {
  @Autowired
  FactCheckResultMapper factCheckMapper;

  @Test
  public void FactCheckResultMapper_ShouldMappJsonToDto() {

    PodamFactory factory = new PodamFactoryImpl();
    FactCheckResultDto expected = factory.manufacturePojo(FactCheckResultDto.class);

    try {
      String jsonResult = factCheckMapper.ToJson(expected);
      FactCheckResultDto actual = factCheckMapper.ToDto(jsonResult);
      Assertions.assertEquals(expected, actual);
    } catch (Exception exp) {
      Assertions.fail("Exception " + exp);
    }
  }

/*  @Test
  public void FactCheckResultMapper_ShouldMappDtoToJson() {


    String expected =
        "{\"pathList\":[{\"path\":[{\"subject\":\"http://dbpedia.org/resource/Albert_Einstein\",\"object\":\"http://dbpedia.org/resource/Weimar_Republic\",\"property\":\"http://dbpedia.org/ontology/citizenship\"},{\"subject\":\"http://dbpedia.org/resource/Weimar_Republic\",\"object\":\"http://dbpedia.org/resource/Deutschlandlied\",\"property\":\"http://dbpedia.org/ontology/anthem\"},{\"subject\":\"http://dbpedia.org/resource/Germany\",\"object\":\"http://dbpedia.org/resource/Deutschlandlied\",\"property\":\"http://dbpedia.org/ontology/anthem\"}],\"pathScore\":0.4658725951883458,\"pathText\":\"Albert Einstein's citizenship is Weimar Republic. Weimar Republic's anthem is Deutschlandlied. Germany's anthem is Deutschlandlied.\"},{\"path\":[{\"subject\":\"http://dbpedia.org/resource/Albert_Einstein\",\"object\":\"http://dbpedia.org/resource/Heinrich_Friedrich_Weber\",\"property\":\"http://dbpedia.org/ontology/academicAdvisor\"},{\"subject\":\"http://dbpedia.org/resource/Heinrich_Friedrich_Weber\",\"object\":\"http://dbpedia.org/resource/Germany\",\"property\":\"http://dbpedia.org/ontology/nationality\"}],\"pathScore\":0.4577263691777928,\"pathText\":\"Albert Einstein's academic advisor is Heinrich Friedrich Weber. Heinrich Friedrich Weber's nationality is Germany.\"},{\"path\":[{\"subject\":\"http://dbpedia.org/resource/Albert_Einstein\",\"object\":\"http://dbpedia.org/resource/Free_State_of_Prussia\",\"property\":\"http://dbpedia.org/ontology/citizenship\"},{\"subject\":\"http://dbpedia.org/resource/Free_State_of_Prussia\",\"object\":\"http://dbpedia.org/resource/Berlin\",\"property\":\"http://dbpedia.org/ontology/capital\"},{\"subject\":\"http://dbpedia.org/resource/Berlin\",\"object\":\"http://dbpedia.org/resource/Germany\",\"property\":\"http://dbpedia.org/ontology/country\"}],\"pathScore\":0.5918341353917891,\"pathText\":\"Albert Einstein's citizenship is Free State of Prussia. Free State of Prussia's capital is Berlin. Berlin's country is Germany.\"},{\"path\":[{\"subject\":\"http://dbpedia.org/resource/Albert_Einstein\",\"object\":\"http://dbpedia.org/resource/Heinrich_Friedrich_Weber\",\"property\":\"http://dbpedia.org/ontology/academicAdvisor\"},{\"subject\":\"http://dbpedia.org/resource/Heinrich_Friedrich_Weber\",\"object\":\"http://dbpedia.org/resource/Germany\",\"property\":\"http://dbpedia.org/ontology/residence\"}],\"pathScore\":0.41932196447155173,\"pathText\":\"Albert Einstein's academic advisor is Heinrich Friedrich Weber. Heinrich Friedrich Weber's residence is Germany.\"},{\"path\":[{\"subject\":\"http://dbpedia.org/resource/Albert_Einstein\",\"object\":\"http://dbpedia.org/resource/Ulm\",\"property\":\"http://dbpedia.org/ontology/birthPlace\"},{\"subject\":\"http://dbpedia.org/resource/Ulm\",\"object\":\"http://dbpedia.org/resource/Germany\",\"property\":\"http://dbpedia.org/ontology/country\"}],\"pathScore\":0.81807681521125,\"pathText\":\"Albert Einstein's birth place is Ulm. Ulm's country is Germany.\"}],\"graphScore\":0.9875111204176672,\"inputTriple\":{\"subject\":\"http://dbpedia.org/resource/Albert_Einstein\",\"object\":\"http://dbpedia.org/resource/Germany\",\"property\":\"http://dbpedia.org/ontology/birthPlace\"},\"taskid\":\"541\",\"filedata\":null,\"defactoScore\":0.9978021978021978,\"complexProofs\":[{\"website\":\"https://en.wikipedia.org/wiki/Gustav Peter Bucky\",\"proofPhrase\":\"Einstein had also moved from Germany to the U.S. not long before that .\",\"trustworthiness\": 0.5851062861709623,},{\"website\":\"https://en.wikipedia.org/wiki/Shinichi Suzuki\",\"proofPhrase\":\"In Germany , he claimed to have spent time under the guardianship of Albert Einstein .\",\"trustworthiness\": 0.5851062861709623}],\"subject\":\"http://dbpedia.org/resource/Albert_Einstein\",\"predicate\":\"http://dbpedia.org/ontology/birthPlace\",\"object\":\"http://dbpedia.org/resource/Germany\",\"corpusFactCheckIsSucceed\":true,\"corpusFactCheckErrorMessage\":\"msg\",\"graphBaseFactCheckIsSucceed\":true,\"graphBaseFactCheckErrorMessage\":\"msg\"}";

    try {
      FactCheckResultDto factCheckResult = factCheckMapper.ToDto(expected);
      String actual = factCheckMapper.ToJson(factCheckResult);
      Assertions.assertEquals(expected, actual);
    } catch (Exception exp) {
      Assertions.fail("Exception " + exp);
    }
  }*/

  @Test
  public void FactCheckResultMapper_IfServicesAreDown_ShouldMapDtoToJson() {


    String expected =
        "{\"facadeScore\":0.0,\"explanation\":null,\"pathList\":null,\"graphScore\":0.0,\"inputTriple\":null,\"taskid\":null,\"filedata\":null,\"defactoScore\":0.0,\"complexProofs\":null,\"subject\":null,\"predicate\":null,\"object\":null,\"corpusFactCheckIsSucceed\":false,\"corpusFactCheckErrorMessage\":\"msg\",\"graphBaseFactCheckIsSucceed\":false,\"graphBaseFactCheckErrorMessage\":\"msg\"}";

    try {
      FactCheckResultDto factCheckResult = factCheckMapper.ToDto(expected);
      String actual = factCheckMapper.ToJson(factCheckResult);
      Assertions.assertEquals(expected, actual);
    } catch (Exception exp) {
      Assertions.fail("Exception " + exp);
    }
  }

}
