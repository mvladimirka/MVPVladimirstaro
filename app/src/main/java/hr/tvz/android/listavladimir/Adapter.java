package hr.tvz.android.listavladimir;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;



public class Adapter extends RecyclerView.Adapter<Adapter.Viewholder> {

    private List <ModelClass> modelClassList;
    private View.OnClickListener onClick;

    public Adapter(List<ModelClass> modelClassList) {
        this.modelClassList = modelClassList;
        this.onClick = onClick;

    }


    @Override
    public Viewholder onCreateViewHolder(ViewGroup viewGroup, int i){
    View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_layout, viewGroup,false);
        view.setOnClickListener(this.onClick);
    return new Viewholder(view);
    }


    @Override
    public void onBindViewHolder(Viewholder viewholder, int position) {
        int resource = modelClassList.get(position).getImageResource();
        String title = modelClassList.get(position).getTitle();
        String body = modelClassList.get(position).getBody();
        viewholder.setData(resource,title,body);

    }

    @Override
    public int getItemCount() {
        return modelClassList.size();
    }

    public static class Viewholder extends RecyclerView.ViewHolder{

        private ImageView imageView;
        private TextView title;
        private TextView body;

        public Viewholder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            title = itemView.findViewById(R.id.title);
            body = itemView.findViewById(R.id.body);
        }
        private void setData(int resource,String titleText,String bodyText){
            imageView.setImageResource(resource);
            title.setText(titleText);
            body.setText(bodyText);
        }
    }

}
