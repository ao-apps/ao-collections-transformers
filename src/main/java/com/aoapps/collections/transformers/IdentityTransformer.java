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
 * Performs no type conversion.
 *
 * @author  AO Industries, Inc.
 *
 * @see  Transformer#identity()
 */
final class IdentityTransformer<E> implements Transformer<E, E> {

	static final IdentityTransformer<Object> instance = new IdentityTransformer<>();

	private IdentityTransformer() {
		// Do nothing
	}

	@Override
	public E toWrapped(E e) {
		return e;
	}

	@Override
	public E fromWrapped(E w) {
		return w;
	}

	@Override
	@SuppressWarnings("unchecked")
	public Transformer<Object, Object> unbounded() {
		return (Transformer<Object, Object>)this;
	}

	@Override
	public Transformer<E, E> invert() {
		return this;
	}
}
