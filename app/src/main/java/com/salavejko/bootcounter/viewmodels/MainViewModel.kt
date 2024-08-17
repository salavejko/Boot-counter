package com.salavejko.bootcounter.viewmodels

import androidx.lifecycle.ViewModel
import com.salavejko.bootcounter.repositories.BootCounterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(repository: BootCounterRepository) : ViewModel() {
    val bootEventsFlow = repository.getAllBootEvents().map {
        return@map it.groupBy { it.date }.map {
            Pair(it.key, it.value.size)
        }
    }
}