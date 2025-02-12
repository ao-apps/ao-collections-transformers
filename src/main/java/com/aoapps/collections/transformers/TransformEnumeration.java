/*
 * ao-collections-transformers - Bi-directional collection transformations for Java.
 * Copyright (C) 2020, 2021, 2022, 2025  AO Industries, Inc.
 *     support@aoindustries.com
 *     7262 Bull Pen Cir
 *     Mobile, AL 36695
 *
 * This file is part of ao-collections-transformers.
 *
 * ao-collections-transformers is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * ao-collections-transformers is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with ao-collections-transformers.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.aoapps.collections.transformers;

import java.util.Enumeration;
import java.util.Iterator;

/**
 * Wraps an {@link Enumeration}, with optional type conversion.
 *
 * @author  AO Industries, Inc.
 */
public class TransformEnumeration<E, W> implements Enumeration<E> {

  /**
   * Wraps an enumeration.
   */
  public static <E, W> TransformEnumeration<E, W> of(Enumeration<W> enumeration, Transformer<E, W> transformer) {
    return (enumeration == null) ? null : new TransformEnumeration<>(enumeration, transformer);
  }

  /**
   * See {@link #of(java.util.Enumeration, com.aoapps.collections.transformers.Transformer)}.
   *
   * @see  Transformer#identity()
   */
  public static <E> TransformEnumeration<E, E> of(Enumeration<E> enumeration) {
    return of(enumeration, Transformer.identity());
  }

  private final Enumeration<W> wrapped;
  protected final Transformer<E, W> transformer;

  protected TransformEnumeration(Enumeration<W> wrapped, Transformer<E, W> transformer) {
    this.wrapped = wrapped;
    this.transformer = transformer;
  }

  protected Enumeration<W> getWrapped() {
    return wrapped;
  }

  @Override
  public boolean hasMoreElements() {
    return getWrapped().hasMoreElements();
  }

  @Override
  public E nextElement() {
    return transformer.fromWrapped(getWrapped().nextElement());
  }

  @Override
  public Iterator<E> asIterator() {
    Iterator<W> iter = getWrapped().asIterator();
    return new Iterator<>() {
      @Override public boolean hasNext() {
        return iter.hasNext();
      }

      @Override public E next() {
        return transformer.fromWrapped(iter.next());
      }
    };
  }
}
