package cn.skyduck.custom_control.pop_list;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import cn.skyduck.activity.R;
import cn.skyduck.custom_control.CustomControlDelegate;
import cn.skyduck.toolutils.DebugLog;

public class RadioPopList extends LinearLayout {
	private final String TAG = this.getClass().getSimpleName();

	public enum ActionEnum {
		// ITEM点击
		LIST_ITEM_CLICK
	}

	private CustomControlDelegate delegate;
	private int positionOfSelectedItem = 0;

	public void setTitle(String title) {
		TextView titleTextView = (TextView) findViewById(R.id.title_TextView);
		if (titleTextView != null) {
			titleTextView.setText(title);
		}
	}

	//
	private ListView listView;

	public RadioPopList(Context context) {
		super(context);
	}

	public RadioPopList(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public RadioPopList(Context context, CustomControlDelegate delegate) {
		super(context);

		this.delegate = delegate;

		LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		layoutInflater.inflate(R.layout.view_pop_list, this, true);

		listView = (ListView) findViewById(R.id.list_item_ListView);
		listView.setOnItemClickListener(listItemClickListener);
	}

	public void setAdapter(ArrayAdapter<String> radioPopListAdapter) {
		if (radioPopListAdapter == null) {
			DebugLog.e(TAG, "radioPopListAdapter is null");
			return;
		}
		listView.setAdapter(radioPopListAdapter);
	}

	private AdapterView.OnItemClickListener listItemClickListener = new AdapterView.OnItemClickListener() {

		// parent : The AdapterView where the click happened.
		// view : The view within the AdapterView that was clicked (this will be a
		// view provided by the adapter)
		// position : The position of the view in the adapter.
		// id : The row id of the item that was clicked.
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			RadioPopList.this.positionOfSelectedItem = position;
			if (delegate != null) {
				delegate.customControlOnAction(RadioPopList.this, ActionEnum.LIST_ITEM_CLICK);
			}
		}
	};

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
	}

	public int getPositionOfSelectedItem() {
		return positionOfSelectedItem;
	}
}
