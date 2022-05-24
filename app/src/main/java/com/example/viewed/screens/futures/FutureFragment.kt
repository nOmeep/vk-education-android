package com.example.viewed.screens.futures

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.viewed.MAIN
import com.example.viewed.R
import com.example.viewed.databinding.FragmentFutureBinding
import com.example.viewed.models.UpcomingItemModel
import java.lang.Exception


class FutureFragment : Fragment() {



        private var mBinding: FragmentFutureBinding? = null
        private val binding get() = mBinding!!
        private lateinit var recyclerView: RecyclerView
        private val adapter by lazy { FutureAdapter() }


        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            mBinding = FragmentFutureBinding.inflate(layoutInflater, container, false)
            return binding.root

        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
            init()
        }

        private fun init() {
            val viewModel = ViewModelProvider(this)[FutureFragmentViewModel::class.java]
            recyclerView = binding.rvFuture
            recyclerView.adapter = adapter
            try {
                viewModel.getUpcoming()
                viewModel.myUpcoming.observe(viewLifecycleOwner, {list ->
                    adapter.setList(list.body()!!.results)
                })
            } catch (e: Exception) {
                Toast.makeText(MAIN,e.message, Toast.LENGTH_SHORT).show()

            }
        }

        override fun onDestroy() {
            super.onDestroy()
            mBinding = null
        }
        companion object {
        fun clickMovie(model: UpcomingItemModel) {
            val bundle = Bundle()
            bundle.putSerializable("movie", model)
            MAIN.navController.navigate(R.id.action_futureFragment_to_FDetailFragment2, bundle)
        }
    }
}



