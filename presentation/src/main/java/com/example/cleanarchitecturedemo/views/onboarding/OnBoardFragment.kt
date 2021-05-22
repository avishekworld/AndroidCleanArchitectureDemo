package com.example.cleanarchitecturedemo.views.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.cleanarchitecturedemo.databinding.FragmentOnBoardBinding
import com.example.cleanarchitecturedemo.viewmodels.OnBoardViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 * Use the [OnBoardFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class OnBoardFragment : Fragment() {

    private lateinit var binding : FragmentOnBoardBinding
    private val viewModel : OnBoardViewModel by viewModel()
    private val viewID : Int by lazy {
        arguments?.getInt(VIEW_ID) ?: 0
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnBoardBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.viewState.observe(viewLifecycleOwner) {
            renderProcessingView(it.processingViewState)
            renderContentView(it.contentViewState)
        }
        binding.nextButton.setOnClickListener {
            viewModel.handleEvent(OnBoardViewModel.OnBoardEvent.NextClicked(viewID))
        }
        viewModel.handleEvent(OnBoardViewModel.OnBoardEvent.Init(viewID))
    }

    private fun renderProcessingView(state : OnBoardViewModel.ProcessingViewState) {
        when (state) {
            is OnBoardViewModel.ProcessingViewState.Hide -> binding.progressBar.visibility = View.GONE
            is OnBoardViewModel.ProcessingViewState.Show -> binding.progressBar.visibility = View.VISIBLE
        }
    }

    private fun renderContentView(state : OnBoardViewModel.ContentViewState) {
        when(state) {
            is OnBoardViewModel.ContentViewState.Hide -> binding.contentView.visibility = View.GONE
            is OnBoardViewModel.ContentViewState.Show -> {
                binding.contentView.visibility = View.VISIBLE
                binding.descriptionTextView.text = state.data.description
            }
        }
    }

    companion object {
        private const val VIEW_ID = "view_id"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param id on-board view id.
         * @return A new instance of fragment OnBoardFragment.
         */
        @JvmStatic
        fun newInstance(id: Int) =
            OnBoardFragment().apply {
                arguments = Bundle().apply {
                    putInt(VIEW_ID, id)
                }
            }
    }
}