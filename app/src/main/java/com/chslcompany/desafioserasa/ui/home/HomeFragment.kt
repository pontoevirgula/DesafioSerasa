package com.chslcompany.desafioserasa.ui.home

import android.os.Bundle
import android.view.*
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import com.chslcompany.desafioserasa.R
import com.chslcompany.desafioserasa.core.util.*
import com.chslcompany.desafioserasa.databinding.FragmentHomeBinding
import com.chslcompany.desafioserasa.domain.model.Results
import dagger.hilt.android.AndroidEntryPoint

const val DETAIL = "DETAIL"

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var homeAdapter: HomeAdapter
    private lateinit var connectionLiveData: ConnectionLiveData

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        setupToolbar()

        viewModel.getCharacters(ts().toLong(), apiKey(), hash())
        connectionLiveData = ConnectionLiveData(requireActivity())
        setupObserve()

    }

    private fun setupObserve() {
        viewModel.response.observe(viewLifecycleOwner) {
            if (viewLifecycleOwner.lifecycle.currentState != Lifecycle.State.RESUMED) return@observe
            binding.progressBar.visibility = if (it.loading == true) View.VISIBLE else View.GONE
            when (it.status) {
                Status.SUCCESS -> {
                    it.data?.let { response ->
                        if (binding.txtFeedbackUser.isVisible){
                            binding.txtFeedbackUser.isVisible.not()
                        }
                        setRecyclerView(response.data.results as MutableList<Results>)
                    }
                }
                Status.ERROR -> {
                    binding.txtFeedbackUser.run {
                        visibility = View.VISIBLE
                        text = it.error?.message
                    }

                }
                Status.LOADING -> {
                    it?.loading?.let { status ->
                        binding.progressBar.visibility = if (status) View.VISIBLE else View.GONE
                    }
                }
            }
        }

        viewModel.insert.observe(viewLifecycleOwner) {
            if (viewLifecycleOwner.lifecycle.currentState != Lifecycle.State.RESUMED) return@observe
            when (it.status) {
                Status.SUCCESS -> {
                    it.data?.let {
                        message(binding.rvHome, getString(R.string.bookmarking_success))
                    }
                }
                Status.ERROR -> {
                    message(binding.rvHome, getString(R.string.bookmarking_error))
                }
                Status.LOADING -> {
                }
            }
        }

        connectionLiveData.observe(viewLifecycleOwner) { isNetworkAvailable ->
            if (!isNetworkAvailable) {
                binding.txtFeedbackUser.run {
                    setRecyclerView(mutableListOf())
                    visibility = View.VISIBLE
                    text = context.getString(R.string.connection_failed)
                }
            } else {
                binding.txtFeedbackUser.run {
                    visibility = View.GONE
                    viewModel.getCharacters(ts().toLong(), apiKey(), hash())
                }
            }
        }
    }

    private fun setAdapter(results: MutableList<Results>) {
        homeAdapter = HomeAdapter( results,
            { result ->
                findNavController().navigate(R.id.action_homeFragment_to_detailFragment,
                    Bundle().apply { putSerializable(DETAIL, result) })
            }) { result ->
            viewModel.insert(result)
        }
    }

    private fun setRecyclerView(results: MutableList<Results>) {
        setAdapter(results)
        binding.rvHome.run {
            setHasFixedSize(true)
            adapter = homeAdapter
        }
    }

    private fun setupToolbar() {
        (activity as HomeActivity).setSupportActionBar(binding.customToolbar)
        (activity as HomeActivity).supportActionBar?.title = ""
    }

    private fun message(view: View, message: String) {
        (activity as HomeActivity).showMessage(view, message)
    }

}