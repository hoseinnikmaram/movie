package com.homeandroid.movie.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.homeandroid.movie.R
import com.homeandroid.movie.databinding.HomeFragmentBinding
import com.homeandroid.movie.util.Recyclerview_Adapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {


   private val viewModel by viewModel<HomeViewModel>()
    private  var binding: HomeFragmentBinding? =null
    private lateinit var recyclerviewAdapter: Recyclerview_Adapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
            binding = HomeFragmentBinding.inflate(inflater,container,false)

        return binding?.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.get_movie("batman")

        viewModel.list_movie.observe(viewLifecycleOwner) {
            binding!!.progress.visibility=View.GONE
            binding?.recyclerview!!.layoutManager =
                    StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
            // LinearLayoutManager(requireActivity())

            recyclerviewAdapter = Recyclerview_Adapter(it.Search!!)
            binding?.recyclerview!!.adapter = recyclerviewAdapter

            recyclerviewAdapter.OnClickListener(object :Recyclerview_Adapter.Click_Item{
                override fun item_movie(id: String) {
                    findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToDetailsFragment(id))

                    // val bundle = Bundle()
                    // bundle.putString("id", id)
                    //findNavController().navigate(R.id.action_homeFragment_to_detailsFragment,bundle)
                }
            })
        }

    }
}