package sg.toru.exviewpager2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2

class MainActivity : AppCompatActivity() {

    private lateinit var viewPager2: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewPager2 = findViewById(R.id.viewpager2)
//        viewPager2.adapter = ViewPagerAdapter()
        viewPager2.adapter = ViewPagerFragmentAdapter(this)
    }
}

class DummyCardViewHolder internal constructor(view: View):RecyclerView.ViewHolder(view){
    private var text: TextView = view.findViewById(R.id.layout_text)
    fun bind(data:String){
        text.text = data
    }
}
class ViewPagerAdapter(): RecyclerView.Adapter<DummyCardViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DummyCardViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_viewpager2, parent, false)
        return DummyCardViewHolder(view)
    }

    override fun getItemCount(): Int = 3

    override fun onBindViewHolder(holder: DummyCardViewHolder, position: Int) {
        holder.bind(position.toString())
    }
}

// this is recommended way.. but how can we deal with the case of using Fragment for argument?
class ViewPagerFragmentAdapter(activity:FragmentActivity):FragmentStateAdapter(activity){
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return ViewPagerFragment()
    }
}