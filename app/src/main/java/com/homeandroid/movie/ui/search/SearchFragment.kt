package com.homeandroid.movie.ui.search

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.TextView.OnEditorActionListener
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.homeandroid.movie.R
import com.homeandroid.movie.databinding.SearchFragmentBinding
import com.homeandroid.movie.util.Recyclerview_Adapter
import org.koin.androidx.viewmodel.ext.android.viewModel


class SearchFragment : Fragment() {


    private val viewModel by viewModel<SearchViewModel>()
    private lateinit var binding: SearchFragmentBinding
    private lateinit var recyclerviewAdapter: Recyclerview_Adapter

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        binding = SearchFragmentBinding.inflate(inflater, container, false)

        return binding.root

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        recyclerviewAdapter = Recyclerview_Adapter(null)
        binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                if (s.length >= 1) {
                    binding.ImClear.visibility = View.VISIBLE
                } else {
                    binding.ImClear.visibility = View.GONE
                }
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {}
        })

        binding.etSearch.setOnEditorActionListener(OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                hideKeyboard()
                ActionSearch()

                return@OnEditorActionListener true
            }
            false
        })

        binding.imgSearch.setOnClickListener {
            hideKeyboard()
            ActionSearch()
        }

        binding.ImClear.setOnClickListener {
            binding.etSearch.setText("")
            binding.ImClear.visibility = View.GONE
        }



        viewModel.list_movie.observe(viewLifecycleOwner) {

            binding.progress.visibility = View.GONE
            binding.recyclerview.visibility = View.VISIBLE

            binding.recyclerview.layoutManager =
                    StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
            // LinearLayoutManager(requireActivity())

            recyclerviewAdapter = Recyclerview_Adapter(it.Search!!)
            binding.recyclerview.adapter = recyclerviewAdapter

            recyclerviewAdapter.OnClickListener(object : Recyclerview_Adapter.Click_Item {
                override fun item_movie(id: String) {
                    findNavController().navigate(SearchFragmentDirections.actionSearchFragmentToDetailsFragment(id))
                    //   val bundle = Bundle()
                    //   bundle.putString("id", id)
                    //   findNavController().navigate(R.id.action_homeFragment_to_detailsFragment,bundle)
                }
            })


        }
    }

    fun ActionSearch() {

        viewModel.get_movie(binding.etSearch.text.toString())
        binding.progress.visibility = View.VISIBLE
        binding.recyclerview.visibility = View.GONE
    }

    fun hideKeyboard() {
        val view = getActivity()?.currentFocus
        if (view != null) {
            val imm = getActivity()?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }

        }

    fun showKeyboard() {
        val view = getActivity()?.currentFocus
        if (view != null) {
            val imm = getActivity()?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.showInputMethodAndSubtypeEnabler(tag);
        }

    }

}

