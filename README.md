# Country-Code-Picker


## Original Article on Dev.to Click below 👇

<a href="https://dev.to/siddharthsing/simple-country-code-picker-for-android-in-kotlin-5aeo">
  <img src="https://img.shields.io/badge/dev.to-0A0A0A?style=for-the-badge&logo=dev.to&logoColor=white" height="200px" width="500px">
  </a>



## App's Overview 
<p>

* In this article, I am going to demonstrate how to create a simple country code picker in an android application through Kotlin. 

* Also, how to validate phone numbers for every country possible.
<p> 

<img src="https://dev-to-uploads.s3.amazonaws.com/uploads/articles/kezbeo2v3tq3x3fw558c.gif" height="400px" width="700px">

## Table Of Content

- [Project Setup ](#setup)
- [Building the app's UI ](#UI)
- [Creating the Backend of the app](#back)
- [Writer's Support ❤️](#support)

<a name="setup"></a>
## Project Setup

* Add the below line of code in `build.gradle (project)` under `allprojects`.
```
maven { url "https://jitpack.io" }
``` 
The code snippet will look like the one below :
```

allprojects {
    repositories {
        google()
        jcenter()
        maven { url "https://jitpack.io" }
    }
}
```
* Dependency to be added : Add the dependency below in `build.gradle (Module)`
```
implementation 'com.github.joielechong:countrycodepicker:2.4.2'
```

The code snippet will look like the one below :
```
dependencies {

   ...
    other dependencies   
   ...

    /* Country Code Picker Dependency*/
    implementation 'com.github.joielechong:countrycodepicker:2.4.2'
}
```

---

<a name="UI"></a>
## App's UI

The app's UI is broken into two parts :

1. [Explanation of Country Code Picker (ccp)](#expCCP)
2. [Complete `.xml` code file (activity_main.xml)](#xml)

<a name="expCCP"></a>
> Explanation of ccp :

* The below code snippet shows the implementation of the country code picker through the `.xml` file. The below code snippet is not customized, it is the naive form of ccp but it will work completely fine in the android application. We shall see the customization in the next section of the blog.

```
<com.rilixtech.widget.countrycodepicker.CountryCodePicker
            android:id="@+id/ccp"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:padding="16dp"
            />

```

* Customization of ccp in .xml file
 
   1. **`app:ccp_defaultNameCode="IN"`** - `ccp_defaultNameCode` attribute will set the the default country of the ccp spinner.

   2. **`app:ccp_defaultCode="91"`** - `ccp_defaultCode` will similarly set the default country code in the spinner.

   3. **`app:ccp_textColor="#1C2020"`** - change the color of ccp according to app's theme with `ccp_textColor` attritubute.

   4. **`app:ccp_countryPreference="IN,US,NZ"`** - `ccp_countryPreference` will set some of the preferred countries on the top, so that they will be easily accessed. seethe exaple below :

 <img src="https://dev-to-uploads.s3.amazonaws.com/uploads/articles/y8ol5ocnb1kcwky8tqtx.png" height="500px" width="400px">

The customized code snippet :

```
  <com.rilixtech.widget.countrycodepicker.CountryCodePicker
            android:id="@+id/ccp"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            app:ccp_defaultNameCode="IN"
            app:ccp_defaultCode="91"
            app:ccp_textColor="#1C2020"
            android:padding="16dp"
            app:ccp_countryPreference="IN,US,NZ"/>

```
There are many attributes present in order to customize the ccp, you can get to know about them all in it's documentation [here](https://github.com/joielechong/CountryCodePicker).

<a name="xml"></a>
> Complete .xml file (activity_main.xml) :

```
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"

    tools:context=".MainActivity"
    >

    <LinearLayout
        android:id="@+id/llPhone"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="horizontal"
        android:layout_centerInParent="true"

        >

        <com.rilixtech.widget.countrycodepicker.CountryCodePicker
            android:id="@+id/ccp"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            app:ccp_defaultNameCode="IN"
            app:ccp_defaultCode="91"
            app:ccp_textColor="#1C2020"
            android:padding="16dp"
            app:ccp_countryPreference="IN,US,NZ"/>

        <EditText
            android:id="@+id/edtPhone"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:layout_marginRight="16dp"
            android:padding="15dp"
            android:hint="@string/enter_phone_number"
            android:importantForAutofill="no"
            android:inputType="number" />

    </LinearLayout>
    <Button
        android:id="@+id/btnSendOTP"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_below="@+id/llPhone"
        android:padding="15dp"
        android:layout_margin="16dp"
        android:text="Send OTP"
        android:textStyle="bold"
        />
</RelativeLayout>
```

---

<a name="back"></a> 
## App's Backend

* Let's first link all the UI components with `MainActivity.kt`

```
val ccp:CountryCodePicker = findViewById(R.id.ccp)
val edtPhoneNumber: EditText = findViewById(R.id.edtPhone)
val btnOTP: Button = findViewById(R.id.btnSendOTP)

```

* Tell ccp which edit text should be registered for phone numbers, use the below line of code
```
ccp.registerPhoneNumberTextView(edtPhoneNumber)
```

* Now, let us create a function to check the validity of the phone number entered wrt the country code selected.

   **`ccp.isValid`** will check whether the phone number 
      is correct or not.

The function looks like :

```
private fun checkValidity(ccp: CountryCodePicker): Boolean {

        return if(ccp.isValid) {
            Toast.makeText(this, "number " + ccp.fullNumber + " is valid.", Toast.LENGTH_SHORT).show()
            true
        } else {
            false
        }

    }
```

The function returns `true` if phone number entered is correct otherwise it will return `false`.

* Finally, at last we will handle the button click and we are done to see the magic.

```
    btnOTP.setOnClickListener{
            
            if(checkValidity(ccp))
            {
                //Change with your own functionality
                Toast.makeText(this, "OTP Sent", Toast.LENGTH_SHORT).show()

            }else{
                Toast.makeText(this, "Please enter a valid phone number", Toast.LENGTH_SHORT).show()
            }

        }

```
If the `checkValidity()` function returns true it will show a toast `OTP Sent`, you can change it with your function. 



---

<a name="support"></a> 
## Writer's Support
* If you find the article useful show some ❤️ by staring some of my repositories and following me on dev.to and github.
 <div>
  <p align="middle">
  <a href="https://www.linkedin.com/in/siddharth-singh-baghel-912866190/">
  <img src="https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white">
  </a>
  <a href="https://github.com/Siddharth-sing">
  <img src="https://img.shields.io/badge/GitHub-100000?style=for-the-badge&logo=github&logoColor=white">
  </a>
  <a href="https://dev.to/siddharthsing">
  <img src="https://img.shields.io/badge/dev.to-0A0A0A?style=for-the-badge&logo=dev.to&logoColor=white">
  </a>





