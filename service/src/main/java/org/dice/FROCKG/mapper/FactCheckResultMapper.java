package org.dice.FROCKG.mapper;

import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dice.FROCKG.data.dto.FactCheckResultDto;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class FactCheckResultMapper {

  Logger logger = LogManager.getLogger(FactCheckResultMapper.class);

  public FactCheckResultDto ToDto(String json) throws Exception {
    ObjectMapper objectMapper = new ObjectMapper();
    try {
      FactCheckResultDto factCheckResultDto =
          objectMapper.readValue(json, FactCheckResultDto.class);
      return factCheckResultDto;
    } catch (IOException e) {
      logger.error(e.getMessage());
      throw new Exception(e);
    }
  }

  public String ToJson(FactCheckResultDto input) throws Exception {
    ObjectMapper objectMapper = new ObjectMapper();
    try {
      return objectMapper.writeValueAsString(input);
    } catch (IOException e) {
      logger.error(e.getMessage());
      throw new Exception(e);
    }
  }
}
