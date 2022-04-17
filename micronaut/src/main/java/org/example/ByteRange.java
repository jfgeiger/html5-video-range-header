package org.example;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ByteRange {

  private static final String BYTE_RANGE_PREFIX = "bytes=";

  private static final String BYTE_RANGE_PAIRS_SEPARATOR = ",";


  private static final String BYTE_RANGE_PAIR_INFIX = "-";

  private static final int END_UNSET = -1;

  private final int begin;

  private final int end;

  public ByteRange(final int begin) {
    this(begin, END_UNSET);
  }

  public ByteRange(final int begin, final int end) {
    this.begin = begin;
    this.end = end;
  }

  public int getBegin() {
    return begin;
  }

  public int getEnd() {
    return end;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    final var byteRange = (ByteRange) o;
    return begin == byteRange.begin && end == byteRange.end;
  }

  @Override
  public int hashCode() {
    return Objects.hash(begin, end);
  }

  public static List<ByteRange> parse(final String string) {
    if (string.startsWith(BYTE_RANGE_PREFIX)) {
      final var pairs = string.substring(BYTE_RANGE_PREFIX.length()).split(
          BYTE_RANGE_PAIRS_SEPARATOR);

      return Arrays.stream(pairs)
          .map(ByteRange::parsePair)
          .collect(Collectors.toList());
    } else {
      throw new IllegalArgumentException();
    }
  }

  private static ByteRange parsePair(final String pair) {
    if (pair.contains(BYTE_RANGE_PAIR_INFIX)) {
      final var pairElements = pair.split(BYTE_RANGE_PAIR_INFIX);

      if (pairElements.length > 2) {
        throw new IllegalArgumentException();
      } else {
        return ByteRange.createByteRange(pairElements);
      }
    } else {
      throw new IllegalArgumentException();
    }
  }

  private static ByteRange createByteRange(final String[] pairElements) {
    try {
      final var beginElement = pairElements[0];
      final var begin = Integer.parseInt(beginElement);

      if (pairElements.length == 1) {
        return new ByteRange(begin);
      } else {
        final var endElement = pairElements[1];
        final var end = Integer.parseInt(endElement);

        if (end < begin) {
          throw new IllegalArgumentException();
        } else {
          return new ByteRange(begin, end);
        }
      }
    } catch (final NumberFormatException e) {
      throw new IllegalArgumentException();
    }
  }
}