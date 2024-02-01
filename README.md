# WorkManager with Hilt using App Startup

Example for Medium post:[link](https://medium.com/@santimattius/workmanager-with-hilt-and-app-startup-80b34062e144)

## Schedule tasks with WorkManager.

WorkManager is the recommended solution for persistent work. Work is persistent when it remains
scheduled through app restarts and system reboots. Because most background processing is best
accomplished through persistent work, WorkManager is the primary recommended API for background
processing.

## Dependency injection with Hilt

Hilt is a dependency injection library for Android that reduces the boilerplate of doing manual
dependency injection in your project. Doing manual dependency injection requires you to construct
every class and its dependencies by hand, and to use containers to reuse and manage dependencies.

Hilt provides a standard way to use DI in your application by providing containers for every Android
class in your project and managing their lifecycles automatically. Hilt is built on top of the
popular DI library Dagger to benefit from the compile-time correctness, runtime performance,
scalability, and Android Studio support that Dagger provides. For more information, see Hilt and
Dagger.

This guide explains the basic concepts of Hilt and its generated containers. It also includes a
demonstration of how to bootstrap an existing app to use Hilt.

## App Startup

The App Startup library provides a straightforward, performant way to initialize components at
application startup. Both library developers and app developers can use App Startup to streamline
startup sequences and explicitly set the order of initialization.

Instead of defining separate content providers for each component you need to initialize, App
Startup allows you to define component initializers that share a single content provider. This can
significantly improve app startup time.

## Initialize components at app startup

Apps and libraries often rely on having components initialized right away when the app starts up.
You can meet this need by using content providers to initialize each dependency, but content
providers are expensive to instantiate and can slow down the startup sequence unnecessarily.
Additionally, Android initializes content providers in an undetermined order. App Startup provides a
more performant way to initialize components at app startup and explicitly define their
dependencies.

To use App Startup to initialize components automatically at startup, you must define a component
initializer for each component that the app needs to initialize.
Implement component initializers

You define each component initializer by creating a class that implements the Initializer<T>
interface. This interface defines two important methods:

- The create() method, which contains all of the necessary operations to initialize the component
  and returns an instance of T.
- The dependencies() method, which returns a list of the other Initializer<T> objects that the
  initializer depends on. You can use this method to control the order in which the app runs the
  initializers at startup.

## References

- [WorkManager Documentation](https://developer.android.com/topic/libraries/architecture/workmanager)
- [App Startup Documentation](https://developer.android.com/topic/libraries/app-startup)
- [Dependency injection with Hilt](https://developer.android.com/training/dependency-injection/hilt-android)
