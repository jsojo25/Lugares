package com.example.lugares.ui.lugar
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lugares.R
import com.example.lugares.adapter.LugarAdapter
import com.example.lugares.databinding.FragmentLugarBinding
import com.example.lugares.viewModel.LugarViewModel


class LugarFragment : Fragment() {
    private lateinit var lugarViewModel: LugarViewModel
    private var _binding: FragmentLugarBinding? = null
    private val binding get() = _binding!!
    // This property is only valid between onCreateView and
    // onDestroyView.
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        lugarViewModel =
            ViewModelProvider(this).get(LugarViewModel::class.java)
        _binding = FragmentLugarBinding.inflate(inflater,container,false)
        binding.addLugarFabButton.setOnClickListener {
            findNavController().navigate(R.id.action_nav_lugar_to_addLugarFragment3)
        }

        return binding.root
    }

    val lugarAdapter = LugarAdapter()
    val reciclador = binding.reciclador
    reciclador.adapter = lugarAdapter
    reciclador.layoutManager = LinearLayoutManager(requireContext())

    lugarViewModel = ViewModelProvider(this)[LugarViewModel::class.java]
    lugarViewModel.getAllData.observe(viewLifecycleOwner){
        lugares -> lugarAdapter.setData(lugares)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}