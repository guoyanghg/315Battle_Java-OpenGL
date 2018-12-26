# 315Battle_Java-OpenGL
Eclipse projects of 3D naval battle game

Battle3.0 is the latest version.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. 

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

## Game Operation

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


## Authors

Yang Guo & Liqun Yang


## Project Details

As a teamwork project, and as one of the first tasks after learning programming, understanding software engineering is particularly important. After carefully analyzing the key points and difficulties of this development, we decided to abandon the traditional waterfall model and switch to the evolutionary prototype development model. To this end, we have divided the development of the game into three phases.

* Basic Roaming Edition 1.0

Initially we built the 3d scenes with jogl2 and implemented roaming in the scenes. The implementation was mainly completed by using a "mobile viewpoint". That is, the viewpoint corresponded with the coordinates of the player, thereby forming a first person effect similar to Counter-Strike.

Subsequently, the shell and gravity acceleration were implemented on the 1.5 version, and the Weaponcontrol class was completed, so that the player can freely roam and fire in the scene. Roaming mainly tests the roaming effect with reference to a stationary reference. This version is based on experiments, mainly in the experimental jogl2 all methods, and some applications of graphics.

However, during the development process, we gradually discovered that there is no feedback from the falling of the shells after firing them. This has greatly reduced the gameplay. This has become a problem that needs to be solved separately in the next development.

* AI Battle Edition 2.0

In this version, in order to make the game preliminary and have some playability, we perfected most of the details, including the game background (using textures, low memory consumption, and good results), water ripples, waves, etc. At the same time, in order to increase the playability of the game, a fine AI was designed to show the true naval battle scene as perfectly as possible. The 2.2 version perfected the operation class Eventlistener. Its operation mode emulates the operation mode of the online game "Sword Spirit". The mouse and the keyboard are used together to solve the drawbacks of the java self-listener, making the interaction with the player smooth and natural.

* Network Battle Integration Edition 3.0

Finally, we started to achieve online battles on the network. We used the 2.5beta version of the initial test to achieve the final game. In 3.0 we finally perfected the start interface, making the game functional enough to truly become a game. Whilst the game is not yet fully complete, at least there is a basic amount of game software. In the development, we originally planned to adopt the popular client/server model, but considering that there is no fixed server, we finally decided to implement it by adopting the p2p network model.
