# 315Battle_Java-OpenGL
Eclipse projects of 3D naval battle game



## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites
Eclipse for Java
JOGL 2.1
Download: http://jogamp.org/deployment/archive/rc/

Only following jars corresponding to your own system.
```
Gluegen-rt
Gluegen-rt-natives
Jogl-all
Jogl-all-natives
Nativewindow-natives
Newt-natives-

```

### Installing

1.Copy the project into workspace the import it

```
Ecplise->File->import->choose your project
```

2.Installing JOGL

```
Project-> Add Path-> JOGL2.1
```


## Running 

Explain how to run the automated tests for this system

### Game Operation

Explain what these tests test and why

```
Give an example
```

## Authors

Yang Guo & Liqun Yang


## Project Details

As a teamwork project and the first work after mature programming technology, understanding software engineering is particularly important. After carefully analyzing the key points and difficulties of this development, we decided to abandon the traditional waterfall model and switch to the development model of evolutionary prototype development. To this end, we have done related learning and divided the development of the game into three steps.

1. Basic Roaming Edition 1.0

Initially try to build 3d scenes with jogl2 and implement roaming in the scene. The implementation method is mainly completed by using a mobile viewpoint. That is, the viewpoint has a correspondence with the coordinates of the player, thereby forming a first person effect similar to CS.

At the same time, the shell and gravity acceleration are implemented on the 1.5 version, and the Weaponcontrol class is completed, so that the player can freely roam and fire in the scene. Roaming mainly tests the roaming effect with reference to a stationary reference. This version is based on experiments, mainly in the experimental jogl2 all methods, and some applications of graphics.
However, during the development process, I gradually discovered that there is no feedback from the falling of the shells after the firing, which will greatly reduce the gameplay. This has become a problem that needs to be solved separately in the next development.

2. AI Battle Edition 2.0

In this version, in order to make the game preliminary and have some playability, we have perfected most of the details, including the game background (using textures, low memory consumption, and good results), water ripples, waves, etc. At the same time, in order to increase the playability of the game, a fine ai is designed to show the true naval battle scene as perfectly as possible. The 2.2 version is perfect for the operation class Eventlistener. Its operation mode emulates the operation mode of the online game "Sword Spirit". The mouse and the keyboard are used together to solve the drawbacks of the java self-listener, making the interaction with the player smooth and natural.

3. Network Battle Integration Edition 3.0

Finally, we started to achieve online battles on the network (2.5beta version of the initial test to achieve the final game. In 3.0 we finally perfected the start interface, making the game truly become an exquisite crafts), making this game truly become a game At least there is a certain amount of game software. In the development, we originally planned to adopt the popular client/server model, but considering that there is no fixed server, we finally decided to implement it by adopting the p2p network model


