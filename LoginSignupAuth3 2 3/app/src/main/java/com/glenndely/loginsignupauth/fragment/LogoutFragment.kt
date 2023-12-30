package com.glenndely.loginsignupauth.fragment

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.glenndely.loginsignupauth.LoginActivity
import com.glenndely.loginsignupauth.R

class LogoutFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_logout, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Get references to views in the profile header
        val usernameTextView: TextView = view.findViewById(R.id.usernameTextView)
        val emailTextView: TextView = view.findViewById(R.id.emailTextView)

        // Set user information (replace with your actual user data)
        val username = "John Doe"
        val email = "john.doe@example.com"

        usernameTextView.text = username
        emailTextView.text = email

        // Get reference to the logout button
        val logoutButton: Button = view.findViewById(R.id.logoutButton)

        // Set click listener for the logout button
        logoutButton.setOnClickListener {
            // Show a confirmation dialog before logging out
            showLogoutConfirmationDialog()
        }
    }

    private fun showLogoutConfirmationDialog() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Logout Confirmation")
        builder.setMessage("Are you sure you want to logout?")
        builder.setPositiveButton("Yes") { _: DialogInterface, _: Int ->
            // User clicked Yes, perform logout action
            performLogout()
        }
        builder.setNegativeButton("No") { dialog: DialogInterface, _: Int ->
            // User clicked No, dismiss the dialog
            dialog.dismiss()
        }
        builder.show()
    }

    private fun performLogout() {
        // Perform logout action here
        // For example, you can navigate to the login screen or clear user session
        // You may also want to show a confirmation dialog before logging out

        // For simplicity, let's assume you want to navigate to the login screen
        val intent = Intent(requireContext(), LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
        requireActivity().finish() // Finish the current activity to prevent going back to it
    }
}