package com.leo.mypagerfragment1;

import java.util.ArrayList;
import java.util.List;

import fragment.TestFragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;


public class MainActivity extends FragmentActivity implements OnClickListener{

	//	private String tag = "MainActivity";

	//当前标签的标记
	private int index;

	//颜色组
	private int colorArr[] = {R.color.black,R.color.blue};

	//母控件id
	private int mId = R.id.ac_ls_ll;

	//子控件布局id
	private int zId = R.layout.in_tv;

	//子控件textview id
	private int tvId = R.id.tt_tv;

	//下部id
	private int bottomId = R.id.ac_ll;

	//标签页数
	private int num = 3;

	//标题组
	private String titleArr[] = {"中国","美国","日本","俄罗斯","英国","法国","德国"};
	//text组
	private String textArr[] = {"北京","华盛顿","东京","莫斯科","伦敦","巴黎","伦敦"};

	//母布局
	private LinearLayout mLinearLayout;

	//每个标签的布局
	private LayoutParams params;

	//标签组
	private LinearLayout layout[];

	//fragment组
	private List<TestFragment> fragmentList; 


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initView();

	}

	private void initView() {

		mLinearLayout = (LinearLayout) findViewById(mId);

		params = new LayoutParams(getXspWidth()/num, LayoutParams.WRAP_CONTENT);

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
		addFragment(index);

	}

	protected void addFragment(int postion){
		getSupportFragmentManager().beginTransaction().add(bottomId, fragmentList.get(postion)).commit();
	}
	
	protected void replaceFragment(int postion){
		getSupportFragmentManager().beginTransaction().replace(bottomId, fragmentList.get(postion)).commit();
	}

	protected void removeFragment(int postion){
		getSupportFragmentManager().beginTransaction().remove(fragmentList.get(postion)).commit();
	}

	//更改状态
	protected void setViewIndex(int postion){
		setViewState(index, true);
		setViewState(postion, false);

		//变更fragment
		replaceFragment(postion);

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


}
