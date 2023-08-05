package com.ayeminoo.tsuka.models

sealed class InputType {

    class Number(val value : String) : InputType()
    object Point : InputType()

    object Clear : InputType()

    object RemoveOnce : InputType()


}
