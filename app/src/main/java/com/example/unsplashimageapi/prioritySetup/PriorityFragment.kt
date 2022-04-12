package com.example.unsplashimageapi.prioritySetup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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

        binding?.apply {
            Go.setOnClickListener { executeSearch() }
        }

    }

    /**
     * This function will be called when the user clicks the search button
     */
    private fun executeSearch(){
        val query = binding?.queryText?.text.toString()

        when {
            // If the query does not meet some requirements
            !viewModel.confirmValid(query) -> {

                // Call on the function to show the error
                inValidInput(true)

                // A toast to ensure functionality works fine
                showToastMessage(1)

            }
            else -> {
                // Assign the query text to the view model
                viewModel.setQuery(query)

                // Call on the function to not show the error
                inValidInput(false)

                // A toast message to ensure functionality works fine
                showToastMessage(0)
            }
        }
    }

    /**
     * Handles the error display
     */
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

    /**
     * A toast message will always show until the Internet Api is completed
     */
    private fun showToastMessage(value : Int): Boolean {

        /**
         * Set the text depending on the value of the parameters passed
         */
        val text: String = when (value) {
            1 -> {
                getString(R.string.error_toast)
            }
            else -> {
                getString(R.string.valid_toast)
            }
        }

        Toast.makeText(activity, text, Toast.LENGTH_SHORT ).show()
        return true
    }
}