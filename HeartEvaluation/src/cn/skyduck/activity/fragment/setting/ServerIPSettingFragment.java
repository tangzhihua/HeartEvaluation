package cn.skyduck.activity.fragment.setting;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import cn.skyduck.activity.R;
import cn.skyduck.adapter.ServerIPListViewAdapter;
import cn.skyduck.global_data_cache.GlobalDataCacheForMemorySingleton;
import cn.skyduck.model.setting.sub_section.ServerIPForSystemSetting;
import cn.skyduck.toolutils.DebugLog;

/**
 * 设置服务器IP界面
 * 
 * @author hesiming
 * 
 */
public class ServerIPSettingFragment extends Fragment {
	private final String TAG = this.getClass().getSimpleName();
	private ListView serverIPListView;
	private ServerIPListViewAdapter serverIPListViewAdapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		DebugLog.i(TAG, "Fragment-->onCreate");
	}

	@Override
	public void onPause() {
		super.onPause();
		DebugLog.i(TAG, "Fragment-->onPause");

		// 保存
		GlobalDataCacheForMemorySingleton.getInstance.getSystemSetting().updateServerIPList(serverIPListViewAdapter.getLatestServerIPList());
		GlobalDataCacheForMemorySingleton.getInstance.getSystemSetting().setDefaultServerIPIndex(serverIPListViewAdapter.getLatestDefaultServerIPIndex());
	}

	@Override
	public void onStop() {
		super.onStop();

		DebugLog.i(TAG, "Fragment-->onStop");

	}

	@Override
	public void onStart() {
		super.onStart();
		DebugLog.i(TAG, "Fragment-->onStart");
	}

	@Override
	public void onResume() {
		super.onResume();
		DebugLog.i(TAG, "Fragment-->onResume");
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		DebugLog.i(TAG, "Fragment-->onDestroy");

	}

	// ////////////////////////////////////////////////////////////////////////////
	// Fragment比activity还要多出几个生命周期回调方法，这些额外的方法是为了与activity的交互而设立
	// ////////////////////////////////////////////////////////////////////////////

	// 当fragment被加入到activity时调用（在这个方法中可以获得所在的activity）。
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		DebugLog.i(TAG, "Fragment-->onAttach");
	}

	// 当activity要得到fragment的layout时，调用此方法，fragment在其中创建自己的layout(界面)。
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		DebugLog.i(TAG, "Fragment-->onCreateView");

		final View rootView = inflater.inflate(R.layout.fragment_server_ip_setting_layout, container, false);

		// 获取listview
		serverIPListView = (ListView) rootView.findViewById(R.id.server_ip_listView);

		// 加载顶部title
		final LayoutInflater mInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		final View headerView = mInflater.inflate(R.layout.view_server_ip_listview_header_layout, null);
		serverIPListView.addHeaderView(headerView);

		// 加载服务器列表 adapter
		final ArrayList<ServerIPForSystemSetting> serverIPListCloneData = GlobalDataCacheForMemorySingleton.getInstance.getSystemSetting().getServerIPListClone();
		serverIPListViewAdapter = new ServerIPListViewAdapter(getActivity(), serverIPListCloneData, GlobalDataCacheForMemorySingleton.getInstance.getSystemSetting().getDefaultServerIPIndex());
		serverIPListView.setAdapter(serverIPListViewAdapter);
		return rootView;
	}

	// 当activity的onCreated()方法返回后调用此方法。
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		DebugLog.i(TAG, "Fragment-->onActivityCreated");
	}

	// 当fragment的layout被销毁时被调用。
	@Override
	public void onDestroyView() {
		super.onDestroyView();

		DebugLog.i(TAG, "Fragment-->onDestroyView");
	}

	// 当fragment被从activity中删掉时被调用。
	@Override
	public void onDetach() {
		super.onDetach();

		DebugLog.i(TAG, "Fragment-->onDetach");
	}

}
