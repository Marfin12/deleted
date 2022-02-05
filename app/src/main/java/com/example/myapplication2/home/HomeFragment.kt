package com.example.myapplication2.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication2.R
import com.example.myapplication2.core.data.Resource
import com.example.myapplication2.core.ui.TourismAdapter
import com.example.myapplication2.databinding.FragmentHomeBinding
import com.example.myapplication2.detail.DetailTourismActivity
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModel()

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {

//            hapus kode berikut
//            val factory = ViewModelFactory.getInstance(requireActivity())
//            homeViewModel = ViewModelProvider(this, factory)[HomeViewModel::class.java]

            val tourismAdapter = TourismAdapter()
            tourismAdapter.onItemClick = { selectedData ->
                val action = HomeFragmentDirections.actionHomeFragmentToDetailActivity(selectedData)
                findNavController().navigate(action)
            }

            homeViewModel.tourism.observe(viewLifecycleOwner, { tourism ->
                if (tourism != null) {
                    when (tourism) {
                        is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                        is Resource.Success -> {
                            binding.progressBar.visibility = View.GONE
                            tourismAdapter.setData(tourism.data)
                        }
                        is Resource.Error -> {
                            binding.progressBar.visibility = View.GONE
                            binding.viewError.root.visibility = View.VISIBLE
                            binding.viewError.tvError.text = tourism.message ?: getString(R.string.something_wrong)
                        }
                    }
                }
            })

            with(binding.rvTourism) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = tourismAdapter
            }
        }
    }

//    fun goToDetailScreen() {
//        val action = HomeFragmentDirections.actionHomeFragmentToDetailActivity()
//        findNavController().navigate(action)
//    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
