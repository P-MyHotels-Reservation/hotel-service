package com.example.hotelservice.controller;

import com.example.hotelservice.model.entity.HotelEntity;
import com.example.hotelservice.model.entity.RoomEntity;
import com.example.hotelservice.model.request.HotelCreatedRequest;
import com.example.hotelservice.model.request.HotelUpdatedRequest;
import com.example.hotelservice.model.request.RoomCreatedRequest;
import com.example.hotelservice.model.request.RoomUpdatedRequest;
import com.example.hotelservice.model.response.HotelResponse;
import com.example.hotelservice.model.response.RoomResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping("/api/room")
public interface IRoomController {

  /**
   * This controller is used to create room in the database.
   *
   * @param room Room Created Requests {@link RoomCreatedRequest}
   * @return Room Response {@link RoomResponse}
   */
  @PostMapping
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "created room",
          content = {@Content(mediaType = "application/json",
              schema = @Schema(implementation = RoomEntity.class))}),
      @ApiResponse(responseCode = "400", description = "Invalid field",
          content = @Content),
      @ApiResponse(responseCode = "403", description = "conflict",
          content = @Content)})
  ResponseEntity<RoomResponse> createRoom(@RequestBody RoomCreatedRequest room);

  /**
   * This controller is used to get room in the database.
   *
   * @param roomId room id
   * @return Room Response {}
   */
  @GetMapping("/{room-id}")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Get room",
          content = {@Content(mediaType = "application/json",
              schema = @Schema(implementation = RoomEntity.class))}),
      @ApiResponse(responseCode = "404", description = "Not found",
          content = @Content)})
  ResponseEntity<RoomResponse> getRoom(@PathVariable("room-id") BigInteger roomId);

  @PutMapping("/{room-id}")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "updated room",
          content = {@Content(mediaType = "application/json",
              schema = @Schema(implementation = RoomEntity.class))}),
      @ApiResponse(responseCode = "404", description = "Not found",
          content = @Content)})
  ResponseEntity<RoomResponse> updateRoom(@PathVariable("room-id") BigInteger roomId,
                                            @RequestBody RoomUpdatedRequest room);

  @DeleteMapping("/{room-id}")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "delete room",
          content = {@Content(mediaType = "application/json",
              schema = @Schema(implementation = RoomEntity.class))}),
      @ApiResponse(responseCode = "404", description = "Not found",
          content = @Content)})
  ResponseEntity<Boolean> deleteRoom(@PathVariable("room-id") BigInteger roomId);

}
