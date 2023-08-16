package com.example.laba4_4

interface Listener<T: IParam> {
    fun onClick(param: T)
}
interface IParam