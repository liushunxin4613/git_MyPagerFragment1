package adapter;

import java.util.List;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ViewAdapter extends BaseAdapter {
	
	private List<View> mList;
	
	public ViewAdapter(List<View> mList) {
		this.mList = mList;
	}

	@Override
	public int getCount() {
		return mList.size();
	}

	@Override
	public Object getItem(int position) {
		return mList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View view, ViewGroup print) {
		View mView = null;
		ViewHolord holord = null;
		if (mView == null) {
			
		} else {
			
		}
		
		
		
		return null;
	}
	
	class ViewHolord{
		TextView tv;
	}

}
