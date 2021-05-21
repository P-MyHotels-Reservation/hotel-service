package com.example.hotelservice.controller;

import com.example.hotelservice.model.entity.HotelEntity;
import com.example.hotelservice.model.request.HotelCreatedRequest;
import com.example.hotelservice.model.request.HotelUpdatedRequest;
import com.example.hotelservice.model.response.HotelResponse;
import com.example.hotelservice.model.response.RoomResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import java.math.BigInteger;

@RestController
@RequestMapping("/api/hotel")
public interface IHotelController {

  /**
   * This controller API is used to create hotel in the database.
   *
   * @param hotel hotel created request {@link HotelCreatedRequest}
   * @return HotelResponse {@link HotelResponse}
   */
  @PostMapping
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "created hotel",
          content = {@Content(mediaType = "application/json",
              schema = @Schema(implementation = HotelEntity.class))}),
      @ApiResponse(responseCode = "400", description = "Invalid field",
          content = @Content),
      @ApiResponse(responseCode = "403", description = "conflict",
          content = @Content)})
  ResponseEntity<HotelResponse> createHotel(@RequestBody HotelCreatedRequest hotel);

  /**
   * This controller API is used to get hotel in the database.
   *
   * @param hotelId hotel id
   * @return HotelResponse {@link HotelResponse}
   */
  @GetMapping("/{hotel-id}")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Get hotel",
          content = {@Content(mediaType = "application/json",
              schema = @Schema(implementation = HotelEntity.class))}),
      @ApiResponse(responseCode = "404", description = "Not found",
          content = @Content)})
  ResponseEntity<HotelResponse> getHotel(@PathVariable("hotel-id") BigInteger hotelId);

  /**
   * This controller API is used to update hotel in the database.
   *
   * @param hotelId hotel id
   * @param hotel hotel updated request {@link HotelUpdatedRequest}
   * @return HotelResponse {@link HotelResponse}
   */
  @PutMapping("/{hotel-id}")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "updated hotel",
          content = {@Content(mediaType = "application/json",
              schema = @Schema(implementation = HotelEntity.class))}),
      @ApiResponse(responseCode = "404", description = "Not found",
          content = @Content)})
  ResponseEntity<HotelResponse> updateHotel(@PathVariable("hotel-id") BigInteger hotelId,
                                            @RequestBody HotelUpdatedRequest hotel);

  /**
   * This controller API is used to delete hotel in the database.
   *
   * @param hotelId hotel id
   * @return Boolean
   */
  @DeleteMapping("/{hotel-id}")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "delete hotel",
          content = {@Content(mediaType = "application/json",
              schema = @Schema(implementation = HotelEntity.class))}),
      @ApiResponse(responseCode = "404", description = "Not found",
          content = @Content)})
  ResponseEntity<Boolean> deleteHotel(@PathVariable("hotel-id") BigInteger hotelId);

  /**
   * This controller API is used to get list of hotels in the database.
   *
   * @return List Hotel Response {@link List<HotelResponse>}
   */
  @GetMapping()
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Get hotel",
          content = {@Content(mediaType = "application/json",
              schema = @Schema(implementation = HotelEntity.class))}),
      @ApiResponse(responseCode = "404", description = "Not found",
          content = @Content)})
  ResponseEntity<List<HotelResponse>> getHotelList();

  /**
   * This controller API is used to get list of rooms in the database.
   *
   * @param hotelId hotel id
   * @return List Room Response {@link List<RoomResponse>}
   */
  @GetMapping("/{hotel-id}/rooms/search")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Get hotel",
          content = {@Content(mediaType = "application/json",
              schema = @Schema(implementation = HotelEntity.class))}),
      @ApiResponse(responseCode = "404", description = "Not found",
          content = @Content)})
  ResponseEntity<List<RoomResponse>> getRoomList(@PathVariable("hotel-id") BigInteger hotelId);

}
