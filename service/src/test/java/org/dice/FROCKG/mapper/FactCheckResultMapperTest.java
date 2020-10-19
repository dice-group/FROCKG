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
        "{\"pathList\":[{\"path\":[{\"subject\":\"http://dbpedia.org/resource/Joe_Biden\",\"object\":\"http://dbpedia.org/resource/Barack_Obama\",\"property\":\"http://dbpedia.org/ontology/president\"},{\"subject\":\"http://dbpedia.org/resource/United_States\",\"object\":\"http://dbpedia.org/resource/Joe_Biden\",\"property\":\"http://dbpedia.org/ontology/leader\"}],\"pathScore\":0.3551597263223194,\"pathText\":\"Not available\"},{\"path\":[{\"subject\":\"http://dbpedia.org/resource/Charles_Bolden\",\"object\":\"http://dbpedia.org/resource/Barack_Obama\",\"property\":\"http://dbpedia.org/ontology/president\"},{\"subject\":\"http://dbpedia.org/resource/Charles_Bolden\",\"object\":\"http://dbpedia.org/resource/United_States\",\"property\":\"http://dbpedia.org/ontology/country\"}],\"pathScore\":0.4571163088506346,\"pathText\":\"Not available\"},{\"path\":[{\"subject\":\"http://dbpedia.org/resource/Kentucky_Air_National_Guard\",\"object\":\"http://dbpedia.org/resource/Barack_Obama\",\"property\":\"http://dbpedia.org/ontology/commander\"},{\"subject\":\"http://dbpedia.org/resource/Kentucky_Air_National_Guard\",\"object\":\"http://dbpedia.org/resource/United_States\",\"property\":\"http://dbpedia.org/ontology/country\"}],\"pathScore\":0.2855117457884706,\"pathText\":\"Not available\"},{\"path\":[{\"subject\":\"http://dbpedia.org/resource/East_Greenwich_High_School\",\"object\":\"http://dbpedia.org/resource/Barack_Obama\",\"property\":\"http://dbpedia.org/ontology/alumni\"},{\"subject\":\"http://dbpedia.org/resource/East_Greenwich_High_School\",\"object\":\"http://dbpedia.org/resource/United_States\",\"property\":\"http://dbpedia.org/ontology/country\"}],\"pathScore\":0.41966650565862706,\"pathText\":\"Not available\"},{\"path\":[{\"subject\":\"http://dbpedia.org/resource/Edward_Jurith\",\"object\":\"http://dbpedia.org/resource/Barack_Obama\",\"property\":\"http://dbpedia.org/ontology/president\"},{\"subject\":\"http://dbpedia.org/resource/Edward_Jurith\",\"object\":\"http://dbpedia.org/resource/United_States\",\"property\":\"http://dbpedia.org/ontology/deathPlace\"}],\"pathScore\":0.42657347900304476,\"pathText\":\"Not available\"},{\"path\":[{\"subject\":\"http://dbpedia.org/resource/Nuevo_(Bayamón)\",\"object\":\"http://dbpedia.org/resource/Barack_Obama\",\"property\":\"http://dbpedia.org/ontology/leaderName\"},{\"subject\":\"http://dbpedia.org/resource/Nuevo_(Bayamón)\",\"object\":\"http://dbpedia.org/resource/United_States\",\"property\":\"http://dbpedia.org/ontology/isPartOf\"}],\"pathScore\":0.2406815985288701,\"pathText\":\"Not available\"},{\"path\":[{\"subject\":\"http://dbpedia.org/resource/Marc_Grossman\",\"object\":\"http://dbpedia.org/resource/Barack_Obama\",\"property\":\"http://dbpedia.org/ontology/president\"},{\"subject\":\"http://dbpedia.org/resource/Marc_Grossman\",\"object\":\"http://dbpedia.org/resource/United_States\",\"property\":\"http://dbpedia.org/ontology/nationality\"}],\"pathScore\":0.5082047393147204,\"pathText\":\"Not available\"},{\"path\":[{\"subject\":\"http://dbpedia.org/resource/Barack_Obama\",\"object\":\"http://dbpedia.org/resource/Democratic_Party_(United_States)\",\"property\":\"http://dbpedia.org/ontology/party\"},{\"subject\":\"http://dbpedia.org/resource/Democratic_Party_(United_States)\",\"object\":\"http://dbpedia.org/resource/United_States\",\"property\":\"http://dbpedia.org/ontology/country\"}],\"pathScore\":0.581412601052446,\"pathText\":\"Not available\"},{\"path\":[{\"subject\":\"http://dbpedia.org/resource/Michael_McFaul\",\"object\":\"http://dbpedia.org/resource/Barack_Obama\",\"property\":\"http://dbpedia.org/ontology/president\"},{\"subject\":\"http://dbpedia.org/resource/Michael_McFaul\",\"object\":\"http://dbpedia.org/resource/United_States\",\"property\":\"http://dbpedia.org/ontology/residence\"}],\"pathScore\":0.3781072730329282,\"pathText\":\"Not available\"},{\"path\":[{\"subject\":\"http://dbpedia.org/resource/William_B._Pollard_III\",\"object\":\"http://dbpedia.org/resource/Barack_Obama\",\"property\":\"http://dbpedia.org/ontology/appointer\"},{\"subject\":\"http://dbpedia.org/resource/William_B._Pollard_III\",\"object\":\"http://dbpedia.org/resource/United_States\",\"property\":\"http://dbpedia.org/ontology/nationality\"}],\"pathScore\":0.33855728951638286,\"pathText\":\"Not available\"},{\"path\":[{\"subject\":\"http://dbpedia.org/resource/Carol_Browner\",\"object\":\"http://dbpedia.org/resource/Barack_Obama\",\"property\":\"http://dbpedia.org/ontology/president\"},{\"subject\":\"http://dbpedia.org/resource/Carol_Browner\",\"object\":\"http://dbpedia.org/resource/United_States\",\"property\":\"http://dbpedia.org/ontology/birthPlace\"}],\"pathScore\":0.43305476778495794,\"pathText\":\"Not available\"},{\"path\":[{\"subject\":\"http://dbpedia.org/resource/Barack_Obama\",\"object\":\"http://dbpedia.org/resource/Illinois\",\"property\":\"http://dbpedia.org/ontology/region\"},{\"subject\":\"http://dbpedia.org/resource/Illinois\",\"object\":\"http://dbpedia.org/resource/United_States\",\"property\":\"http://dbpedia.org/ontology/country\"}],\"pathScore\":0.5112074443210004,\"pathText\":\"Not available\"},{\"path\":[{\"subject\":\"http://dbpedia.org/resource/Barack_Obama\",\"object\":\"http://dbpedia.org/resource/Hawaii\",\"property\":\"http://dbpedia.org/ontology/birthPlace\"},{\"subject\":\"http://dbpedia.org/resource/Hawaii\",\"object\":\"http://dbpedia.org/resource/United_States\",\"property\":\"http://dbpedia.org/ontology/country\"}],\"pathScore\":0.6821697997789379,\"pathText\":\"Not available\"},{\"path\":[{\"subject\":\"http://dbpedia.org/resource/Eleanor_Louise_Ross\",\"object\":\"http://dbpedia.org/resource/Barack_Obama\",\"property\":\"http://dbpedia.org/ontology/appointer\"},{\"subject\":\"http://dbpedia.org/resource/Eleanor_Louise_Ross\",\"object\":\"http://dbpedia.org/resource/United_States\",\"property\":\"http://dbpedia.org/ontology/residence\"}],\"pathScore\":0.29909085699173454,\"pathText\":\"Not available\"},{\"path\":[{\"subject\":\"http://dbpedia.org/resource/Susan_Richard_Nelson\",\"object\":\"http://dbpedia.org/resource/Barack_Obama\",\"property\":\"http://dbpedia.org/ontology/appointer\"},{\"subject\":\"http://dbpedia.org/resource/Susan_Richard_Nelson\",\"object\":\"http://dbpedia.org/resource/United_States\",\"property\":\"http://dbpedia.org/ontology/birthPlace\"}],\"pathScore\":0.2918406748022939,\"pathText\":\"Not available\"},{\"path\":[{\"subject\":\"http://dbpedia.org/resource/Barack_Obama\",\"object\":\"http://dbpedia.org/resource/Columbia_College,_Columbia_University\",\"property\":\"http://dbpedia.org/ontology/almaMater\"},{\"subject\":\"http://dbpedia.org/resource/Columbia_College,_Columbia_University\",\"object\":\"http://dbpedia.org/resource/United_States\",\"property\":\"http://dbpedia.org/ontology/country\"}],\"pathScore\":0.6388512932889593,\"pathText\":\"Not available\"}],\"graphScore\":0.9999155010717403,\"inputTriple\":{\"subject\":\"http://dbpedia.org/resource/Barack_Obama\",\"object\":\"http://dbpedia.org/resource/United_States\",\"property\":\"http://dbpedia.org/ontology/nationality\"}}";

    try {
      FactCheckResultDto factCheckResult = factCheckMapper.ToDto(expected);
      String actual = factCheckMapper.ToJson(factCheckResult);
      Assertions.assertEquals(expected, actual);
    } catch (Exception exp) {
      Assertions.fail("Exception " + exp);
    }
  }
}
