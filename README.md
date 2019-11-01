
# LiveData testing and modification to await multiple values.
As stated [publicly](https://twitter.com/ppvi/status/1169498619105427456), livedata needs to be tested with a [copy/pasted](https://github.com/android/architecture-components-samples/blob/master/LiveDataSample/app/src/test/java/com/android/example/livedatabuilder/util/LiveDataTestUtil.kt) helper function.
However, this class is insufficient for you're looking to await multiple values. So I [changed](https://github.com/AniketSK/DistanceSorter/commit/586a00b9713705a48c2d984fee800cf74370b656) the implementation to take this param.

# Calculation of great circle distance
We're going to run an initial calculation of the two coordinates, 53.339428, -6.257664 and 52.986375, -6.043701
Using the wikipedia forumla:
```kotlin
import java.lang.Math.*

// Calculate Earth Radius
// From: https://en.wikipedia.org/wiki/Great-circle_distance#Radius_for_spherical_Earth
val equatorialRadius : Double = 6378.137
val polarRadius : Double = 6356.752
val meanEarthRadius: Double = (1.0 / 3.0) * (2 * equatorialRadius + polarRadius)

// Note that all results are only within an error bound since the Earth isn't perfectly spherical and we've made assumptions.
val errorMargin = 0.005

// Taking sample values, where lambda is longitude, and theta is latitude.
// For two coordinate values, lambda1 (longitude) = l1, theta1 (latitude) = t1 and for the second set of coordinates, l2, t2  
val l1 : Double = toRadians(-6.257664) // All values are given to us in degrees so they must be converted to radians
val l2 : Double = toRadians(-6.043701)

val t1 : Double = toRadians(53.339428)
val t2 : Double = toRadians(52.986375)

val dLambda : Double = l1 - l2

val centralAngle = acos(sin(t1) * sin(t2) + cos(t1) * cos(t2) * cos(dLambda))

val distance = meanEarthRadius * centralAngle

System.out.print(distance) // ~41.7km

// Note that if the distance is within 0.5% of the minimum distance, for safety's sake, we may want to include those people.

```

To double check our numbers, a sanity check was performed with:
1. Google Maps, which lists it as 47km reasonable for a walking distance that isn't straight line.
2. Another site https://www.gpsvisualizer.com/calculators#distance which shows the distance to be 41.8km

Allowing for variations in radius of the earth used and such, the final value looks quite reasonable. 