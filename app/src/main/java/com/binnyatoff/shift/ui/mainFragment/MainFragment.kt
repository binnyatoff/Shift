package com.binnyatoff.shift.ui.mainFragment

import  android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.binnyatoff.shift.R
import com.binnyatoff.shift.appComponent
import com.binnyatoff.shift.databinding.FragmentMainBinding
import javax.inject.Inject


class MainFragment : Fragment() {
    @Inject
    lateinit var mainViewModelFactory: MainViewModelFactory

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onAttach(context: Context) {
        super.onAttach(context)
        context.appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mainViewModel =
            ViewModelProvider(this, mainViewModelFactory)[MainViewModel::class.java]

        mainViewModel.mainViewState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is MainFragmentState.Loaded -> loadedState(state)
                else -> {}
            }
        }

        with(binding) {
            send.setOnClickListener {
                val binNumber = binNumber.text
                mainViewModel.obtainEvent(MainFragmentEvent.GetBinInformation(binNumber))
            }
            binNumber.addTextChangedListener(object : TextWatcher {
                val space = ' '
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int,
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                }

                override fun afterTextChanged(s: Editable) {

                    if (s.isEmpty()) {
                        numberCard.text = getString(R.string.card_number)
                    } else {
                        numberCard.text = s
                    }
                    var pos = 0

                    while (true) {
                        if (pos >= s.length) break

                        if (space == s[pos] && ((pos + 1) % 5 != 0 || pos + 1 == s.length)) {
                            s.delete(pos, pos + 1)
                        } else {
                            pos++
                        }
                    }

                    pos = 4
                    while (true) {
                        if (pos >= s.length) break

                        val c: Char = s[pos]
                        if ("0123456789".indexOf(c) >= 0) {
                            s.insert(pos, "" + space)
                        }
                        pos += 5
                    }
                }
            })
            infoButton.setOnClickListener {
                infoCollapse()
            }
        }
    }

    private fun infoCollapse() {
        with(binding) {
            if (info.visibility == View.GONE) {
                infoButton.setImageResource(R.drawable.ic_arrow_up)
                info.visibility = View.VISIBLE
            } else {
                infoButton.setImageResource(R.drawable.ic_arrow_down)
                info.visibility = View.GONE
            }
        }
    }

    private fun initState(state: MainFragmentState.Init) {

    }

    private fun loadingState(state: MainFragmentState.Loading) {

    }

    private fun loadedState(state: MainFragmentState.Loaded) {
        binding.info.text = state.binList.toString()
        infoCollapse()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}