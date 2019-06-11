package hr.tvz.android.listavladimir;

import android.app.Application;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.Database;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowLog;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.structure.BaseModel;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        // Initialize DBFlow
        FlowManager.init(new FlowConfig.Builder(this).build());
        // Setting Log Display
        FlowLog.setMinimumLoggingLevel(FlowLog.Level.V);
    }

    @Database(name = MyDatabase.NAME, version = MyDatabase.VERSION)
    public class MyDatabase {
        // Database name
        public static final String NAME = "MyDatabase";
        // Database Version Number
        public static final int VERSION = 1;
    }
    @Table(database = MyDatabase.class)
    public static class NoteTable extends BaseModel {
        @Column
        @PrimaryKey
        int id;
        @Column
        private String title;
        @Column
        private String date;
        @Column
        private String content;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

//        public void insertData(NoteBean model){
//            //1.model,insert()
//            model.setTitle("title");
//            model.setDate("2018-04-17");
//            model.setContent("content");
//            model.insert();
//            //2.SQLite.insert()
//            SQLite.insert(NoteBean.class)
//                    .columns(NoteBean_Table.title,NoteBean_Table.date,NoteBean_Table.content)
//                    .values("title","2018-04-17","content")
//                    .execute();
//        }
//        public void deleteData(NoteBean model){
//            //1.model.delete()
//            model.delete();
//            //2.SQLite.delete()
//            SQLite.delete(NoteBean.class)
//                    .where(NoteBean_Table.title.is("title"))
//                    .and(NoteBean_Table.id.is(10))
//                    .async()
//                    .execute();
//            // Delete the entire table
//            Delete.table(NoteBean.class);
//            // Delete multiple tables
//            Delete.table(NoteBean.class,NoteBean1.class);
//        }
//        public void updateData(NoteBean model) {
//            //1.model.update()
//            model.update();
//            //2.SQLite.update()
//            SQLite.update(NoteBean.class)
//                    .set(NoteBean_Table.title.eq("title"),
//                            NoteBean_Table.content.eq("content"))
//                    .where(NoteBean_Table.id.is(10))
//                    .async()
//                    .execute();
//        }
//        public List<NoteBean> queryData(){
//            // Query according to conditions
//            List<NoteBean> noteBeans = SQLite.select()
//                    .from(NoteBean.class)
//                    .where(NoteBean_Table.title.is("title"))
//                    .queryList();
//            return noteBeans;
//        }
    }
}
