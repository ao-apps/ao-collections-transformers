/*
 * ao-collections-transformers - Bi-directional collection transformations for Java.
 * Copyright (C) 2020  AO Industries, Inc.
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
 * along with ao-collections-transformers.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.aoindustries.collections.transformers;

import java.util.function.Function;

/**
 * @author  AO Industries, Inc.
 */
public class FunctionalTransformer<E,W> extends AbstractTransformer<E,W> {

	protected final Function<? super E,? extends W> toWrapped;
	protected final Function<? super W,? extends E> fromWrapped;

	/**
	 * @param eClass The wrapper type
	 * @param wClass The wrapped type
	 * @param toWrapped Converts from wrapper to wrapped type
	 * @param fromWrapped Converts from wrapped to wrapper type
	 */
	public FunctionalTransformer(
		Class<E> eClass,
		Class<W> wClass,
		Function<? super E,? extends W> toWrapped,
		Function<? super W,? extends E> fromWrapped
	) {
		super(eClass, wClass);
		this.toWrapped = toWrapped;
		this.fromWrapped = fromWrapped;
	}

	/**
	 * @param eClass The wrapper type
	 * @param wClass The wrapped type
	 * @param toWrapped Converts from wrapper to wrapped type
	 * @param fromWrapped Converts from wrapped to wrapper type
	 */
	FunctionalTransformer(
		Class<E> eClass,
		Class<W> wClass,
		Function<? super E,? extends W> toWrapped,
		Function<? super W,? extends E> fromWrapped,
		AbstractTransformer<W,E> inverted
	) {
		super(eClass, wClass, inverted);
		this.toWrapped = toWrapped;
		this.fromWrapped = fromWrapped;
	}

	@Override
	public W toWrapped(E e) {
		return toWrapped.apply(e);
	}

	@Override
	public E fromWrapped(W w) {
		return fromWrapped.apply(w);
	}
}
