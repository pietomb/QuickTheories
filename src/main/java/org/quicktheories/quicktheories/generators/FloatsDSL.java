package org.quicktheories.quicktheories.generators;

import org.quicktheories.quicktheories.core.Source;

import static org.quicktheories.quicktheories.generators.Floats.range;

/**
 * A Class for creating Float Sources that will produce floats within a set
 * interval and will shrink within this domain.
 */
public class FloatsDSL {

  /**
   * Generates Floats inclusively bounded below by Float.NEGATIVE_INFINITY and
   * above by Float.POSITIVE_INFINITY.
   * 
   * The Source is weighted so it is likely to generate Float.NEGATIVE_INFINITY,
   * Float.POSITIVE_INFINITY, Float.MAX_VALUE one or more times.
   * 
   * @return a Source of type Float
   */
  public Source<Float> fromNegativeInfinityToPositiveInfinity() {
    return Compositions.weightWithValues(
        Floats.fromNegativeInfinityToPositiveInfinity(),
        Float.NEGATIVE_INFINITY, Float.POSITIVE_INFINITY, Float.MAX_VALUE);
  }

  /**
   * Generates Floats inclusively bounded below by Float.NEGATIVE_INFINITY and
   * above by a value very close to zero on the negative side.
   * 
   * The Source is weighted so it is likely to generate the upper and lower
   * limits of the domain one or more times.
   * 
   * @return a Source of type Float
   */
  public Source<Float> fromNegativeInfinityToNegativeZero() {
    return Compositions.weightWithValues(
        Floats.fromNegativeInfinityToNegativeZero(),
        Float.NEGATIVE_INFINITY, -0f);
  }

  /**
   * Generates Floats inclusively bounded below by -Float.MAX_VALUE and above by
   * Float.MAX_VALUE.
   * 
   * The Source is weighted so it is likely to generate the upper and lower
   * limits of the domain one or more times.
   * 
   * @return a Source of type Float
   */
  public Source<Float> fromNegativeFloatMaxToPositiveFloatMax() {
    return Compositions.weightWithValues(
        Floats.fromNegativeFloatMaxToPositiveFloatMax(), -Float.MAX_VALUE,
        Float.MAX_VALUE);
  }

  /**
   * Generates Floats inclusively bounded below by -Float.MAX_VALUE and above by
   * a value very close to zero on the negative side.
   * 
   * The Source is weighted so it is likely to generate the upper and lower
   * limits of the domain one or more times.
   * 
   * @return a Source of type Float
   */
  public Source<Float> fromNegativeFloatMaxToNegativeZero() {
    return Compositions
        .weightWithValues(
            Floats.fromNegativeFloatMaxToNegativeZero(),
            -Float.MAX_VALUE, -0f);
  }

  /**
   * Generates Floats inclusively bounded below by a value very close to zero on
   * the positive side and above by Float.POSITIVE_INFINITY.
   * 
   * The Source is weighted so it is likely to generate the upper and lower
   * limits of the domain one or more times.
   * 
   * @return a Source of type Float
   */
  public Source<Float> fromZeroToPositiveInfinity() {
    return Compositions.weightWithValues(
        Floats.fromZeroToPositiveInfinity(),
        Float.POSITIVE_INFINITY, 0f);
  }

  /**
   * Generates Floats inclusively bounded below by a value very close to zero on
   * the positive side and above by Float.MAX_VALUE.
   * 
   * The Source is weighted so it is likely to generate the upper and lower
   * limits of the domain one or more times.
   * 
   * @return a Source of type Float
   */
  public Source<Float> fromZeroToFloatMax() {
    return Compositions.weightWithValues(
        Floats.fromZeroToFloatMax(),
        Float.MAX_VALUE, 0f);
  }

  /**
   * Generates Floats inclusively bounded below by zero and above by one.
   *
   * The Source is weighted so it is likely to generate the upper and lower
   * limits of the domain one or more times.
   * 
   * @return a Source of type Float
   */
  public Source<Float> fromZeroToOne() {
    return Compositions.weightWithValues(
        Floats.fromZeroToOne(),
        1f, 0f);
  }

  /**
   * Generates Floats in Java, including Float.NaN - which will only ever shrink
   * to itself.
   * 
   * The Source is weighted so it is likely to generate Float.NEGATIVE_INFINITY,
   * Float.POSITIVE_INFINITY, Float.MAX_VALUE and Float.NaN one or more times.
   * 
   * @return a Source of type Float
   */
  public Source<Float> allFloats() {
    return Compositions.combineWithValues(
        fromNegativeInfinityToPositiveInfinity(), Float.NaN);
  }

  /**
   * Constructs a IntegerDomainBuilder object with an inclusive lower bound
   *
   * @param startInclusive
   *          - lower bound of domain
   * @return an IntegerDomainBuilder
   */
  public FloatDomainBuilder from(final float startInclusive) {
    return new FloatDomainBuilder(startInclusive);
  }


  public class FloatDomainBuilder {

    private final float startInclusive;

    private FloatDomainBuilder(float startInclusive) {
      this.startInclusive = startInclusive;
    }

    /**
     * Generates integers within the interval specified with an inclusive lower
     * bound and exclusive upper bound.
     *
     * The Source is weighted so it is likely to generate the upper and lower
     * limits of the domain one or more times.
     *
     * @param endExclusive
     *          - exclusive upper bound of domain
     * @return a Source of type Integer
     */
    public Source<Float> upTo(final float endExclusive) {
      return between(startInclusive, endExclusive - 1);
    }


  }

  /**
   * Generates Integers within the interval specified with an inclusive lower
   * and upper bound.
   *
   * The Source is weighted so it is likely to generate the upper and lower
   * limits of the domain one or more times.
   *
   * @param startInclusive
   *          - inclusive lower bound of domain
   * @param endInclusive
   *          - inclusive upper bound of domain
   * @return a Source of type Integer
   */
  public Source<Float> between(final float startInclusive,
                                 final float endInclusive) {
    ArgumentAssertions.checkArguments(startInclusive <= endInclusive,
            "There are no Integer values to be generated between (%s) and (%s)",
            startInclusive, endInclusive);
    return Compositions.weightWithValues(
            range(Math.round(startInclusive), Math.round(endInclusive), 0), startInclusive, endInclusive);
  }
}
