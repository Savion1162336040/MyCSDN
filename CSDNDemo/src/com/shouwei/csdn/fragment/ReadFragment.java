package com.shouwei.csdn.fragment;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.admin.DeviceAdminReceiver;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.shouwei.csdn.R;
import com.shouwei.csdn.activity.NewsDetailActivity;
import com.shouwei.csdn.adapter.FragmentReadItemAdapter;
import com.shouwei.csdn.entity.NewsTable;
import com.shouwei.csdn.service.LockService;
import com.shouwei.csdn.util.HtmlParse;
import com.shouwei.csdn.util.MyConstants;

@SuppressLint("ValidFragment")
public class ReadFragment extends Fragment implements OnClickListener,
		OnItemClickListener {
	Activity mActivity;
	View view, footview;
	ImageView open_sidemenu, refresh;
	Button loadmore, lock;
	SlidingMenu mSlidingmenu;
	TextView center;
	List<NewsTable> datalist;
	ListView lv;
	static final int SUCCEED = 10086;
	static final int FALIED = 10000;
	RotateAnimation anim;
	int currentPage = 1;

	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			if (msg.what == SUCCEED) {
				FragmentReadItemAdapter adapter = new FragmentReadItemAdapter(
						mActivity, (List<NewsTable>) msg.obj);
				lv.setAdapter(adapter);
			} else if (msg.what == FALIED) {
				MyConstants.showToast(mActivity, "无数据");
			}
			if (anim != null && anim.hasStarted()) {
				anim.cancel();
			}
		};
	};

	public ReadFragment(Activity mActivity, SlidingMenu mSlidingmenu) {
		this.mActivity = mActivity;
		this.mSlidingmenu = mSlidingmenu;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = LayoutInflater.from(mActivity).inflate(R.layout.fragment_frame,
				null);
		init();
		getInfo(currentPage);
		return view;
	}

	private void init() {
		open_sidemenu = (ImageView) view
				.findViewById(R.id.content_title_bar_view_opensidemenu);
		center = (TextView) view
				.findViewById(R.id.content_title_bar_view_center);
		refresh = (ImageView) view
				.findViewById(R.id.content_title_bar_view_search);
		lv = (ListView) view.findViewById(R.id.fragment_frame_content_lv);
		footview = LayoutInflater.from(mActivity).inflate(
				R.layout.foot_view_more, null);
		loadmore = (Button) footview.findViewById(R.id.foot_view_more);
		lock = (Button) view.findViewById(R.id.content_title_bar_view_lock);
		lock.setOnClickListener(this);
		open_sidemenu.setOnClickListener(this);
		refresh.setOnClickListener(this);
		center.setText("阅读");
		loadmore.setOnClickListener(this);
		lv.setFooterDividersEnabled(true);
		lv.addFooterView(footview);
		lv.setOnItemClickListener(this);
	}

	private void getInfo(int page) {
		final int mPage = page;
		new Thread() {
			@Override
			public void run() {
				super.run();
				datalist = HtmlParse.parseNewsHtml(HtmlParse.getData(
						MyConstants.NEWS_TYPE_NEWS, mPage));
				Message msg = new Message();
				if (datalist != null && datalist.size() > 0) {
					msg.what = SUCCEED;
					msg.obj = datalist;
					handler.sendMessage(msg);
				} else {
					handler.sendEmptyMessage(FALIED);
				}
			}
		}.start();

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.content_title_bar_view_opensidemenu:
			if (mSlidingmenu != null) {
				mSlidingmenu.showMenu();
			}
			break;
		case R.id.content_title_bar_view_search:
			MyConstants.showToast(mActivity, "点击刷新");
			anim = (RotateAnimation) AnimationUtils.loadAnimation(mActivity,
					R.anim.rotate_anim);
			refresh.startAnimation(anim);
			getInfo(currentPage = 1);
			break;
		case R.id.foot_view_more:
			MyConstants.showToast(mActivity, "下一页");
			getInfo(++currentPage);
			break;
		case R.id.content_title_bar_view_lock:
			DevicePolicyManager policy = (DevicePolicyManager) mActivity
					.getSystemService(Context.DEVICE_POLICY_SERVICE);
			ComponentName componentName = new ComponentName(mActivity,
					MyAdmin.class);
			if (policy.isAdminActive(componentName)) {
				policy.lockNow();
			}
			break;
		default:
			break;
		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Intent t = new Intent();
		t.setClass(mActivity, NewsDetailActivity.class);
		t.putExtra("target_url", datalist.get(position).getTarget_url());
		startActivity(t);
	}

//	public void lock(View view){
//	　　　　　　//通过反射获取到sdk隐藏的服务 　
//	　　　　　　Method method = Class.forName("android.os.ServiceManager")　
//	　　　　　　　　　　.getMethod("getService", String.class);　
//	　　　　　　IBinder binder = (IBinder) method.invoke(null,//激活服务　
//	　　　　　　　　　　new Object[] { Context.DEVICE_POLICY_SERVICE });　
//	　　　　　　 mService = IDevicePolicyManager.Stub.asInterface(binder);　
//	　　　　　　 //定义组件的名字 　
//	　　　　　　 ComponentName mAdminName = new ComponentName(this, MyAdmin.class);　
//	　　　　　　 //注册权限　
//	　　　　　　 if (mService != null) {　
//	　　　　　　　　　　//判断自定义的广播接受者 是不是被注册成 deviceadmin的权限 　
//	　　　　　　　　　　if (!mService.isAdminActive(mAdminName)) {　
//	　　　　　　　　　　　　　　　　Intent intent = new Intent(　
//	　　　　　　　　　　　　　　　　DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);　
//	　　　　　　　　　　　　　　　　intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN,　
//	　　　　　　　　　　　　　　　　　　　　mAdminName);　
//	　　　　　　　　　　　　　　　　startActivity(intent);　
//	　　　　　　　　　　　　　　}　
//	　　　　　　　　　　//调用服务实现锁屏 　
//	　　　　　　　　　　mService.lockNow();　
//	　　　　　　　　　　//设置解锁密码　
//	　　　　　　　　　　mService.resetPassword("123", 0);　
//	　　　　　　 }　
//	}
	public void lock(){
		try {
			Method method = Class.forName("android.os.ServiceManager").getMethod("getService",String.class);
			IBinder binder = (IBinder) method.invoke(null, new Object[]{Context.DEVICE_POLICY_SERVICE});
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	class MyAdmin extends DeviceAdminReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			super.onReceive(context, intent);
			System.out.println("onreceiver");
		}

		@Override
		public void onEnabled(Context context, Intent intent) {
			System.out.println("激活使用");
			super.onEnabled(context, intent);
		}

		@Override
		public void onDisabled(Context context, Intent intent) {
			System.out.println("取消激活");
			super.onDisabled(context, intent);
		}

	}
}
