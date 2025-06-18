package com.example.fp_batik;

public class BatikViewModel {
}
class BatikViewModel(private val repository: BatikRepository) : ViewModel() {
    private val _batikDetail = MutableLiveData<Batik?>()
    val batikDetail: LiveData<Batik?> = _batikDetail

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?> = _errorMessage

    fun loadBatikDetail(batikId: String) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val result = repository.getBatikById(batikId)
                _batikDetail.value = result
            } catch (e: Exception) {
                _errorMessage.value = "Gagal memuat data: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }
}