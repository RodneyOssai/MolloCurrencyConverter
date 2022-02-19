package com.getmollo.CurrencyConverter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.getmollo.CurrencyConverter.databinding.HomeFragmentBinding

class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }
    private var _binding: HomeFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {_binding = HomeFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var  spinnerItemsList:MutableList<SpinnerItem> = ArrayList()
        val adapter = SpinnerAdapter(context!!,spinnerItemsList)
        binding.toCurrencyDropdown.setAdapter(adapter)
        viewModel.currencyList?.observe(this, Observer<List<SpinnerItem?>?>{ spinnerItems ->
            // update UI
            for (item in spinnerItems) {
                if (item != null) {
                    spinnerItemsList.add(SpinnerItem(item.currencyTicker,item.currencyLogo,item.fromEurValue))
                }
            }
            adapter.notifyDataSetChanged();
        })



        binding.toCurrencyDropdown.setAdapter(adapter)
    }

}