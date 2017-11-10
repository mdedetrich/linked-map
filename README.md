# Ordered Map

An extension to the scala's collections library which provides an immutable `Map`
which has effectively constant lookup time on all operations (including key and index
lookup) while maintaining key ordering.

## Design

The default implementation of an `scala.collection.immutable.OrderedMap` is a
`scala.collection.immutable.VectorMap`, which is a composite data structure that
internally stores a `scala.collection.immutable.HashMap` and `scala.collection.immutable.Vector`.

This is currently the only known practical implementation of such an immutable
structure. Equivalent implementations can be seen in libraries such as
[immutable.js](https://facebook.github.io/immutable-js/), see 
[here](https://github.com/facebook/immutable-js/blob/d3bce8d9baacac1bf9c233cec1c84e25e8db4083/src/OrderedMap.js)
for an example.

Like Scala's standard `collection.immutable.Map`, `scala.collection.immutable.OrderedMap`
features specialization for `OrderedMap`'s up to size 4, since small `OrderedMap`'s
are the most likely to be created.

### Compared to ListMap

Scala contains an immutable Map which maintains key ordering called a `scala.collection.immutable.ListMap`.
This is a `Map` structure which is backed by a linked list, so it will maintain
key ordering however since it is a linked list it will have O(n) time for querying
by index/key.

### Why use the scala.collection package?

While using the scala namespace is discouraged, there are certain classes/methods
(such as `AbstractMap`) which cannot be accessed outside of this package. Ordered Map
requires access to these classes for performance/code size reasons.

## Supported Scala Versions

Ordered Map is currently built for Scala 2.10.x, 2.11.x and 2.12.x. 2.13.x is
under consideration, as it may make more sense to include an implementation
directly in the Scala Collections redesign
