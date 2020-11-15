# [<img src="ao-logo.png" alt="AO Logo" width="35" height="40">](https://github.com/aoindustries) [AO Collections](https://github.com/aoindustries/ao-collections) / [Transformers](https://github.com/aoindustries/ao-collections-transformers)
<p>
	<a href="https://aoindustries.com/life-cycle#project-alpha">
		<img src="https://aoindustries.com/ao-badges/project-alpha.svg" alt="project: alpha" />
	</a>
	<a href="https://aoindustries.com/life-cycle#management-preview">
		<img src="https://aoindustries.com/ao-badges/management-preview.svg" alt="management: preview" />
	</a>
	<a href="https://aoindustries.com/life-cycle#packaging-developmental">
		<img src="https://aoindustries.com/ao-badges/packaging-developmental.svg" alt="packaging: developmental" />
	</a>
	<br />
	<a href="https://docs.oracle.com/javase/8/docs/api/">
		<img src="https://aoindustries.com/ao-badges/java-8.svg" alt="java: &gt;= 8" />
	</a>
	<a href="http://semver.org/spec/v2.0.0.html">
		<img src="https://aoindustries.com/ao-badges/semver-2.0.0.svg" alt="semantic versioning: 2.0.0" />
	</a>
	<a href="https://www.gnu.org/licenses/lgpl-3.0">
		<img src="https://aoindustries.com/ao-badges/license-lgpl-3.0.svg" alt="license: LGPL v3" />
	</a>
</p>

Bi-directional collection transformations for Java.

## Project Links
* [Project Home](https://aoindustries.com/ao-collections/transformers/)
* [Changelog](https://aoindustries.com/ao-collections/transformers/changelog)
* [API Docs](https://aoindustries.com/ao-collections/transformers/apidocs/)
* [Maven Central Repository](https://search.maven.org/artifact/com.aoindustries/ao-collections-transformers)
* [GitHub](https://github.com/aoindustries/ao-collections-transformers)

## Features
* Bi-directional transformations allow for modification of the transformed collections.
* Support for all the major collections framework interfaces (Enumeration, ListIterator, Map, Set, SortedSet, Queue, Deque, …).
* Small footprint, self-contained, no transitive dependencies - not part of a big monolithic package.

## Motivation
[LEGO®](https://www.lego.com/) were my absolute favorite as a child.  I played with them for years.  I brought my
creations everywhere: submarine navigating the depths of the bathtub, motorized pickup truck climbing the neighbor's
steep grass hill, airplane flying in the wind outside the car window, hydroplane skimming across the summer lake.
They were the best.

Then I found computers.  Computers were my absolute favorite.  I played with them for years.  I still play with them.
They are the best.

Now, as a superficially responsible adult, I like to be able to compose software as I would build with LEGO®.  Toward
this end, we have created bi-directional collections transformations for Java.  This allows the composition of
collections in new ways.

## Example
For example, our specific motivation to develop this API was the desire to have a linked map with identity keys (use
arbitrary objects as keys while maintaining map ordering). We could not simply combine
[IdentityHashMap](https://docs.oracle.com/javase/8/docs/api/java/util/IdentityHashMap.html) and
[LinkedHashMap](https://docs.oracle.com/javase/8/docs/api/java/util/LinkedHashMap.html) because they are both concrete
implementations.  We also could not combine
[IdentityKey](https://aoindustries.com/ao-collections/apidocs/com/aoindustries/collections/IdentityKey.html) with
[LinkedHashMap](https://docs.oracle.com/javase/8/docs/api/java/util/LinkedHashMap.html) because that would alter the map
key type to be the identity-key wrapper.

The solution is a bi-directional transformation through
[IdentityKey](https://aoindustries.com/ao-collections/apidocs/com/aoindustries/collections/IdentityKey.html) on top of
[LinkedHashMap](https://docs.oracle.com/javase/8/docs/api/java/util/LinkedHashMap.html).  We can now add/remove objects and
fully interact with the map with the identity-key wrapping performed on-the-fly behind-the-scenes:
<pre>TransformMap.of(
	new LinkedHashMap&lt;&gt;(),
	new FunctionalTransformer&lt;&gt;(
		Savepoint.class,
		(Class&lt;IdentityKey&lt;Savepoint&gt;&gt;)(Class)IdentityKey.class,
		IdentityKey::of,
		IdentityKey::getValue
	),
	Transformer.identity()
)</pre>

## Alternatives
* [Google Guava](https://github.com/google/guava) - Provides *one-way* transform views of the most common collections
  types in the [com.google.common.collect](https://guava.dev/releases/19.0/api/docs/com/google/common/collect/package-summary.html)
  package.

## Contact Us
For questions or support, please [contact us](https://aoindustries.com/contact):

Email: [support@aoindustries.com](mailto:support@aoindustries.com)  
Phone: [1-800-519-9541](tel:1-800-519-9541)  
Phone: [+1-251-607-9556](tel:+1-251-607-9556)  
Web: https://aoindustries.com/contact
