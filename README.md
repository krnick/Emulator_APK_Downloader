Android Emulator Downloader via Python script
===========================


# Android

1. Clone the project

[Emulator_APK_Downloader](https://github.com/krnick/Emulator_APK_Downloader)

![](https://i.imgur.com/URAmo0w.png)

2. Install the AVD and install it.

3. Login to the Google Play store.

4. Open APK_Downloader app and allow the permission of AccessibilityService.

![](https://i.imgur.com/AYYkmRj.png)
![](https://i.imgur.com/1p1VAWd.png)




# Emulator

Search Emulator name to start.

```shell=
$ cd /home/$USER/Android/Sdk/tools/bin/

$ ./avdmanager list avd
```

![](https://i.imgur.com/0Bt9csb.png)


Start Emulator with GUI
```shell=
$ ./emulator -avd Pixel_API_26
```

Start Emulator without GUI

```shell=
$ ./emulator -avd Pixel_API_26 -no-audio -no-window
```



# Python adb

Install python-adb-pure

```shell=
$ pip3 install pure-python-adb
```


```python=
from adb.client import Client as AdbClient
# Default is "127.0.0.1" and 5037
client = AdbClient(host="127.0.0.1", port=5037)
device = client.device("emulator-5554")


apk_name = input("Package Name:")


shell_script = "am start -a android.intent.action.VIEW -d 'market://details?id=" + apk_name +"'"

device.shell(shell_script)
```

# Demo

![](https://i.imgur.com/F0S079y.gif)

