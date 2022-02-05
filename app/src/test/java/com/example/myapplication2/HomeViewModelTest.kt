package com.example.myapplication2

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.example.myapplication2.core.data.Resource
import com.example.myapplication2.core.domain.model.Tourism
import com.example.myapplication2.core.domain.usecase.TourismUseCase
import com.example.myapplication2.home.HomeViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations.initMocks

@RunWith(JUnit4::class)
class HomeViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = TestCoroutineDispatcher()

    private lateinit var mockTourismUseCase: TourismUseCase
    private lateinit var spyHomeViewModel: HomeViewModel

    @Mock
    lateinit var observer: Observer<Resource<List<Tourism>>>

    @Mock
    lateinit var mockFlowTourismUseCase: Flow<Resource<List<Tourism>>>

    @Mock
    lateinit var mockLiveDataTourismUseCase: LiveData<Resource<List<Tourism>>>

    @Before
    fun setUp() {
        initMocks(this)
        Dispatchers.setMain(testDispatcher)
        mockTourismUseCase = mock(TourismUseCase::class.java)
        mockFlowTourismUseCase = mock(Flow::class.java) as Flow<Resource<List<Tourism>>>
        mockLiveDataTourismUseCase = mock(LiveData::class.java) as LiveData<Resource<List<Tourism>>>
        `when`(mockTourismUseCase.getAllTourism()).thenReturn(mockFlowTourismUseCase)
        val homeViewModel = HomeViewModel(mockTourismUseCase)
        spyHomeViewModel = spy(homeViewModel)
    }

    @Test
    fun lastMatchViewModelObserverTest() {
        spyHomeViewModel.tourism.observeForever(observer)

        assert(spyHomeViewModel.tourism.hasObservers() == true)
    }

}