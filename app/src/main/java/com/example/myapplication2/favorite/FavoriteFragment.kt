package com.example.myapplication2.favorite

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication2.core.ui.TourismAdapter
import com.example.myapplication2.databinding.FragmentFavoriteBinding
import com.example.myapplication2.detail.DetailTourismActivity
import org.koin.android.viewmodel.ext.android.viewModel

class FavoriteFragment : Fragment() {

    private val favoriteViewModel: FavoriteViewModel by viewModel()

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        if (activity != null) {

//            hapus kode berikut
//            val factory = ViewModelFactory.getInstance(requireActivity())
//            favoriteViewModel = ViewModelProvider(this, factory)[FavoriteViewModel::class.java]

            val tourismAdapter = TourismAdapter()
            tourismAdapter.onItemClick = { selectedData ->
                val action = FavoriteFragmentDirections.actionFavoriteFragmentToDetailActivity(selectedData)
                findNavController().navigate(action)
            }

            favoriteViewModel.favoriteTourism.observe(viewLifecycleOwner, { dataTourism ->
                tourismAdapter.setData(dataTourism)
                binding.viewEmpty.root.visibility = if (dataTourism.isNotEmpty()) View.GONE else View.VISIBLE
            })

            with(binding.rvTourism) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = tourismAdapter
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
