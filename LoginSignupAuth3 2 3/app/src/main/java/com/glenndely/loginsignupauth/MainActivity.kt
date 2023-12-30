package com.glenndely.loginsignupauth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.glenndely.loginsignupauth.databinding.ActivityNoteHomeBinding  // Correct import
import com.glenndely.loginsignupauth.fragment.HomeFragment
import com.glenndely.loginsignupauth.fragment.LogoutFragment
import com.glenndely.loginsignupauth.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"
    private lateinit var binding: ActivityNoteHomeBinding  // Correct binding class
    private lateinit var db: NotesDataBaseHelper
    private lateinit var adapter: Adapter

    private val homeFragment = HomeFragment()
    private val logoutFragment = LogoutFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNoteHomeBinding.inflate(layoutInflater)  // Correct layout inflation
        setContentView(binding.root)

        // Initialize BottomNavigationView
        binding.navBtn. setOnNavigationItemSelectedListener{
            when (it.itemId) {
                R.id.nav_home -> {
                    setCurrentFragment(homeFragment)
                    Log.i(TAG, "Home Selected")
                    true
                }

                R.id.nav_logout -> {
                    setCurrentFragment(logoutFragment)
                    Log.i(TAG, "Logout Selected")
                    true
                }

                else -> false
            }
        }

        db = NotesDataBaseHelper(this)
        adapter = Adapter(db.getAllNotes(), this)

        binding.notesRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.notesRecyclerView.adapter = adapter

        binding.addButton.setOnClickListener {
            val intent = Intent(this, AddNoteActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_wrapper, fragment)
            commit()
        }

    override fun onResume() {
        super.onResume()
        adapter.refreshData(db.getAllNotes())
    }
}
