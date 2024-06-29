package com.halan.task

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.halan.task.databinding.FragmentTweetBinding
import com.halan.task.utils.copy
import com.halan.task.utils.disable
import com.halan.task.utils.enable
import com.halan.task.viewmodel.TweetViewModel
import com.rowaad.app.data.remote.NetWorkState
import com.rowaad.app.data.remote.TweetState
import com.rowaad.app.usecase.Consts
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TweetFragment : Fragment(R.layout.fragment_tweet) {
    private var binding: FragmentTweetBinding? = null
    private val viewModel: TweetViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding=FragmentTweetBinding.bind(view)
        handleUiStates()
        setupActions()
    }

    private fun setupActions() {
        //text watcher
        binding?.etTweet?.addTextChangedListener { viewModel.validateTweet(it.toString()) }

        //button actions
        binding?.clearTextButton?.setOnClickListener {
            binding?.etTweet?.setText("")
        }
        binding?.copyTextButton?.setOnClickListener {
            requireActivity().copy(binding?.etTweet?.text.toString())
        }
        binding?.postTweetButton?.setOnClickListener {
            viewModel.postTweet(tweet = binding?.etTweet?.text.toString())
        }

    }

    private fun handleUiStates() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.textStateFlow.collect { tweetState ->
                    when (tweetState) {
                        is TweetState.Empty -> {
                            binding?.tvCharactersTypedCount?.text = "0/280"
                            binding?.tvCharactersRemainingCount?.text = "280"
                            binding?.tvCharactersRemainingCount?.setTextColor(
                                ColorStateList.valueOf(
                                    Color.BLACK
                                )
                            )
                            binding?.copyTextButton?.disable()
                            binding?.clearTextButton?.disable()
                        }

                        is TweetState.NormalAverage -> {
                            binding?.tvCharactersTypedCount?.text = "${tweetState.count}/280"
                            binding?.tvCharactersRemainingCount?.text = tweetState.remain
                            binding?.tvCharactersRemainingCount?.setTextColor(
                                ColorStateList.valueOf(
                                    Color.BLUE
                                )
                            )
                            binding?.copyTextButton?.enable()
                            binding?.clearTextButton?.enable()
                        }

                        is TweetState.AboveAverage -> {
                            binding?.tvCharactersTypedCount?.text = "${tweetState.count}/280"
                            binding?.tvCharactersRemainingCount?.text = tweetState.remain
                            binding?.tvCharactersRemainingCount?.setTextColor(
                                ColorStateList.valueOf(
                                    Color.YELLOW
                                )
                            )
                            binding?.copyTextButton?.enable()
                            binding?.clearTextButton?.enable()

                        }

                        is TweetState.OverLimit -> {
                            binding?.tvCharactersTypedCount?.text = "${tweetState.count}/280"
                            binding?.tvCharactersRemainingCount?.text = tweetState.remain
                            binding?.tvCharactersRemainingCount?.setTextColor(
                                ColorStateList.valueOf(
                                    Color.RED
                                )
                            )
                            binding?.copyTextButton?.enable()
                            binding?.clearTextButton?.enable()

                        }
                    }
                }
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.tweetFlow.collect { networkState ->
                    when (networkState) {
                        is NetWorkState.Idle -> {

                        }

                        is NetWorkState.Loading -> {
                            //show loader
                            Toast.makeText(
                                requireContext(),
                                "Loading...",
                                Toast.LENGTH_SHORT
                            ).show()


                        }

                        is NetWorkState.StopLoading -> {
                            //stop loader
                            Toast.makeText(
                                requireContext(),
                                "Done",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                        is NetWorkState.Success<*> -> {
                            binding?.etTweet?.setText("")
                            Toast.makeText(
                                requireContext(),
                                "Your tweet Posted Successfully",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                        is NetWorkState.Error -> {
                            //handle proper user message inside base this dummy only for time purpose
                            when (networkState.th.message) {
                                Consts.ERROR_API.BAD_REQUEST -> {
                                    Toast.makeText(
                                        requireContext(),
                                        "bad request",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }

                                Consts.ERROR_API.FORBIDDEN -> {
                                    Toast.makeText(
                                        requireContext(),
                                        "user forbidden ",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }

                                Consts.ERROR_API.UNAUTHRIZED -> {
                                    Toast.makeText(
                                        requireContext(),
                                        "un authorized ",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }

                                Consts.ERROR_API.NOT_FOUND -> {
                                    Toast.makeText(
                                        requireContext(),
                                        "url not found ",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }

                            }
                        }

                    }
                }
            }
        }
    }





    override fun onDestroyView() {
        super.onDestroyView()
        binding=null
    }
}