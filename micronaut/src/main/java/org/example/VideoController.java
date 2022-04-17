package org.example;

import io.micronaut.http.HttpHeaders;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Header;
import io.micronaut.http.annotation.Produces;
import java.io.IOException;
import java.util.Objects;

@Controller("video.mp4")
public class VideoController {

  private static final String VIDEO_MP4 = "video/mp4";

  @Get
  @Produces(VIDEO_MP4)
  public HttpResponse<byte[]> get(@Header(HttpHeaders.RANGE) final String rangeHeader) {
    try (final var inputStream = Objects.requireNonNull(
        VideoController.class.getResourceAsStream("video.mp4"))) {
      final var bytes = inputStream.readAllBytes();

      return PartialContentResponse.build(rangeHeader, bytes);
    } catch (final IOException e) {
      throw new IllegalArgumentException(e);
    }
  }
}