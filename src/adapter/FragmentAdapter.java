package adapter;

import java.util.List;

import fragment.TestFragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class FragmentAdapter extends FragmentPagerAdapter {
	
	private List<TestFragment> mFragemnts;

	public FragmentAdapter(FragmentManager fm,List<TestFragment> mFragemnts) {
		super(fm);
		this.mFragemnts = mFragemnts;
	}

	@Override
	public Fragment getItem(int position) {
		return mFragemnts.get(position);
	}

	@Override
	public int getCount() {
		return mFragemnts.size();
	}

}
