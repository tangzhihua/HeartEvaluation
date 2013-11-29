package cn.skyduck.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import cn.skyduck.activity.R;
import cn.skyduck.global_data_cache.QuestionnaireCodeEnum;

public class QuestionnaireGridViewAdapter extends BaseAdapter {

	private LayoutInflater mInflater;
	private ArrayList<QuestionnaireCodeEnum> dataSource;
	private OnQuestionnaireClickListener onQuestionnaireClickListener;

	public interface OnQuestionnaireClickListener {
		public void onQuestionnaireClick(QuestionnaireCodeEnum questionnaireCodeEnum);
	};

	public void setOnQuestionnaireClickListener(OnQuestionnaireClickListener onQuestionnaireClickListener) {
		this.onQuestionnaireClickListener = onQuestionnaireClickListener;
	}

	public QuestionnaireGridViewAdapter(Context context, ArrayList<QuestionnaireCodeEnum> dataSource) {
		this.mInflater = LayoutInflater.from(context);
		this.dataSource = dataSource;
	}

	/**
	 * 更改 GridView 数据源
	 * 
	 * @param newDataSource
	 */
	public void changeDataSource(final ArrayList<QuestionnaireCodeEnum> newDataSource) {
		if (newDataSource == null) {
			assert false : "入参 newDataSource 为空. ";
			return;
		}

		this.dataSource = newDataSource;
		this.notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		return dataSource == null ? 0 : dataSource.size();
	}

	@Override
	public Object getItem(int position) {
		return dataSource.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.gridview_cell_of_questionnaire_navigationgrid, null);
			holder = new ViewHolder();
			holder.cellButton = (Button) convertView.findViewById(R.id.cell_button);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		// 对 问卷名称 进行处理
		String fullNameString = ((QuestionnaireCodeEnum) getItem(position)).getFullName();
		int indexOfThumbnailName = fullNameString.indexOf("(");
		if (indexOfThumbnailName != -1) {
			String thumbnailNameString = fullNameString.substring(indexOfThumbnailName);
			String fortNameString = fullNameString.substring(0, indexOfThumbnailName);
			fullNameString = fortNameString + "\n" + thumbnailNameString;
		}

		holder.cellButton.setTag(getItem(position));
		holder.cellButton.setText(fullNameString);
		holder.cellButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				QuestionnaireCodeEnum questionnaireCodeEnum = (QuestionnaireCodeEnum) v.getTag();
				if (onQuestionnaireClickListener != null) {
					// 通知外层用户选中的 问卷类型
					onQuestionnaireClickListener.onQuestionnaireClick(questionnaireCodeEnum);
				}
			}
		});

		return convertView;
	}

	private static final class ViewHolder {
		public Button cellButton;
	}

}
