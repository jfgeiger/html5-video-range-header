package org.example;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VideoController {

  private static final String MEDIA_TYPE_VIDEO_MP4 = "video/mp4";

  private static final String VIDEO_MP4 = "video.mp4";

  @GetMapping(value = VIDEO_MP4, produces = MEDIA_TYPE_VIDEO_MP4)
  public ResponseEntity<Resource> get() throws IOException {
    final var byteArrayOutputStream = new ByteArrayOutputStream();
    final var inputStream = this.getInputStream();
    IOUtils.copy(inputStream, byteArrayOutputStream);

    final var contentLength = byteArrayOutputStream.size();
    final var inputStreamResource = new HttpRangeInputStreamResource(this.getInputStream(),
        contentLength);

    return ResponseEntity.ok(inputStreamResource);
  }

  private InputStream getInputStream() {
    return Objects.requireNonNull(VideoController.class.getResourceAsStream(VIDEO_MP4));
  }
}