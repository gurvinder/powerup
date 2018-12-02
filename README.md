<img src= "https://static.wixstatic.com/media/949cc6_31587017db2f43738a6ee9d36b743450~mv2.png/v1/crop/x_0,y_0,w_976,h_498/fill/w_400,h_200,al_c,usm_0.66_1.00_0.01/949cc6_31587017db2f43738a6ee9d36b743450~mv2.png"/> <img src= "https://upload.wikimedia.org/wikipedia/commons/thumb/f/fe/2018_FIRST_Power_Up_game_logo.svg/1200px-2018_FIRST_Power_Up_game_logo.svg.png" height="200px" width="400px"/>

## Rahway Robotribe Robotics FRC Team #1228

[![License](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE) [![Java](https://img.shields.io/badge/Java-1.8.0__152-blue.svg)](https://www.java.com) [![wpilibj](https://img.shields.io/badge/wpilibj-tested-brightgreen.svg)](http://first.wpi.edu/FRC/roborio/release/docs/java/) [![AHRS](https://img.shields.io/badge/AHRS-tested-brightgreen.svg)](https://pdocs.kauailabs.com/navx-mxp/software/roborio-libraries/java/) 

This is the code for FRC Power Up(2018) as written by Gurvinder Singh[Lead Programmer] for the team. This code was written between January - March 2018 and has been updated during/after competition season for various reasons including but not limited to changing PWM ports, addition/deletion of physical sensors, etc.
* [Getting Started](#getting-started)
* [Requirements](#requirements)
  * Eclipse IDE
  * Java 8 JDK
  * Install the correct development plugins
  * Image the roboRio
  * Program the Radio
* [Installation](#installation)
* [Built With](#built-with)
  * wpilibj
  * AHRS
* [License](#license)

### Getting Started
These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. These instructions will also help you deploy your code to the roboRio. See requirements & blank for notes on how to deploy the project on a live system.

### Requirements ###
* [Eclipse IDE](https://www.eclipse.org/downloads/) - Used to compile/build and deploy code to roboRio.
* [Java 8 JDK](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) - To use Eclipse you must have Java 8 JDK installed on your system. Do not select Java 9 as it is not supported.
  * Make sure JDK 8 is currently the one being used/selected
    * Open Preferences and Navigate to the "Installed JREs" tab
    * Select JDK 1.8.* and click "OK"
* [Install the correct development plugins:](https://wpilib.screenstepslive.com/s/currentCS/m/getting_started/l/599679-installing-eclipse-c-java)
  * Open Eclipse IDE
  * Select "Help"
  * Click "Install new software"
  * From here you need to add a software update site, the location where the plugins will be downloaded. Push the "Add..."   button then fill in the "Add Repository" dialog with:
      * Name: FRC Plugins
      * Location: http://first.wpi.edu/FRC/roborio/release/eclipse/
  * Click "OK"
* [Image the roboRio:](https://wpilib.screenstepslive.com/s/currentCS/m/getting_started/l/144984-imaging-your-roborio)
  * Make sure roboRIO imaging tool and latest image are installed with the latest [NI Update Suite](https://wpilib.screenstepslive.com/s/currentCS/m/labview/l/144150-installing-the-frc-update-suite-all-languages).
  * Connect the roboRIO USB Device port to the PC using a USB Type-A (Male) to Type-B (Male) cable
    * **The roboRIO should only be imaged via the USB connection. It is not recommended to attempt imaging using the Ethernet connection.**
  * Launch the imaging tool and after launching, the roboRIO Imaging Tool will scan for available roboRIOs and indicate any found in the top left box
  * Begin imaging process
    * Make sure the roboRIO is selected in the top left pane
    * Enter your team number in the box in the top right
    * Make sure the Disable RT Startup App box is unchecked in the bottom right
    * Check the box to Format Target and select the latest image version in the box
    * Click Reformat to begin the imaging process
  * Reboot the roboRIO using the Reset button to have the new team number take effect
  * Recompile and deploy any robot code
* [Program the Radio:](https://wpilib.screenstepslive.com/s/currentCS/m/getting_started/l/144986-programming-your-radio)
  * Download the latest [FRC Radio Configuration Utility](https://firstfrc.blob.core.windows.net/frc2018/Radio/FRC_Radio_Configuration_18_1_0.zip) Installer
  * Disable WiFi connections on your computer, as it may prevent the configuration utility from properly communicating with the bridge
  * Launch the software and refresh the available network interfaces
  * Select the ethernet interface called "Local Area Connection"
  * Click "OK"
  * Update or re-load the FRC Firmware to OpenMesh radio
    * Make sure the OpenMesh radio is selected in the Radio dropdown
    * Make sure the radio is connected to the PC via Ethernet and is detected
    * Press the Load Firmware button
    * When prompted, plug in the radio power. The software should detect the radio, load the firmware and prompt you when complete
  * Configuration Process
    * Make sure the correct Team #### is displayed in the text box
    * Enter WPA Key - (Optional)
    * Make sure radio and mode are correct
    * Click "Configure"

### Installation ###
* Open Eclipse IDE
* Load current project into project Explorer
  * Right click on the project in the Project Explorer
  * Select "Run As"
  * Select "WPILib Java Deploy"
    * **There is no need to reboot the roboRIO to run the new code, as the newly deployed code will begin running automatically**

### Built With ###
* [wpilibj](http://first.wpi.edu/FRC/roborio/release/docs/java/) - Set of Java classes that controls the hardware in the FRC control system.
* [AHRS](https://pdocs.kauailabs.com/navx-mxp/software/roborio-libraries/java/) - Used to control navX-MXP 9-axis inertial/magnetic sensor and motion processor.

### License ###
This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for more details.
- - - -
_a project by [Gurvinder Singh](https://github.com/gurvinder)_
