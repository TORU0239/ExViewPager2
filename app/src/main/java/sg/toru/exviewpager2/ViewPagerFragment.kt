package sg.toru.exviewpager2

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class ViewPagerFragment : Fragment() {

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.e("ADAPTER", "onAttach")
    }

    override fun onDetach() {
        super.onDetach()
        Log.e("ADAPTER", "onDetach")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.e("ADAPTER", "onViewCreated")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        Log.e("ADAPTER", "onCreateView")
        return inflater.inflate(R.layout.fragment_view_pager, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) = ViewPagerFragment()
    }
}
