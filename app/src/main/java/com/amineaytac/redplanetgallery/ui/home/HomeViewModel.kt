package com.amineaytac.redplanetgallery.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amineaytac.redplanetgallery.core.common.Resource
import com.amineaytac.redplanetgallery.core.data.repo.MarsRepository
import com.amineaytac.redplanetgallery.core.network.dto.Photo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val marsRepository: MarsRepository
) : ViewModel() {

    val getMarsItems: MutableLiveData<Resource<List<Photo>>> = MutableLiveData()

    init {
        getMarsItems()
    }

    private fun getMarsItems() {
        viewModelScope.launch {
            getMarsItems.postValue(
                handleResponse(marsRepository.getAllMarsPictures()) as Resource<List<Photo>>?
            )
        }
    }

    fun isItemFavorited(itemId: String): LiveData<Boolean> {
        return marsRepository.isItemFavorited(itemId)
    }

    private fun handleResponse(response: Resource<List<Photo>?>): Resource<out List<Photo>?> {
        return when (response) {
            is Resource.Success -> Resource.Success(response.data.orEmpty())
            is Resource.Error -> Resource.Error(response.message.orEmpty())
            is Resource.Loading -> Resource.Loading()
        }
    }
}