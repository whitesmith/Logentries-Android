# Logging support for Android devices [![](https://jitpack.io/v/whitesmith/Logentries-Android.svg)](https://jitpack.io/#whitesmith/Logentries-Android) [![Build Status](https://travis-ci.org/LogentriesCommunity/le_android.svg)](https://travis-ci.org/LogentriesCommunity/le_android)  [![API](https://img.shields.io/badge/API-15%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=15)  [![Gradle Version](https://img.shields.io/badge/gradle-3.0-green.svg)](https://docs.gradle.org/current/release-notes) [![License](https://img.shields.io/badge/license-MIT-blue.svg?style=flat)](https://github.com/mdp/rotp/blob/master/LICENSE) [![Javadoc](https://img.shields.io/badge/javadoc-SNAPSHOT-green.svg)](https://jitpack.io/com/github/kibotu/le_android/master-SNAPSHOT/javadoc/index.html)

## Setup

Set up an account with Logentries at <https://logentries.com/>, and create a logfile, by clicking + Add New button and selecting the Manual Configuration Option at the bottom. Select Token TCP as the source type and copy the Token UUID printed in green.

Next go to [Jitpack](https://jitpack.io/#whitesmith/Logentries-Android) and select the latest version of the Android library. Follow the instructions provided to install the library.

Add the permission **`android.permission.INTERNET`** to the project manifest file.

## Use

In the desired Activity class, ``import com.logentries.logger.Logentries;``

The following simple example shows the library used in a basic Android application Activity - where the logger is set
to use TCP with a Token UUID "`159axea4-xxxx-xxxx-xxxx-xxxxxxxxxxxx`" - this is a dummy token for demonstration purposes only.
Remember to replace the Token with one taken from the Log created from the earlier setup.

When a new instance of the Activity is created, a simple log event message is sent. For further information on the Android
 Activity and its life cycle, refer to the official Android developer documentation.

```java
import android.app.Activity;
import android.os.Bundle;
import com.logentries.logger.Logentries;
import java.io.IOException;

public class MyActivity extends Activity {
	private Logentries logger = null;

	/**
	 * Called when the activity is first created.
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		try {
			logger = Logentries.init(this, "159axea4-xxxx-xxxx-xxxx-xxxxxxxxxxxx");
		} catch (IOException e) {
			e.printStackTrace();
		}
		logger.log("MyActivity has been created");
		//or
		Logentries.getInstance().log("MyActivity has been created");
	}
}
```

The number and type of arguments of the 'Logentries.init' are as follows:

(Context context, String token)

- 'context' : for example, if in an Activity class, use ``getApplicationContext()``, or if in an Application class, use ``getBaseContext()``.

- 'token' : the Token UUID, this is unique to the log to which the log events are sent
 	This can be copied from the log in the the Logentries Account

## Development

Build the project into a jar using:

    $ ./gradlew jar

You can also upload the jar to `bintray` using:

    $ ./gradlew bintrayUpload

In order to upload to `bintray` you will need to set up some values in a `local.properties` file.
This file should contain your `bintray` information and also your repo settings.

Once uploaded to `bintray` you should be able to add this library as a dependency as normal using in your `pom.xml` or `build.gradle` file.
More details on which `repo` to include can be found on the `bintray` website.
