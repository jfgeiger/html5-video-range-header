package org.example;

import io.micronaut.http.HttpHeaders;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

public class PartialContentResponse {

  private static final String BYTES = "bytes";

  private PartialContentResponse() {
  }

  public static HttpResponse<byte[]> build(final String rangeHeader, final byte[] bytes) {
    return Optional.ofNullable(rangeHeader)
        .map(ByteRange::parse)
        .map(getPartialContent(bytes))
        .orElseGet(getOK(bytes));
  }

  private static Function<List<ByteRange>, HttpResponse<byte[]>> getPartialContent(byte[] bytes) {
    return byteRanges -> {
      final var byteRange = byteRanges.get(0);
      final var begin = byteRange.getBegin();
      final var byteRangeEnd = byteRange.getEnd();
      final int end;

      if (byteRangeEnd == -1) {
        end = bytes.length;
      } else {
        end = byteRangeEnd;
      }

      final var body = Arrays.copyOfRange(bytes, begin, end);

      return HttpResponse.status(HttpStatus.PARTIAL_CONTENT)
          .header(HttpHeaders.ACCEPT_RANGES, BYTES)
          .header(HttpHeaders.CONTENT_RANGE,
              MessageFormat.format("{0} {1}-{2}/{3}", BYTES, Integer.toString(begin),
                  Integer.toString(end - 1), Integer.toString(bytes.length)))
          .header(HttpHeaders.CONTENT_LENGTH, String.valueOf(body.length))
          .body(body);
    };
  }

  private static Supplier<HttpResponse<byte[]>> getOK(byte[] bytes) {
    return () -> HttpResponse
        .ok(bytes);
  }
}