package org.example;

import java.io.IOException;
import java.io.InputStream;
import org.springframework.core.io.InputStreamResource;

public class HttpRangeInputStreamResource extends InputStreamResource {

  private final long contentLength;

  public HttpRangeInputStreamResource(final InputStream inputStream, final long contentLength) {
    super(inputStream);

    this.contentLength = contentLength;
  }

  @Override
  public long contentLength() throws IOException {
    return this.contentLength;
  }
}