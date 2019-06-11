package hr.tvz.android.listavladimir;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.facebook.drawee.backends.pipeline.Fresco;

import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Widget extends AppWidgetProvider {
    public Widget() {
        super();
    }

    @Override
    public void onUpdate(final Context context, final AppWidgetManager appWidgetManager, final int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);

        if (!Fresco.hasBeenInitialized()) {
            Fresco.initialize(context);
        }
        DataHelper.createService()
                .getLista().enqueue (new Callback<List<ModelClass>>() {
                    @Override
                    public void onResponse(Call<List<ModelClass>> call, Response<List<ModelClass>> response) {
                        List<ModelClass> languages = response.body();
                        final ModelClass latest = languages.stream().max(new Comparator<ModelClass>() {
                            @Override
                            public int compare(ModelClass o1, ModelClass o2) {
                                return o1.id.compareTo(o2.id);
                            }
                        }).get();
                        System.out.println(">>> Widget data fetched!");
                        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
                        views.setTextViewText(R.id.widget_lista, "Latest: " + latest.getTitle());
                        for (int id : appWidgetIds) {
                            appWidgetManager.updateAppWidget(id, views);
                        }
                    }

                    @Override
                    public void onFailure(Call<List<ModelClass>> call, Throwable t) {
                        System.out.println("Failed to fetch!");
                    }
                });
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);

        System.out.println(">>> ON RECEIVE!!!");
    }


}
