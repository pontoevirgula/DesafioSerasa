package com.chslcompany.desafioserasa.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.chslcompany.desafioserasa.R
import com.chslcompany.desafioserasa.core.util.Status
import com.chslcompany.desafioserasa.databinding.FragmentDetailBinding
import com.chslcompany.desafioserasa.domain.model.Results
import com.chslcompany.desafioserasa.ui.home.DETAIL
import com.chslcompany.desafioserasa.ui.home.HomeActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding
    private val viewModel: DetailViewModel by viewModels()
    private lateinit var results: Results

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        results = arguments?.getSerializable(DETAIL) as Results

        setToolbar()
        back()
        fillDataDetail(results)

        setupObserver()

        binding.fab.setOnClickListener {
            viewModel.insert(results)
        }

    }

    private fun setupObserver(){
        viewModel.insert.observe(viewLifecycleOwner){
            if (viewLifecycleOwner.lifecycle.currentState != Lifecycle.State.RESUMED) return@observe
            when(it.status){
                Status.SUCCESS -> {
                    it.data?.let {
                        message(binding.fab, getString(R.string.character_saved))
                    }

                }
                Status.ERROR -> {
                    message(binding.fab, getString(R.string.error_bookmark))
                }
                Status.LOADING -> {}
            }
        }
    }

    private fun setToolbar() {
        val activity = (activity as AppCompatActivity)
        activity.run {
            setSupportActionBar(binding.toolbar)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_white_24)
            supportActionBar?.title = ""
        }
    }

    private fun back() {
        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun fillDataDetail(results: Results) {
        binding.run {
            setImage(results, imgPoster)
            setImage(results, imgMiniPoster)
            txtTitle.text = results.name
            txtDescription.text = results.description
        }

    }

    private fun message(view: View, message: String) {
        (activity as HomeActivity).showMessage(view, message)
    }

    private fun FragmentDetailBinding.setImage(results: Results, image: ImageView)
            = Glide.with(this@DetailFragment)
        .load("${results.thumbnail.path}.${results.thumbnail.extension}")
        .fitCenter()
        .into(image)
}