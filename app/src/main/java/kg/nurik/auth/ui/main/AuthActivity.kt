package kg.nurik.auth.ui.main

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import kg.nurik.auth.databinding.ActivityMainBinding
import kg.nurik.auth.ui.NewActivtiy.NewActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class AuthActivity : AppCompatActivity() {

    private val viewModel by viewModel<AuthViewModel>()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupListener()
        setupViewModel()
    }

    private fun setupViewModel() {
        viewModel.actionNewScreen.observe(this, Observer {
            startActivity(Intent(this, NewActivity::class.java))
        })
        viewModel.error.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        })
    }

    private fun setupListener() {
        binding.btnInput.setOnClickListener {
            viewModel.login( //отдаем майл и пароль
                binding.etEmail.text.toString(),
                binding.etPassword.text.toString()
            )
        }
    }
}