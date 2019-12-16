package sg.toru.exviewpager2

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2

/**
 * A simple [Fragment] subclass.
 */
class MainFragment : Fragment() {

    private lateinit var viewPager2: ViewPager2

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewPager2 = view.findViewById(R.id.viewpager2)
        val adapter = ViewPagerAdapter{
            startActivity(Intent(context, SecondActivity::class.java))
        }
        val arrayList = ArrayList<String>().apply {
            add("TEST1")
            add("TEST2")
            add("TEST3")
            add("TEST4")
        }
        adapter.list = arrayList

        val fragmentAdapter = ViewPagerFragmentAdapter(childFragmentManager, lifecycle, arrayList){
            startActivity(Intent(context, SecondActivity::class.java))
        }
        viewPager2.adapter = fragmentAdapter
//        viewPager2.adapter = adapter
    }
}

class DummyCardViewHolder internal constructor(view: View): RecyclerView.ViewHolder(view){
    private var text: TextView = view.findViewById(R.id.layout_text)
    fun bind(data:String){
        text.text = data
    }
}
class ViewPagerAdapter(val callback:()->Unit): RecyclerView.Adapter<DummyCardViewHolder>(){

    var list:List<String> = ArrayList()
    set(value){
        field = value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DummyCardViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_viewpager2, parent, false)
        view.findViewById<Button>(R.id.btn_go_to_next).setOnClickListener {
            callback.invoke()
        }
        return DummyCardViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: DummyCardViewHolder, position: Int) {
        holder.bind(list[position])
    }
}

// this is recommended way.. but how can we deal with the case of using Fragment for argument?
class ViewPagerFragmentAdapter(fragmentManager: FragmentManager,
                               lifecycle:Lifecycle,
                               val dataList:ArrayList<String>,
                               val callback:()->Unit): FragmentStateAdapter(fragmentManager, lifecycle){

    var list:List<String> = ArrayList()
        set(value){
            field = value
            notifyDataSetChanged()
        }
    override fun getItemCount(): Int = dataList.size

    override fun createFragment(position: Int): Fragment {
        return ViewPagerFragment.newInstance(dataList[position], callback)
    }
}