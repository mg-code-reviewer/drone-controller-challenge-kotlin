package com.sumup.drone_challenge.logic

interface IDirection {
    val name: String
    fun turnLeft()
    fun turnRight()
    fun moveForward()
    fun init()
}