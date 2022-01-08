package com.example.moviebox.ui.home.FragmentContainer

interface Communicator {

    fun passData(movieId: String)

    fun passShowData(showId: String)

    fun passKey(key : String)


}