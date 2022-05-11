/*
 * ao-collections-transformers - Bi-directional collection transformations for Java.
 * Copyright (C) 2020, 2021, 2022  AO Industries, Inc.
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

/**
 * Performs type conversions between two classes.
 *
 * @param  <E>  The wrapper type
 * @param  <W>  The wrapped type
 *
 * @author  AO Industries, Inc.
 */
public abstract class AbstractTransformer<E, W> implements Transformer<E, W> {

  protected final Class<E> eClass;
  protected final Class<W> wClass;
  protected final AbstractTransformer<W, E> inverted;

  /**
   * Creates a new type converter.
   *
   * @param eClass The wrapper type
   * @param wClass The wrapped type
   */
  protected AbstractTransformer(
      Class<E> eClass,
      Class<W> wClass
  ) {
    this.eClass = eClass;
    this.wClass = wClass;
    this.inverted = new FunctionalTransformer<>(
        wClass,
        eClass,
        this::fromWrapped,
        this::toWrapped,
        this
    );
  }

  /**
   * Creates a new type converter.
   *
   * @param eClass The wrapper type
   * @param wClass The wrapped type
   */
  AbstractTransformer(
      Class<E> eClass,
      Class<W> wClass,
      AbstractTransformer<W, E> inverted
  ) {
    this.eClass = eClass;
    this.wClass = wClass;
    this.inverted = inverted;
  }

  @Override
  public abstract W toWrapped(E e);

  @Override
  public abstract E fromWrapped(W w);

  // Java 9: new Transformer<>
  private final Transformer<Object, Object> unbouned = new Transformer<Object, Object>() {
    /**
     * Unwraps the given object if is of our wrapper type.
     *
     * @return  The unwrapped object or {@code o} if not of our wrapper type.
     */
    @Override
    public Object toWrapped(Object e) {
      return eClass.isInstance(e) ? AbstractTransformer.this.toWrapped(eClass.cast(e)) : e;
    }

    /**
     * Wraps the given object if is of our wrapped type.
     *
     * @return  The wrapped object or {@code o} if not of our wrapped type.
     */
    @Override
    public Object fromWrapped(Object w) {
      return wClass.isInstance(w) ? AbstractTransformer.this.fromWrapped(wClass.cast(w)) : w;
    }

    @Override
    public Transformer<Object, Object> unbounded() {
      return this;
    }

    @Override
    public Transformer<Object, Object> invert() {
      return AbstractTransformer.this.invert().unbounded();
    }
  };

  @Override
  public Transformer<Object, Object> unbounded() {
    return unbouned;
  }

  @Override
  public AbstractTransformer<W, E> invert() {
    return inverted;
  }
}
