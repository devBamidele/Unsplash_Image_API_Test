package com.example.unsplashimageapi.prioritySetup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.unsplashimageapi.R
import com.example.unsplashimageapi.databinding.FragmentPriorityBinding


class PriorityFragment : Fragment() {

    private val viewModel: PriorityViewModel by viewModels()

    private var binding: FragmentPriorityBinding? = null

    /**
     * Inflates the layout with Data Binding,
     * sets its lifecycle owner to the OverviewFragment to enable Data Binding to observe live data
     *
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentPriorityBinding.inflate(inflater)

        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding!!.lifecycleOwner = this

        // Giving the binding access to the OverviewViewModel
        binding!!.viewModel = viewModel



        //.doOnTextChanged

        // Finally returns the binding
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    /**
     * This function will be called when the user clicks the search button
     */
    fun executeSearch(){
        val query = binding?.queryText?.text.toString()


        when {
            !viewModel.confirmValid(query) -> {
                inValidInput(true)
            }
            else -> {
                viewModel.setQuery(query)
                inValidInput(false)
            }
        }


    }


    private fun inValidInput(error: Boolean) {
        if(error) {
            binding?.apply {
                searchField.let{
                    it.isErrorEnabled = true
                    it.error = getString(R.string.invalid)
                }
            }
        } else {
            binding?.apply {
                searchField.isErrorEnabled = false
            }
        }
    }


}