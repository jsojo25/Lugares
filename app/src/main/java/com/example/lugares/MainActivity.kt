package com.example.lugares

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import com.example.lugares.databinding.ActivityMainBinding
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        FirebaseApp.initializeApp(this)
        auth = Firebase.auth

        binding.btLogin.setOnClickListener(){
            haceLogin()
        }

        binding.btRegistrar.setOnClickListener()
        {
            haceRegister()
        }
    }

    private fun haceRegister() {
        val email = binding.etEmail.text.toString()
        val clave = binding.etClave.text.toString()

        //Se hace el registro
        auth.createUserWithEmailAndPassword(email,clave)
            .addOnCompleteListener(this){
                task->
                if(task.isSuccessful)
                {
                    Log.d("Creando Usuario","Registrado")
                    val user = auth.currentUser
                    actualiza(user)
                }else{
                    Log.d("Creando Usuario","Fallo")
                    Toast.makeText(this,"Fallo en la creacion del usuario",Toast.LENGTH_LONG)
                    actualiza(null)
                }
            }
    }

    private fun haceLogin() {
        val email = binding.etEmail.text.toString()
        val clave = binding.etClave.text.toString()

        //Se hace el login
        auth.signInWithEmailAndPassword(email,clave)
            .addOnCompleteListener(this){
                task->
                if(task.isSuccessful){
                    Log.d("Autenticado","Autenticado")
                    val user = auth.currentUser
                    actualiza(user)
                }else{
                    Log.d("Autenticado","Fallo")
                    Toast.makeText(baseContext,"Fallo",Toast.LENGTH_LONG)
                    actualiza(null)
                }
            }
    }

    private fun actualiza(user: FirebaseUser?)
    {
        if(user != null)
        {
            val intent = Intent(this,Principal::class.java)
            startActivity(intent)

        }
    }

    public override fun onStart()
    {
        super.onStart()
        val usuario = auth.currentUser
        actualiza(usuario)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId)
        {
            R.id.action_logoff -> {
                Firebase.auth.signOut()
                finish()
                true
            }else -> super.onOptionsItemSelected(item)
        }
    }
}