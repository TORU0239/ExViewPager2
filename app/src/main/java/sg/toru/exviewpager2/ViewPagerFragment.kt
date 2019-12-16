package sg.toru.exviewpager2

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment

class ViewPagerFragment(val data:String,
                        val callback: () -> Unit) : Fragment() {

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.e("ADAPTER", "onAttach")
    }

    override fun onDetach() {
        super.onDetach()
        Log.e("ADAPTER", "onDetach")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        Log.e("ADAPTER", "onCreateView")
        return inflater.inflate(R.layout.fragment_view_pager, container, false)
    }

    private lateinit var textView: TextView
    private var injectedData:String = ""
    set(value) {
        field = value
        textView.text = field
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.e("ADAPTER", "onViewCreated")
        view.findViewById<Button>(R.id.btn_go_to_next).setOnClickListener {
            callback.invoke()
        }

        textView = view.findViewById<TextView>(R.id.txt)
        textView.text = data
    }

    fun injectData(data:String){
        this.injectedData = data
    }

    companion object {
        @JvmStatic
        fun newInstance(data:String,
                        callback:()->Unit) = ViewPagerFragment(data, callback)
    }
}
