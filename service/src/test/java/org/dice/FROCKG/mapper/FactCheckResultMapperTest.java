package org.dice.FROCKG.mapper;

import org.dice.FROCKG.data.dto.FactCheckResultDto;
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

  @Test
  public void FactCheckResultMapper_ShouldMappDtoToJson() {


    String expected =
        "{\"pathList\":[{\"path\":[{\"subject\":\"http://dbpedia.org/resource/Albert_Einstein\",\"object\":\"http://dbpedia.org/resource/Weimar_Republic\",\"property\":\"http://dbpedia.org/ontology/citizenship\"},{\"subject\":\"http://dbpedia.org/resource/Weimar_Republic\",\"object\":\"http://dbpedia.org/resource/Deutschlandlied\",\"property\":\"http://dbpedia.org/ontology/anthem\"},{\"subject\":\"http://dbpedia.org/resource/Germany\",\"object\":\"http://dbpedia.org/resource/Deutschlandlied\",\"property\":\"http://dbpedia.org/ontology/anthem\"}],\"pathScore\":0.4658725951883458,\"pathText\":\"Albert Einstein's citizenship is Weimar Republic. Weimar Republic's anthem is Deutschlandlied. Germany's anthem is Deutschlandlied.\"},{\"path\":[{\"subject\":\"http://dbpedia.org/resource/Albert_Einstein\",\"object\":\"http://dbpedia.org/resource/Heinrich_Friedrich_Weber\",\"property\":\"http://dbpedia.org/ontology/academicAdvisor\"},{\"subject\":\"http://dbpedia.org/resource/Heinrich_Friedrich_Weber\",\"object\":\"http://dbpedia.org/resource/Germany\",\"property\":\"http://dbpedia.org/ontology/nationality\"}],\"pathScore\":0.4577263691777928,\"pathText\":\"Albert Einstein's academic advisor is Heinrich Friedrich Weber. Heinrich Friedrich Weber's nationality is Germany.\"},{\"path\":[{\"subject\":\"http://dbpedia.org/resource/Albert_Einstein\",\"object\":\"http://dbpedia.org/resource/Free_State_of_Prussia\",\"property\":\"http://dbpedia.org/ontology/citizenship\"},{\"subject\":\"http://dbpedia.org/resource/Free_State_of_Prussia\",\"object\":\"http://dbpedia.org/resource/Berlin\",\"property\":\"http://dbpedia.org/ontology/capital\"},{\"subject\":\"http://dbpedia.org/resource/Berlin\",\"object\":\"http://dbpedia.org/resource/Germany\",\"property\":\"http://dbpedia.org/ontology/country\"}],\"pathScore\":0.5918341353917891,\"pathText\":\"Albert Einstein's citizenship is Free State of Prussia. Free State of Prussia's capital is Berlin. Berlin's country is Germany.\"},{\"path\":[{\"subject\":\"http://dbpedia.org/resource/Albert_Einstein\",\"object\":\"http://dbpedia.org/resource/Heinrich_Friedrich_Weber\",\"property\":\"http://dbpedia.org/ontology/academicAdvisor\"},{\"subject\":\"http://dbpedia.org/resource/Heinrich_Friedrich_Weber\",\"object\":\"http://dbpedia.org/resource/Germany\",\"property\":\"http://dbpedia.org/ontology/residence\"}],\"pathScore\":0.41932196447155173,\"pathText\":\"Albert Einstein's academic advisor is Heinrich Friedrich Weber. Heinrich Friedrich Weber's residence is Germany.\"},{\"path\":[{\"subject\":\"http://dbpedia.org/resource/Albert_Einstein\",\"object\":\"http://dbpedia.org/resource/Ulm\",\"property\":\"http://dbpedia.org/ontology/birthPlace\"},{\"subject\":\"http://dbpedia.org/resource/Ulm\",\"object\":\"http://dbpedia.org/resource/Germany\",\"property\":\"http://dbpedia.org/ontology/country\"}],\"pathScore\":0.81807681521125,\"pathText\":\"Albert Einstein's birth place is Ulm. Ulm's country is Germany.\"}],\"graphScore\":0.9875111204176672,\"inputTriple\":{\"subject\":\"http://dbpedia.org/resource/Albert_Einstein\",\"object\":\"http://dbpedia.org/resource/Germany\",\"property\":\"http://dbpedia.org/ontology/birthPlace\"},\"taskid\":\"541\",\"filedata\":null,\"defactoScore\":0.9978021978021978,\"complexProofs\":[{\"website\":\"https://en.wikipedia.org/wiki/Jost Winteler\",\"proofPhrase\":\"Winteler and Einstein also shared a mutual distrust of imperial Germany 's nationalism .\"},{\"website\":\"https://en.wikipedia.org/wiki/Einstein family\",\"proofPhrase\":\"Weissenhorn, Germany: Anton H. Konrad Verlag. pp. 220, 301, 334, 378, 383. \\\"Short life history: Elsa Einstein\\\".\"},{\"website\":\"https://en.wikipedia.org/wiki/Gustav Peter Bucky\",\"proofPhrase\":\"Einstein had also moved from Germany to the U.S. not long before that .\"},{\"website\":\"https://en.wikipedia.org/wiki/Shinichi Suzuki\",\"proofPhrase\":\"In Germany , he claimed to have spent time under the guardianship of Albert Einstein .\"},{\"website\":\"https://en.wikipedia.org/wiki/George S. Messersmith\",\"proofPhrase\":\"While he did not personally interview Albert Einstein , Messersmith cleared the way for the scientist to leave Germany .\"},{\"website\":\"https://en.wikipedia.org/wiki/Rhodes House\",\"proofPhrase\":\"Albert Einstein -- Honours , prizes and awards : Oxford University , Albert Einstein in the World Wide Web , Germany .\"},{\"website\":\"https://en.wikipedia.org/wiki/Heinrich Sontheim\",\"proofPhrase\":\"Weissenhorn, Germany: Anton H. Konrad Verlag. pp. 197–198, 378–379. [in German] Tänzer, Arnold (1931). \\\"Der Stammbaum Prof. Albert Einsteins\\\".\"},{\"website\":\"https://en.wikipedia.org/wiki/SS Belgenland (1914)\",\"proofPhrase\":\"http://www.shapell.org/manuscript/einstein-renounces-german-citizenship-becomes-outlaw-in-nazi-germany Frank C. Bowen The Shipbreaking Industry .\"},{\"website\":\"https://en.wikipedia.org/wiki/Jürgen Neffe\",\"proofPhrase\":\"In 2005 , Neffe published a biography on Albert Einstein , which ranked eighth among non-fiction bestsellers that year in Germany .\"},{\"website\":\"https://en.wikipedia.org/wiki/Jürgen Neffe\",\"proofPhrase\":\": Made in Germany '21. Hoffmann und Campe, Hamburg 2004, ISBN 3-455-10417-7 Einstein – eine Biographie.\"},{\"website\":\"https://en.wikipedia.org/wiki/Max Talmey\",\"proofPhrase\":\"Talmey was then attending Medical School in Germany. Talmey was a weekly lunch guest of Einstein's family.\"},{\"website\":\"https://en.wikipedia.org/wiki/Jürgen Neffe\",\"proofPhrase\":\"Vorbild Einstein - Gedanken zu Wissenschaftspopularisierung und Technikskepsis. In: Frank-Walter Steinmeier, Matthias Machnig (HG.) : Made in Germany '21.\"},{\"website\":\"https://en.wikipedia.org/wiki/Harold Urey\",\"proofPhrase\":\"At the conclusion of his stay , his traveled to Germany , where his met Albert Einstein and James Franck .\"},{\"website\":\"https://en.wikipedia.org/wiki/October 17\",\"proofPhrase\":\"1933 -- Albert Einstein flees Nazi Germany and moves to the United States .\"},{\"website\":\"https://en.wikipedia.org/wiki/Hans Albert Einstein\",\"proofPhrase\":\"Einstein 's father , Albert , left Germany in 1933 to escape the virulently antisemitic Nazi threat .\"}],\"subject\":\"http://dbpedia.org/resource/Albert_Einstein\",\"predicate\":\"http://dbpedia.org/ontology/birthPlace\",\"object\":\"http://dbpedia.org/resource/Germany\",\"corpusFactCheckIsSucceed\":true,\"corpusFactCheckErrorMessage\":\"msg\",\"graphBaseFactCheckIsSucceed\":true,\"graphBaseFactCheckErrorMessage\":\"msg\"}";

    try {
      FactCheckResultDto factCheckResult = factCheckMapper.ToDto(expected);
      String actual = factCheckMapper.ToJson(factCheckResult);
      Assertions.assertEquals(expected, actual);
    } catch (Exception exp) {
      Assertions.fail("Exception " + exp);
    }
  }

  @Test
  public void FactCheckResultMapper_IfServicesAreDown_ShouldMappDtoToJson() {


    String expected =
        "{\"pathList\":null,\"graphScore\":0.0,\"inputTriple\":null,\"taskid\":null,\"filedata\":null,\"defactoScore\":0.0,\"complexProofs\":null,\"subject\":null,\"predicate\":null,\"object\":null,\"corpusFactCheckIsSucceed\":false,\"corpusFactCheckErrorMessage\":\"msg\",\"graphBaseFactCheckIsSucceed\":false,\"graphBaseFactCheckErrorMessage\":\"msg\"}";

    try {
      FactCheckResultDto factCheckResult = factCheckMapper.ToDto(expected);
      String actual = factCheckMapper.ToJson(factCheckResult);
      Assertions.assertEquals(expected, actual);
    } catch (Exception exp) {
      Assertions.fail("Exception " + exp);
    }
  }

}
