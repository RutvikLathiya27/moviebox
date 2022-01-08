package com.example.moviebox.ui.home.ShowViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moviebox.Repository.ShowsRepository

class ShowViewModelFactory(private val showsRepository: ShowsRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ShowViewModel(showsRepository) as T
    }


}