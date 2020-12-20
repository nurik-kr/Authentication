package kg.nurik.auth.ui.NewActivtiy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import kg.nurik.auth.databinding.ActivityNewActivtityBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewActivity : AppCompatActivity() {

    private val viewModel by viewModel<NewViewModel>()
    private lateinit var binding: ActivityNewActivtityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewActivtityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupViewModel()
    }

    private fun setupViewModel() {
        viewModel.error.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        }
        )
//        viewModel.data.observe(this, Observer {
//            binding.tvUserName.text = it.firstName
//        })
        viewModel.getProfileModel().observe(this, Observer {
            binding.tvUserName.text = it?.firstName         //отображается с бд
        })
    }
}