import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sozluk_uygulamasi.DetayActivity
import com.example.sozluk_uygulamasi.Kelimeler
import com.example.sozluk_uygulamasi.databinding.CardTasarimBinding

class KelimelerAdapter(private val mContext: Context, private var kelimelerListe: List<Kelimeler>) :
    RecyclerView.Adapter<KelimelerAdapter.CardTasarimTutucu>() {

    inner class CardTasarimTutucu(private val binding: CardTasarimBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(kelime: Kelimeler) {
            binding.tvIngilizce.text = kelime.ingilizce
            binding.tvTurkce.text = kelime.turkce

            binding.kelimeCard.setOnClickListener {
                val intent = Intent(mContext, DetayActivity::class.java)
                intent.putExtra("nesne", kelime)
                mContext.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardTasarimTutucu {
        val binding = CardTasarimBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CardTasarimTutucu(binding)
    }

    override fun getItemCount(): Int {
        return kelimelerListe.size
    }

    override fun onBindViewHolder(holder: CardTasarimTutucu, position: Int) {
        holder.bind(kelimelerListe[position])
    }
}