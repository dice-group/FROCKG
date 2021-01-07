package org.dice.FROCKG.mapper;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dice.FROCKG.data.dto.FactCheckResultDto;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class FactCheckResultMapper {

  private static final Logger logger = LogManager.getLogger(FactCheckResultMapper.class);

  public FactCheckResultDto ToDto(String json) {
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    try {
      FactCheckResultDto factCheckResultDto =
          objectMapper.readValue(json, FactCheckResultDto.class);
      return factCheckResultDto;
    } catch (IOException e) {
      logger.error(e.getMessage());
      return null;
    }
  }

  public FactCheckResultDto ToDto(List<String> jsons) {
    List<FactCheckResultDto> results = new LinkedList<FactCheckResultDto>();

    for (String s : jsons) {
      results.add(ToDto(s));
    }

    return Merge(results);
  }

  private FactCheckResultDto Merge(List<FactCheckResultDto> results) {
    FactCheckResultDto mergedResult = new FactCheckResultDto();
    for (FactCheckResultDto result : results) {
      mergedResult.updateIfNotNull(result);
    }
    return mergedResult;
  }


  public String ToJson(FactCheckResultDto input) {
    ObjectMapper objectMapper = new ObjectMapper();
    try {
      return objectMapper.writeValueAsString(input);
    } catch (IOException e) {
      logger.error(e.getMessage());
      return "";
    }
  }
}
