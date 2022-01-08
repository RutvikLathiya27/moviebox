package com.example.moviebox.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.moviebox.databinding.ActivityMainBinding
import com.example.moviebox.ui.home.FragmentContainer.Communicator
import androidx.fragment.app.FragmentTransaction
import com.example.moviebox.R
import com.example.moviebox.ui.home.Fragments.*


class MainActivity : AppCompatActivity(), Communicator {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val moviesFragment = MoviesFragment()
        val showsFragment = ShowsFragment()
        val searchFragments = SearchFragments()

        setFragment(moviesFragment)

        binding.bottomNav.setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.nav_movies -> setFragment(moviesFragment)
                R.id.nav_shows -> setFragment(showsFragment)
                R.id.nav_search -> setFragment(searchFragments)
            }
            true
        }
    }

    private fun setFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.nav_host_fragment, fragment)
                .commit()
        }
    }

    override fun passData(movieId: String) {
        val bundle = Bundle()
        bundle.putString("movieId", movieId)

        val movieDetailFragment = MovieDetailFragment()
        movieDetailFragment.arguments= bundle

        val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.nav_host_fragment, movieDetailFragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commitAllowingStateLoss()

    }

    override fun passShowData(showId: String) {
        val bundle = Bundle()
        bundle.putString("showId", showId)

        val showDetailFragment = ShowDetailFragment()
        showDetailFragment.arguments= bundle

        val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.nav_host_fragment, showDetailFragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commitAllowingStateLoss()
    }

    override fun passKey(key: String) {
        val bundle = Bundle()
        bundle.putString("key", key)

        val viewAllFragment = ViewAllFragment()
        viewAllFragment.arguments = bundle

        val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.nav_host_fragment, viewAllFragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commitAllowingStateLoss()

    }

}