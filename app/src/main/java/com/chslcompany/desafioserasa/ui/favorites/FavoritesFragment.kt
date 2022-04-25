package com.chslcompany.desafioserasa.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import com.chslcompany.desafioserasa.R
import com.chslcompany.desafioserasa.core.util.ConfirmDialog
import com.chslcompany.desafioserasa.core.util.Status
import com.chslcompany.desafioserasa.databinding.FragmentFavoritesBinding
import com.chslcompany.desafioserasa.ui.home.HomeActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritesFragment : Fragment() {
    private lateinit var binding: FragmentFavoritesBinding
    private val viewModel: FavoritesViewModel by viewModels()
    private lateinit var favoritesAdapter: FavoritesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter()
        setRecyclerView()
        setupObserve()

    }

    private fun setupObserve() {
        viewModel.getCharacters().observe(viewLifecycleOwner){ results ->
            when{
                results.isNotEmpty() -> favoritesAdapter.submitList(results)
                else -> {
                    favoritesAdapter.submitList(results)
                    message(binding.rvFavorites, getString(R.string.there_are_no_characters))
                }
            }
        }

        viewModel.delete.observe(viewLifecycleOwner){
            if (viewLifecycleOwner.lifecycle.currentState != Lifecycle.State.RESUMED) return@observe
            when(it.status) {
                Status.SUCCESS -> {
                    message(binding.rvFavorites, getString(R.string.exclude_success))
                }
                Status.ERROR -> {}
                Status.LOADING -> {}
            }
        }
    }

    private fun setRecyclerView() {
        binding.rvFavorites.run {
            setHasFixedSize(true)
            adapter = favoritesAdapter
        }
    }

    private fun setAdapter() {
        favoritesAdapter = FavoritesAdapter { result ->
            ConfirmDialog().apply {
                setYesListener {
                    viewModel.deleteCharacters(result)
                }
            }.show(parentFragmentManager, TAG)
        }
    }

    private fun message(view: View, message: String) {
        (activity as HomeActivity).showMessage(view, message)
    }

    companion object{
        var TAG: String = FavoritesFragment::class.java.simpleName
    }

}