package com.amineaytac.redplanetgallery.ui.favorite

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amineaytac.redplanetgallery.core.data.model.FavoriteModel
import com.amineaytac.redplanetgallery.core.data.repo.MarsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val repository: MarsRepository
) : ViewModel() {

    val getMarsItems: MutableLiveData<List<FavoriteModel>?> = MutableLiveData()
    init { getItemFavorite() }
    private fun getItemFavorite() = viewModelScope.launch(Dispatchers.IO) {
        getMarsItems.postValue(repository.getItemFavorite()) }
    fun addItemFavorite(item: FavoriteModel) = viewModelScope.launch(Dispatchers.IO) {
        repository.addItemFavorite(item) }
    fun deleteItemFavorite(id: Int) = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteItemFavorite(id)
        getItemFavorite() }
}