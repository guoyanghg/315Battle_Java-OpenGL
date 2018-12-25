# 315Battle_Java-OpenGL
Eclipse projects of 3D naval battle game

Battle3.0 is the latest version.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites
* Eclipse for Java
* JOGL 2.1

Download the Jogl2.1 jars at
http://jogamp.org/deployment/archive/rc/

```
The dependency package name is:
Gluegen-rt
Gluegen-rt-natives
Jogl-all
Jogl-all-natives
Nativewindow-natives
Newt-natives-
Please download jars corresponding to your own system.

```

### Installing

* Copy it to the eclipse workspace, then import it using File->import 

```
Ecplise->File->import->choose your project
```

* Installing JOGL

```
Project-> Add Path-> JOGL2.1
```


## Running 

You can find the Main fuction in playground.java

### Game Operation

* Single Play

In order to operate the game, please use the following keys on the keyboard:

W S - to move forwards and backwards

Q E A D - to adjust the muzzle angle

F R - to adjust the firing speed

Space bar - to fire

Drag the left and right mouse buttons - to adjust the angle of view


* Online Battle

This requires a simple P2P connection between the two parties.

Both parties should open the P2P client, then the inviter should enter the IP address of the other party to request the P2P connection to be established.


### Authors

Yang Guo & Liqun Yang


### Project Details

As a teamwork project, and as one of the first tasks after learning programming, understanding software engineering is particularly important. After carefully analyzing the key points and difficulties of this development, we decided to abandon the traditional waterfall model and switch to the evolutionary prototype development model. To this end, we have divided the development of the game into three phases.

* Basic Roaming Edition 1.0

Initially we built the 3d scenes with jogl2 and implemented roaming in the scenes. The implementation was mainly completed by using a "mobile viewpoint". That is, the viewpoint corresponded with the coordinates of the player, thereby forming a first person effect similar to Counter-Strike.

Subsequently, the shell and gravity acceleration were implemented on the 1.5 version, and the Weaponcontrol class was completed, so that the player can freely roam and fire in the scene. Roaming mainly tests the roaming effect with reference to a stationary reference. This version is based on experiments, mainly in the experimental jogl2 all methods, and some applications of graphics.

However, during the development process, we gradually discovered that there is no feedback from the falling of the shells after firing them. This has greatly reduced the gameplay. This has become a problem that needs to be solved separately in the next development.

* AI Battle Edition 2.0

In this version, in order to make the game preliminary and have some playability, we perfected most of the details, including the game background (using textures, low memory consumption, and good results), water ripples, waves, etc. At the same time, in order to increase the playability of the game, a fine AI was designed to show the true naval battle scene as perfectly as possible. The 2.2 version perfected the operation class Eventlistener. Its operation mode emulates the operation mode of the online game "Sword Spirit". The mouse and the keyboard are used together to solve the drawbacks of the java self-listener, making the interaction with the player smooth and natural.

* Network Battle Integration Edition 3.0

Finally, we started to achieve online battles on the network. We used the 2.5beta version of the initial test to achieve the final game. In 3.0 we finally perfected the start interface, making the game functional enough to truly become a game. Whilst the game is not yet fully complete, at least there is a basic amount of game software. In the development, we originally planned to adopt the popular client/server model, but considering that there is no fixed server, we finally decided to implement it by adopting the p2p network model.

### Appendix
This is the contents of http://jogamp.org/deployment/archive/rc/

________________________________________
 	Parent Directory
 	- 	 
 	gluegen_3-jogl_2/
2013-06-25 23:09 	- 	 
 	gluegen_3-jogl_3/
2013-06-25 23:09 	- 	 
 	gluegen_4-jogl_4/
2013-06-25 23:09 	- 	 
 	gluegen_8-joal_1-jogl_11-jocl_1/
2013-06-25 23:09 	- 	 
 	gluegen_15-joal_7-jogl_18-jocl_6/
