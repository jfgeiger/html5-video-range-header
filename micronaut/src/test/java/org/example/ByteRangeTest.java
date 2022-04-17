package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Collections;
import org.junit.jupiter.api.Test;

class ByteRangeTest {

  @Test
  void shouldThrowIllegalArgumentExceptionWhenPrefixMissing() {
    assertThrows(IllegalArgumentException.class, () -> ByteRange.parse(""));
  }

  @Test
  void shouldThrowIllegalArgumentExceptionWhenPairInfixMissing() {
    assertThrows(IllegalArgumentException.class, () -> ByteRange.parse("bytes=1,2"));
  }

  @Test
  void shouldThrowIllegalArgumentExceptionWhenPairContainsMoreThanOneInfix() {
    assertThrows(IllegalArgumentException.class, () -> ByteRange.parse("bytes=1-2-3"));
  }

  @Test
  void shouldThrowIllegalArgumentExceptionWhenBeginIsNotANumber() {
    assertThrows(IllegalArgumentException.class, () -> ByteRange.parse("bytes=a-1"));
  }

  @Test
  void shouldThrowIllegalArgumentExceptionWhenEndIsNotANumber() {
    assertThrows(IllegalArgumentException.class, () -> ByteRange.parse("bytes=1-a"));
  }

  @Test
  void shouldReturnOpenByteRange() {
    assertEquals(Collections.singletonList(new ByteRange(1)), ByteRange.parse("bytes=1-"));
  }

  @Test
  void shouldThrowIllegalArgumentExceptionWhenEndIsSmallerThenBegin() {
    assertThrows(IllegalArgumentException.class, () -> ByteRange.parse("bytes=2-1"));
  }

  @Test
  void shouldReturnClosedByteRange() {
    assertEquals(Collections.singletonList(new ByteRange(1, 2)), ByteRange.parse("bytes=1-2"));
  }
}