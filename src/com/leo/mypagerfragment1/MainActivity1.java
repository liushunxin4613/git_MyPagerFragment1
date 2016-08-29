package com.leo.mypagerfragment1;

import java.util.ArrayList;
import java.util.List;

import fragment.TestFragment;
import adapter.FragmentAdapter;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;


public class MainActivity1 extends FragmentActivity implements OnClickListener,OnPageChangeListener{

	private String tag = "MainActivity";

	//当前标签的标记
	private int index;

	//颜色组
	private int colorArr[] = {R.color.black,R.color.blue};

	//母控件id
	private int mId = R.id.ac1_ls_ll;

	//子控件布局id
	private int zId = R.layout.in_tv;

	//子控件textview id
	private int tvId = R.id.tt_tv;

	//标签页数
	private int num = 3;

	//标题组
	private String titleArr[] = {"中国","美国","日本","俄罗斯","英国","法国","德国"};
	//text组
	private String textArr[] = {"北京","华盛顿","东京","莫斯科","伦敦","巴黎","柏林"};

	//母布局
	private LinearLayout mLinearLayout;

	//每个标签的布局
	private LayoutParams params;

	//标签组
	private LinearLayout layout[];

	//fragment组
	private List<TestFragment> fragmentList;

	//下部控件viewpagerId
	private int viewPagerId = R.id.ac1_vp;

	//viewpager
	private ViewPager pager;

	//标签最左侧标记
	private int leftIndex = 0;

	private HorizontalScrollView scrollView;

	//子标签宽度
	private int sonWidth;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main1);

		initView();

	}

	private void initView() {

		mLinearLayout = (LinearLayout) findViewById(mId);

		//获取每个子控件的宽度
		sonWidth = getXspWidth()/num;

		params = new LayoutParams(sonWidth, LayoutParams.WRAP_CONTENT);

		layout = new LinearLayout[titleArr.length];
		for (int i = 0; i < layout.length; i++) {
			layout[i] = getView();

			//加入子控件layout[i]的监听事件
			layout[i].setId(i);
			layout[i].setOnClickListener(this);

			TextView tv = (TextView) layout[i].findViewById(tvId);
			tv.setText(titleArr[i]);

			mLinearLayout.addView(layout[i]);
		}

		//初始化fragment
		fragmentList = new ArrayList<TestFragment>();
		for (int i = 0; i < textArr.length; i++) {
			fragmentList.add(new TestFragment(textArr[i]));
		}

		//初始化首标签
		index = 0;
		setViewState(index, false);

		//初始化下部控件
		pager = (ViewPager) findViewById(viewPagerId);
		pager.setOnPageChangeListener(this);

		FragmentAdapter adapter = new FragmentAdapter(getSupportFragmentManager(), fragmentList);
		pager.setAdapter(adapter);
		pager.setCurrentItem(index);


		//测试
		scrollView = (HorizontalScrollView) findViewById(R.id.ac1_hs);

	}

	//滑动标签页
	protected void smoothScrollBy(int key){
		scrollView.smoothScrollBy(sonWidth * key, 0);
	}

	//更改状态
	protected void setViewIndex(int postion){
		setViewState(index, true);
		setViewState(postion, false);

		//变更fragment
		pager.setCurrentItem(postion);

		index = postion;
	}

	//更改子控件状态
	protected void setViewState(int postion,boolean is){
		layout[postion].setEnabled(is);
		TextView tv = (TextView) layout[postion].findViewById(tvId);
		if (is) {
			tv.setTextColor(getResources().getColor(colorArr[0]));	
		}else {
			tv.setTextColor(getResources().getColor(colorArr[1]));
		}
	}

	//获取子控件
	protected LinearLayout getView(){
		LayoutInflater inflater = getLayoutInflater();
		LinearLayout layout = (LinearLayout) inflater.inflate(zId, null);
		layout.setLayoutParams(params);
		return layout;
	}

	//获取屏幕宽度
	private int getXspWidth(){
		int widthPixels = 0;
		WindowManager wm = (WindowManager) getSystemService(WINDOW_SERVICE);
		DisplayMetrics metrics = new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(metrics);
		widthPixels = metrics.widthPixels;
		return widthPixels;
	}

	@Override
	public void onClick(View v) {
		setViewIndex(v.getId());
	}

	@Override
	public void onPageScrollStateChanged(int postion) {
	}

	@Override
	public void onPageScrolled(int postion, float f, int i) {
	}

	@Override
	public void onPageSelected(int postion) {
		ifLeftIndex(postion);
		setViewIndex(postion);
	}
	
	//确定当前显示标签的最左侧位置
	protected void ifLeftIndex(int postion){
		//滑向右侧
		if (postion > leftIndex + num - 1) {
			leftIndex++;
			smoothScrollBy(1);
		}
		//滑向左侧
		if (postion < index) {
			leftIndex = postion;
			smoothScrollBy(-1);
		}
	}

}
