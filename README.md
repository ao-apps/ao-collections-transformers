# [<img src="ao-logo.png" alt="AO Logo" width="35" height="40">](https://github.com/ao-apps) [AO OSS](https://github.com/ao-apps/ao-oss) / [Collections](https://github.com/ao-apps/ao-collections) / [Transformers](https://github.com/ao-apps/ao-collections-transformers)

[![project: current stable](https://oss.aoapps.com/ao-badges/project-current-stable.svg)](https://aoindustries.com/life-cycle#project-current-stable)
[![management: production](https://oss.aoapps.com/ao-badges/management-production.svg)](https://aoindustries.com/life-cycle#management-production)
[![packaging: active](https://oss.aoapps.com/ao-badges/packaging-active.svg)](https://aoindustries.com/life-cycle#packaging-active)  
[![java: &gt;= 11](https://oss.aoapps.com/ao-badges/java-11.svg)](https://docs.oracle.com/en/java/javase/11/docs/api/)
[![semantic versioning: 2.0.0](https://oss.aoapps.com/ao-badges/semver-2.0.0.svg)](https://semver.org/spec/v2.0.0.html)
[![license: LGPL v3](https://oss.aoapps.com/ao-badges/license-lgpl-3.0.svg)](https://www.gnu.org/licenses/lgpl-3.0)

[![Build](https://github.com/ao-apps/ao-collections-transformers/workflows/Build/badge.svg?branch=master)](https://github.com/ao-apps/ao-collections-transformers/actions?query=workflow%3ABuild)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.aoapps/ao-collections-transformers/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.aoapps/ao-collections-transformers)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?branch=master&project=com.aoapps%3Aao-collections-transformers&metric=alert_status)](https://sonarcloud.io/dashboard?branch=master&id=com.aoapps%3Aao-collections-transformers)
[![Lines of Code](https://sonarcloud.io/api/project_badges/measure?branch=master&project=com.aoapps%3Aao-collections-transformers&metric=ncloc)](https://sonarcloud.io/component_measures?branch=master&id=com.aoapps%3Aao-collections-transformers&metric=ncloc)  
[![Reliability Rating](https://sonarcloud.io/api/project_badges/measure?branch=master&project=com.aoapps%3Aao-collections-transformers&metric=reliability_rating)](https://sonarcloud.io/component_measures?branch=master&id=com.aoapps%3Aao-collections-transformers&metric=Reliability)
[![Security Rating](https://sonarcloud.io/api/project_badges/measure?branch=master&project=com.aoapps%3Aao-collections-transformers&metric=security_rating)](https://sonarcloud.io/component_measures?branch=master&id=com.aoapps%3Aao-collections-transformers&metric=Security)
[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?branch=master&project=com.aoapps%3Aao-collections-transformers&metric=sqale_rating)](https://sonarcloud.io/component_measures?branch=master&id=com.aoapps%3Aao-collections-transformers&metric=Maintainability)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?branch=master&project=com.aoapps%3Aao-collections-transformers&metric=coverage)](https://sonarcloud.io/component_measures?branch=master&id=com.aoapps%3Aao-collections-transformers&metric=Coverage)

Bi-directional collection transformations for Java.

## Project Links
* [Project Home](https://oss.aoapps.com/collections/transformers/)
* [Changelog](https://oss.aoapps.com/collections/transformers/changelog)
* [API Docs](https://oss.aoapps.com/collections/transformers/apidocs/)
* [Central Repository](https://central.sonatype.com/artifact/com.aoapps/ao-collections-transformers)
* [GitHub](https://github.com/ao-apps/ao-collections-transformers)

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
[IdentityHashMap](https://docs.oracle.com/en/java/javase/11/docs/api/docs/api/java/util/IdentityHashMap.html) and
[LinkedHashMap](https://docs.oracle.com/en/java/javase/11/docs/api/docs/api/java/util/LinkedHashMap.html) because they are both concrete
implementations.  We also could not combine
[IdentityKey](https://oss.aoapps.com/collections/apidocs/com.aoapps.collections/com/aoapps/collections/IdentityKey.html) with
[LinkedHashMap](https://docs.oracle.com/en/java/javase/11/docs/api/docs/api/java/util/LinkedHashMap.html) because that would alter the map
key type to be the identity-key wrapper.

The solution is a bi-directional transformation through
[IdentityKey](https://oss.aoapps.com/collections/apidocs/com.aoapps.collections/com/aoapps/collections/IdentityKey.html) on top of
[LinkedHashMap](https://docs.oracle.com/en/java/javase/11/docs/api/docs/api/java/util/LinkedHashMap.html).  We can now add/remove objects and
fully interact with the map with the identity-key wrapping performed on-the-fly behind-the-scenes:
<pre>Map&lt;SomeKeyType, ?&gt; map = TransformMap.of(
  new LinkedHashMap&lt;&gt;(),
  new FunctionalTransformer&lt;&gt;(
    SomeKeyType.class,
    (Class&lt;IdentityKey&lt;SomeKeyType&gt;&gt;)(Class)IdentityKey.class,
    IdentityKey::of,
    IdentityKey::getValue
  ),
  Transformer.identity()
)</pre>

## Alternatives
* [Google Guava](https://github.com/google/guava) - Provides **one-way** transform views of the most common collections
  types in the [com.google.common.collect](https://guava.dev/releases/19.0/api/docs/com/google/common/collect/package-summary.html)
  package.

## Contact Us
For questions or support, please [contact us](https://aoindustries.com/contact):

Email: [support@aoindustries.com](mailto:support@aoindustries.com)  
Phone: [1-800-519-9541](tel:1-800-519-9541)  
Phone: [+1-251-607-9556](tel:+1-251-607-9556)  
Web: https://aoindustries.com/contact
