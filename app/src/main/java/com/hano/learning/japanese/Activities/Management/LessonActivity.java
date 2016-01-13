package com.hano.learning.japanese.Activities.Management;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.hano.learning.japanese.Activities.BaseNavActivity;
import com.hano.learning.japanese.Activities.ConfirmDialog;
import com.hano.learning.japanese.R;

import java.util.ArrayList;

import Adapters.LessonListAdapter;
import Models.LessonModel;
import Utilities.SQLiteUtils;

/**
 * Created by Hano on 20/12/2015.
 */
public class LessonActivity extends BaseNavActivity {
    private SQLiteUtils utils = new SQLiteUtils(this);
    private String msg;
    private long rs;
    private LessonCreateDialog createDialog;
    private ConfirmDialog confirmDialog;

    private ArrayList<LessonModel> lessonList = new ArrayList<LessonModel>();
    LessonListAdapter adapter = null;
    ListView lvwLesson = null;
    FloatingActionButton fabCreate = null;
    FloatingActionButton fabDelete = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getLayoutInflater().inflate(R.layout.lesson_layout, frameLayout);
        lvwLesson = (ListView) findViewById(R.id.lvwLesson);

        utils.initialDb(false);
        lessonList = utils.getAllLessons();

        fabCreate = (FloatingActionButton) findViewById(R.id.fabCreate);
        fabDelete = (FloatingActionButton) findViewById(R.id.fabDelete);

        adapter = new LessonListAdapter(this, R.layout.lesson_list_layout, lessonList){
            @Override
            protected void onCheck() {
                fabDelete.setVisibility(adapter.getCheckCount() > 0 ? View.VISIBLE : View.GONE);
            }
        };
        lvwLesson.setAdapter(adapter);

        createDialog = new LessonCreateDialog(utils) {
            @Override
            protected void onCreateLesson(LessonModel les, View view) {
                long rs = utils.insertLesson(les);
                if (rs != -1) {
                    les.setID(rs);
                    lessonList.add(les);
                    adapter.notifyDataSetChanged();
                    msg = "Insert lesson successfully!";
                } else {
                    msg = "Insert lesson fail!";
                }

                Snackbar.make(view, "Insert lesson successfully!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        };

        confirmDialog = new ConfirmDialog("Delete") {
            @Override
            protected void onCloseAction(View view, boolean isOK) {
                if (isOK) {
                    int success = 0;
                    int fail = 0;

                    for (int i = lessonList.size() - 1; i >= 0; i--) {
                        if (lessonList.get(i).isCheck()) {
                            if (utils.deleteLesson(lessonList.get(i).getID()) == 1) {
                                lessonList.remove(i);
                                success++;
                            } else {
                                fail++;
                            }
                        }
                    }
                    adapter.notifyDataSetChanged();
                    adapter.resetCheckCount();

                    if (success == 0 && fail == 0) {
                        msg = "No lesson selected!";
                    } else if (fail == 0) {
                        msg = "Delete " + success + " lessons successfully!";
                    } else if (success == 0) {
                        msg = "Delete " + fail + " lessons fail!";
                    } else {
                        msg = "Delete " + success + " lessons successfully!\n";
                        msg += "Delete " + fail + " lessons fail!";
                    }

                    Snackbar.make(view, msg, Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            }
        };
    }

    public void delete(View view){
        msg = "Are you want to delete " + adapter.getCheckCount() + " lesson" + (adapter.getCheckCount() > 1 ? "s?" : "?");
        confirmDialog.setView(view);
        confirmDialog.show(getSupportFragmentManager(), msg);
    }

    public void create(View view){
        createDialog.setView(view);
        createDialog.show(getSupportFragmentManager(), "Create Lesson");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
