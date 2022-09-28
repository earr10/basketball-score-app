package mx.com.earr.basketballscoreapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private var _localScoreViewModel: MutableLiveData<Int> = MutableLiveData()
    private var _visitorScoreViewModel: MutableLiveData<Int> = MutableLiveData()

    val localScoreViewModel: LiveData<Int>
        get() = _localScoreViewModel

    val visitorScoreViewModel: LiveData<Int>
        get() = _visitorScoreViewModel

    init {
        resetScores()
    }

    fun resetScores() {
        _localScoreViewModel.value = 0
        _visitorScoreViewModel.value = 0
    }

    fun addPointsToScore(points: Int, isLocal: Boolean) {
        if (isLocal) {
            _localScoreViewModel.value = _localScoreViewModel.value?.plus(points)
        } else {
            _visitorScoreViewModel.value = _visitorScoreViewModel.value?.plus(points)
        }
    }

    fun decreaseLocalScore() {
        if (_localScoreViewModel.value!! > 0) {
            _localScoreViewModel.value = _localScoreViewModel.value?.minus(1)
        }
    }

    fun decreaseVisitorScore() {
        if (_visitorScoreViewModel.value!! > 0) {
            _visitorScoreViewModel.value = _visitorScoreViewModel.value?.minus(1)
        }
    }
}