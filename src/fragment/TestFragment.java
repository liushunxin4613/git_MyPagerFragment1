package fragment;

import com.leo.mypagerfragment1.R;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class TestFragment extends Fragment {

	private String s;

	public TestFragment(String s) {
		this.s = s;
	}

	@SuppressLint("InflateParams")
	@Override
	public View onCreateView(LayoutInflater inflater,
			ViewGroup container,Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.item_fragment, null);
		TextView tv = (TextView) view.findViewById(R.id.if_tv);
		tv.setText(s);
		return view;
	}

}