2013-06-25 23:09 	- 	 
 	gluegen_20-joal_10-jogl_23-jocl_12/
2013-06-25 23:09 	- 	 
 	gluegen_20-joal_10-jogl_23-jocl_13/
2013-06-25 23:09 	- 	 
 	gluegen_22-joal_12-jogl_25-jocl_15/
2013-06-25 23:09 	- 	 
 	gluegen_24-joal_14-jogl_27-jocl_17/
2013-06-25 23:09 	- 	 
 	gluegen_24-joal_14-jogl_28-jocl_18/
2013-06-25 23:09 	- 	 
 	gluegen_25-joal_15-jogl_30-jocl_19/
2013-06-25 23:09 	- 	 
 	gluegen_28-joal_17-jogl_41-jocl_25/
2013-06-25 23:09 	- 	 
 	gluegen_32-joal_19-jogl_44-jocl_28/
2013-06-25 23:09 	- 	 
 	gluegen_33-joal_20-jogl_45-jocl_29/
2013-06-25 23:09 	- 	 
 	gluegen_41-joal_24-jogl_49-jocl_33/
2013-06-25 23:09 	- 	 
 	gluegen_41-joal_24-jogl_50-jocl_34/
2013-06-25 23:09 	- 	 
 	gluegen_42-joal_25-jogl_53-jocl_35/
2013-06-25 23:09 	- 	 
 	gluegen_43-joal_26-jogl_57-jocl_36/
2013-06-25 23:09 	- 	 
 	gluegen_44-joal_27-jogl_58-jocl_37/
2013-06-25 23:09 	- 	 
 	gluegen_49-joal_29-jogl_60-jocl_38/
2013-06-25 23:09 	- 	 
 	gluegen_49-joal_29-jogl_62-jocl_39/
2013-06-25 23:09 	- 	 
 	gluegen_52-joal_32-jogl_66-jocl_41/
2013-06-25 23:09 	- 	 
 	t0
2010-12-12 23:17 	0 	 
 	test/
2013-06-25 23:08 	- 	 
 	util/
2011-09-14 05:01 	- 	 
 	v2.0-rc3/
2013-06-25 23:07 	- 	 
 	v2.0-rc4/
2013-06-25 23:07 	- 	 
 	v2.0-rc5/
2013-06-25 23:07 	- 	 
 	v2.0-rc6/
2013-06-25 23:07 	- 	 
 	v2.0-rc7/
2013-06-25 23:07 	- 	 
 	v2.0-rc8/
2013-06-25 23:07 	- 	 
 	v2.0-rc9/
2013-06-25 23:07 	- 	 
 	v2.0-rc10/
2013-06-25 23:07 	- 	 
 	v2.0-rc11/
2013-06-25 23:07 	- 	 
 	v2.0.2-rc12/
2013-06-25 22:26 	- 	 
 	v2.0.2/
2013-07-21 05:26 	- 	 
 	v2.1.0/
2013-10-16 12:59 	- 	 
 	v2.1.1/
2013-10-19 07:25 	- 	 
 	v2.1.2/
2013-11-01 21:11 	- 	 
 	v2.1.3/
2013-12-12 20:15 	- 	 
 	v2.1.4/
2014-01-31 16:50 	- 	 
 	v2.1.5/
2014-03-11 06:49 	- 	 
 	v2.2.0/
2014-08-07 02:55 	- 	 
 	v2.2.1/
2014-09-08 10:55 	- 	 
 	v2.2.2/
2014-09-24 01:34 	- 	 
 	v2.2.3/
2014-10-10 15:22 	- 	 
 	v2.2.4/
2014-10-10 17:59 	- 	 
 	v2.3.0/
2015-03-11 22:33 	- 	 
 	v2.3.1/
2015-03-27 18:41 	- 	 
 	v2.3.2/
2015-10-10 05:11 	- 	 
________________________________________
Apache/2.4.25 (Debian) Server at jogamp.org Port 80



