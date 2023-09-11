# Jetpack Compose API 21-23 issue

When integrating a published library into an application project that uses
Jetpack Compose, running the application on an Android device with API 21-23
installed will result in a crash.

The following stacktrace is found in logcat:

```
FATAL EXCEPTION: main
    Process: com.repro.integratingapp, PID: 3168
    java.lang.NoSuchMethodError: No static method weight$default(Landroidx/compose/foundation/layout/RowScope;Landroidx/compose/ui/Modifier;FZILjava/lang/Object;)Landroidx/compose/ui/Modifier; in class Landroidx/compose/foundation/layout/RowScope; or its super classes (declaration of 'androidx.compose.foundation.layout.RowScope' appears in /data/app/com.repro.integratingapp-1/base.apk)
    	at com.repro.jclibrary.ExposedComposableKt.ExposedComposable(ExposedComposable.kt:13)
    	at com.repro.integratingapp.ComposableSingletons$MainActivityKt$lambda-1$1.invoke(MainActivity.kt:24)
    	at com.repro.integratingapp.ComposableSingletons$MainActivityKt$lambda-1$1.invoke(MainActivity.kt:23)
    	at androidx.compose.runtime.internal.ComposableLambdaImpl.invoke(ComposableLambda.jvm.kt:108)
    	at androidx.compose.runtime.internal.ComposableLambdaImpl.invoke(ComposableLambda.jvm.kt:35)
    	at androidx.compose.material3.SurfaceKt$Surface$1.invoke(Surface.kt:132)
    	at androidx.compose.material3.SurfaceKt$Surface$1.invoke(Surface.kt:114)
    	at androidx.compose.runtime.internal.ComposableLambdaImpl.invoke(ComposableLambda.jvm.kt:108)
    	at androidx.compose.runtime.internal.ComposableLambdaImpl.invoke(ComposableLambda.jvm.kt:35)
    	at androidx.compose.runtime.CompositionLocalKt.CompositionLocalProvider(CompositionLocal.kt:228)
    	at androidx.compose.material3.SurfaceKt.Surface-T9BRK9s(Surface.kt:111)
    	at com.repro.integratingapp.ComposableSingletons$MainActivityKt$lambda-2$1.invoke(MainActivity.kt:23)
    	at com.repro.integratingapp.ComposableSingletons$MainActivityKt$lambda-2$1.invoke(MainActivity.kt:21)
    	at androidx.compose.runtime.internal.ComposableLambdaImpl.invoke(ComposableLambda.jvm.kt:108)
    	at androidx.compose.runtime.internal.ComposableLambdaImpl.invoke(ComposableLambda.jvm.kt:35)
    	at androidx.compose.runtime.CompositionLocalKt.CompositionLocalProvider(CompositionLocal.kt:228)
    	at androidx.compose.material3.TextKt.ProvideTextStyle(Text.kt:360)
    	at androidx.compose.material3.MaterialThemeKt$MaterialTheme$1.invoke(MaterialTheme.kt:81)
    	at androidx.compose.material3.MaterialThemeKt$MaterialTheme$1.invoke(MaterialTheme.kt:80)
    	at androidx.compose.runtime.internal.ComposableLambdaImpl.invoke(ComposableLambda.jvm.kt:108)
    	at androidx.compose.runtime.internal.ComposableLambdaImpl.invoke(ComposableLambda.jvm.kt:35)
    	at androidx.compose.runtime.CompositionLocalKt.CompositionLocalProvider(CompositionLocal.kt:228)
    	at androidx.compose.material3.MaterialThemeKt.MaterialTheme(MaterialTheme.kt:73)
    	at com.repro.integratingapp.ui.theme.ThemeKt.IntegratingAppTheme(Theme.kt:65)
    	at com.repro.integratingapp.ComposableSingletons$MainActivityKt$lambda-3$1.invoke(MainActivity.kt:21)
    	at com.repro.integratingapp.ComposableSingletons$MainActivityKt$lambda-3$1.invoke(MainActivity.kt:20)
    	at androidx.compose.runtime.internal.ComposableLambdaImpl.invoke(ComposableLambda.jvm.kt:108)
    	at androidx.compose.runtime.internal.ComposableLambdaImpl.invoke(ComposableLambda.jvm.kt:35)
    	at androidx.compose.ui.platform.ComposeView.Content(ComposeView.android.kt:428)
    	at androidx.compose.ui.platform.AbstractComposeView$ensureCompositionCreated$1.invoke(ComposeView.android.kt:252)
    	at androidx.compose.ui.platform.AbstractComposeView$ensureCompositionCreated$1.invoke(ComposeView.android.kt:251)
    	at androidx.compose.runtime.internal.ComposableLambdaImpl.invoke(ComposableLambda.jvm.kt:108)
    	at androidx.compose.runtime.internal.ComposableLambdaImpl.invoke(ComposableLambda.jvm.kt:35)
    	at androidx.compose.runtime.CompositionLocalKt.CompositionLocalProvider(CompositionLocal.kt:228)
    	at androidx.compose.ui.platform.CompositionLocalsKt.ProvideCommonCompositionLocals(CompositionLocals.kt:195)
    	at androidx.compose.ui.platform.AndroidCompositionLocals_androidKt$ProvideAndroidCompositionLocals$3.invoke(AndroidCompositionLocals.android.kt:119)
    	at androidx.compose.ui.platform.AndroidCompositionLocals_androidKt$ProvideAndroidCompositionLocals$3.invoke(AndroidCompositionLocals.android.kt:118)
    	at androidx.compose.ru
```

This is due to the use of `Spacer(modifier = Modifier.weight(1f)`, without 
specifying a value for the parameter `fill`. 

This project contains a minimal reproduction of the issue.

## Steps to reproduce

Build and publish the library

```bash
cd JCLibrary
./gradlew publishToMavenLocal
```

Build and install the app:

```bash
cd IntegratingApp
./gradlew installDebug
```

Then run the application on an API21, 22 or 23 emulated device.
