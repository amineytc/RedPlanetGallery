package com.amineaytac.redplanetgallery.ui.search

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
class SearchViewModel @Inject constructor(private val marsRepository: MarsRepository) :
    ViewModel() {

    val searchMarsItems: MutableLiveData<Resource<List<Photo>>> = MutableLiveData()
    fun searchMarsItems(search: String) = viewModelScope.launch {
        if (search.length >= 3) {
            searchMarsItems.postValue(handleResponse(marsRepository.getSearchMarsPicture(search)))
        } else {
            searchMarsItems.postValue(Resource.Success(emptyList()))
        }
    }
    private fun handleResponse(response: Resource<List<Photo>?>): Resource<List<Photo>> {
        return when (response) {
            is Resource.Success -> Resource.Success(response.data.orEmpty())
            is Resource.Error -> Resource.Error(response.message.orEmpty())
            is Resource.Loading -> Resource.Loading()
        }
    }
}