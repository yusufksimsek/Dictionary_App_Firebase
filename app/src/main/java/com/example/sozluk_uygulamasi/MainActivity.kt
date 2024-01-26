package com.example.sozluk_uygulamasi

import KelimelerAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sozluk_uygulamasi.databinding.ActivityMainBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var kelimelerListe: ArrayList<Kelimeler>
    private lateinit var adapter: KelimelerAdapter
    private lateinit var refKelimeler: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.title = "Sozluk Uygulamasi"
        setSupportActionBar(binding.toolbar)

        binding.rv.setHasFixedSize(true)
        binding.rv.layoutManager = LinearLayoutManager(this@MainActivity)

        val db = FirebaseDatabase.getInstance()
        refKelimeler = db.getReference("kelimeler")

        kelimelerListe = ArrayList()

        adapter = KelimelerAdapter(this@MainActivity, kelimelerListe)

        binding.rv.adapter = adapter

        tumKelimeler()

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)

        val item = menu.findItem(R.id.action_ara)
        val searchView = item.actionView as SearchView
        searchView.setOnQueryTextListener(this)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onQueryTextSubmit(query: String): Boolean {
        aramaYap(query)
        Log.e("Gonderilen Arama",query)
        return true
    }

    override fun onQueryTextChange(newText: String): Boolean {
        aramaYap(newText)
        Log.e("Harf Girdikce",newText)
        return true
    }

    fun tumKelimeler(){
        refKelimeler.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {

                kelimelerListe.clear()

                for (s in snapshot.children){
                    val kelime = s.getValue(Kelimeler::class.java)

                    if (kelime!=null){
                        kelime.kelime_id = s.key
                        kelimelerListe.add(kelime)
                    }
                }

                adapter.notifyDataSetChanged()

            }

            override fun onCancelled(error: DatabaseError) {

            }
        })

    }

    fun aramaYap(aramaKelime:String){
        refKelimeler.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {

                kelimelerListe.clear()

                for (s in snapshot.children){
                    val kelime = s.getValue(Kelimeler::class.java)

                    if (kelime!=null){

                        if(kelime.ingilizce!!.contains(aramaKelime)){
                            kelime.kelime_id = s.key
                            kelimelerListe.add(kelime)
                        }

                    }
                }

                adapter.notifyDataSetChanged()

            }

            override fun onCancelled(error: DatabaseError) {

            }
        })

    }


}
