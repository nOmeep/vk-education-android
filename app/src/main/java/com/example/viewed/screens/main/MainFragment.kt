package com.example.viewed.screens.main

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.viewed.MAIN
import com.example.viewed.R
import com.example.viewed.databinding.FragmentMainBinding
import com.example.viewed.models.MovieItemModel


class MainFragment : Fragment() {
    private var mBinding: FragmentMainBinding? = null
    private val binding get() = mBinding!!
    lateinit var recyclerView: RecyclerView
    private val adapter by lazy { MainAdapter() }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentMainBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        val viewModel = ViewModelProvider(this)[MainFragmentViewModel::class.java]
        viewModel.getMovies()
        recyclerView = binding.rvMain
        recyclerView.adapter = adapter
        viewModel.getMovies()
        viewModel.myMovies.observe(viewLifecycleOwner) { list ->
            adapter.setList(list.body()!!.results)
        }
    }





    override fun onDestroy() {
        super.onDestroy()
        mBinding = null
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.item_future -> {
                MAIN.navController.navigate(R.id.action_mainFragment_to_futureFragment)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    companion object {
        fun clickMovie(model: MovieItemModel) {
            val bundle = Bundle()
            bundle.putSerializable("movie", model)
            MAIN.navController.navigate(R.id.action_mainFragment_to_detailFragment, bundle)
        }
    }
}