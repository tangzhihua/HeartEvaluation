package cn.skyduck.adapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.TextView;
import cn.skyduck.activity.R;
import cn.skyduck.global_data_cache.GlobalConstant.QuestionnaireStateEnum;
import cn.skyduck.questionnaire.model.FullQuestionnaireModel;

public class QuestionnaireListViewAdapter extends BaseAdapter {

	public abstract static interface OnItemClickListener {
		public abstract void onItemClick(int position);
	}

	private LayoutInflater inflater;
	private List<DataSourceOfQuestionnaireListItem> dataSource = new ArrayList<DataSourceOfQuestionnaireListItem>();
	private OnItemClickListener onItemClickListener;

	public QuestionnaireListViewAdapter(Context context, OnItemClickListener onItemClickListener) {
		this.inflater = LayoutInflater.from(context);
		this.onItemClickListener = onItemClickListener;
	}

	public void setSelectAll(boolean isSelectAll) {
		for (DataSourceOfQuestionnaireListItem dataSourceOfQuestionnaireListItem : dataSource) {
			dataSourceOfQuestionnaireListItem.isChecked = isSelectAll;
		}

		notifyDataSetChanged();
	}

	public void changeDataSource(List<FullQuestionnaireModel> questionnaireList) {
		dataSource.clear();
		for (FullQuestionnaireModel questionnaireModel : questionnaireList) {
			DataSourceOfQuestionnaireListItem dataSourceOfQuestionnaireListItem = new DataSourceOfQuestionnaireListItem(questionnaireModel);
			dataSourceOfQuestionnaireListItem.isChecked = false;
			dataSource.add(dataSourceOfQuestionnaireListItem);
		}

		notifyDataSetChanged();
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

	@SuppressLint("SimpleDateFormat")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.listview_cell_of_questionnaire, null);
			holder = new ViewHolder();
			// 单选按钮
			holder.questionnaireNameCheckBox = (CheckBox) convertView.findViewById(R.id.questionnaire_name_checkBox);
			holder.questionnaireNameCheckBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {

				@Override
				public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
					final int position = (Integer) buttonView.getTag();
					final DataSourceOfQuestionnaireListItem dataSourceOfQuestionnaireListItem = (DataSourceOfQuestionnaireListItem) getItem(position);
					dataSourceOfQuestionnaireListItem.isChecked = isChecked;
				}
			});

			final View.OnClickListener itemClickListener = new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					onItemClickListener.onItemClick((Integer) v.getTag());
				}
			};
			//
			holder.nameTextView = (TextView) convertView.findViewById(R.id.name_textView);
			holder.nameTextView.setOnClickListener(itemClickListener);
			holder.sexTextView = (TextView) convertView.findViewById(R.id.sex_textView);
			holder.sexTextView.setOnClickListener(itemClickListener);
			holder.ageTextView = (TextView) convertView.findViewById(R.id.age_textView);
			holder.ageTextView.setOnClickListener(itemClickListener);
			holder.defaultPrintTextView = (TextView) convertView.findViewById(R.id.default_print_textView);
			holder.defaultPrintTextView.setOnClickListener(itemClickListener);
			holder.beginTimeTextView = (TextView) convertView.findViewById(R.id.begin_time_textView);
			holder.beginTimeTextView.setOnClickListener(itemClickListener);
			holder.cardNumberTextView = (TextView) convertView.findViewById(R.id.card_number_textView);
			holder.cardNumberTextView.setOnClickListener(itemClickListener);
			holder.stateImageView = (ImageView) convertView.findViewById(R.id.state_imageView);
			holder.stateImageView.setOnClickListener(itemClickListener);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		// 当前量表数据
		final DataSourceOfQuestionnaireListItem dataSourceOfQuestionnaireListItem = (DataSourceOfQuestionnaireListItem) getItem(position);

		//
		holder.nameTextView.setTag(position);
		holder.sexTextView.setTag(position);
		holder.ageTextView.setTag(position);
		holder.defaultPrintTextView.setTag(position);
		holder.beginTimeTextView.setTag(position);
		holder.cardNumberTextView.setTag(position);
		holder.stateImageView.setTag(position);
		//
		holder.questionnaireNameCheckBox.setTag(position);
		holder.questionnaireNameCheckBox.setText(dataSourceOfQuestionnaireListItem.questionnaireModel.getQuestionnaireCodeEnum().getShortName());
		holder.questionnaireNameCheckBox.setChecked(dataSourceOfQuestionnaireListItem.isChecked);
		if (dataSourceOfQuestionnaireListItem.questionnaireModel.getRespondentsInformation() != null) {
			//
			holder.nameTextView.setText(dataSourceOfQuestionnaireListItem.questionnaireModel.getRespondentsInformation().getName());
			//
			holder.cardNumberTextView.setText(dataSourceOfQuestionnaireListItem.questionnaireModel.getRespondentsInformation().getCardNumber());
			//
			if (dataSourceOfQuestionnaireListItem.questionnaireModel.getRespondentsInformation().getAge() > 0) {
				holder.ageTextView.setText("" + dataSourceOfQuestionnaireListItem.questionnaireModel.getRespondentsInformation().getAge());
			} else {
				holder.ageTextView.setText("");
			}
			//
			if (dataSourceOfQuestionnaireListItem.questionnaireModel.getRespondentsInformation().getSexEnum() != null) {
				holder.sexTextView.setText(dataSourceOfQuestionnaireListItem.questionnaireModel.getRespondentsInformation().getSexEnum().getName());
			} else {
				holder.sexTextView.setText("");
			}
		} else {
			holder.nameTextView.setText("");
		}

		holder.defaultPrintTextView.setText(dataSourceOfQuestionnaireListItem.questionnaireModel.getQuestionnaireCodeEnum().getDefaultPrintType().getName());
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd kk:mm");
		holder.beginTimeTextView.setText(dateFormat.format(dataSourceOfQuestionnaireListItem.questionnaireModel.getBeginTestDate()));

		// 只有彻底完成的考题, 才显示 "已完成" 图标, "COMPLETED_OF_END_TEST_IN_ADVANCE 可以提前交卷" 的试题不显示为 "已完成" 图标
		if (QuestionnaireStateEnum.COMPLETED == dataSourceOfQuestionnaireListItem.questionnaireModel.getQuestionnaireStateEnum()) {
			holder.stateImageView.setImageResource(R.drawable.done);
		} else {
			holder.stateImageView.setImageResource(R.drawable.pause);
		}

		return convertView;
	}

	private static final class ViewHolder {
		// 量表名称
		public CheckBox questionnaireNameCheckBox;
		// 测试者姓名
		public TextView nameTextView;
		// 测试者性别
		public TextView sexTextView;
		// 测试者年龄
		public TextView ageTextView;
		// 默认打印方式
		public TextView defaultPrintTextView;
		// 开始时间
		public TextView beginTimeTextView;
		// 卡号
		public TextView cardNumberTextView;
		// 状态
		public ImageView stateImageView;
	}

	/**
	 * 
	 * @author hesiming
	 * 
	 */
	private final class DataSourceOfQuestionnaireListItem {
		private boolean isChecked = false;

		private final FullQuestionnaireModel questionnaireModel;

		public DataSourceOfQuestionnaireListItem(FullQuestionnaireModel questionnaireModel) {
			this.questionnaireModel = questionnaireModel;
		}
	}

	/**
	 * 获取被选中的量表索引
	 * 
	 * @return
	 */
	public List<Integer> getCheckedIndexArray() {
		List<Integer> indexArray = new ArrayList<Integer>();
		for (int i = 0; i < dataSource.size(); i++) {
			if (dataSource.get(i).isChecked) {
				indexArray.add(i);
			}
		}
		return indexArray;
	}
}
