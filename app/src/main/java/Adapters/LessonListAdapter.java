package Adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.hano.learning.japanese.R;

import java.util.ArrayList;

import Models.LessonModel;

/**
 * Created by Hano on 20/12/2015.
 */
public abstract class LessonListAdapter extends ArrayAdapter<LessonModel> {
    Activity context = null;
    ArrayList<LessonModel> lessonList = null;
    int layoutId;
    int checkCount;

    public LessonListAdapter(Activity context, int layoutId, ArrayList<LessonModel> arr) {
        super(context, layoutId, arr);
        this.layoutId = layoutId;
        this.context = context;
        this.lessonList = arr;
        checkCount = 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        convertView = inflater.inflate(layoutId, null);

        final LessonModel les = lessonList.get(position);

        CheckBox cbxChoice = (CheckBox) convertView.findViewById(R.id.cbxChoice);
        TextView tvwName = (TextView) convertView.findViewById(R.id.tvwName);
        TextView tvwOwner = (TextView) convertView.findViewById(R.id.tvwOwner);
        TextView tvwWords = (TextView) convertView.findViewById(R.id.tvwWords);

        cbxChoice.setChecked(les.isCheck());
        tvwName.setText(les.getName());
        tvwOwner.setText(les.getOwner());
        tvwWords.setText(String.valueOf(les.getWords()));

        cbxChoice.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                les.setIsCheck(isChecked);
                checkCount += isChecked ? 1 : -1;
                onCheck();
            }
        });

        return convertView;
    }

    protected abstract void onCheck();

    public int getCheckCount(){
        return checkCount;
    }

    public void resetCheckCount(){
        checkCount = 0;
        onCheck();
    }
}

