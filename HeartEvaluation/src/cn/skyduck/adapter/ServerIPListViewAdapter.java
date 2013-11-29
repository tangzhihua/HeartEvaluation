package cn.skyduck.adapter;

import java.util.ArrayList;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import cn.skyduck.activity.MyApplication;
import cn.skyduck.activity.R;
import cn.skyduck.model.setting.sub_section.ServerIPForSystemSetting;

public class ServerIPListViewAdapter extends BaseAdapter {

	private LayoutInflater mInflater;
	private ArrayList<ServerIPForSystemSetting> dataSource;
	private Context mContext;
	private int defaultServerIPIndex = 0;

	public int getLatestDefaultServerIPIndex() {
		return defaultServerIPIndex;
	}

	public ArrayList<ServerIPForSystemSetting> getLatestServerIPList() {
		return dataSource;
	}

	public ServerIPListViewAdapter(Context context, ArrayList<ServerIPForSystemSetting> dataSource, int defaultServerIPIndex) {
		this.mInflater = LayoutInflater.from(context);
		this.dataSource = dataSource;
		this.mContext = context;
		this.defaultServerIPIndex = defaultServerIPIndex;
	}

	@Override
	public int getCount() {
		return dataSource == null ? 0 : dataSource.size();
	}

	// 返回当前Item显示的数据，方便在Activity中的onItemClick方法中调用
	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {

		ViewHolder holder = null;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.listview_cell_of_server_ip, null);
			holder = new ViewHolder();
			holder.settingButton = (Button) convertView.findViewById(R.id.setting_button);
			holder.serverNameTextView = (TextView) convertView.findViewById(R.id.server_name_textView);
			holder.serverIPTextView = (TextView) convertView.findViewById(R.id.server_ip_textView);
			holder.defaultCheckBox = (CheckBox) convertView.findViewById(R.id.default_checkBox);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		// 当前服务器IP数据
		final ServerIPForSystemSetting serverIPForSystemSetting = dataSource.get(position);

		// 设置当前IP是否是默认IP
		if (position == defaultServerIPIndex) {
			holder.defaultCheckBox.setChecked(true);
		} else {
			holder.defaultCheckBox.setChecked(false);
		}

		//
		holder.defaultCheckBox.setTag(position);
		holder.defaultCheckBox.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Integer position = (Integer) v.getTag();
				if (position != ServerIPListViewAdapter.this.defaultServerIPIndex) {
					ServerIPListViewAdapter.this.defaultServerIPIndex = position;
					ServerIPListViewAdapter.this.notifyDataSetChanged();
				} else {
					((CheckBox) v).setChecked(true);
				}
			}
		});

		// 设置科室名称
		holder.serverNameTextView.setText(serverIPForSystemSetting.getServerName());
		// 设置服务器IP地址
		holder.serverIPTextView.setText(serverIPForSystemSetting.getServerIP());

		// 设置button的点击事件
		final TextView serverNameTextView = holder.serverNameTextView;
		final TextView serverIPTextView = holder.serverIPTextView;
		holder.settingButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// 显示 IP修改 对话框
				showServerIPChangeDialog(serverNameTextView, serverIPTextView, position);
			}
		});
		return convertView;

	}

	/**
	 * IP修改 对话框
	 * 
	 * @param serverNameTextView
	 * @param serverIPTextView
	 * @param position
	 */
	private void showServerIPChangeDialog(final TextView serverNameTextView, final TextView serverIPTextView, final int position) {
		// 创建window
		final View dialogView = LayoutInflater.from(mContext).inflate(R.layout.dialog_setting_server_ip_layout, null);
		final Dialog dialog = new Dialog(mContext, R.style.AlertDialog);
		dialog.show();
		Window window = dialog.getWindow();
		window.setContentView(dialogView);

		// 映射提示框布局中的控件
		final EditText serverNameEditText = (EditText) dialogView.findViewById(R.id.server_name_editText);
		serverNameEditText.setText(serverNameTextView.getText().toString());
		final EditText serverIPEeitText = (EditText) dialogView.findViewById(R.id.server_ip_editText);
		serverIPEeitText.setText(serverIPTextView.getText().toString());
		final ImageView serverNameErrorIcon = (ImageView) dialogView.findViewById(R.id.server_name_error_imageView);
		final ImageView serverIPErrorIcon = (ImageView) dialogView.findViewById(R.id.server_ip_error_imageView);

		// 取消 按钮
		final Button cancelDialogButton = (Button) dialogView.findViewById(R.id.cancel_button);
		cancelDialogButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				dialog.dismiss();
			}
		});

		// 保存 按钮
		final Button saveServerIPButton = (Button) dialogView.findViewById(R.id.save_button);
		saveServerIPButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				final String serverName = serverNameEditText.getText().toString();
				final String serverIP = serverIPEeitText.getText().toString();
				String errorMessageString = "";

				serverNameErrorIcon.setVisibility(View.INVISIBLE);
				serverIPErrorIcon.setVisibility(View.INVISIBLE);
				do {

					if (TextUtils.isEmpty(serverName)) {
						errorMessageString = mContext.getResources().getString(R.string.name_can_not_be_blank);
						serverNameErrorIcon.setVisibility(View.VISIBLE);
						break;
					}

					if (TextUtils.isEmpty(serverIP)) {
						errorMessageString = mContext.getResources().getString(R.string.IP_can_not_be_blank);
						serverIPErrorIcon.setVisibility(View.VISIBLE);
						break;
					}

					serverNameTextView.setText(serverName);
					serverIPTextView.setText(serverIP);
					ServerIPForSystemSetting serverIPForSystemSetting = (ServerIPForSystemSetting) dataSource.get(position);
					serverIPForSystemSetting.setServerName(serverName);
					serverIPForSystemSetting.setServerIP(serverIP);

					dialog.dismiss();
					return;
				} while (false);

				// 用户输入错误
				Toast.makeText(MyApplication.getApplication(), errorMessageString, Toast.LENGTH_SHORT).show();
				return;
			}
		});
	}

	private static final class ViewHolder {
		public Button settingButton;
		public TextView serverNameTextView;
		public TextView serverIPTextView;
		public CheckBox defaultCheckBox;
	}

}
