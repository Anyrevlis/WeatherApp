package com.example.weatherapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.ViewModel.SearchViewModel
import com.example.weatherapp.databinding.SearchFragmentBinding
import com.example.weatherapp.databinding.SearchResultItemBinding
import com.example.weatherapp.utils.DiffCallback
import com.example.weatherapp.utils.SearchResultsAdapter

class SearchFragment : Fragment() {

    private lateinit var binding : SearchFragmentBinding
    private lateinit var searchResults : RecyclerView
    private lateinit var viewModel : SearchViewModel
    private lateinit var searchView: SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =SearchFragmentBinding.inflate(inflater, container,false)

        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchResults = binding.recyclerview
        searchResults.layoutManager = LinearLayoutManager(context)
        searchView = binding.searchview

        val adapter = SearchResultsAdapter(DiffCallback())
        searchResults.adapter = adapter

        viewModel = ViewModelProvider(this).get(SearchViewModel::class.java)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                viewModel.search(query)
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                adapter.filter.filter(newText)
                return true
            }
        })
        viewModel.searchResults.observe(viewLifecycleOwner) { results ->
            adapter.submitList(results)
        }
    }
}
