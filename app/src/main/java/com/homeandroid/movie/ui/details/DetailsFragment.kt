package com.homeandroid.movie.ui.details

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.homeandroid.movie.R
import com.homeandroid.movie.databinding.DetailsFragmentBinding
import com.homeandroid.movie.databinding.HomeFragmentBinding
import com.homeandroid.movie.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailsFragment : Fragment() {



    private val viewModel by viewModel<DetailsViewModel>()
    private lateinit var binding:DetailsFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DetailsFragmentBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.back.setOnClickListener {

            getActivity()?.onBackPressed()
        }
       val id=DetailsFragmentArgs.fromBundle(requireArguments()).id
            //arguments?.getString("id")
        binding.data = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.get_movie_details(id)


    }

}