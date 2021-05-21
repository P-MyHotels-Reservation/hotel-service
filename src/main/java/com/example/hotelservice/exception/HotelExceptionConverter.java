package com.example.hotelservice.exception;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class HotelExceptionConverter {

  private final ObjectMapper mapper = new ObjectMapper();

  public JsonNode toJsonNode(HotelErrorResponse errorDetail, String extraError) {
    ObjectNode rootNode = this.mapper.createObjectNode();
    rootNode.put("errorCode", errorDetail.getErrorCode());
    rootNode.put("errorMessage", errorDetail.getMessage().concat(extraError));
    return rootNode;
  }

}
